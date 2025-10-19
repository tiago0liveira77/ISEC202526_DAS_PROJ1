package org.example.tree;

import org.example.interfaces.MathExpression;
import org.example.interfaces.OperationHandler;

public class OperationNode implements MathExpression {
    MathExpression left;
    MathExpression right;
    String operator;
    OperationHandler operationHandler;

    public OperationNode(MathExpression left, MathExpression right, String operator, OperationHandler operationHandler) {
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.operationHandler = operationHandler;
    }

    @Override
    public int calculate() {
        int leftValue = this.left.calculate();
        int rightValue = this.right.calculate();

        return this.operationHandler.handleRequest(this.operator,leftValue, rightValue);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }
}
