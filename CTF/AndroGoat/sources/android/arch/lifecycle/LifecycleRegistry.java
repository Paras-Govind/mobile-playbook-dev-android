package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class LifecycleRegistry extends Lifecycle {
    private final LifecycleOwner mLifecycleOwner;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap<>();
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private boolean mNewEventOccurred = false;
    private ArrayList<Lifecycle.State> mParentStates = new ArrayList<>();
    private Lifecycle.State mState = Lifecycle.State.INITIALIZED;

    public LifecycleRegistry(LifecycleOwner provider) {
        this.mLifecycleOwner = provider;
    }

    public void markState(Lifecycle.State state) {
        this.mState = state;
    }

    public void handleLifecycleEvent(Lifecycle.Event event) {
        this.mState = getStateAfter(event);
        if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
            this.mNewEventOccurred = true;
            return;
        }
        this.mHandlingEvent = true;
        sync();
        this.mHandlingEvent = false;
    }

    private boolean isSynced() {
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        Lifecycle.State eldestObserverState = this.mObserverMap.eldest().getValue().mState;
        Lifecycle.State newestObserverState = this.mObserverMap.newest().getValue().mState;
        return eldestObserverState == newestObserverState && this.mState == newestObserverState;
    }

    private Lifecycle.State calculateTargetState(LifecycleObserver observer) {
        Map.Entry<LifecycleObserver, ObserverWithState> previous = this.mObserverMap.ceil(observer);
        Lifecycle.State parentState = null;
        Lifecycle.State siblingState = previous != null ? previous.getValue().mState : null;
        if (!this.mParentStates.isEmpty()) {
            parentState = this.mParentStates.get(r1.size() - 1);
        }
        return min(min(this.mState, siblingState), parentState);
    }

    @Override // android.arch.lifecycle.Lifecycle
    public void addObserver(LifecycleObserver observer) {
        Lifecycle.State initialState = this.mState == Lifecycle.State.DESTROYED ? Lifecycle.State.DESTROYED : Lifecycle.State.INITIALIZED;
        ObserverWithState statefulObserver = new ObserverWithState(observer, initialState);
        ObserverWithState previous = this.mObserverMap.putIfAbsent(observer, statefulObserver);
        if (previous != null) {
            return;
        }
        boolean isReentrance = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
        Lifecycle.State targetState = calculateTargetState(observer);
        this.mAddingObserverCounter++;
        while (statefulObserver.mState.compareTo(targetState) < 0 && this.mObserverMap.contains(observer)) {
            pushParentState(statefulObserver.mState);
            statefulObserver.dispatchEvent(this.mLifecycleOwner, upEvent(statefulObserver.mState));
            popParentState();
            targetState = calculateTargetState(observer);
        }
        if (!isReentrance) {
            sync();
        }
        this.mAddingObserverCounter--;
    }

    private void popParentState() {
        this.mParentStates.remove(r0.size() - 1);
    }

    private void pushParentState(Lifecycle.State state) {
        this.mParentStates.add(state);
    }

    @Override // android.arch.lifecycle.Lifecycle
    public void removeObserver(LifecycleObserver observer) {
        this.mObserverMap.remove(observer);
    }

    public int getObserverCount() {
        return this.mObserverMap.size();
    }

    @Override // android.arch.lifecycle.Lifecycle
    public Lifecycle.State getCurrentState() {
        return this.mState;
    }

    static Lifecycle.State getStateAfter(Lifecycle.Event event) {
        switch (AnonymousClass1.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[event.ordinal()]) {
            case 1:
            case 2:
                return Lifecycle.State.CREATED;
            case 3:
            case 4:
                return Lifecycle.State.STARTED;
            case 5:
                return Lifecycle.State.RESUMED;
            case 6:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.arch.lifecycle.LifecycleRegistry$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$Event;
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$State;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            $SwitchMap$android$arch$lifecycle$Lifecycle$State = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            $SwitchMap$android$arch$lifecycle$Lifecycle$Event = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    private static Lifecycle.Event downEvent(Lifecycle.State state) {
        int i = AnonymousClass1.$SwitchMap$android$arch$lifecycle$Lifecycle$State[state.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    private static Lifecycle.Event upEvent(Lifecycle.State state) {
        int i = AnonymousClass1.$SwitchMap$android$arch$lifecycle$Lifecycle$State[state.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    private void forwardPass() {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> ascendingIterator = this.mObserverMap.iteratorWithAdditions();
        while (ascendingIterator.hasNext() && !this.mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> entry = ascendingIterator.next();
            ObserverWithState observer = entry.getValue();
            while (observer.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                pushParentState(observer.mState);
                observer.dispatchEvent(this.mLifecycleOwner, upEvent(observer.mState));
                popParentState();
            }
        }
    }

    private void backwardPass() {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> descendingIterator = this.mObserverMap.descendingIterator();
        while (descendingIterator.hasNext() && !this.mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> entry = descendingIterator.next();
            ObserverWithState observer = entry.getValue();
            while (observer.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                Lifecycle.Event event = downEvent(observer.mState);
                pushParentState(getStateAfter(event));
                observer.dispatchEvent(this.mLifecycleOwner, event);
                popParentState();
            }
        }
    }

    private void sync() {
        while (!isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo(this.mObserverMap.eldest().getValue().mState) < 0) {
                backwardPass();
            }
            Map.Entry<LifecycleObserver, ObserverWithState> newest = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && newest != null && this.mState.compareTo(newest.getValue().mState) > 0) {
                forwardPass();
            }
        }
        this.mNewEventOccurred = false;
    }

    static Lifecycle.State min(Lifecycle.State state1, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state1) >= 0) ? state1 : state2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ObserverWithState {
        GenericLifecycleObserver mLifecycleObserver;
        Lifecycle.State mState;

        ObserverWithState(LifecycleObserver observer, Lifecycle.State initialState) {
            this.mLifecycleObserver = Lifecycling.getCallback(observer);
            this.mState = initialState;
        }

        void dispatchEvent(LifecycleOwner owner, Lifecycle.Event event) {
            Lifecycle.State newState = LifecycleRegistry.getStateAfter(event);
            this.mState = LifecycleRegistry.min(this.mState, newState);
            this.mLifecycleObserver.onStateChanged(owner, event);
            this.mState = newState;
        }
    }
}
