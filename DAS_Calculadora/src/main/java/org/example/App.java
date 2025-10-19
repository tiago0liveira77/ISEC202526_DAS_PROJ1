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

        System.out.println("Calculadora Simples. Digite as suas expressões.");
        System.out.println("Use 'L' para listar o histórico. Use '=' para calcular.");

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

            // 4. Divide a linha em "tokens" usando o espaço como separador.
            // Por exemplo, a string "10 + 5 =" torna-se um array: ["10", "+", "5", "="]
            String[] tokens = line.trim().split("\\s+");

            // 5. Envia cada token, um a um, para ser processado pela nossa máquina de estados.
            // A complexidade está toda escondida dentro do método calculator.processToken().
            for (String token : tokens) {
                calculator.processToken(token);
            }
        }
    }
}
