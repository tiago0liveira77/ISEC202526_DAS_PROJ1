package org.example;

import org.example.composite.Expression;
import org.example.flyweight.NumberFactory;
import org.example.composite.Operation;
import org.example.state.StateManager;
import org.example.visitor.ExpressionVisitor;
import org.example.visitor.ReplaceOperatorVisitor;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StateManager manager = new StateManager();
        CalculatorMemory memory = new CalculatorMemory();

        System.out.println("--- Calculadora ---");
        System.out.println("Insira uma expressão matemática com números e operadores (+, -, *, /).");
        System.out.println("Use 'L' para listar, 'Q' para sair.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input> ");
        while (scanner.hasNext()) {
            String input = scanner.next();

            if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equals("=")) {
                Expression finalExpression = manager.finalizeExpression();

                if (finalExpression != null) {
                    memory.add(finalExpression);
                    double result = finalExpression.evaluate();
                    System.out.println("Result> " + result);
                }
            } else if (input.equalsIgnoreCase("L")) {
                System.out.println("--- Expressões na Memória ---");
                List<Expression> all = memory.getAll();
                if (all.isEmpty()) {
                    System.out.println("(Memória vazia)");
                } else {
                    for (int i = 0; i < all.size(); i++) {
                        // Imprime: "0: (1 + 2)"
                        System.out.println(i + ": " + all.get(i).toString());
                    }
                }
            } else if (input.equalsIgnoreCase("R")){
                try {
                    int index = scanner.nextInt();
                    String originalOpStr = scanner.next();
                    String newOpStr = scanner.next();

                    if (originalOpStr.length() != 1 || newOpStr.length() != 1) {
                        System.out.println("Erro: Operadores devem ter 1 caractere.");
                    } else {
                        Expression expression = memory.get(index);

                        if (expression != null) {
                            System.out.println("Debug (Main): A executar 'R' na expressão: " + expression.toString());

                            ExpressionVisitor replacer = new ReplaceOperatorVisitor(originalOpStr.charAt(0), newOpStr.charAt(0));

                            expression.accept(replacer);

                            System.out.println("Debug (Main): Substituição concluída. Nova expressão: " + expression.toString());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Comando 'R' mal formatado. Use R <idx> <op_orig> <op_novo>");
                    if (scanner.hasNextLine()) scanner.nextLine();
                }
            } else if(input.equalsIgnoreCase("C")){
                try {
                    int index1 = scanner.nextInt();
                    int index2 = scanner.nextInt();
                    String opStr = scanner.next();

                    if (opStr.length() != 1) {
                        System.out.println("Erro: Operador deve ter 1 caractere.");
                    } else {
                        Expression exp1 = memory.get(index1);
                        Expression exp2 = memory.get(index2);
                        char op = opStr.charAt(0);

                        if (exp1 != null && exp2 != null) {
                            Expression combinedExpression = new Operation(exp1, exp2, op);

                            System.out.println("Debug (Main): A combinar " + index1 + " e " + index2 + " com '" + op + "'");
                            System.out.println("Debug (Main): Nova expressão: " + combinedExpression);

                            manager.loadFromMemory(combinedExpression);
                            System.out.println("Expressão combinada carregada. Insira '=' para avaliar.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Comando 'C' mal formatado. Use C <idx1> <idx2> <op>");
                    if (scanner.hasNextLine()) scanner.nextLine(); // Limpa a linha
                }
            } else if(input.toUpperCase().startsWith("M")){
                try {
                    String indexStr = input.substring(1);
                    int index = Integer.parseInt(indexStr);

                    Expression exp = memory.get(index);

                    if (exp != null) {
                        manager.loadFromMemory(exp);
                        System.out.println("Expressão " + index + " carregada: " + exp);
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Comando 'M' mal formatado. Use M<indice>, ex: M0");
                }
            } else {
                manager.processToken(input);
            }

            System.out.print("Input> ");
        }

        scanner.close();
        System.out.println("Calculadora terminada.");

    }
}
