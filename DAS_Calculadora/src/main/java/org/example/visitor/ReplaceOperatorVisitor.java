package org.example.visitor;

import org.example.composite.Number;
import org.example.composite.Operation;

public class ReplaceOperatorVisitor implements ExpressionVisitor{

    private char originalOp;
    private char newOp;

    public ReplaceOperatorVisitor(char originalOp, char newOp) {
        this.originalOp = originalOp;
        this.newOp = newOp;
    }

    @Override
    public void visit(Number number) {
        System.out.println("Debug (Visitor): A visitar um Number. Nada a fazer.");
    }

    @Override
    public void visit(Operation operation) {
        System.out.println("Debug (Visitor): A visitar uma Operation com o operador: " + operation.getOperator());
        if (operation.getOperator() == this.originalOp) {
            System.out.println("Debug (Visitor): ENCONTRADO! A trocar " + this.originalOp + " por " + this.newOp);
            operation.setOperator(this.newOp);
        }
    }
}
