package advent2015;

import java.util.Arrays;

public class Day17 {

    private static final int VALUE = 150;
    private int counter = 0;
    private int minWays;
    int[] array;

    public int wynik1(final String input) {
        final String[] lines = input.split("\\n");
        array = new int[lines.length];
        final int N = array.length;
        for (int i = 0; i < N; i++) array[i] = Integer.parseInt(lines[i]);
        final int min = getMinNumber(array);
        final int max = getMaxNumber(array);
        minWays = max;


        for (int i = min; i <= max; i++) combinationsOfIndexes(N, i);

        return counter;
    }

    private void check(final int[] ar) {
        int result = 0;
        final int N = ar.length;
        for (int i = 0; i < N; i++) result += array[ar[i]];
        if (result == VALUE) {
            ++counter;
        }
    }

    private void check2(final int[] ar) {
        int result = 0;
        final int N = ar.length;
        for (int i = 0; i < N; i++) result += array[ar[i]];
        if (result == VALUE) {
            if (N < minWays) {
                minWays = N;
                counter = 1;
                System.out.println(Arrays.toString(ar));
            }
            else if (N == minWays) ++counter;

        }
    }


    private  void combinationsOfIndexes(final int N, final int k) {
        int[] t = new int[k];
        for (int i = 0; i < k; i++) t[i] = i;
        int i = k - 1;
        check2(t);
        while (t[0] != (N - k)) {
            if (t[i] == (N - k + i)) {
                i--;
                continue;
            }
            t[i]++;
            for (int j = i + 1, m = 1; j < k; j++, m++) t[j] = t[i] + m;
            check2(t);
            if (t[k - 1] == N - 1) continue;
            i = k - 1;

        }
    }


    private int getMaxNumber(int[] array) {
        int result = 0;
        java.util.Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            result += array[i];
            if (result > VALUE) return i;
        }
        return -1;
    }

    private int getMinNumber(int[] array) {
        int result = 0;
        java.util.Arrays.sort(array);
        for (int i = array.length - 1; i >= 0; i--) {
            result += array[i];
            if (result > VALUE) return array.length - i;
        }
        return -1;
    }

}
