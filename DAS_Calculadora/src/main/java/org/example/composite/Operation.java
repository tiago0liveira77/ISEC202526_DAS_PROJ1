package org.example.composite;

import org.example.chainofresponsability.OperationChainFactory;
import org.example.chainofresponsability.OperationHandler;
import org.example.visitor.ExpressionVisitor;

//Representa uma operação na expressão
public class Operation implements Expression{

    private Expression left;
    private Expression right;
    private char operator;

    private static OperationHandler operationChain = OperationChainFactory.getChain();

    public Operation(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    @Override
    public double evaluate() {
        System.out.println("Debug (Operation): a avaliar " + this.operator + "...");

        double leftValue = left.evaluate();
        double rightValue = right.evaluate();

        Double result = operationChain.handle(this.operator, leftValue, rightValue);

        if (result == null) {
            // Se o resultado for 'null', significa que nenhum
            // handler na cadeia sabia tratar deste operador.
            throw new IllegalStateException("Operador desconhecido ou não suportado: " + this.operator);
        }

        return result;
    }

    public String toString(){
       return String.format("(%s %s %s)", left.toString(), operator, right.toString());
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
        this.left.accept(visitor);
        this.right.accept(visitor);
    }

    public Expression getLeft() { return left; }
    public Expression getRight() { return right; }
    public char getOperator() { return operator; }

    public void setOperator(char operator) {
        this.operator = operator;
    }


}
