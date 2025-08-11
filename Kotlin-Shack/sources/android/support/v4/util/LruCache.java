package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = maxSize;
        }
        trimToSize(maxSize);
    }

    public final V get(K k) {
        Throwable th;
        V v;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            try {
                try {
                    V v2 = this.map.get(k);
                    if (v2 != null) {
                        this.hitCount++;
                        return v2;
                    }
                    this.missCount++;
                    V create = create(k);
                    if (create == null) {
                        return null;
                    }
                    synchronized (this) {
                        this.createCount++;
                        v = (V) this.map.put(k, create);
                        if (v != null) {
                            this.map.put(k, v);
                        } else {
                            this.size += safeSizeOf(k, create);
                        }
                    }
                    if (v != null) {
                        entryRemoved(false, k, create, v);
                        return v;
                    }
                    trimToSize(this.maxSize);
                    return create;
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    public final V put(K key, V value) {
        V previous;
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(key, value);
            previous = this.map.put(key, value);
            if (previous != null) {
                this.size -= safeSizeOf(key, previous);
            }
        }
        if (previous != null) {
            entryRemoved(false, key, previous, value);
        }
        trimToSize(this.maxSize);
        return previous;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0075, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
            r2 = r1
        L3:
            monitor-enter(r6)
            int r3 = r6.size     // Catch: java.lang.Throwable -> L76
            if (r3 < 0) goto L57
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch: java.lang.Throwable -> L76
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L15
            int r3 = r6.size     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L15
            goto L57
        L15:
            int r3 = r6.size     // Catch: java.lang.Throwable -> L76
            if (r3 <= r7) goto L55
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch: java.lang.Throwable -> L76
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L76
            if (r3 == 0) goto L22
            goto L55
        L22:
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch: java.lang.Throwable -> L76
            java.util.Set r3 = r3.entrySet()     // Catch: java.lang.Throwable -> L76
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L76
            java.lang.Object r3 = r3.next()     // Catch: java.lang.Throwable -> L76
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Throwable -> L76
            java.lang.Object r4 = r3.getKey()     // Catch: java.lang.Throwable -> L76
            r1 = r4
            java.lang.Object r4 = r3.getValue()     // Catch: java.lang.Throwable -> L76
            r2 = r4
            java.util.LinkedHashMap<K, V> r4 = r6.map     // Catch: java.lang.Throwable -> L76
            r4.remove(r1)     // Catch: java.lang.Throwable -> L76
            int r4 = r6.size     // Catch: java.lang.Throwable -> L76
            int r5 = r6.safeSizeOf(r1, r2)     // Catch: java.lang.Throwable -> L76
            int r4 = r4 - r5
            r6.size = r4     // Catch: java.lang.Throwable -> L76
            int r4 = r6.evictionCount     // Catch: java.lang.Throwable -> L76
            r5 = 1
            int r4 = r4 + r5
            r6.evictionCount = r4     // Catch: java.lang.Throwable -> L76
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L76
            r6.entryRemoved(r5, r1, r2, r0)
            goto L3
        L55:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L76
            return
        L57:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L76
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76
            r3.<init>()     // Catch: java.lang.Throwable -> L76
            java.lang.Class r4 = r6.getClass()     // Catch: java.lang.Throwable -> L76
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L76
            r3.append(r4)     // Catch: java.lang.Throwable -> L76
            java.lang.String r4 = ".sizeOf() is reporting inconsistent results!"
            r3.append(r4)     // Catch: java.lang.Throwable -> L76
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L76
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L76
            throw r0     // Catch: java.lang.Throwable -> L76
        L76:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L76
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }

    public final V remove(K key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            try {
                try {
                    V previous = this.map.remove(key);
                    if (previous != null) {
                        this.size -= safeSizeOf(key, previous);
                    }
                    if (previous != null) {
                        entryRemoved(false, key, previous, null);
                    }
                    return previous;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    protected V create(K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + key + "=" + value);
        }
        return result;
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int hitPercent;
        int accesses = this.hitCount + this.missCount;
        hitPercent = accesses != 0 ? (100 * this.hitCount) / accesses : 0;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(hitPercent));
    }
}
