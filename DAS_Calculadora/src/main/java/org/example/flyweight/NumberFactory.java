package org.example.flyweight;

import java.util.HashMap;
import java.util.Map;
import org.example.composite.Number;

//Serve como cache para não serem criados números repetidos
public class NumberFactory {

    private static Map<Double, Number> numberCache = new HashMap<>();

    public static Number getNumber(double value) {

        if (!numberCache.containsKey(value)) {
            numberCache.put(value, new Number(value));
            System.out.println("Debug (Flyweight): Criei um NOVO objeto Number para: " + value);
        }else{
            System.out.println("Debug (Flyweight): Reutilizei um objeto Number existente para: " + value);
        }
        return numberCache.get(value);
    }
}
