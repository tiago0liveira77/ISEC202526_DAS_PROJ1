package org.example.composite;

import org.example.visitor.ExpressionVisitor;

//Interface que as folhas e os ramos vão implementar
public interface Expression {

    //Calcula o valor numérico da expressão
    double evaluate();
    //Retorna a representação em string da expressão (Comando L)
    String toString();

    // A "porta de entrada" para o Visitor
    void accept(ExpressionVisitor visitor);
}
