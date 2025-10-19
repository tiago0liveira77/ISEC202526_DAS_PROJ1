package org.example.states;

import org.example.builder.MathExpressionBuilder;
import org.example.interfaces.CalculatorState;

public class WaitingForOperatorState implements CalculatorState {
    private MathExpressionBuilder builder;
    private CalculatorContext context;

    public WaitingForOperatorState(CalculatorContext context, MathExpressionBuilder builder) {
        this.context = context;
        this.builder = builder;
    }

    @Override
    public void processNumber(int number) {
        System.out.println("ERROR - ESPERADO: OPERADOR");
    }

    @Override
    public void processOperator(String op) {
        builder.setOperator(op);
        context.setState(new StartState(this.context, this.builder));
    }
}
