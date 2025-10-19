package org.example.states;

import org.example.builder.MathExpressionBuilder;
import org.example.interfaces.CalculatorState;

public class StartState implements CalculatorState {
    private MathExpressionBuilder builder;
    private CalculatorContext context;

    public StartState(CalculatorContext context, MathExpressionBuilder builder) {
        this.context = context;
        this.builder = builder;
    }

    @Override
    public void processNumber(int number) {
        this.builder.addValue(number);

        //Atualizar state
        context.setState(new WaitingForOperatorState(this.context, this.builder));
    }

    @Override
    public void processOperator(String op) {
        //Validar se e um operador
        System.out.println("ERROR - ESPERADO: Numero");
    }
}
