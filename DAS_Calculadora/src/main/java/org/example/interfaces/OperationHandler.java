package org.example.interfaces;

public abstract class OperationHandler {
    protected OperationHandler nextHandler;

    public void setNext(OperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract int handleRequest(String operator, int a, int b);
}
