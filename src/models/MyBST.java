package models;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST<K, V>.MyNode<K, V>> {
    public class MyNode<K, V> {
        private K key;
        private V val;
        private MyNode<K, V> left, right;

        public MyNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }
    private MyNode root;
    private int size;

    public MyBST() {
        size = 0;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new MyNode<>(key, val);

            size++;
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

                    size++;
                    return;
                }

                current = current.right;
            }

            if (key.compareTo(current.key) < 0) {
                if (current.left == null) {
                    current.left = new MyNode<>(key, val);

                    size++;
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

    public int getSize() {
        return size;
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

                    size--;
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

                    size--;
                    return;
                }

                // one child
                if (parent == null) {
                    root = (current.left != null) ? current.left : current.right;

                    size--;
                    return;
                } else {
                    if (parent.left == current) {
                        parent.left = (current.left != null) ? current.left : current.right;
                    } else {
                        parent.right = (current.left != null) ? current.left : current.right;
                    }

                    size--;
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

    @Override
    public Iterator<MyNode<K, V>> iterator() {
        return new MyBSTIterator();
    }

    private class MyBSTIterator implements Iterator<MyNode<K, V>> {
        private Stack<MyNode<K, V>> stack;

        public MyBSTIterator() {
            stack = new Stack<>();
            moveLeft(root);
        }

        private void moveLeft(MyNode<K, V> current) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public MyNode<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Stack is empty");
            }

            MyNode<K, V> current = stack.pop();
            if (current.right != null) {
                moveLeft(current.right);
            }

            return current;
        }
    }
}
