package org.example.state;

import org.example.builder.ExpressionBuilder;

public class WaitingForOperatorState implements State{
    @Override
    public void handleNumber(StateManager context, double value) {
        System.out.println("Erro de Sintaxe: Esperava um OPERADOR, mas recebi o número '" + value + "'");
        context.setBuilder(new ExpressionBuilder());
        context.setState(new StartState());
    }

    @Override
    public void handleOperator(StateManager context, char op) {
        System.out.println("Debug (State): WaitingForOperatorState a processar operador " + op);
        context.getBuilder().addOperator(op);
        context.setState(new WaitingForNumberState());
    }
}
