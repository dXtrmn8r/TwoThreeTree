import org.junit.Test;

import static org.junit.Assert.*;


public class TwoThreeTreeTests {

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
    public void doubleCascadeLeftWithDuplicates() {
        Tree t = new Tree();
        assertTrue(t.insert(1));
        assertTrue(t.insert(9));
        assertTrue(t.insert(15));
        assertTrue(t.insert(13));
        assertTrue(t.insert(20));
        assertTrue(t.insert(7));
        assertTrue(t.insert(4));        // double split here
        assertFalse(t.insert(1));
        assertFalse(t.insert(9));
        assertFalse(t.insert(15));
        assertFalse(t.insert(1));
        assertFalse(t.insert(9));
        assertFalse(t.insert(15));
        assertFalse(t.insert(13));
        assertFalse(t.insert(20));
        assertFalse(t.insert(7));
        assertFalse(t.insert(4));
        assertFalse(t.insert(13));
        assertFalse(t.insert(20));
        assertFalse(t.insert(7));
        assertFalse(t.insert(4));

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

    @Test
    public void increasingTest() {

        Tree t = new Tree();
        t.insert(100);
        assertEquals(1, t.size(100));
        assertEquals(1, t.size());

        t.insert(110);
        assertEquals(2, t.size(100));
        assertEquals(2, t.size(110));
        assertEquals(2, t.size());

        t.insert(120);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(3, t.size());

        t.insert(130);
        assertEquals(1, t.size(100));
        assertEquals(4, t.size(110));
        assertEquals(2, t.size(120));
        assertEquals(2, t.size(130));
        assertEquals(4, t.size());

        t.insert(140);
        assertEquals(1, t.size(100));
        assertEquals(5, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(5, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(5, t.size());

        t.insert(150);
        assertEquals(1, t.size(100));
        assertEquals(6, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(6, t.size(130));
        assertEquals(2, t.size(140));
        assertEquals(2, t.size(150));
        assertEquals(6, t.size());

        t.insert(160);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(7, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(3, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(7, t.size());

        t.insert(170);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(8, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(4, t.size(150));
        assertEquals(2, t.size(160));
        assertEquals(2, t.size(170));
        assertEquals(8, t.size());

        t.insert(170);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(8, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(4, t.size(150));
        assertEquals(2, t.size(160));
        assertEquals(2, t.size(170));
        assertEquals(8, t.size());

        t.insert(180);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(9, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(5, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(5, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(9, t.size());

        t.insert(180);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(9, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(5, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(5, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(9, t.size());

        t.insert(190);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(10, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(6, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(6, t.size(170));
        assertEquals(2, t.size(180));
        assertEquals(2, t.size(190));
        assertEquals(10, t.size());

        t.insert(190);
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(10, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(6, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(6, t.size(170));
        assertEquals(2, t.size(180));
        assertEquals(2, t.size(190));
        assertEquals(10, t.size());

        for (int i = 0; i < 21; i++) {
            t.insert(10 * (i + 20));
        }

        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(7, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(3, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(15, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(7, t.size(210));
        assertEquals(1, t.size(220));
        assertEquals(3, t.size(230));
        assertEquals(1, t.size(240));
        assertEquals(31, t.size(250));
        assertEquals(1, t.size(260));
        assertEquals(3, t.size(270));
        assertEquals(1, t.size(280));
        assertEquals(7, t.size(290));
        assertEquals(1, t.size(300));
        assertEquals(3, t.size(310));
        assertEquals(1, t.size(320));
        assertEquals(15, t.size(330));
        assertEquals(1, t.size(340));
        assertEquals(3, t.size(350));
        assertEquals(1, t.size(360));
        assertEquals(7, t.size(370));
        assertEquals(1, t.size(380));
        assertEquals(3, t.size(390));
        assertEquals(1, t.size(400));
        assertEquals(31, t.size());
    }

    private void finalTwoTreeTree(Tree t) {
        assertEquals(1, t.size(100));
        assertEquals(3, t.size(110));
        assertEquals(1, t.size(120));
        assertEquals(11, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(3, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(11, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(11, t.size());
    }

    @Test
    public void decreasingTest() {
        Tree t = new Tree();
        t.insert(200);
        assertEquals(1, t.size(200));
        assertEquals(1, t.size());

        t.insert(190);
        assertEquals(2, t.size(190));
        assertEquals(2, t.size(200));
        assertEquals(2, t.size());

        t.insert(180);
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(3, t.size());

        t.insert(170);
        assertEquals(2, t.size(170));
        assertEquals(2, t.size(180));
        assertEquals(4, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(4, t.size());

        t.insert(160);
        assertEquals(1, t.size(160));
        assertEquals(5, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(5, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(5, t.size());

        t.insert(150);
        assertEquals(2, t.size(150));
        assertEquals(2, t.size(160));
        assertEquals(6, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(6, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(6, t.size());

        t.insert(140);
        assertEquals(1, t.size(140));
        assertEquals(3, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(7, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(7, t.size());

        t.insert(130);
        assertEquals(2, t.size(130));
        assertEquals(2, t.size(140));
        assertEquals(4, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(8, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(8, t.size());

        t.insert(130);
        assertEquals(2, t.size(130));
        assertEquals(2, t.size(140));
        assertEquals(4, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(8, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(8, t.size());

        t.insert(120);
        assertEquals(1, t.size(120));
        assertEquals(5, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(5, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(9, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(9, t.size());

        t.insert(120);
        assertEquals(1, t.size(120));
        assertEquals(5, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(5, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(9, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(9, t.size());

        t.insert(110);
        assertEquals(2, t.size(110));
        assertEquals(2, t.size(120));
        assertEquals(6, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(6, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(10, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(10, t.size());

        t.insert(110);
        assertEquals(2, t.size(110));
        assertEquals(2, t.size(120));
        assertEquals(6, t.size(130));
        assertEquals(1, t.size(140));
        assertEquals(6, t.size(150));
        assertEquals(1, t.size(160));
        assertEquals(10, t.size(170));
        assertEquals(1, t.size(180));
        assertEquals(3, t.size(190));
        assertEquals(1, t.size(200));
        assertEquals(10, t.size());

        t.insert(100);
        finalTwoTreeTree(t);
    }

}