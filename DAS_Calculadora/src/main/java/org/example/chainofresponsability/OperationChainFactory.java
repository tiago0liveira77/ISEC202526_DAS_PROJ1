package org.example.chainofresponsability;

public class OperationChainFactory {

    private static OperationHandler chainStart;

    private static void buildChain() {

        OperationHandler add = new AddHandler();
        OperationHandler sub = new SubtractionHandler();
        OperationHandler mult = new MultiplyHandler();
        OperationHandler div = new DivideHandler();

        add.setNext(sub);
        sub.setNext(mult);
        mult.setNext(div);

        chainStart = add;
    }

    public static OperationHandler getChain() {
        if (chainStart == null) {
            buildChain();
        }
        return chainStart;
    }
}
