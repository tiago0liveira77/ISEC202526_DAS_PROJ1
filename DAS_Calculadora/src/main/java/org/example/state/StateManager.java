package org.example.state;

import org.example.builder.ExpressionBuilder;
import org.example.composite.Expression;
import org.example.visitor.ExpressionVisitor;
import org.example.visitor.ReplaceOperatorVisitor;

public class StateManager {

    private State currentState;
    private ExpressionBuilder builder;

    public StateManager() {
        this.builder = new ExpressionBuilder();
        this.currentState = new StartState();
        System.out.println("Debug (Engine): Motor criado. Estado inicial: StartState");
    }

    public void setState(State newState) {
        this.currentState = newState;
    }

    public ExpressionBuilder getBuilder() {
        return this.builder;
    }

    public void setBuilder(ExpressionBuilder builder) {
        this.builder = builder;
    }

    public void processToken(String token) {
        try {
            double value = Double.parseDouble(token);
            this.currentState.handleNumber(this, value);
        } catch (NumberFormatException e) {
            if (token.length() == 1) {
                char op = token.charAt(0);
                this.currentState.handleOperator(this, op);
            } else {
                System.out.println("Erro: Token '" + token + "' desconhecido.");
            }
        }
    }

    public Expression finalizeExpression() {
        if (!(this.currentState instanceof WaitingForOperatorState)) {
            System.out.println("Erro de Sintaxe: Expressão incompleta. Não pode calcular.");

            this.builder = new ExpressionBuilder();
            this.setState(new StartState()); // Volta ao início
            return null;
        }

        Expression finalExpression = this.builder.getResult();
        System.out.println("Debug (Engine): Expressão finalizada. Resultado obtido.");

        this.builder = new ExpressionBuilder();
        this.setState(new StartState());
        System.out.println("Debug (Engine): A voltar ao StartState.");

        return finalExpression;
    }

    public void loadFromMemory(Expression exp) {
        this.builder.setInitialExpression(exp);
        this.setState(new WaitingForOperatorState());
        System.out.println("Debug (Engine): A carregar da memória. A mudar para WaitingForOperatorState.");
    }

}
