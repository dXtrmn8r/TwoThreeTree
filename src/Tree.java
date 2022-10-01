import java.util.ArrayList;

/**
 * An implementation of a 2-3 Tree for SJSU CS 146 with Dr. David Scot Taylor.
 *
 * @author Darren Peralta
 * @version 2.1
 * @since 1
 */
public class Tree {

    Node root;

    public Tree() {
        root = null;
    }

    private boolean inTree(int x) {
        if (this.root == null)
            return false;

        Node location = root.searchNode(x);
        return (location != null && location.getKey().contains(x));
    }

    private Node search(int x) {
        if (this.root == null)
            return null;
        Node t = this.root.searchNode(x);
        if (!t.getKey().contains(x))
            return null;
        return t;
    }

    public boolean insert(int x) {

        if (inTree(x)) return false;
        if (root == null) {
            root = new Node(x);
            return true;
        }
        Node nodeAdded = root.searchNode(x);

        if (nodeAdded.keySize() <= Node.MAX_KEY_SIZE)    // change this to accommodate adding two keys without issue
            nodeAdded.addKey(nodeAdded.indexToCheck(x),x);

        if (nodeAdded.keySize() > Node.MAX_KEY_SIZE)
            nodeAdded.split();
        if (root.getParent() != null)
            root = root.getParent();

        root.inspect();

        return true;
    }

    public int size(int x) {
        Node xRoot = this.search(x);

        if (xRoot == null)
            return 0;
        return xRoot.size();
    }

    public int size() {
        if (root == null)
            return 0;
        return root.size();
    }

    /**
     * Gets the {@code Integer} at index x if the {@code Tree} is a sorted array.
     *
     * @param index the index to search for
     * @return the {@code Integer} at the corresponding {@code index}.
     */
    public int get(int index) {
        if (root == null)
            return -1;
        return root.get(index);
    }

    private static class Node {
        final static int MAX_KEY_SIZE = 2;
        final static int MAX_CHILDREN_SIZE = 3;
        private final ArrayList<Integer> key = new ArrayList<>();
        private final ArrayList<Node> children = new ArrayList<>();
        private Node parent;

        public Node(int value) {
            key.add(value);
        }

        private Node getParent() {
            return parent;
        }

        private void setParent(Node parent) {
            this.parent = parent;
        }

        private Node getChild(int index) {
            return children.get(index);
        }

        private void addChild(Node newChild) {
            newChild.setParent(this);
            children.add(newChild);
        }

        private void addChild (int index, Node newChild) {
            newChild.setParent(this);
            children.add(index, newChild);
        }

        private void addKey(int index, int newKey) {
            key.add(index, newKey);
        }

        private ArrayList<Integer> getKey() {
            return key;
        }

        private int keySize() {
            return key.size();
        }

        private int numberOfChildren() {
            return children.size();
        }

        private int at(int index) {
            return getKey().get(index);
        }

        private int size() {
            int sizeVal = keySize();
            for (Node n : children)
                sizeVal += n.size();
            return sizeVal;
        }

        private boolean isLeaf() {
            return (this.numberOfChildren() == 0);
        }

        private void split() {
            Node newParent;
            int median = this.at(1);
            int medianLocation = 0;
            if (getParent() == null)
                newParent = this;
            else {
                newParent = getParent();
                newParent.addKey(newParent.indexToCheck(median), median);
                medianLocation = getParent().getKey().indexOf(median);
            }

            if (this.numberOfChildren() > MAX_CHILDREN_SIZE) {
                newParent.setParent(new Node(median));
                newParent = newParent.getParent();

                for (int i = 0; i < 2; i++) {
                    newParent.addChild(new Node(this.at(2 * i)));
                    newParent.getChild(i).setParent(newParent);
                    for (int j = 0; j < 2; j++) {
                        newParent.getChild(i).addChild(this.getChild(2 * i + j));
                        newParent.getChild(i).getChild(j).setParent(newParent);
                    }
                }
            } else {
                newParent.addChild(medianLocation, new Node(this.at(0)));    // leftmost element
                newParent.getChild(medianLocation).setParent(newParent);
                newParent.addChild(medianLocation + 1, new Node(this.at(2)));    // rightmost element
                newParent.getChild(medianLocation + 1).setParent(newParent);

                if (getParent() == null) {
                    newParent.getKey().remove(0);    // old left child
                    newParent.getKey().remove(1);    // old right child
                } else
                    newParent.children.remove(medianLocation + 2);
            }
            if (newParent.keySize() > MAX_KEY_SIZE) newParent.split();
        }

        public int get(int index) {
            int cumulativeSize = 0;
            int indexToSearch = 0;
            Node nodeToSearch;

            if (isLeaf()) return this.at(index);

            while (index > cumulativeSize) {
                nodeToSearch = getChild(indexToSearch);
                if (index < cumulativeSize + nodeToSearch.size())
                    return nodeToSearch.get(index - cumulativeSize);
                else if (index == cumulativeSize + nodeToSearch.size()) {
                    assert (cumulativeSize + nodeToSearch.size() + 1 < size());
                    return at(indexToSearch);
                }

                cumulativeSize += (nodeToSearch.size() + 1);
                indexToSearch++;
            }
            return getChild(indexToSearch).get(index - cumulativeSize);
        }

        private Node searchNode(int x) {
            if (getKey().contains(x) || isLeaf())
                return this;
            else
                return getChild(indexToCheck(x)).searchNode(x);
        }

        private int indexToCheck(int x) {
            int indexToCheck = 0;
            while (indexToCheck < this.keySize() && x > this.at(indexToCheck))
                indexToCheck++;
            return indexToCheck;
        }

        private void inspect() {
            assert (keySize() <= MAX_KEY_SIZE);
            assert (numberOfChildren() <= MAX_CHILDREN_SIZE);
            for (Node child : children) {
                child.inspect();
            }
        }
    }
}
