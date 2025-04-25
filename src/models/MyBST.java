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
            if (key.compareTo(current.key) == 0) {
                current.val = val;

                return;
            }

            if (key.compareTo(current.key) > 0) {
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
        MyNode<K, V> parent = null;

        while (current != null) {
            if (key.compareTo(current.key) == 0) {

                // leaf
                if (current.left == null && current.right == null) {
                    if (parent == null) {
                        root = null;
                    } else if (parent.left == current) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }

                    return;
                }

                // two children
                if (current.left != null && current.right != null) {
                    MyNode<K, V> inOrder = findInOrder(current);
                    MyNode<K, V> inOrderParent = findInOrderParent(current);

                    current.val = inOrder.val;
                    current.key = inOrder.key;
                    if (inOrder.right != null) {
                        inOrderParent.left = inOrder.right;
                    } else if (inOrderParent.left == inOrder) {
                        inOrderParent.left = null;
                    } else {
                        inOrderParent.right = null;
                    }

                    return;
                }

                // one child
                if (parent == null) {
                    root = (current.left != null) ? current.left : current.right;

                    return;
                } else {
                    if (parent.left == current) {
                        parent.left = (current.left != null) ? current.left : current.right;
                    } else {
                        parent.right = (current.left != null) ? current.left : current.right;
                    }

                    return;
                }
            }

            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            throw new NullPointerException("Key " + key + " does not exist.");
        }
    }

    private MyNode<K, V> findInOrderParent(MyNode<K, V> start) {
        MyNode<K, V> parent = start;
        start = start.right;
        while (start.left != null) {
            parent = start;
            start = start.left;
        }

        return parent;
    }

    private MyNode<K, V> findInOrder(MyNode<K, V> start) {
        start = start.right;
        while (start.left != null) {
            start = start.left;
        }

        return start;
    }

    public Iterable<K> iterator() {
        return null;
    }
}
