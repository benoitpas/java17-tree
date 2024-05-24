package org.benoit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.AbstractMap;


public class TreeTest {

    // Could be added to 'Tree' to simplify the code and made public to
    // have it accessible from unit tests. As it doesn't have side effects,
    // it will not create issues to have it public.
    AbstractMap.SimpleEntry<Integer, String> pair(Integer i, String v) {
        return new AbstractMap.SimpleEntry<>(i, v);
    }

    @Test
    public void testAddId() {
        Tree.Leaf l = new Tree.Leaf();
        Tree.Branch expectedtb = new Tree.Branch(pair(4, "a"),
                new Tree.Branch(pair(0, "b"), l, l),
                new Tree.Branch(pair(3, "c"),
                        new Tree.Branch(pair(1, "d"), l, l),
                        new Tree.Branch(pair(2, "e"), l, l)));

        Tree t = new Tree();
        assertEquals(expectedtb, t.addId(App.tb));
    }
}
