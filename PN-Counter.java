package crdt.counters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.Math.max;

import crdt.CRDT;

/**
 * Increment and decrement counter
 */
public class PNCounter<T> implements CRDT<PNCounter<T>> {

    private static final long serialVersionUID = 1L;

    private GCounter<T> inc = new GCounter<T>();
    private GCounter<T> dec = new GCounter<T>();

    public void increment(T key) {
        inc.increment(key);
    }

    public int get() {
        return inc.get() - dec.get();
    }

    public void decrement(T key) {
        dec.increment(key);
    }

    /**
     * Merge another counter into this one
     */

    public void merge(PNCounter<T> other) {
        inc.merge( other.inc );
        dec.merge( other.dec );
    }


    public PNCounter<T> copy() {
        PNCounter<T> copy = new PNCounter<T>();
        copy.inc = inc.copy();
        copy.dec = dec.copy();
        return copy;
    }
}