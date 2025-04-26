package models;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V val;
        private HashNode<K, V> next;

        public HashNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private HashNode<K, V>[] chainArr;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArr = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArr = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    public int getM() {
        return M;
    }

    public int getBucketSize(int index) {
        HashNode<K, V> current = chainArr[index];
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode());
    }

    public void put(K key, V value) {
        int index = hash(key) % M;
        if (chainArr[index] == null) {
            chainArr[index] = new HashNode<>(key, value);
            size++;
        } else {
            HashNode<K, V> root = chainArr[index];
            while (root != null) {
                if (root.key.equals(key)) {
                    root.val = value;

                    return;
                }
                if (root.next == null) {
                    root.next = new HashNode<>(key, value);

                    size++;
                    return;
                }
                root = root.next;
            }
        }
    }

    public V get(K key) {
        try {
            return findNode(chainArr[hash(key) % M], key).val;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private HashNode<K, V> findNode(HashNode<K, V> root, K key) throws NullPointerException {
        while (root != null) {
            if (root.key.equals(key)) {

                return root;
            }
            root = root.next;
        }

        throw new NullPointerException("A " + key + " key does not exist");
    }

    public V remove(K key) {
        V value = null;
        HashNode<K, V> current = chainArr[hash(key) % M];
        HashNode<K, V> previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                value = current.val;
                if (previous == null && current.next == null) {
                    chainArr[hash(key) % M] = null;
                } else if (previous == null && current.next != null) {
                    chainArr[hash(key) % M] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;
                return value;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArr[i];
            while (current != null) {
                if (current.val.equals(value)) {

                    return true;
                }
                current = current.next;
            }
        }

        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArr[i];
            while (current != null) {
                if (current.val.equals(value)) {

                    return current.key;
                }
                current = current.next;
            }
        }

        return null;
    }
}
