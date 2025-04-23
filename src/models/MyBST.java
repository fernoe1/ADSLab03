package models;

public class MyBST<K extends Comparable<K>, V> {
    private class MyNode<K, V> {
        private K key;
        private V val;
        private MyNode left, right;

        public MyNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    private MyNode root;
    private int size;

    public void put(K key, V val) {
        if (root == null) {
            root = new MyNode<>(key, val);

            return;
        }

        MyNode<K, V> current = root;
        while (current != null) {
            if (key.compareTo(current.key) > 0 || key.compareTo(current.key) == 0) {
                if (current.right == null) {
                    current.right = new MyNode<>(key, val);

                    return;
                }

                current = current.right;
            }

            if (key.compareTo(current.key) < 0) {
                if (current.left == null) {
                    current.left = new MyNode<>(key, val);

                    return;
                }

                current = current.left;
            }
        }
    }

    public V get(K key) {
       try {
           return (V) findNode(root, key).val;
       } catch (NullPointerException e) {
           return null;
       }
    }

    private MyNode<K, V> findNode(MyNode<K, V> root, K key) {
        while (root != null) {
            if (key.compareTo(root.key) == 0) {

                return root;
            }

            if (key.compareTo(root.key) > 0 ) {
                root = root.right;
            }

            if (key.compareTo(root.key) < 0) {
                root = root.left;
            }
        }

        throw new NullPointerException("A " + key + " key does not exist");
    }

    public void delete(K key) {
        MyNode<K, V> current = root;
        MyNode<K, V> previous = null;

        while (current != null) {
            if (key.compareTo(current.key) == 0) {
                if (current.left == null && current.right == null) {
                    if (key.compareTo(previous.key) > 0) {
                        previous.right = null;
                    }

                    if (key.compareTo(previous.key) < 0) {
                        previous.left = null;
                    }

                    return;
                }

                if (current.left != null && current.right != null) {
                    MyNode<K, V> inOrder = current;

                    while (inOrder.left.left != null) {
                        inOrder = inOrder.left;
                    }

                    current.val = (V) inOrder.left.val;
                    inOrder.left = null;

                    return;
                }

                if (current.left != null || current.right != null) {
                    if (current.left != null) {
                        current.val = (V) current.left.val;
                        current.left = null;
                    }

                    if (current.right != null) {
                        current.val = (V) current.right.val;
                        current.right = null;
                    }

                    return;
                }
            }

            previous = current;
            if (key.compareTo(current.key) > 0) {
                current = current.right;
            }
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            }
        }

    }

    public Iterable<K> iterator() {
        return null;
    }
}
