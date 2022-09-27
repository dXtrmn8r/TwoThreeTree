import org.junit.Test;
import org.junit.Assert;

public class TwoTreeTreeTests {

    @Test
    public void rootTest() {
        Tree tree = new Tree();
        tree.insert(9);

        Assert.assertEquals(9, tree.getRoot().get(0));
        assert(tree.getRoot().getParent() == null);
        assert(tree.getRoot().getLeft() == null);
        assert(tree.getRoot().getRight() == null);
    }

    @Test
    public void splitTest() {
        Tree tree = new Tree();
        tree.insert(9);
        tree.insert(3);
        tree.insert(7);

        Tree.Node r = tree.getRoot();

        Assert.assertEquals(7, r.get(0));
        Assert.assertEquals(1, r.getKey().size());
        assert(r.getParent() == null);
        assert(r.getLeft() != null);
        Assert.assertEquals(3, r.getLeft().get(0));
        Assert.assertEquals(1, r.getLeft().getKey().size());
        assert(r.getLeft().getParent() == r);
        assert(r.getRight() != null);
        Assert.assertEquals(9, r.getRight().get(0));
        Assert.assertEquals(1, r.getRight().getKey().size());
        assert(r.getRight().getParent() == r);
    }

}
