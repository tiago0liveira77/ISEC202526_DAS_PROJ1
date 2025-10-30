package org.example.state;

/**
        * Descrição da Máquina de Estados (Padrão State)
        *
        * A máquina tem 3 estados:
        *
        * 1. StartState:
        * - O estado inicial (builder está vazio).
        * - SÓ ACEITA: um Número (ou uma expressão da Memória 'M'/'C').
        * - SE RECEBER NÚMERO: guarda-o no builder e transita para 'WaitingForOperatorState'.
        * - SE RECEBER OPERADOR: dá erro.
        *
        * 2. WaitingForOperatorState:
        * - O estado em que estamos depois de ter um operando válido (ex: "1" ou "(1+2)").
        * - SÓ ACEITA: um Operador (ou o comando '=' para finalizar).
        * - SE RECEBER OPERADOR: guarda-o no builder e transita para 'WaitingForNumberState'.
        * - SE RECEBER NÚMERO: dá erro (ex: "1 2").
        *
        * 3. WaitingForNumberState:
        * - O estado em que estamos depois de ter um operador (ex: "1 +").
        * - SÓ ACEITA: um Número.
        * - SE RECEBER NÚMERO: guarda-o no builder (que o combina com o anterior) e
        * transita de VOLTA para 'WaitingForOperatorState'.
        * - SE RECEBER OPERADOR: dá erro (ex: "1 + *").
        *
        * O 'CalculatorEngine' (o Contexto) guarda o estado atual e delega-lhe
        * o processamento de cada token.
        */

public interface State {

    void handleNumber(StateManager context, double value);
    void handleOperator(StateManager context, char op);
}
