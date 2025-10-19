package org.example.visitor;

import org.example.interfaces.Visitor;
import org.example.tree.NumberLeaf;
import org.example.tree.OperationNode;

public class OpReplaceVisitor implements Visitor {
    private String originalOp;
    private String newOp;

    public OpReplaceVisitor(String originalOp, String newOp) {
        this.originalOp = originalOp;
        this.newOp = newOp;
    }

    @Override
    public void visit(OperationNode node) {
        if(node.getOperator().equals(this.originalOp)){
            node.setOperator(this.newOp);
        }
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    @Override
    public void visit(NumberLeaf leaf) {
        //System.out.println("ERRO - Nao e possivel alterar operador de NumberLeaf");
    }
}
