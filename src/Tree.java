import java.util.ArrayList;

public class Tree {

    Node root;

    public Tree() {
        root = null;
    }

    private boolean isAlreadyOnTree(int x) {
        if (this.root == null) return false;

        Node t = root;
        return t.search(x);
    }

    private Node searchNode(int x) {
        if (this.root == null) return null;
        Node t = this.root.whereToPlace(x);
        if (!t.getKey().contains(x)) return null;
        return t;
    }

    public boolean insert(int x) {

        if (isAlreadyOnTree(x)) return false;
        if (this.root == null) {
            this.root = new Node();
            this.root.insert(x);
        }
        Node where = root.whereToPlace(x);
        where.insert(x);

        if (where.key.size() == Node.CRITICAL_SIZE) {
            Node parentToAdd = root.getParent();
            if (parentToAdd == null)
                parentToAdd = new Node();

            int median = root.getKey().get(1);
            // the ArrayList is already sorted, so it'll always be in index 1

            parentToAdd.insert(median);

            Node newLeft = new Node();

            parentToAdd.setLeft(newLeft);
            newLeft.setParent(parentToAdd);
            newLeft.insert(root.getKey().get(0));

            Node newRight = new Node();

            parentToAdd.setRight(newRight);
            newRight.setParent(parentToAdd);
            newRight.insert(root.getKey().get(2));

            if (root.getParent() != null) root = root.getParent();
        }
        return true;
    }

    public int size(int x) {
        Node xRoot = this.searchNode(x);

        if (xRoot == null) return 0;
        return xRoot.size();
    }

    public int size() {
        return this.root.size();
    }

    public int get(int x) {

        return 0;
    }

    class Node {
        final static int MAX_SIZE = 2;
        final static int CRITICAL_SIZE = 3;
        private Node parent;
        private Node left;
        private Node middle;
        private Node right;
        private ArrayList<Integer> key = new ArrayList<>();

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
            int sizeVal = 0;
            if (isLeaf()) sizeVal += this.key.size();
            if (this.getLeft() != null) sizeVal += this.getLeft().size();
            if (this.getRight() != null) sizeVal += this.getRight().size();
            if (this.getMiddle() != null) sizeVal += this.getMiddle().size();

            return sizeVal;
        }

        private boolean isLeaf() {
            return (this.getLeft() == null && this.getMiddle() == null && this.getRight() == null);
        }

        public boolean insert(int x) {
            if (key.size() < CRITICAL_SIZE) {    // change this to accommodate adding two keys without issue
                if (key.size() == 0) {
                    key.add(x);                             // add the one key
                } else {
                    if (x < key.get(0)) {
                        key.add(0, x);                     // insert at the beginning
                    } else if (x < key.get(size() - 1)) {
                        key.add(size() - 1, x);            // insert in the middle
                    } else if (x > key.get(size() - 1)) {
                        key.add(x);                             // insert at the end
                    } else {
                        return false;                           // duplicate
                    }
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

}
