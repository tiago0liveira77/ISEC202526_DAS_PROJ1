package org.example.composite;

import org.example.visitor.ExpressionVisitor;

//Representa um número na expressão
public class Number implements Expression{

    private double value;

    public Number(double value) {
        this.value = value;
    }

    //Retorna o valor do número
    @Override
    public double evaluate() {
        System.out.println("Debug (Number): a avaliar... vou retornar " + this.value);
        return this.value;
    }

    //Retorna o número como string
    public String toString(){
       return String.format("%s", this.value);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

}
