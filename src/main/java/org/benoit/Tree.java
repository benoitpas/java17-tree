package org.benoit;

import java.util.AbstractMap;

public class Tree<T> {
    // The Tree class should be made a singleton but doesn't seem to work with the type parameters
    public interface INode<T> {
    }
    public record Branch<T>(T value, INode<T> left, INode<T> right) implements INode<T> {
    }

    public record Leaf<T>() implements INode<T> {
    }

    // Move declaration of Leaf/Branch here

    public INode<AbstractMap.SimpleEntry<Integer,T>> addId(INode<T> node) {
        return addId(node,0).getValue();
    }
    
    AbstractMap.SimpleEntry<Integer,INode<AbstractMap.SimpleEntry<Integer,T>>>
        addId(INode<T> node, Integer start) {
        INode<AbstractMap.SimpleEntry<Integer,T>> l = new Leaf<>();
        var r = new AbstractMap.SimpleEntry<>(start, l);
        // Waiting for switch on types (https://openjdk.java.net/jeps/8213076)
        // but still nice to use JEP 394 (pattern matching for instanceof)
        if (node instanceof Branch<T> b) {
            var newLeft = addId(b.left(), start);
            var newRight = addId(b.right(), newLeft.getKey());
            var newBranch = new Branch<>(
                    new AbstractMap.SimpleEntry<>(newRight.getKey(), b.value()),
                    newLeft.getValue(),
                    newRight.getValue()
            );
            r = new AbstractMap.SimpleEntry<>(newRight.getKey() + 1, newBranch);
        }
        return r;
    }
}
