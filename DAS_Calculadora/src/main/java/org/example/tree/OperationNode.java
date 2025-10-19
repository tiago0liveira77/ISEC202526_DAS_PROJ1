package org.example.tree;

import org.example.interfaces.MathExpression;

public class OperationNode implements MathExpression {
    MathExpression left;
    MathExpression right;
    String operator;

    public OperationNode(MathExpression left, MathExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int calculate() {
        switch (operator){
            case "+":
                return left.calculate() + right.calculate();
            case "*":
                return left.calculate() * right.calculate();
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }
}
