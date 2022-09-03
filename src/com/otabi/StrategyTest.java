package com.otabi;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {
    Strategy strategy;

    static ArrayList<Class<? extends Strategy>> strategies = new ArrayList<>();
    static {
        strategies.add(Simple.class);
        strategies.add(Optimal.class);
        strategies.add(OptimalStreams.class);
    }

    @Test
    void solutionTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<? extends Strategy> strategyClass : strategies) {
            strategy = strategyClass.getDeclaredConstructor().newInstance();
            TestArrays.tests.forEach((key, value) ->
                    assertEquals(key, strategy.solution(value)));
        }
    }
}