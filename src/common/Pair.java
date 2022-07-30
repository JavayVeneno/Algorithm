package common;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Pair<K, V> {

    private final Map<K, V> data = new HashMap<>();

    public Pair() {

    }

    public Pair(K k, V v) {

        if (k == null || v == null) {
            throw new IllegalArgumentException("pair can not store null");
        }
        this.data.put(k, v);
    }

    public K getKey() {

        Set<K> ks = this.data.keySet();
        Iterator<K> iterator = ks.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    public V getValue() {
        if (getKey() != null) {
            return this.data.get(getKey());
        }
        return null;
    }

    public boolean containsKey(K k) {
        return data.containsKey(k);
    }
}
