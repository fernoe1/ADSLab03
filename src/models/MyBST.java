package models;

public class MyBST<K extends Comparable<K>, V> {
    private class MyNode {
        private K key;
        private V val;
        private MyNode left, right;

        public MyNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private MyNode root;

    public void put(K key, V val) {

    }

    public V get(K key) {
        return null;
    }

    public void delete(K key) {

    }

    public Iterable<K> iterator() {
        return null;
    }
}
