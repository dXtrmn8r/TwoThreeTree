import java.util.ArrayList;

public class Tree {

    private Node root;
    class Node {

        private ArrayList<Integer> key = new ArrayList<Integer>();
        private final int MAX_SIZE = 2; // maximum size of key
        private Node left;
        private Node middle;
        private Node right;

        public Node() {
            this.left = null;
            this.middle = null;
            this.right = null;
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

        public void setKey(int newKey) {
            if (key.size() < MAX_SIZE) {
                // key.add(newKey);
                // Collections.sort(key);
                if (newKey < key.get(0)) key.add(0,newKey);
                else key.add(1, newKey);
            }
            else System.err.print("key is full. "+newKey+" is not stored.");
        }

        public ArrayList<Integer> getKey() {
            return this.key;
        }
        public int get(int index) {
            return key.get(index);
        }

        public void removeKey(int keyToRemove) {
            key.remove(keyToRemove);
        }
    }

    public Tree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void addItem(Node node, int newKey) {
        if (node == null) {
            node = new Node();
            node.setKey(newKey);
        } else if (node.key.size() < 2) {
            node.setKey(newKey);
        } else {
            if (newKey < node.get(0)) {
                addItem(node.getLeft(), newKey);
            } else if (newKey < node.get(1)) {
                addItem(node.getMiddle(), newKey);
            } else {
                addItem(node.getRight(), newKey);
            }
        }
    }


}
