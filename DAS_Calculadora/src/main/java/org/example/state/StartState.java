package org.example.state;

public class StartState implements State{

    @Override
    public void handleNumber(StateManager context, double value) {
        context.getBuilder().addOperand(value);
        context.setState(new WaitingForOperatorState());
    }

    @Override
    public void handleOperator(StateManager context, char op) {
        System.out.println("Erro de Sintaxe: Esperava um NÚMERO, mas recebi o operador '" + op + "'");
    }
}
