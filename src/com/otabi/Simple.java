package com.otabi;

public class Simple implements Strategy {
    @SuppressWarnings("ForLoopReplaceableByForEach")
    @Override
    public long solution(int[] a) {
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                sum += Integer.parseInt(String.valueOf(a[i]) + String.valueOf(a[j]));
            }
        }
        return sum;
    }
}
