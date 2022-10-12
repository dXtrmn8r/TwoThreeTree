import java.util.ArrayList;

/**
 * An implementation of a 2-3 Tree for CS 146 at San José State University
 * with Dr. David Scot Taylor.
 *
 * @author Darren Peralta
 * @version 2.4
 * @since 1
 */
public class Tree {

    /**
     * The root of the entire {@code Tree}.
     */
    Node root;

    /**
     * Returns an empty instance of a 2-3 Tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Searches for a specific value within the {@code Tree}.
     *
     * @param x the value to search for
     * @return the node the value is in or {@code null} if no node has that value
     */
    private Node search(int x) {
        if (this.root == null) return null;
        Node t = this.root.searchNode(x);
        if (!t.key.contains(x))
            return null;
        return t;
    }

    /**
     * Inserts a particular value in the {@code Tree}.
     *
     * @param x the value to insert
     * @return {@code true} if {@code x} is inserted or {@code false} if {@code x}
     * is already in the tree.
     */
    public boolean insert(int x) {

        if (search(x) != null)
            return false;
        if (root == null) {
            root = new Node(x);
            return true;
        }
        Node nodeAdded = root.searchNode(x);

        nodeAdded.addKey(nodeAdded.indexToCheck(x), x);

        if (nodeAdded.numberOfKeys() > Node.MAX_KEY_SIZE)
            nodeAdded.split();
        if (root.parent != null)
            root = root.parent;

        root.inspect();

        return true;
    }

    /**
     * Calculates the size of the subtree rooted at the {@code Node}
     * with a particular value.
     *
     * @param x the value that will be at the root of the subtree.
     * @return the number of {@code int} keys in that subtree.
     */
    public int size(int x) {
        Node xRoot = this.search(x);

        if (xRoot == null)
            return 0;
        return xRoot.size;
    }

    /**
     * Calculates the size of the entire {@code Tree}.
     *
     * @return the number of {@code int} keys in the entire tree.
     */
    public int size() {
        if (root == null)
            return 0;
        return root.size;
    }

    /**
     * Gets the {@code int} key at index x if the {@code Tree} is a sorted array.
     *
     * @param index the index to search for
     * @return the {@code Integer} at the corresponding {@code index}
     */
    public int get(int index) {
        if (root == null)
            return -1;
        return root.get(index);
    }

    /**
     * An implementation of a {@code Node} in a 2-3 tree.
     */
    private class Node {
        private final static int MAX_KEY_SIZE = 2;
        private final static int MAX_CHILDREN_SIZE = 3;
        private final ArrayList<Integer> key = new ArrayList<>();
        private final ArrayList<Node> children = new ArrayList<>();
        private Node parent;
        private int size;

        /**
         * Creates a specific instance of the node with a key.
         *
         * @param value the first int to be stored in the {@code Node}.
         */
        private Node(int value) {
            key.add(value);
            size = 1;
        }

        /**
         * Returns the child {@code Node} from a certain index.
         *
         * @param index the index of the child {@code Node}
         * @return the corresponding child {@code Node}
         */
        private Node getChild(int index) {
            return children.get(index);
        }

        private void addChild(Node newChild) {
            newChild.parent = this;
            children.add(newChild);
            incrementSize(newChild);
        }

        private void addChild(int index, Node newChild) {
            newChild.parent = this;
            children.add(index, newChild);
            incrementSize(newChild);
        }

        private void addKey(int index, int newKey) {
            key.add(index, newKey);
            incrementSize();
        }

        /**
         * Returns the number of keys the {@code Node} has.
         *
         * @return the number of keys
         */
        private int numberOfKeys() {
            return key.size();
        }

        /**
         * Returns the number of child nodes the {@code Node} has
         *
         * @return the number of child nodes
         */
        private int numberOfChildren() {
            return children.size();
        }

        /**
         * Returns the key at the specified index.
         *
         * @param index the index
         * @return the key at the specified index.
         */
        private int at(int index) {
            return key.get(index);
        }

        /**
         * Splits the node having more than the maximum keys allowed.
         */
        private void split() {
            // assert (numberOfKeys() > MAX_KEY_SIZE);
            Node newParent;
            int median = this.at(1);
            int medianLocation = 0;
            if (this.parent != null) {
                newParent = this.parent;
                newParent.addKey(newParent.indexToCheck(median), median);
                medianLocation = parent.key.indexOf(median);
            } else {
                newParent = this;
            }

            if (this.numberOfChildren() > MAX_CHILDREN_SIZE) {  //four children
                if (this.parent == null) {
                    newParent = new Node(median);
                    medianLocation = 0;
                }

                this.parent = newParent;

                for (int i = 0; i < 2; i++) {
                    newParent.addChild(medianLocation + i, new Node(this.at(2 * i)));
                    for (int j = 0; j < 2; j++)
                        newParent.getChild(medianLocation + i).addChild(this.getChild(2 * i + j));
                    if (newParent.numberOfChildren() == 4 && newParent.numberOfKeys() < 3) {
                        // which will still have the three proper children + the child with 3 keys or four nodes
                        newParent.decrementSize(newParent.getChild(medianLocation + 2));
                        newParent.children.remove(medianLocation + 2);
                    }
                }
            } else {    // if the node only has three children

                for (int i = 0; i < 2; i++) {
                    newParent.addChild(medianLocation + i, new Node(this.at(2 * i)));
                }

                if (parent == null) {
                    newParent.key.remove(0);    // old left child
                    newParent.key.remove(1);    // old right child
                    newParent.size -= 2;
                } else {
                    newParent.decrementSize(newParent.getChild(medianLocation + 2));
                    newParent.children.remove(medianLocation + 2);
                }
            }
            if (newParent.numberOfKeys() > MAX_KEY_SIZE)
                newParent.split();
        }

        private int get(int index) {
            int cumulativeSize = 0;
            int indexToSearch = 0;
            Node nodeToSearch;

            if (numberOfChildren() == 0) return this.at(index);

            while (index > cumulativeSize) {
                nodeToSearch = getChild(indexToSearch);
                if (index < cumulativeSize + nodeToSearch.size)
                    return nodeToSearch.get(index - cumulativeSize);
                else if (index == cumulativeSize + nodeToSearch.size) {
                    // assert (cumulativeSize + nodeToSearch.size + 1 < size);
                    return at(indexToSearch);
                }

                cumulativeSize += (nodeToSearch.size + 1);
                indexToSearch++;
            }
            return getChild(indexToSearch).get(index - cumulativeSize);
        }

        private Node searchNode(int x) {
            if (key.contains(x) || numberOfChildren() == 0)
                return this;
            else
                return getChild(indexToCheck(x)).searchNode(x);
        }

        /**
         * Given a value, it looks for the index of the child {@code Node}
         * to go down on.
         *
         * @param x the value to search for
         * @return the index of the child {@code Node} to traverse to.
         */
        private int indexToCheck(int x) {
            int indexToCheck = 0;
            while (indexToCheck < this.numberOfKeys() && x > this.at(indexToCheck))
                indexToCheck++;
            return indexToCheck;
        }

        private void inspect() {
            assert (numberOfKeys() <= MAX_KEY_SIZE);
            assert (numberOfChildren() <= MAX_CHILDREN_SIZE);
            for (Node child : children) {
                child.inspect();
            }
        }

        public void incrementSize() {
            this.size++;
            if (this.parent != null) {
                this.parent.incrementSize();
            }
        }

        private void incrementSize(Node childNode) {
            this.size += childNode.size;
            if (this.parent != null) {
                this.parent.incrementSize(childNode);
            }
        }

        private void decrementSize(Node nodeToRemove) {
            this.size -= nodeToRemove.size;
            if (this.parent != null) {
                this.parent.decrementSize(nodeToRemove);
            }
        }
    }
}
