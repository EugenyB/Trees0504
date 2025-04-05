package tree;

public class Tree<T extends Comparable<T>> {
    private Node<T> root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean add(T key) {
        if (isEmpty()) {
            root = new Node<>(key);
            return true;
        }
        return addInSubTree(key, root);
    }

    private boolean addInSubTree(T key, Node<T> root) {
        if (key.equals(root.getKey())) {
            return false;
        }
        if (key.compareTo(root.getKey()) < 0) { // key < root.key
            if (root.getLeft() == null) {
                root.setLeft(new Node<>(key));
                return true;
            } else {
                return addInSubTree(key, root.getLeft());
            }
        } else {
            if (root.getRight() == null) {
                root.setRight(new Node<>(key));
                return true;
            } else {
                return addInSubTree(key, root.getRight());
            }
        }
    }

    public void traverse() {
        traverse(root);
        System.out.println();
    }

    private void traverse(Node<T> root) {
        if (root != null) {
            traverse(root.getLeft());
            visit(root);
            traverse(root.getRight());
        }
    }

    private void visit(Node<T> root) {
        System.out.print(root.getKey() + " ");
    }

    public boolean contains(T key) {
        return find(key) != null;
    }

    private Node<T> find(T key) {
        if (isEmpty()) {
            return null;
        }
        return findInSubTree(key, root);
    }

    private Node<T> findInSubTree(T key, Node<T> root) {
        if (root == null || key.equals(root.getKey())) {
            return root;
        }
        if (key.compareTo(root.getKey()) < 0) {
            return findInSubTree(key, root.getLeft());
        } else {
            return findInSubTree(key, root.getRight());
        }
    }

    public void remove(T key) {
        remove(key, root);
    }

    private Node<T> remove(T key, Node<T> root) {
        if (root == null) {
            return root;
        }
        if (key.equals(root.getKey())) {
            if (root.isLeaf()) {
                root = null;
            } else if (root.getLeft() != null) {
                root.setKey(predecessor(root));
                root.setLeft(remove(root.getKey(), root.getLeft())); // было root.setLeft(remove(key, root.getLeft()));
            } else {
                root.setKey(successor(root));
                root.setRight(remove(root.getKey(), root.getRight())); // было root.setRight(remove(key, root.getRight()));
            }
        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(remove(key, root.getLeft()));
        } else {
            root.setRight(remove(key, root.getRight()));
        }
        return root;
    }

    private Node<T> removeElement(T key, Node<T> root) {
        if (root == null) {
            return root;
        }
        if (key.compareTo(root.getKey()) > 0) {
            root.setRight(removeElement(key, root.getRight()));
        } else if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(removeElement(key, root.getLeft()));
        } else {
            if (root.isLeaf()) {
                root = null;
            } else if (root.getRight() != null) {
                root.setKey(successor(root));
                root.setRight(removeElement(root.getKey(), root.getRight()));
            } else {
                root.setKey(predecessor(root));
                root.setLeft(removeElement(root.getKey(), root.getLeft()));
            }
        }
        return root;
    }

    private T successor(Node<T> root) {
        root = root.getRight();
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getKey();
    }

    private T predecessor(Node<T> root) {
        root = root.getLeft();
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root.getKey();
    }
}
