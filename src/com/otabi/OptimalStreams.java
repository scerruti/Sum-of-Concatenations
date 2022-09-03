package com.otabi;

import java.util.Arrays;
import java.util.stream.IntStream;

public class OptimalStreams implements Strategy {
    @Override
    public long solution(int[] a) {
        long sum;
        long sumOfValues = Arrays.stream(a).asLongStream().reduce(0L, Long::sum);

        long[] numberLengthCounts = new long[(int) Math.floor(Math.log10(Integer.MAX_VALUE)) + 1];

        Arrays.stream(a).forEach(x -> numberLengthCounts[(int) Math.floor(Math.log10(x)) + 1]++);

        sum = IntStream.range(0, numberLengthCounts.length-1)
                .mapToLong(i -> sumOfValues * numberLengthCounts[i] * (int) Math.pow(10, i))
                .reduce(sumOfValues * a.length, Long::sum);

        return sum;
    }
}
