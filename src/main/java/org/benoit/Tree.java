package org.benoit;

import java.util.AbstractMap;

public class Tree<T> {
    // The Tree class should be made a singleton but doesn't with the type parameters
    // Using a sealed class (introduced in Java17)
    public sealed interface INode<T> permits Branch, Leaf {
    }
    public record Branch<T>(T value, INode<T> left, INode<T> right) implements INode<T> {
    }
    public record Leaf<T>() implements INode<T> {
    }

    public INode<AbstractMap.SimpleEntry<Integer,T>> addId(INode<T> node) {
        return addId(node,0).getValue();
    }

    // Because it is not possible to declare a variable in an expression in Java, I had to write this provate class
    // In Scala the code could have put in the case statement
    private AbstractMap.SimpleEntry<Integer,INode<AbstractMap.SimpleEntry<Integer,T>>> newBranch(Branch<T> b, Integer start)  {
        var newLeft = addId(b.left(), start);
        var newRight = addId(b.right(), newLeft.getKey());
        var newBranch = new Branch<>(
            new AbstractMap.SimpleEntry<>(newRight.getKey(), b.value()),
                newLeft.getValue(),
                newRight.getValue());
        return new AbstractMap.SimpleEntry<>(newRight.getKey() + 1, newBranch);
    }

    // Here we also need to create a function because of the variable leaf. Also because we need to specify the type of
    // the function, the syntax is not very elegant.
    private AbstractMap.SimpleEntry<Integer, INode<AbstractMap.SimpleEntry<Integer,T>>> newLeaf(Integer start) {
        final INode<AbstractMap.SimpleEntry<Integer,T>> leaf = new Leaf<>();
        return new AbstractMap.SimpleEntry<>(start, leaf);
    }

    // Replace AbstractMap.SimpleEntry with record
    AbstractMap.SimpleEntry<Integer,INode<AbstractMap.SimpleEntry<Integer,T>>>
        addId(INode<T> node, Integer start) {
        // The switch starts to look good !
        return switch(node) {
            case Branch<T> b -> newBranch(b, start);
            default -> newLeaf(start);
        };
    }
}
