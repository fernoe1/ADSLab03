package models;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V val;

        public HashNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private HashNode<K, V>[] chainArr;
    private int M = 11;
    private int size;

    public MyHashTable() {

    }

    public MyHashTable(int M) {

    }

    private int hash(K key) {
        return 0;
    }

    public void put(K key, V value) {

    }

    public V get(K key) {
        return null;
    }

    public V remove(K key) {
        return null;
    }

    public boolean contains(V value) {
        return false;
    }

    public K getKey(V value) {
        return null;
    }
}
