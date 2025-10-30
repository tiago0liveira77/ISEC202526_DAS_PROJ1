package org.example.builder;

import org.example.composite.Expression;
import org.example.composite.Operation;
import org.example.flyweight.NumberFactory;

public class ExpressionBuilder {

    private Expression currentExpression;

    private char operator;

    private boolean waitingForSecondOperand;

    public ExpressionBuilder() {
        this.currentExpression = null;
        this.operator = '\0'; // '\0' é o caractere nulo
        this.waitingForSecondOperand = false;
    }

    public void addOperand(double value){

        Expression number = NumberFactory.getNumber(value);

        //Significa que é o primeiro número
        if(this.currentExpression == null) {
            this.currentExpression = number;
            System.out.println("Debug (Builder): Adicionado o primeiro operando: " + value);
        } else if(this.waitingForSecondOperand){
            System.out.println("Debug (Builder): Segundo operando (" + value + ") adicionado. A criar Operation...");
            this.currentExpression = new Operation(this.currentExpression, number, this.operator);
            this.waitingForSecondOperand = false; // Já não estamos à espera
        }else{
            throw new IllegalStateException("Erro de lógica do Builder: Recebi um operando quando não estava à espera.");
        }
    }

    public void addOperator(char operator){

        if (this.currentExpression == null) {
            // Erro: não podemos começar com um operador (ex: "+ 1")
            throw new IllegalStateException("Erro de lógica do Builder: Recebi um operador sem um operando primeiro.");
        }
        if (this.waitingForSecondOperand) {
            // Erro: dois operadores seguidos (ex: "1 + *")
            throw new IllegalStateException("Erro de lógica do Builder: Recebi um operador quando estava à espera de um operando.");
        }
        this.operator = operator;
        this.waitingForSecondOperand = true;
        System.out.println("Debug (Builder): Operador adicionado: " + operator);
    }

    public Expression getResult() {
        if (waitingForSecondOperand) {
            throw new IllegalStateException("Erro: Expressão incompleta. Falta o segundo operando.");
        }

        return this.currentExpression;
    }

    public void setInitialExpression(Expression exp) {
        // Se já estávamos a construir algo, limpamos
        if (this.currentExpression != null || this.waitingForSecondOperand) {
            System.out.println("Debug (Builder): Builder foi resetado antes de carregar da memória.");
            this.operator = '\0'; // '\0' é o caractere nulo
        }

        this.currentExpression = exp;
        this.waitingForSecondOperand = false;

        System.out.println("Debug (Builder): Expressão " + exp.toString() + " carregada da memória.");
    }

}
