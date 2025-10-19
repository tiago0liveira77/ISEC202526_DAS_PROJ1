package org.example;

import org.example.states.CalculatorContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // 1. Cria a instância principal do nosso sistema.
        // O CalculatorContext gere o estado, o builder e o histórico.
        CalculatorContext calculator = new CalculatorContext();

        // 2. Prepara o leitor para o input da consola.
        // O Scanner é uma classe do Java para ler inputs de várias fontes, incluindo o teclado.
        Scanner scanner = new Scanner(System.in);

        showWelcome();

        // 3. Loop infinito para manter a aplicação a correr.
        // O programa só termina se o fecharmos manualmente.
        while (true) {
            // Mostra um prompt para o utilizador.
            System.out.print("Input> ");

            // Lê a linha inteira que o utilizador escreveu.
            String line = scanner.nextLine();

            // Se a linha estiver vazia, ignora e pede novo input.
            if (line.trim().isEmpty()) {
                continue;
            }

            calculator.processCommand(line);

        }
    }

    private static void showWelcome(){
        System.out.println("********************************************************************");
        System.out.println("* *");
        System.out.println("*\t\t\tCalculadora Simples\t\t   *");
        System.out.println("* *");
        System.out.println("********************************************************************");
        System.out.println("* Instruções: Digite uma expressão (e.g., 10 + 5) e use '=' para   *");
        System.out.println("* calcular. Os tokens devem ser separados por espaços.             *");
        System.out.println("* *");
        System.out.println("* Comandos disponíveis:                                            *");
        System.out.println("* L           -> Lista o histórico de expressões.                  *");
        System.out.println("* M<idx>      -> Recupera uma expressão do histórico (e.g., M0).   *");
        System.out.println("* R <idx> <op> <novoOp> -> Substitui um operador (e.g., R 0 + *).  *");
        System.out.println("* C <idx1> <idx2> <op>  -> Combina duas expressões (e.g.,C 0 1 +). *");
        System.out.println("* *");
        System.out.println("********************************************************************");
        System.out.println(); // Linha em branco para separar do primeiro prompt
    }
}
