package org.example.state;

import org.example.builder.ExpressionBuilder;

public class WaitingForNumberState implements State{
    @Override
    public void handleNumber(StateManager context, double value) {
        System.out.println("Debug (State): WaitingForNumberState a processar número " + value);
        context.getBuilder().addOperand(value);
        context.setState(new WaitingForOperatorState());
    }

    @Override
    public void handleOperator(StateManager context, char op) {
        System.out.println("Erro de Sintaxe: Esperava um NÚMERO, mas recebi o operador '" + op + "'");
        context.setBuilder(new ExpressionBuilder());
        context.setState(new StartState());
    }
}
