package org.example.states;

import org.example.builder.MathExpressionBuilder;
import org.example.handlers.AddHandler;
import org.example.handlers.MultiplyHandler;
import org.example.handlers.SubtractHandler;
import org.example.interfaces.CalculatorState;
import org.example.interfaces.MathExpression;
import org.example.interfaces.OperationHandler;


public class CalculatorContext
{
    private OperationHandler operationHandler;
    private CalculatorState currentState;
    private MathExpressionBuilder builder;

    public CalculatorContext() {
        this.operationHandler = setUpChain();
        this.builder = new MathExpressionBuilder(this.operationHandler);
        this.currentState = new StartState(this, this.builder);

    }

    public void setState(CalculatorState newState){
        this.currentState = newState;
    }

    public MathExpressionBuilder getBuilder() {
        return this.builder;
    }

    public void processToken(String token) {
        // Converte o token para maiúsculas para facilitar o tratamento de comandos
        String upperToken = token.toUpperCase();

        // Tratamento de comandos especiais primeiro
        switch (upperToken) {
            case "=":
                //--> Lógica para finalizar a expressão.
                //--> 1. Obtém a MathExpression final do builder.
                //--> 2. Se a expressão for válida, calcula, imprime e guarda no histórico.
                //--> 3. Limpa o builder com builder.reset().
                //--> 4. Repõe a máquina de estados para o início: this.setState(new StartState(...));
                MathExpression expFinal = this.builder.getExpression();
                System.out.println("Resultado de " + expFinal + " = " + expFinal.calculate());
                this.builder.reset();
                this.currentState = new StartState(this, this.builder);
                break;

            case "L":
                //--> Lógica para listar o histórico de expressões.
                //--> Percorre a lista `history` e imprime cada expressão com o seu índice.
                break;

            // Adiciona aqui 'case' para os outros comandos: 'M', 'R', 'C'
            case "M": //Imprimir expressao atual
                System.out.println(this.builder.getExpression().toString());
                break;

            default:
                // Se não for um comando especial, tenta processar como número ou operador.
                try {
                    // Tenta converter para número
                    int number = Integer.parseInt(token);
                    // Se conseguir, delega para o estado atual.
                    this.currentState.processNumber(number);
                } catch (NumberFormatException e) {
                    // Se não for um número, assume que é um operador.
                    // (Podes adicionar mais validações aqui se quiseres)
                    this.currentState.processOperator(token);
                }
                break;
        }
    }

    private OperationHandler setUpChain(){
        OperationHandler addHandler = new AddHandler();
        OperationHandler subtractHandler = new SubtractHandler();
        OperationHandler multiplyHandler = new MultiplyHandler();

        addHandler.setNext(subtractHandler);
        subtractHandler.setNext(multiplyHandler);

        return addHandler;
    }

}
