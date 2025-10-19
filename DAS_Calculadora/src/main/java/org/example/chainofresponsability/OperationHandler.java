package org.example.chainofresponsability;

public interface OperationHandler {

    void setNext(OperationHandler next);
    Double handle(char operator, double left, double right);
}
