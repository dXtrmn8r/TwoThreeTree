import java.util.ArrayList;

public class Tree {

    class Node {
        private Node parent;
        private Node left;
        private Node middle;
        private Node right;
        private ArrayList<Integer> key = new ArrayList<>();
        final int MAX_SIZE = 2;
        final int CRITICAL_SIZE = 3;

        public Node() {
            parent = null;
            left = null;
            middle = null;
            right = null;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getMiddle() {
            return middle;
        }

        public void setMiddle(Node middle) {
            this.middle = middle;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public ArrayList<Integer> getKey() {
            return key;
        }

        public int size() {
            return this.key.size() + this.left.size() + this.middle.size() + this.right.size();
        }

        private boolean isLeaf() {
            return (this.getLeft() == null && this.getMiddle() == null && this.getRight() == null);
        }

        private int median(ArrayList<Integer> keys, int x) {
            if (x < keys.get(0)) return keys.get(0);
            else if (x < keys.get(1)) return x;
            else return keys.get(1);
        }

        public boolean insert(int x) {
            if (key.size() < CRITICAL_SIZE) {    // change this to accommodate adding two keys without issue
                if (key.size() == 0) {
                    key.add(x);                             // add the one key
                }  else {
                    if (x < key.get(0)) {
                        key.add(0,x);                     // insert at the beginning
                    } else if (x < key.get(size() - 1)) {
                        key.add(size() - 1,x);            // insert in the middle
                    } else if (x > key.get(size()) - 1) {
                        key.add(x);                             // insert at the end
                    } else {
                        return false;                           // duplicate
                    }
                }

                if (key.size() == CRITICAL_SIZE) {
                    Node parentToAdd = this.getParent();

                    int median = key.get(1);
                    // the ArrayList is already sorted, so it'll always be in index 1

                    parentToAdd.insert(median);

                    Node newLeft = new Node();

                    parentToAdd.setLeft(newLeft);
                    newLeft.setParent(parentToAdd);

                    Node newRight = new Node();

                    parentToAdd.setRight(newRight);
                    newRight.setParent(parentToAdd);
                }
            }

            return true;
        }

        public boolean search(int x) {

            if (getKey().contains(x)) {
                return true;
            } else if (isLeaf()) {
                return false;
            } else if (x < getKey().get(0)) {
                return getLeft().search(x);
            } else if (getKey().size() == 2 && x < getKey().get(1)) {
                return getMiddle().search(x);
            } else {
                return getRight().search(x);
            }
        }

        public Node whereToPlace(int x) {

            if (isLeaf()) {
                return this;
            } else if (x < getKey().get(0)) {
                return getLeft().whereToPlace(x);
            } else if (getKey().size() == 2 && x < getKey().get(1)) {
                return getMiddle().whereToPlace(x);
            } else {
                return getRight().whereToPlace(x);
            }
        }
    }

    Node root;

    public Tree() {
        root = null;
    }

    private boolean isAlreadyOnTree(int x) {
        if (root == null) return false;

        Node t = root;
        return t.search(x);
    }

    private Node searchNode(int x) {
        if (root == null) return null;
        Node t = root;
        return t.whereToPlace(x);
    }

    public boolean insert(int x) {

        if (isAlreadyOnTree(x)) return false;
        Node where = root.whereToPlace(x);
        return where.insert(x);

    }




}
