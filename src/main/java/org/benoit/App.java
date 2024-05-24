package org.benoit;

public class App {
    static final Tree.Leaf l = new Tree.Leaf();
    static final Tree.Branch tb = new Tree.Branch("a",
            new Tree.Branch("b", l, l),
            new Tree.Branch("c",
                    new Tree.Branch("d", l, l),
                    new Tree.Branch("e", l, l)));

    public static void main(String[] args) {
        System.out.println(tb);
        Tree t = new Tree();
        System.out.println(t.addId(tb));

    }
}
