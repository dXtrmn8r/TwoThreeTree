import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class GivenTreeTests {

    @Test
    public void emptyTree() {
        Tree t = new Tree();
        assertEquals(-1, t.get(55));
        assertEquals(0, t.size(22));
        assertEquals(0, t.size());
    }
    @Test
    public void singleNodeTree() {
        Tree t = new Tree();
        t.insert(9);

        assertEquals(1, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));

        t.insert(15);
        assertEquals(2, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));
        assertEquals(2, t.size(15));
        assertEquals(0, t.size(18));

        t = new Tree();
        t.insert(15);
        t.insert(9);
        assertEquals(2, t.size(9));
        assertEquals(0, t.size(8));
        assertEquals(0, t.size(10));
        assertEquals(2, t.size(15));
        assertEquals(0, t.size(18));

        assertEquals(9, t.get(0));
        assertEquals(15, t.get(1));


    }

    @Test
    public void oneSplitLeft() {
        Tree t = new Tree();
        t.insert(9);
        t.insert(15);
        t.insert(1);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(1, t.get(0));
        assertEquals(9, t.get(1));
        assertEquals(15, t.get(2));

        assertEquals(3, t.size());
    }

    @Test
    public void oneSplitRight() {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(1, t.get(0));
        assertEquals(9, t.get(1));
        assertEquals(15, t.get(2));
        assertEquals(3, t.size());


    }

    @Test
    public void oneSplitMiddle() {
        Tree t = new Tree();
        t.insert(1);
        t.insert(15);
        t.insert(9);

        assertEquals(3, t.size(9));
        assertEquals(1, t.size(15));
        assertEquals(0, t.size(17));
        assertEquals(0, t.size(11));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(1, t.get(0));
        assertEquals(9, t.get(1));
        assertEquals(15, t.get(2));
        assertEquals(3, t.size());


    }


    @Test
    public void testDuplicates() {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);        // double split here
        assertFalse(t.insert(1));
        t.insert(9);
        t.insert(15);
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(4);

        assertEquals(7, t.size(9));
        assertEquals(3, t.size(4));
        assertEquals(3, t.size(15));

        assertEquals(0, t.size(12));
        assertEquals(1, t.size(13));
        assertEquals(0, t.size(14));
        assertEquals(0, t.size(19));
        assertEquals(1, t.size(20));
        assertEquals(0, t.size(21));

        assertEquals(1, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(0, t.size(6));
        assertEquals(1, t.size(7));
        assertEquals(0, t.size(8));

        assertEquals(1, t.get(0));
        assertEquals(4, t.get(1));
        assertEquals(7, t.get(2));
        assertEquals(9, t.get(3));
        assertEquals(13, t.get(4));
        assertEquals(15, t.get(5));
        assertEquals(20, t.get(6));
        assertEquals(7, t.size());


    }

    @Test
    public void doubleCascadeMiddle() {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(10);
        t.insert(21);
        t.insert(12);        // double split here

        assertEquals(4, t.size(9));
        assertEquals(0, t.size(4));
        assertEquals(4, t.size(15));

        assertEquals(9, t.size(12));
        assertEquals(1, t.size(13));
        assertEquals(0, t.size(14));
        assertEquals(0, t.size(19));
        assertEquals(2, t.size(20));
        assertEquals(2, t.size(21));

        assertEquals(2, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(0, t.size(6));
        assertEquals(2, t.size(7));
        assertEquals(0, t.size(8));

        assertEquals(1, t.get(0));
        assertEquals(7, t.get(1));
        assertEquals(9, t.get(2));
        assertEquals(10, t.get(3));
        assertEquals(12, t.get(4));
        assertEquals(13, t.get(5));
        assertEquals(15, t.get(6));
        assertEquals(20, t.get(7));
        assertEquals(21, t.get(8));
        assertEquals(9, t.size());


    }

    @Test
    public void doubleCascadeRight() {
        Tree t = new Tree();
        t.insert(1);
        t.insert(9);
        t.insert(15);
        t.insert(13);
        t.insert(20);
        t.insert(7);
        t.insert(10);
        t.insert(21);
        t.insert(30);        // double split here

        assertEquals(5, t.size(9));
        assertEquals(0, t.size(4));
        assertEquals(9, t.size(15));

        assertEquals(0, t.size(12));
        assertEquals(2, t.size(13));
        assertEquals(0, t.size(14));
        assertEquals(0, t.size(19));
        assertEquals(1, t.size(20));
        assertEquals(3, t.size(21));
        assertEquals(1, t.size(30));

        assertEquals(2, t.size(1));
        assertEquals(0, t.size(0));
        assertEquals(0, t.size(3));

        assertEquals(0, t.size(6));
        assertEquals(2, t.size(7));
        assertEquals(0, t.size(8));

        assertEquals(1, t.get(0));
        assertEquals(7, t.get(1));
        assertEquals(9, t.get(2));
        assertEquals(10, t.get(3));
        assertEquals(13, t.get(4));
        assertEquals(15, t.get(5));
        assertEquals(20, t.get(6));
        assertEquals(21, t.get(7));
        assertEquals(30, t.get(8));
        assertEquals(9, t.size());


    }


}