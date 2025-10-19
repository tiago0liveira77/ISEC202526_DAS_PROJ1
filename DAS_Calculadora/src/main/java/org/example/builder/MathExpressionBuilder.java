package org.example.builder;

import org.example.factory.NumberFactory;
import org.example.interfaces.MathExpression;
import org.example.tree.OperationNode;

public class MathExpressionBuilder {
    private MathExpression actualExpression;
    private String operator;
    private NumberFactory numberFactory;

    public MathExpressionBuilder() {
        this.numberFactory = new NumberFactory();
    }

    public void addValue(int value){
        MathExpression newValue = this.numberFactory.getValue(value);
        if(this.actualExpression == null){
            this.actualExpression = newValue;
        } else{
            // LÓGICA: Se a 'expressaoAtual' NÃO é nula, significa que já temos
            // o lado esquerdo da operação e também um 'operadorPendente'.
            // Este número é o lado direito. É hora de montar a OperationNode.

            // AÇÃO: Cria uma nova OperationNode. As peças são:
            // Lado Esquerdo -> a 'expressaoAtual' que já tinhas.
            // Lado Direito  -> o 'novoOperando' que acabaste de receber.
            // Operador      -> o 'operadorPendente' que guardaste antes.
            MathExpression newOp = new OperationNode(this.actualExpression, newValue, this.operator);
            this.actualExpression = newOp;
            this.operator = null;
        }
    }

    public MathExpression getExpression() {
        return this.actualExpression;
    }

    public void setOperator(String op){
        this.operator = op;
    }

    public void reset(){
        actualExpression = null;
        operator = null;
    }
}
