package org.example.chainofresponsability;

public class DivideHandler implements OperationHandler {

    private OperationHandler next;

    @Override
    public void setNext(OperationHandler next) {
        this.next = next;
    }

    @Override
    public Double handle(char operator, double left, double right) {

        if (operator == '/') {
            if (right == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return left / right;
        }

        if (this.next != null) {
            return this.next.handle(operator, left, right);
        }

        return null;
    }
}
