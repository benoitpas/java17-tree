package org.benoit;

public class Tree<T> {
    // The Tree class should be made a singleton but it is not possible because of the type parameters
    // Using a sealed class (introduced in Java17)
    public sealed interface INode<T> permits Branch, Leaf {
    }
    public record Branch<T>(T value, INode<T> left, INode<T> right) implements INode<T> {
    }
    public record Leaf<T>() implements INode<T> {
    }

    public record WithId<T>(Integer id, T value) {
    }

    public INode<WithId<T>> addId(INode<T> node) {
        return addId(node,0).value();
    }

    // Because it is not possible to declare a variable in an expression in Java, I had to write this private class
    // In Scala the code could have put in the case statement
    private WithId<INode<WithId<T>>> newBranch(Branch<T> b, Integer start)  {
        var newLeft = addId(b.left(), start);
        var newRight = addId(b.right(), newLeft.id());
        var newBranch = new Branch<>(
            new WithId(newRight.id(), b.value()),
                newLeft.value(),
                newRight.value());
        return new WithId(newRight.id() + 1, newBranch);
    }

    // Here we also need to create a function because of the variable leaf. Also because we need to specify the type of
    // the function, the syntax is not very elegant.
    private WithId<INode<WithId<T>>> newLeaf(Integer start) {
        final INode<WithId<T>> leaf = new Leaf<>();
        return new WithId(start, leaf);
    }

    // Replaced AbstractMap.SimpleEntry with record
    WithId<INode<WithId<T>>>
        addId(INode<T> node, Integer start) {
        // The switch starts to look good !
        return switch(node) {
            case Branch<T> b -> newBranch(b, start);
            default -> newLeaf(start);
        };
    }
}
