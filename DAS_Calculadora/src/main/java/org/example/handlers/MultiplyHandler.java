package org.example.handlers;

import org.example.interfaces.OperationHandler;

public class MultiplyHandler extends OperationHandler {
    @Override
    public int handleRequest(String operator, int a, int b) {
        if (operator.equals("*")) {
            return a * b;
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(operator, a, b);
        }
        return 0;
    }
}
