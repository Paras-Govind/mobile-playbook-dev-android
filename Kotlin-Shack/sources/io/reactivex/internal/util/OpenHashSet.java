package io.reactivex.internal.util;

/* loaded from: classes.dex */
public final class OpenHashSet<T> {
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int capacity) {
        this(capacity, 0.75f);
    }

    public OpenHashSet(int i, float f) {
        this.loadFactor = f;
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        this.mask = roundToPowerOfTwo - 1;
        this.maxSize = (int) (roundToPowerOfTwo * f);
        this.keys = (T[]) new Object[roundToPowerOfTwo];
    }

    public boolean add(T value) {
        T curr;
        T[] a = this.keys;
        int m = this.mask;
        int pos = mix(value.hashCode()) & m;
        T curr2 = a[pos];
        if (curr2 != null) {
            if (curr2.equals(value)) {
                return false;
            }
            do {
                pos = (pos + 1) & m;
                curr = a[pos];
                if (curr == null) {
                }
            } while (!curr.equals(value));
            return false;
        }
        a[pos] = value;
        int i = this.size + 1;
        this.size = i;
        if (i >= this.maxSize) {
            rehash();
        }
        return true;
    }

    public boolean remove(T value) {
        T curr;
        T[] a = this.keys;
        int m = this.mask;
        int pos = mix(value.hashCode()) & m;
        T curr2 = a[pos];
        if (curr2 == null) {
            return false;
        }
        if (curr2.equals(value)) {
            return removeEntry(pos, a, m);
        }
        do {
            pos = (pos + 1) & m;
            curr = a[pos];
            if (curr == null) {
                return false;
            }
        } while (!curr.equals(value));
        return removeEntry(pos, a, m);
    }

    boolean removeEntry(int pos, T[] a, int m) {
        T curr;
        this.size--;
        while (true) {
            int last = pos;
            pos = (pos + 1) & m;
            while (true) {
                curr = a[pos];
                if (curr == null) {
                    a[last] = null;
                    return true;
                }
                int slot = mix(curr.hashCode()) & m;
                if (last > pos) {
                    if (last >= slot && slot > pos) {
                        break;
                    }
                    pos = (pos + 1) & m;
                } else if (last < slot && slot <= pos) {
                    pos = (pos + 1) & m;
                }
            }
            a[last] = curr;
        }
    }

    void rehash() {
        T[] tArr = this.keys;
        int length = tArr.length;
        int i = length << 1;
        int i2 = i - 1;
        T[] tArr2 = (T[]) new Object[i];
        int i3 = this.size;
        while (true) {
            int i4 = i3 - 1;
            if (i3 == 0) {
                this.mask = i2;
                this.maxSize = (int) (i * this.loadFactor);
                this.keys = tArr2;
                return;
            }
            do {
                length--;
            } while (tArr[length] == null);
            int mix = mix(tArr[length].hashCode()) & i2;
            if (tArr2[mix] != null) {
                do {
                    mix = (mix + 1) & i2;
                } while (tArr2[mix] != null);
            }
            tArr2[mix] = tArr[length];
            i3 = i4;
        }
    }

    static int mix(int x) {
        int h = INT_PHI * x;
        return (h >>> 16) ^ h;
    }

    public Object[] keys() {
        return this.keys;
    }

    public int size() {
        return this.size;
    }
}
