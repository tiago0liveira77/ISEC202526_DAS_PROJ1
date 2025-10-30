package org.example.chainofresponsability;

public class SubtractionHandler implements OperationHandler {

    private OperationHandler next;

    @Override
    public void setNext(OperationHandler next) {
        this.next = next;
    }

    @Override
    public Double handle(char operator, double left, double right) {

        if (operator == '-') {
            return left - right;
        }

        if (this.next != null) {
            return this.next.handle(operator, left, right);
        }

        return null;
    }
}
