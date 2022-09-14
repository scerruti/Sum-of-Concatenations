package com.otabi;

import java.util.HashMap;

public class Optimal implements Strategy {
    @SuppressWarnings("ForLoopReplaceableByForEach")
    @Override
    public long solution(int[] a) {
        long sum = 0;
        long sumOfValues = 0;
        int[] nDigitNumbers = new int[10];

        for (int i = 0; i < a.length; i++) {
            sumOfValues += a[i];
            int numDigits = (int) (Math.log10(a[i]) + 1);
            int count = nDigitNumbers.getOrDefault(numDigits, 0);
            nDigitNumbers[numDigits] += 1;
        }

        sum += sumOfValues * a.length;

        for (int numDigits = 0; numDigits <= 10; numDigits++) {
            int count = nDigitNumbers[numDigits];
            sum += sumOfValues * count * Math.pow(10, exponent);
        }

        return sum;
    }
}
