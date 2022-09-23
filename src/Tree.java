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
            if (key.size() < MAX_SIZE) key.add(newKey);
            else System.err.print("key is full. "+newKey+" is not stored.");
        }

        public void removeKey(int keyToRemove) {
            key.remove(keyToRemove);
        }
    }

    public Tree() {
        root = null;
    }

    public void addItem(int newKey) {
        if (root == null) {
            root = new Node();
            root.setKey(newKey);
        }
    }


}
