package org.example.interfaces;

import org.example.tree.NumberLeaf;
import org.example.tree.OperationNode;

public interface Visitor {
    void visit(OperationNode node);
    void visit(NumberLeaf leaf);
}
