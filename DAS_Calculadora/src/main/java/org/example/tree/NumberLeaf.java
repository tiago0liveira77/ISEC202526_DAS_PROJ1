package org.example.tree;

import org.example.interfaces.MathExpression;
import org.example.interfaces.Visitor;

public class NumberLeaf implements MathExpression {
    private int value;

    public NumberLeaf(int value) {
        this.value = value;
    }

    @Override
    public int calculate() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
