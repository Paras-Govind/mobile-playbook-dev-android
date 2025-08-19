package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.BackStackRecord;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class FragmentTransition {
    private static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};

    FragmentTransition() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startTransitions(FragmentManagerImpl fragmentManager, ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, boolean isReordered) {
        if (fragmentManager.mCurState >= 1 && Build.VERSION.SDK_INT >= 21) {
            SparseArray<FragmentContainerTransition> transitioningFragments = new SparseArray<>();
            for (int i = startIndex; i < endIndex; i++) {
                BackStackRecord record = records.get(i);
                boolean isPop = isRecordPop.get(i).booleanValue();
                if (isPop) {
                    calculatePopFragments(record, transitioningFragments, isReordered);
                } else {
                    calculateFragments(record, transitioningFragments, isReordered);
                }
            }
            int i2 = transitioningFragments.size();
            if (i2 != 0) {
                View nonExistentView = new View(fragmentManager.mHost.getContext());
                int numContainers = transitioningFragments.size();
                for (int i3 = 0; i3 < numContainers; i3++) {
                    int containerId = transitioningFragments.keyAt(i3);
                    ArrayMap<String, String> nameOverrides = calculateNameOverrides(containerId, records, isRecordPop, startIndex, endIndex);
                    FragmentContainerTransition containerTransition = transitioningFragments.valueAt(i3);
                    if (isReordered) {
                        configureTransitionsReordered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    } else {
                        configureTransitionsOrdered(fragmentManager, containerId, containerTransition, nonExistentView, nameOverrides);
                    }
                }
            }
        }
    }

    private static ArrayMap<String, String> calculateNameOverrides(int containerId, ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        ArrayList<String> sources;
        ArrayList<String> targets;
        ArrayMap<String, String> nameOverrides = new ArrayMap<>();
        for (int recordNum = endIndex - 1; recordNum >= startIndex; recordNum--) {
            BackStackRecord record = records.get(recordNum);
            if (record.interactsWith(containerId)) {
                boolean isPop = isRecordPop.get(recordNum).booleanValue();
                if (record.mSharedElementSourceNames != null) {
                    int numSharedElements = record.mSharedElementSourceNames.size();
                    if (isPop) {
                        targets = record.mSharedElementSourceNames;
                        sources = record.mSharedElementTargetNames;
                    } else {
                        sources = record.mSharedElementSourceNames;
                        targets = record.mSharedElementTargetNames;
                    }
                    for (int i = 0; i < numSharedElements; i++) {
                        String sourceName = sources.get(i);
                        String targetName = targets.get(i);
                        String previousTarget = nameOverrides.remove(targetName);
                        if (previousTarget != null) {
                            nameOverrides.put(sourceName, previousTarget);
                        } else {
                            nameOverrides.put(sourceName, targetName);
                        }
                    }
                }
            }
        }
        return nameOverrides;
    }

    private static void configureTransitionsReordered(FragmentManagerImpl fragmentManager, int containerId, FragmentContainerTransition fragments, View nonExistentView, ArrayMap<String, String> nameOverrides) {
        ViewGroup sceneRoot;
        Object exitTransition;
        if (fragmentManager.mContainer.onHasView()) {
            ViewGroup sceneRoot2 = (ViewGroup) fragmentManager.mContainer.onFindViewById(containerId);
            sceneRoot = sceneRoot2;
        } else {
            sceneRoot = null;
        }
        if (sceneRoot == null) {
            return;
        }
        Fragment inFragment = fragments.lastIn;
        Fragment outFragment = fragments.firstOut;
        boolean inIsPop = fragments.lastInIsPop;
        boolean outIsPop = fragments.firstOutIsPop;
        ArrayList<View> sharedElementsIn = new ArrayList<>();
        ArrayList<View> sharedElementsOut = new ArrayList<>();
        Object enterTransition = getEnterTransition(inFragment, inIsPop);
        Object exitTransition2 = getExitTransition(outFragment, outIsPop);
        Object sharedElementTransition = configureSharedElementsReordered(sceneRoot, nonExistentView, nameOverrides, fragments, sharedElementsOut, sharedElementsIn, enterTransition, exitTransition2);
        if (enterTransition == null && sharedElementTransition == null) {
            exitTransition = exitTransition2;
            if (exitTransition == null) {
                return;
            }
        } else {
            exitTransition = exitTransition2;
        }
        ArrayList<View> exitingViews = configureEnteringExitingViews(exitTransition, outFragment, sharedElementsOut, nonExistentView);
        ArrayList<View> enteringViews = configureEnteringExitingViews(enterTransition, inFragment, sharedElementsIn, nonExistentView);
        setViewVisibility(enteringViews, 4);
        Object transition = mergeTransitions(enterTransition, exitTransition, sharedElementTransition, inFragment, inIsPop);
        if (transition != null) {
            replaceHide(exitTransition, outFragment, exitingViews);
            ArrayList<String> inNames = FragmentTransitionCompat21.prepareSetNameOverridesReordered(sharedElementsIn);
            FragmentTransitionCompat21.scheduleRemoveTargets(transition, enterTransition, enteringViews, exitTransition, exitingViews, sharedElementTransition, sharedElementsIn);
            FragmentTransitionCompat21.beginDelayedTransition(sceneRoot, transition);
            FragmentTransitionCompat21.setNameOverridesReordered(sceneRoot, sharedElementsOut, sharedElementsIn, inNames, nameOverrides);
            setViewVisibility(enteringViews, 0);
            FragmentTransitionCompat21.swapSharedElementTargets(sharedElementTransition, sharedElementsOut, sharedElementsIn);
        }
    }

    private static void replaceHide(Object exitTransition, Fragment exitingFragment, final ArrayList<View> exitingViews) {
        if (exitingFragment != null && exitTransition != null && exitingFragment.mAdded && exitingFragment.mHidden && exitingFragment.mHiddenChanged) {
            exitingFragment.setHideReplaced(true);
            FragmentTransitionCompat21.scheduleHideFragmentView(exitTransition, exitingFragment.getView(), exitingViews);
            ViewGroup container = exitingFragment.mContainer;
            OneShotPreDrawListener.add(container, new Runnable() { // from class: android.support.v4.app.FragmentTransition.1
                @Override // java.lang.Runnable
                public void run() {
                    FragmentTransition.setViewVisibility(exitingViews, 4);
                }
            });
        }
    }

    private static void configureTransitionsOrdered(FragmentManagerImpl fragmentManager, int containerId, FragmentContainerTransition fragments, View nonExistentView, ArrayMap<String, String> nameOverrides) {
        ViewGroup sceneRoot;
        Object exitTransition;
        if (fragmentManager.mContainer.onHasView()) {
            ViewGroup sceneRoot2 = (ViewGroup) fragmentManager.mContainer.onFindViewById(containerId);
            sceneRoot = sceneRoot2;
        } else {
            sceneRoot = null;
        }
        if (sceneRoot == null) {
            return;
        }
        Fragment inFragment = fragments.lastIn;
        Fragment outFragment = fragments.firstOut;
        boolean inIsPop = fragments.lastInIsPop;
        boolean outIsPop = fragments.firstOutIsPop;
        Object enterTransition = getEnterTransition(inFragment, inIsPop);
        Object exitTransition2 = getExitTransition(outFragment, outIsPop);
        ArrayList<View> sharedElementsOut = new ArrayList<>();
        ArrayList<View> sharedElementsIn = new ArrayList<>();
        Object sharedElementTransition = configureSharedElementsOrdered(sceneRoot, nonExistentView, nameOverrides, fragments, sharedElementsOut, sharedElementsIn, enterTransition, exitTransition2);
        if (enterTransition == null && sharedElementTransition == null) {
            exitTransition = exitTransition2;
            if (exitTransition == null) {
                return;
            }
        } else {
            exitTransition = exitTransition2;
        }
        ArrayList<View> exitingViews = configureEnteringExitingViews(exitTransition, outFragment, sharedElementsOut, nonExistentView);
        Object exitTransition3 = (exitingViews == null || exitingViews.isEmpty()) ? null : exitTransition;
        FragmentTransitionCompat21.addTarget(enterTransition, nonExistentView);
        Object transition = mergeTransitions(enterTransition, exitTransition3, sharedElementTransition, inFragment, fragments.lastInIsPop);
        if (transition != null) {
            ArrayList<View> enteringViews = new ArrayList<>();
            FragmentTransitionCompat21.scheduleRemoveTargets(transition, enterTransition, enteringViews, exitTransition3, exitingViews, sharedElementTransition, sharedElementsIn);
            scheduleTargetChange(sceneRoot, inFragment, nonExistentView, sharedElementsIn, enterTransition, enteringViews, exitTransition3, exitingViews);
            FragmentTransitionCompat21.setNameOverridesOrdered(sceneRoot, sharedElementsIn, nameOverrides);
            FragmentTransitionCompat21.beginDelayedTransition(sceneRoot, transition);
            FragmentTransitionCompat21.scheduleNameReset(sceneRoot, sharedElementsIn, nameOverrides);
        }
    }

    private static void scheduleTargetChange(ViewGroup sceneRoot, final Fragment inFragment, final View nonExistentView, final ArrayList<View> sharedElementsIn, final Object enterTransition, final ArrayList<View> enteringViews, final Object exitTransition, final ArrayList<View> exitingViews) {
        OneShotPreDrawListener.add(sceneRoot, new Runnable() { // from class: android.support.v4.app.FragmentTransition.2
            @Override // java.lang.Runnable
            public void run() {
                Object obj = enterTransition;
                if (obj != null) {
                    FragmentTransitionCompat21.removeTarget(obj, nonExistentView);
                    ArrayList<View> views = FragmentTransition.configureEnteringExitingViews(enterTransition, inFragment, sharedElementsIn, nonExistentView);
                    enteringViews.addAll(views);
                }
                ArrayList<View> views2 = exitingViews;
                if (views2 != null) {
                    if (exitTransition != null) {
                        ArrayList<View> tempExiting = new ArrayList<>();
                        tempExiting.add(nonExistentView);
                        FragmentTransitionCompat21.replaceTargets(exitTransition, exitingViews, tempExiting);
                    }
                    exitingViews.clear();
                    exitingViews.add(nonExistentView);
                }
            }
        });
    }

    private static Object getSharedElementTransition(Fragment inFragment, Fragment outFragment, boolean isPop) {
        Object sharedElementEnterTransition;
        if (inFragment == null || outFragment == null) {
            return null;
        }
        if (isPop) {
            sharedElementEnterTransition = outFragment.getSharedElementReturnTransition();
        } else {
            sharedElementEnterTransition = inFragment.getSharedElementEnterTransition();
        }
        Object transition = FragmentTransitionCompat21.cloneTransition(sharedElementEnterTransition);
        return FragmentTransitionCompat21.wrapTransitionInSet(transition);
    }

    private static Object getEnterTransition(Fragment inFragment, boolean isPop) {
        Object enterTransition;
        if (inFragment == null) {
            return null;
        }
        if (isPop) {
            enterTransition = inFragment.getReenterTransition();
        } else {
            enterTransition = inFragment.getEnterTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(enterTransition);
    }

    private static Object getExitTransition(Fragment outFragment, boolean isPop) {
        Object exitTransition;
        if (outFragment == null) {
            return null;
        }
        if (isPop) {
            exitTransition = outFragment.getReturnTransition();
        } else {
            exitTransition = outFragment.getExitTransition();
        }
        return FragmentTransitionCompat21.cloneTransition(exitTransition);
    }

    private static Object configureSharedElementsReordered(ViewGroup sceneRoot, View nonExistentView, ArrayMap<String, String> nameOverrides, FragmentContainerTransition fragments, ArrayList<View> sharedElementsOut, ArrayList<View> sharedElementsIn, Object enterTransition, Object exitTransition) {
        Object sharedElementTransition;
        Object sharedElementTransition2;
        View epicenterView;
        Rect epicenter;
        final Fragment inFragment = fragments.lastIn;
        final Fragment outFragment = fragments.firstOut;
        if (inFragment != null) {
            inFragment.getView().setVisibility(0);
        }
        if (inFragment != null && outFragment != null) {
            final boolean inIsPop = fragments.lastInIsPop;
            if (nameOverrides.isEmpty()) {
                sharedElementTransition = null;
            } else {
                sharedElementTransition = getSharedElementTransition(inFragment, outFragment, inIsPop);
            }
            ArrayMap<String, View> outSharedElements = captureOutSharedElements(nameOverrides, sharedElementTransition, fragments);
            final ArrayMap<String, View> inSharedElements = captureInSharedElements(nameOverrides, sharedElementTransition, fragments);
            if (nameOverrides.isEmpty()) {
                if (outSharedElements != null) {
                    outSharedElements.clear();
                }
                if (inSharedElements != null) {
                    inSharedElements.clear();
                }
                sharedElementTransition2 = null;
            } else {
                addSharedElementsWithMatchingNames(sharedElementsOut, outSharedElements, nameOverrides.keySet());
                addSharedElementsWithMatchingNames(sharedElementsIn, inSharedElements, nameOverrides.values());
                sharedElementTransition2 = sharedElementTransition;
            }
            if (enterTransition == null && exitTransition == null && sharedElementTransition2 == null) {
                return null;
            }
            callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
            if (sharedElementTransition2 != null) {
                sharedElementsIn.add(nonExistentView);
                FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition2, nonExistentView, sharedElementsOut);
                boolean outIsPop = fragments.firstOutIsPop;
                BackStackRecord outTransaction = fragments.firstOutTransaction;
                setOutEpicenter(sharedElementTransition2, exitTransition, outSharedElements, outIsPop, outTransaction);
                Rect epicenter2 = new Rect();
                View epicenterView2 = getInEpicenterView(inSharedElements, fragments, enterTransition, inIsPop);
                if (epicenterView2 != null) {
                    FragmentTransitionCompat21.setEpicenter(enterTransition, epicenter2);
                }
                epicenterView = epicenterView2;
                epicenter = epicenter2;
            } else {
                epicenterView = null;
                epicenter = null;
            }
            Object sharedElementTransition3 = sharedElementTransition2;
            final View view = epicenterView;
            final Rect rect = epicenter;
            OneShotPreDrawListener.add(sceneRoot, new Runnable() { // from class: android.support.v4.app.FragmentTransition.3
                @Override // java.lang.Runnable
                public void run() {
                    FragmentTransition.callSharedElementStartEnd(Fragment.this, outFragment, inIsPop, inSharedElements, false);
                    View view2 = view;
                    if (view2 != null) {
                        FragmentTransitionCompat21.getBoundsOnScreen(view2, rect);
                    }
                }
            });
            return sharedElementTransition3;
        }
        return null;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> views, ArrayMap<String, View> sharedElements, Collection<String> nameOverridesSet) {
        for (int i = sharedElements.size() - 1; i >= 0; i--) {
            View view = sharedElements.valueAt(i);
            if (nameOverridesSet.contains(ViewCompat.getTransitionName(view))) {
                views.add(view);
            }
        }
    }

    private static Object configureSharedElementsOrdered(ViewGroup sceneRoot, final View nonExistentView, final ArrayMap<String, String> nameOverrides, final FragmentContainerTransition fragments, final ArrayList<View> sharedElementsOut, final ArrayList<View> sharedElementsIn, final Object enterTransition, Object exitTransition) {
        Object sharedElementTransition;
        Object sharedElementTransition2;
        Rect inEpicenter;
        final Fragment inFragment = fragments.lastIn;
        final Fragment outFragment = fragments.firstOut;
        if (inFragment != null && outFragment != null) {
            final boolean inIsPop = fragments.lastInIsPop;
            if (nameOverrides.isEmpty()) {
                sharedElementTransition = null;
            } else {
                sharedElementTransition = getSharedElementTransition(inFragment, outFragment, inIsPop);
            }
            ArrayMap<String, View> outSharedElements = captureOutSharedElements(nameOverrides, sharedElementTransition, fragments);
            if (nameOverrides.isEmpty()) {
                sharedElementTransition2 = null;
            } else {
                sharedElementsOut.addAll(outSharedElements.values());
                sharedElementTransition2 = sharedElementTransition;
            }
            if (enterTransition == null && exitTransition == null && sharedElementTransition2 == null) {
                return null;
            }
            callSharedElementStartEnd(inFragment, outFragment, inIsPop, outSharedElements, true);
            if (sharedElementTransition2 != null) {
                Rect inEpicenter2 = new Rect();
                FragmentTransitionCompat21.setSharedElementTargets(sharedElementTransition2, nonExistentView, sharedElementsOut);
                boolean outIsPop = fragments.firstOutIsPop;
                BackStackRecord outTransaction = fragments.firstOutTransaction;
                setOutEpicenter(sharedElementTransition2, exitTransition, outSharedElements, outIsPop, outTransaction);
                if (enterTransition != null) {
                    FragmentTransitionCompat21.setEpicenter(enterTransition, inEpicenter2);
                }
                inEpicenter = inEpicenter2;
            } else {
                inEpicenter = null;
            }
            final Object finalSharedElementTransition = sharedElementTransition2;
            Object sharedElementTransition3 = sharedElementTransition2;
            final Rect rect = inEpicenter;
            OneShotPreDrawListener.add(sceneRoot, new Runnable() { // from class: android.support.v4.app.FragmentTransition.4
                @Override // java.lang.Runnable
                public void run() {
                    ArrayMap<String, View> inSharedElements = FragmentTransition.captureInSharedElements(ArrayMap.this, finalSharedElementTransition, fragments);
                    if (inSharedElements != null) {
                        sharedElementsIn.addAll(inSharedElements.values());
                        sharedElementsIn.add(nonExistentView);
                    }
                    FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
                    Object obj = finalSharedElementTransition;
                    if (obj != null) {
                        FragmentTransitionCompat21.swapSharedElementTargets(obj, sharedElementsOut, sharedElementsIn);
                        View inEpicenterView = FragmentTransition.getInEpicenterView(inSharedElements, fragments, enterTransition, inIsPop);
                        if (inEpicenterView != null) {
                            FragmentTransitionCompat21.getBoundsOnScreen(inEpicenterView, rect);
                        }
                    }
                }
            });
            return sharedElementTransition3;
        }
        return null;
    }

    private static ArrayMap<String, View> captureOutSharedElements(ArrayMap<String, String> nameOverrides, Object sharedElementTransition, FragmentContainerTransition fragments) {
        SharedElementCallback sharedElementCallback;
        ArrayList<String> names;
        if (nameOverrides.isEmpty() || sharedElementTransition == null) {
            nameOverrides.clear();
            return null;
        }
        Fragment outFragment = fragments.firstOut;
        ArrayMap<String, View> outSharedElements = new ArrayMap<>();
        FragmentTransitionCompat21.findNamedViews(outSharedElements, outFragment.getView());
        BackStackRecord outTransaction = fragments.firstOutTransaction;
        if (fragments.firstOutIsPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
            names = outTransaction.mSharedElementTargetNames;
        } else {
            sharedElementCallback = outFragment.getExitTransitionCallback();
            names = outTransaction.mSharedElementSourceNames;
        }
        outSharedElements.retainAll(names);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, outSharedElements);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = names.get(i);
                View view = outSharedElements.get(name);
                if (view == null) {
                    nameOverrides.remove(name);
                } else if (!name.equals(ViewCompat.getTransitionName(view))) {
                    String targetValue = nameOverrides.remove(name);
                    nameOverrides.put(ViewCompat.getTransitionName(view), targetValue);
                }
            }
        } else {
            nameOverrides.retainAll(outSharedElements.keySet());
        }
        return outSharedElements;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayMap<String, View> captureInSharedElements(ArrayMap<String, String> nameOverrides, Object sharedElementTransition, FragmentContainerTransition fragments) {
        SharedElementCallback sharedElementCallback;
        ArrayList<String> names;
        String key;
        Fragment inFragment = fragments.lastIn;
        View fragmentView = inFragment.getView();
        if (nameOverrides.isEmpty() || sharedElementTransition == null || fragmentView == null) {
            nameOverrides.clear();
            return null;
        }
        ArrayMap<String, View> inSharedElements = new ArrayMap<>();
        FragmentTransitionCompat21.findNamedViews(inSharedElements, fragmentView);
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (fragments.lastInIsPop) {
            sharedElementCallback = inFragment.getExitTransitionCallback();
            names = inTransaction.mSharedElementSourceNames;
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
            names = inTransaction.mSharedElementTargetNames;
        }
        if (names != null) {
            inSharedElements.retainAll(names);
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(names, inSharedElements);
            for (int i = names.size() - 1; i >= 0; i--) {
                String name = names.get(i);
                View view = inSharedElements.get(name);
                if (view == null) {
                    String key2 = findKeyForValue(nameOverrides, name);
                    if (key2 != null) {
                        nameOverrides.remove(key2);
                    }
                } else if (!name.equals(ViewCompat.getTransitionName(view)) && (key = findKeyForValue(nameOverrides, name)) != null) {
                    nameOverrides.put(key, ViewCompat.getTransitionName(view));
                }
            }
        } else {
            retainValues(nameOverrides, inSharedElements);
        }
        return inSharedElements;
    }

    private static String findKeyForValue(ArrayMap<String, String> map, String value) {
        int numElements = map.size();
        for (int i = 0; i < numElements; i++) {
            if (value.equals(map.valueAt(i))) {
                return map.keyAt(i);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static View getInEpicenterView(ArrayMap<String, View> inSharedElements, FragmentContainerTransition fragments, Object enterTransition, boolean inIsPop) {
        String targetName;
        BackStackRecord inTransaction = fragments.lastInTransaction;
        if (enterTransition != null && inSharedElements != null && inTransaction.mSharedElementSourceNames != null && !inTransaction.mSharedElementSourceNames.isEmpty()) {
            if (inIsPop) {
                targetName = inTransaction.mSharedElementSourceNames.get(0);
            } else {
                targetName = inTransaction.mSharedElementTargetNames.get(0);
            }
            return inSharedElements.get(targetName);
        }
        return null;
    }

    private static void setOutEpicenter(Object sharedElementTransition, Object exitTransition, ArrayMap<String, View> outSharedElements, boolean outIsPop, BackStackRecord outTransaction) {
        String sourceName;
        if (outTransaction.mSharedElementSourceNames != null && !outTransaction.mSharedElementSourceNames.isEmpty()) {
            if (outIsPop) {
                sourceName = outTransaction.mSharedElementTargetNames.get(0);
            } else {
                sourceName = outTransaction.mSharedElementSourceNames.get(0);
            }
            View outEpicenterView = outSharedElements.get(sourceName);
            FragmentTransitionCompat21.setEpicenter(sharedElementTransition, outEpicenterView);
            if (exitTransition != null) {
                FragmentTransitionCompat21.setEpicenter(exitTransition, outEpicenterView);
            }
        }
    }

    private static void retainValues(ArrayMap<String, String> nameOverrides, ArrayMap<String, View> namedViews) {
        for (int i = nameOverrides.size() - 1; i >= 0; i--) {
            String targetName = nameOverrides.valueAt(i);
            if (!namedViews.containsKey(targetName)) {
                nameOverrides.removeAt(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callSharedElementStartEnd(Fragment inFragment, Fragment outFragment, boolean isPop, ArrayMap<String, View> sharedElements, boolean isStart) {
        SharedElementCallback sharedElementCallback;
        if (isPop) {
            sharedElementCallback = outFragment.getEnterTransitionCallback();
        } else {
            sharedElementCallback = inFragment.getEnterTransitionCallback();
        }
        if (sharedElementCallback != null) {
            ArrayList<View> views = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            int count = sharedElements == null ? 0 : sharedElements.size();
            for (int i = 0; i < count; i++) {
                names.add(sharedElements.keyAt(i));
                views.add(sharedElements.valueAt(i));
            }
            if (isStart) {
                sharedElementCallback.onSharedElementStart(names, views, null);
            } else {
                sharedElementCallback.onSharedElementEnd(names, views, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<View> configureEnteringExitingViews(Object transition, Fragment fragment, ArrayList<View> sharedElements, View nonExistentView) {
        ArrayList<View> viewList = null;
        if (transition != null) {
            viewList = new ArrayList<>();
            View root = fragment.getView();
            if (root != null) {
                FragmentTransitionCompat21.captureTransitioningViews(viewList, root);
            }
            if (sharedElements != null) {
                viewList.removeAll(sharedElements);
            }
            if (!viewList.isEmpty()) {
                viewList.add(nonExistentView);
                FragmentTransitionCompat21.addTargets(transition, viewList);
            }
        }
        return viewList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setViewVisibility(ArrayList<View> views, int visibility) {
        if (views == null) {
            return;
        }
        for (int i = views.size() - 1; i >= 0; i--) {
            View view = views.get(i);
            view.setVisibility(visibility);
        }
    }

    private static Object mergeTransitions(Object enterTransition, Object exitTransition, Object sharedElementTransition, Fragment inFragment, boolean isPop) {
        boolean overlap = true;
        if (enterTransition != null && exitTransition != null && inFragment != null) {
            overlap = isPop ? inFragment.getAllowReturnTransitionOverlap() : inFragment.getAllowEnterTransitionOverlap();
        }
        if (overlap) {
            Object transition = FragmentTransitionCompat21.mergeTransitionsTogether(exitTransition, enterTransition, sharedElementTransition);
            return transition;
        }
        Object transition2 = FragmentTransitionCompat21.mergeTransitionsInSequence(exitTransition, enterTransition, sharedElementTransition);
        return transition2;
    }

    public static void calculateFragments(BackStackRecord transaction, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isReordered) {
        int numOps = transaction.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            BackStackRecord.Op op = transaction.mOps.get(opNum);
            addToFirstInLastOut(transaction, op, transitioningFragments, false, isReordered);
        }
    }

    public static void calculatePopFragments(BackStackRecord transaction, SparseArray<FragmentContainerTransition> transitioningFragments, boolean isReordered) {
        if (!transaction.mManager.mContainer.onHasView()) {
            return;
        }
        int numOps = transaction.mOps.size();
        for (int opNum = numOps - 1; opNum >= 0; opNum--) {
            BackStackRecord.Op op = transaction.mOps.get(opNum);
            addToFirstInLastOut(transaction, op, transitioningFragments, true, isReordered);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00d6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void addToFirstInLastOut(android.support.v4.app.BackStackRecord r22, android.support.v4.app.BackStackRecord.Op r23, android.util.SparseArray<android.support.v4.app.FragmentTransition.FragmentContainerTransition> r24, boolean r25, boolean r26) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentTransition.addToFirstInLastOut(android.support.v4.app.BackStackRecord, android.support.v4.app.BackStackRecord$Op, android.util.SparseArray, boolean, boolean):void");
    }

    private static FragmentContainerTransition ensureContainer(FragmentContainerTransition containerTransition, SparseArray<FragmentContainerTransition> transitioningFragments, int containerId) {
        if (containerTransition == null) {
            FragmentContainerTransition containerTransition2 = new FragmentContainerTransition();
            transitioningFragments.put(containerId, containerTransition2);
            return containerTransition2;
        }
        return containerTransition;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }
}
