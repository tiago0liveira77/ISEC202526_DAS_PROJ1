package org.example.visitor;

import org.example.composite.Number;
import org.example.composite.Operation;

public interface ExpressionVisitor {

    void visit(Number number);
    void visit(Operation operation);
}
