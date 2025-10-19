package org.example;

import org.example.composite.Expression;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMemory {

    private final List<Expression> storedExpressions;

    public CalculatorMemory() {
        this.storedExpressions = new ArrayList<>();
    }

    public Expression get(int index) {
        if (index < 0 || index >= storedExpressions.size()) {
            System.out.println("Erro: Índice de memória " + index + " inválido.");
            return null;
        }
        return this.storedExpressions.get(index);
    }

    public void add(Expression exp) {
        this.storedExpressions.add(exp);
    }

    public List<Expression> getAll() {
        return new ArrayList<>(this.storedExpressions);
    }

    public int getSize() {
        return this.storedExpressions.size();
    }
}
