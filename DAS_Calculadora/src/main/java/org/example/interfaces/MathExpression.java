package org.example.interfaces;

public interface MathExpression {
    int calculate();
    String toString();
    void accept(Visitor visitor);
}
