package org.example.states;

import org.example.builder.MathExpressionBuilder;
import org.example.handlers.AddHandler;
import org.example.handlers.MultiplyHandler;
import org.example.handlers.SubtractHandler;
import org.example.interfaces.CalculatorState;
import org.example.interfaces.MathExpression;
import org.example.interfaces.OperationHandler;
import org.example.interfaces.Visitor;
import org.example.visitor.OpReplaceVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CalculatorContext {
    private OperationHandler operationHandler;
    private CalculatorState currentState;
    private MathExpressionBuilder builder;
    private List<MathExpression> listHistoricOps;

    public CalculatorContext() {
        this.operationHandler = setUpChain();
        this.builder = new MathExpressionBuilder(this.operationHandler);
        this.currentState = new StartState(this, this.builder);
        this.listHistoricOps = new ArrayList<>();
    }

    public void setState(CalculatorState newState) {
        this.currentState = newState;
    }

    public MathExpressionBuilder getBuilder() {
        return this.builder;
    }

    public void processCommand(String line) {
        String[] tokens = line.trim().split("\\s+");
        if(tokens.length == 0){
            return;
        }

        String upperToken = tokens[0].toUpperCase();

        // Tratamento de comandos especiais primeiro
        if (upperToken.startsWith("M")) {
            String indexString = upperToken.substring(1);
            int index = Integer.parseInt(indexString);
            if (index >= 0 && index < this.listHistoricOps.size()) {
                MathExpression expressao = this.listHistoricOps.get(index);
                System.out.println(index + ": " + expressao);
                this.builder.setExpression(this.listHistoricOps.get(index));
                this.currentState = new WaitingForOperatorState(this, this.builder);
            } else {
                System.out.println("ERROR - INDEX NAO EXISTE");
            }
        } else if (upperToken.startsWith("R")) { //alterar operador
            if (tokens.length != 4) {
                System.out.println("Erro: Formato inválido. Use: R <index> <opOriginal> <opNovo>");
            }
            try {
                int index = Integer.parseInt(tokens[1]);
                String originalOp = tokens[2];
                String newOp = tokens[3];
                if (index >= 0 && index < this.listHistoricOps.size()) {
                    MathExpression expressionToModify = this.listHistoricOps.get(index);
                    Visitor visitor = new OpReplaceVisitor(originalOp, newOp);
                    expressionToModify.accept(visitor);
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro: O índice deve ser um número.");
            }
        } else if (upperToken.equals("L")) { //Listar operaçoes
            showHistoricList();
        } else { //Construir expressao+
            for (String token : tokens) {
                processToken(token);
            }

        }
    }

    private void processToken(String token){
        if (token.equals("=")) {
            //--> Lógica para finalizar a expressão.
            //--> 1. Obtém a MathExpression final do builder.
            //--> 2. Se a expressão for válida, calcula, imprime e guarda no histórico.
            //--> 3. Limpa o builder com builder.reset().
            //--> 4. Repõe a máquina de estados para o início: this.setState(new StartState(...));
            MathExpression expFinal = this.builder.getExpression();
            System.out.println("Resultado de " + expFinal + " = " + expFinal.calculate());
            this.listHistoricOps.add(expFinal);
            this.builder.reset();
            this.currentState = new StartState(this, this.builder);
        } else {
            try {
                int number = Integer.parseInt(token);
                this.currentState.processNumber(number);
            } catch (NumberFormatException e) {
                this.currentState.processOperator(token);
            }
        }

    }

    private OperationHandler setUpChain() {
        OperationHandler addHandler = new AddHandler();
        OperationHandler subtractHandler = new SubtractHandler();
        OperationHandler multiplyHandler = new MultiplyHandler();

        addHandler.setNext(subtractHandler);
        subtractHandler.setNext(multiplyHandler);

        return addHandler;
    }

    private void showHistoricList() {
        //Iterator<MathExpression> it = this.listHistoricOps.iterator();
        //while(it.hasNext()) {
        //     System.out.println(it.next());
        //}
        for (int i = 0; i < listHistoricOps.size(); i++) {
            MathExpression expression = listHistoricOps.get(i);
            System.out.println(i + ": " + expression);
        }
    }

}
