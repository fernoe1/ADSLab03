package models;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Binary Search Tree implementation.
 * @param <K> the key
 * @param <V> the value
 */
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

    /**
     * Puts a new key or updates existing keys value. <br>
     * O(log n) time complexity.
     * @param key the key
     * @param val the value
     */
    public void put(K key, V val) {
        // if the root is null, we initialize it
        if (root == null) {
            root = new MyNode<>(key, val);

            size++;
            return;
        }

        MyNode<K, V> current = root;
        while (current != null) {
            // if a node with that key already exists, update its value
            if (key.compareTo(current.key) == 0) {
                current.val = val;

                return;
            }

            // traverse right if the key is bigger than the current key
            // and initialize a new node if it does not exist
            if (key.compareTo(current.key) > 0) {
                if (current.right == null) {
                    current.right = new MyNode<>(key, val);

                    size++;
                    return;
                }

                current = current.right;
            }

            // same thing as above but for left
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

    /**
     * Gets specific key's value from BST.
     * @param key the key
     * @return the value of that key
     */
    public V get(K key) {
       try {
           return (V) findNode(root, key).val;
       } catch (NullPointerException e) {
           return null;
       }
    }

    /**
     * Returns size of the BST.
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Helper method that finds node using a key. <br>
     * O(log n) time complexity.
     * @param root the root of the BST
     * @param key the key to search for
     * @return the node with that key
     * @throws NullPointerException if the key does not exist
     */
    private MyNode<K, V> findNode(MyNode<K, V> root, K key) {
        while (root != null) {
            // if the key is found return the node
            if (key.compareTo(root.key) == 0) {

                return root;
            }

            // traverse right
            if (key.compareTo(root.key) > 0 ) {
                root = root.right;
            }

            // traverse left
            if (key.compareTo(root.key) < 0) {
                root = root.left;
            }
        }

        // throw null pointer exception if a node with specified key
        // does not exist
        throw new NullPointerException("A " + key + " key does not exist");
    }

    /**
     * Deletes a node with specified key from the BST. <br>
     * O(log n) time complexity.
     * @param key the key of the node to delete
     * @throws NullPointerException if a node with the specified key does not exist
     */
    public void delete(K key) {
        MyNode<K, V> current = root;
        MyNode<K, V> parent = null;

        while (current != null) {
            if (key.compareTo(current.key) == 0) {

                // deleting a leaf
                if (current.left == null && current.right == null) {
                    if (parent == null) {
                        // if it's the root, make it null
                        root = null;
                    } else if (parent.left == current) {
                        // if it's the left leaf, make the left pointer null
                        parent.left = null;
                    } else {
                        // if it's the right, make the right pointer null
                        parent.right = null;
                    }

                    size--;
                    return;
                }

                // deleting a node with two children
                if (current.left != null && current.right != null) {
                    // finding inOrder and inOrderParent
                    MyNode<K, V> inOrder = findInOrder(current);
                    MyNode<K, V> inOrderParent = findInOrderParent(current);

                    // copying the value and key of the inOrder
                    current.val = inOrder.val;
                    current.key = inOrder.key;
                    if (inOrder.right != null) {
                        // making sure we don't lose children of the inOrder node
                        inOrderParent.left = inOrder.right;
                    } else if (inOrderParent.left == inOrder) {
                        // if it does not have any children, make the pointers null
                        inOrderParent.left = null;
                    } else {
                        inOrderParent.right = null;
                    }

                    size--;
                    return;
                }

                // deleting a node with one children
                if (parent == null) {
                    // if the root contains one child, make the next child the root
                    root = (current.left != null) ? current.left : current.right;

                    size--;
                    return;
                } else {
                    if (parent.left == current) {
                        // same thing but for a node
                        parent.left = (current.left != null) ? current.left : current.right;
                    } else {
                        parent.right = (current.left != null) ? current.left : current.right;
                    }

                    size--;
                    return;
                }
            }

            // traversing
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // throw null pointer exception if a node with the given key does not exist
        if (current == null) {
            throw new NullPointerException("Key " + key + " does not exist.");
        }
    }

    /**
     * Finds in order node's parent. <br>
     * O(log n) time complexity.
     * @param start the node to start from
     * @return the in order node's parent
     */
    private MyNode<K, V> findInOrderParent(MyNode<K, V> start) {
        MyNode<K, V> parent = start;
        start = start.right;
        // traversing
        while (start.left != null) {
            parent = start;
            start = start.left;
        }

        return parent;
    }

    /**
     * Finds in order node. <br>
     * O(log n) time complexity.
     * @param start the node to start from
     * @return the in order node
     */
    private MyNode<K, V> findInOrder(MyNode<K, V> start) {
        start = start.right;
        // traversing
        while (start.left != null) {
            start = start.left;
        }

        return start;
    }

    /**
     * Return iterator for for-each.
     * @return the in order iterator
     */
    @Override
    public Iterator<MyNode<K, V>> iterator() {
        return new MyBSTIterator();
    }

    /**
     * Implementation of in order iterator using stack logical data structure.
     */
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
