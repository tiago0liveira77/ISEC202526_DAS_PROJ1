package org.example.factory;

import org.example.interfaces.MathExpression;
import org.example.tree.NumberLeaf;

import java.util.HashMap;
import java.util.Map;

public class NumberFactory {
    private Map<Integer, NumberLeaf> valuesInCache = new HashMap<>();

    public NumberLeaf getValue(int value){
        if (!valuesInCache.containsKey(value))
            valuesInCache.put(value, new NumberLeaf(value));
        return valuesInCache.get(value);

    }
}
