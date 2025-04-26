package models;

/**
 * Implementation of Hash Table.
 * @param <K> the key
 * @param <V> the value
 */
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

    /**
     * Get length of chain array.
     * @return the length of chain array
     */
    public int getM() {
        return M;
    }

    /**
     * Get the size of a bucket. <br>
     * O(n) time complexity.
     * @param index the index of the bucket
     * @return the size of that bucket
     */
    public int getBucketSize(int index) {
        HashNode<K, V> current = chainArr[index];
        int size = 0;
        // iterate through the bucket till the end
        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    /**
     * Runs the hash code method on the key.
     * @param key the key to hash
     * @return hashed value
     */
    private int hash(K key) {
        return Math.abs(key.hashCode());
    }

    /**
     * Hashes the key, calculates the index, and puts a new node at that specified bucket. <br>
     * If a node with that key already exists, updates its value. <br>
     * O(1) best O(n) worst time complexity.
     * @param key the key
     * @param value the value
     */
    public void put(K key, V value) {
        int index = hash(key) % M;
        if (chainArr[index] == null) {
            // if the root at the given index does not exist
            // initialize it
            chainArr[index] = new HashNode<>(key, value);
            size++;
        } else {
            // if the node exists, iterate till the end
            HashNode<K, V> root = chainArr[index];
            while (root != null) {
                if (root.key.equals(key)) {
                    // update the value if a node with that key already exists
                    root.val = value;

                    return;
                }
                if (root.next == null) {
                    // create new node
                    root.next = new HashNode<>(key, value);

                    size++;
                    return;
                }
                root = root.next;
            }
        }
    }

    /**
     * Gets the value of specified key. <br>
     * O(1) best O(n) worst time complexity.
     * @param key the key
     * @return the value of that specified key
     */
    public V get(K key) {
        try {
            return findNode(chainArr[hash(key) % M], key).val;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Finds the node with a specified key.
     * @param root the root of the bucket
     * @param key the specified key
     * @return the node with that specified key
     * @throws NullPointerException if the node with that specified key does not exist
     */
    private HashNode<K, V> findNode(HashNode<K, V> root, K key) throws NullPointerException {
        // iterate through root till the node
        while (root != null) {
            if (root.key.equals(key)) {

                return root;
            }
            root = root.next;
        }

        throw new NullPointerException("A " + key + " key does not exist");
    }

    /**
     * Removes the node with specified key from the hash table. <br>
     * O(1) best O(n) worst time complexity.
     * @param key the key
     * @return the value of the removed node
     */
    public V remove(K key) {
        V value = null;
        HashNode<K, V> current = chainArr[hash(key) % M];
        HashNode<K, V> previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                value = current.val;
                if (previous == null && current.next == null) {
                    // make the root null
                    chainArr[hash(key) % M] = null;
                } else if (previous == null && current.next != null) {
                    // change the root to the next node
                    chainArr[hash(key) % M] = current.next;
                } else {
                    // cut pointers to the middle node
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

    /**
     * Returns true or false depending on if the hash table contains specified value. <br>
     * O(n) time complexity.
     * @param value the value
     * @return true or false
     */
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArr[i];
            // iterate through all buckets
            while (current != null) {
                if (current.val.equals(value)) {

                    return true;
                }
                current = current.next;
            }
        }

        return false;
    }

    /**
     * Returns the key of the specified value. <br>
     * O(n) time complexity.
     * @param value the value
     * @return the key
     */
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArr[i];
            // iterate through all buckets
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
