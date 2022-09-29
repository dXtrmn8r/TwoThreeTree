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
            return true;
        }
        Node where = root.whereToPlace(x);
        where.insert(x);

        while (where.keySize() == Node.CRITICAL_SIZE) {
            Node parentToAdd = where.split();

            where.setParent(parentToAdd);
            where = where.getParent();            // iteratively run this splitting code at the parent
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

    /**
     * Gets the {@code Integer} at index x
     *
     * @param index the index to search for
     * @return
     */
    public int get(int index) {
        Node location = root;
        if (location == null) return -1; // returns -1 if node is not found

        int size = 0;
        if (location.isLeaf()) return location.at(index);

        size = location.getLeft().size();

        if (index < size) return location.getLeft().at(index);
        else if (index < size + 1) return location.at(0);

        size++;
        if (location.getMiddle() != null) {
            size = location.getMiddle().size() + 1;
            if (index < size) return location.getMiddle().at(index - size);
            else if (index < size + 1) return location.at(1);
        }

        return location.getRight().at(index - size);
    }

    class Node {
        final static int MAX_SIZE = 2;
        final static int CRITICAL_SIZE = 3;
        private ArrayList<Integer> key = new ArrayList<>();
        private Node parent;
        private Node left;
        private Node middle;
        private Node right;

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

        public int keySize() {
            return key.size();
        }

        public int size() {
            int sizeVal = keySize();
            if (!isLeaf()) {
                if (this.getLeft() != null) sizeVal += this.getLeft().size();
                if (this.getMiddle() != null) sizeVal += this.getMiddle().size();
                if (this.getRight() != null) sizeVal += this.getRight().size();
            }
            return sizeVal;
        }

        private boolean isLeaf() {
            return (this.getLeft() == null && this.getMiddle() == null && this.getRight() == null);
        }

        public void insert(int x) {
            if (keySize() < CRITICAL_SIZE) {    // change this to accommodate adding two keys without issue
                if (keySize() == 0) {
                    getKey().add(x);                             // add the one key
                } else {
                    if (x < getKey().get(0)) {
                        getKey().add(0, x);                     // insert at the beginning
                    } else if (x < getKey().get(keySize() - 1)) {
                        getKey().add(size() - 1, x);            // insert in the middle
                    } else if (x > getKey().get(keySize() - 1)) {
                        getKey().add(x);                             // insert at the end
                    }
                }
            }
        }

        public Node split() {

            if (isLeaf()) return splitLeaf();

            Node parentToAdd = this.getParent();
            ArrayList<Node> children = new ArrayList<Node>();

            //check for any old nodes
            if (getLeft() != null) children.add(getLeft());
            if (getMiddle() != null) children.add(getMiddle());
            if (getRight() != null) children.add(getRight());

            //create new nodes and add them
            Node newLeft = new Node();
            newLeft.insert(this.getKey().get(0));
            children.add(newLeft);
            Node newRight = new Node();
            newRight.insert(this.getKey().get(2));
            children.add(newRight);

            if (parentToAdd == null)
                parentToAdd = new Node();

            int median = this.getKey().get(1);
            // the ArrayList is already sorted, so it'll always be in index 1

            parentToAdd.insert(median);
            int parentKeyLocation = parentToAdd.getKey().indexOf(median);

            if (children.size() == 2) {
                parentToAdd.setLeft(newLeft);
                newLeft.setParent(parentToAdd);

                parentToAdd.setRight(newRight);
                newRight.setParent(parentToAdd);
            }

            return parentToAdd;
        }

        private Node splitLeaf() {
            Node parentToAdd = this.getParent();

            //create new nodes and add them
            Node newLeft = new Node();
            newLeft.insert(this.getKey().get(0));
            Node newRight = new Node();
            newRight.insert(this.getKey().get(2));

            if (parentToAdd == null)
                parentToAdd = new Node();

            int median = this.getKey().get(1);
            // the ArrayList is already sorted, so it'll always be in index 1

            parentToAdd.insert(median);
            int parentKeyLocation = parentToAdd.getKey().indexOf(median);
            int parentKeySize = parentToAdd.keySize();

            if (parentKeySize == 1) {
                parentToAdd.setLeft(newLeft);
                newLeft.setParent(parentToAdd);

                parentToAdd.setRight(newRight);
                newRight.setParent(parentToAdd);
            } else if (parentKeyLocation == 0 && parentKeySize == 2) {
                parentToAdd.setLeft(newLeft);
                newLeft.setParent(parentToAdd);

                parentToAdd.setMiddle(newRight);
                newRight.setParent(parentToAdd);
            } else if (parentKeyLocation == 1 && parentKeySize == 2) {
                parentToAdd.setMiddle(newLeft);
                newLeft.setParent(parentToAdd);

                parentToAdd.setRight(newRight);
                newRight.setParent(parentToAdd);

            } else if (parentKeySize == 3) {
                median = parentToAdd.at(1);
                ArrayList<Node> nodesToDistribute = new ArrayList<Node>();

                if (parentKeyLocation == 0) {
                    nodesToDistribute.add(parentToAdd.getMiddle());
                    nodesToDistribute.add(parentToAdd.getRight());
                    nodesToDistribute.add(newLeft);
                    nodesToDistribute.add(newRight);
                } else if (parentKeyLocation == 1) {
                    nodesToDistribute.add(newLeft);
                    nodesToDistribute.add(parentToAdd.getMiddle());
                    nodesToDistribute.add(parentToAdd.getRight());
                    nodesToDistribute.add(newRight);
                } else if (parentKeyLocation == 2) {
                    nodesToDistribute.add(newLeft);
                    nodesToDistribute.add(newRight);
                    nodesToDistribute.add(parentToAdd.getMiddle());
                    nodesToDistribute.add(parentToAdd.getRight());
                }

                Node newLeftNonLeafNode = new Node();
                Node newRightNonLeafNode = new Node();

                newLeftNonLeafNode.setLeft(nodesToDistribute.get(0));
                nodesToDistribute.get(0).setParent(newLeftNonLeafNode);
                newLeftNonLeafNode.setRight(nodesToDistribute.get(1));
                nodesToDistribute.get(1).setParent(newLeftNonLeafNode);

                newLeftNonLeafNode.insert(parentToAdd.at(0));

                newLeftNonLeafNode.setParent(parentToAdd);
                parentToAdd.setLeft(newLeftNonLeafNode);

                newRightNonLeafNode.setLeft(nodesToDistribute.get(2));
                nodesToDistribute.get(2).setParent(newRightNonLeafNode);
                newRightNonLeafNode.setRight(nodesToDistribute.get(3));
                nodesToDistribute.get(3).setParent(newRightNonLeafNode);

                newRightNonLeafNode.insert(parentToAdd.at(2));

                newRightNonLeafNode.setParent(parentToAdd);
                parentToAdd.setRight(newRightNonLeafNode);
                parentToAdd.getKey().clear();
                parentToAdd.insert(median);

                parentToAdd.setMiddle(null);
            }

            return parentToAdd;
        }

        public boolean search(int x) {
            if (getKey().contains(x)) {
                return true;
            } else if (isLeaf()) {
                return false;
            } else if (x < getKey().get(0)) {
                return getLeft().search(x);
            } else if (keySize() == 2 && x < getKey().get(1)) {
                return getMiddle().search(x);
            } else {
                return getRight().search(x);
            }
        }

        public Node whereToPlace(int x) {
            if (isLeaf() || getKey().contains(x)) {
                return this;
            } else if (x < getKey().get(0)) {
                return getLeft().whereToPlace(x);
            } else if (keySize() == Node.MAX_SIZE
                    && x < getKey().get(size() - 1)) {
                return getMiddle().whereToPlace(x);
            } else {
                return getRight().whereToPlace(x);
            }
        }

        public int at(int index) {
            return getKey().get(index);
        }
    }

}
