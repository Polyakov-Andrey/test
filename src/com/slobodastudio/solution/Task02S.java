/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slobodastudio.solution;

/**
 * Solving task02. Only static method.
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
public class Task02S {

    /**
     * The method implements the iterative formula <p>
     * <pre>
     *         n
     * S[n] = sum S[i-1]*S[n-i]
     *        i=1
     * </pre>
     *
     * @param n the positive integer
     * @return the values ​​of the right bracket expression for integer n
     */
    public static long solution01(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("");
        }

        long[] s = new long[n + 1];

        s[0] = 1;
        s[1] = 1;

        return s(n, s);
    }

    /**
     * The method implements the iterative formula <p>
     * <pre>
     *         n/2
     * S[n] =( sum S[i-1]*S[n-i] )**2
     *         i=1
     *
     * if (n is odd) S[n]+= S[n/2]*S[n/2]
     *
     * </pre>
     *
     * @param k the positive integer
     * @return the values ​​of the right bracket expression for integer n
     */
    public static long solution02(int n) {
        long[] s = new long[n + 1];

        s[0] = 1;
        s[1] = 1;

        return s1(n, s);
    }

    /**
     * The method implements the iterative formula <p>
     * <pre>
     *         n
     * S[n] = sum S[i-1]*S[n-i]
     *        i=1
     * </pre>
     *
     * @param k the positive integer
     * @param ss the array of values ​​of the right bracket expression
     * @return the values ​​of the right bracket expression for integer k
     */
    private static long s(int k, long[] ss) {
        if (k == 0) {
            return ss[0];
        }
        if (k == 1) {
            return ss[1];
        }
        if (ss[k] != 0) {
            return ss[k];
        }

        long sumS = 0;
        for (int i = 1; i <= k; i++) {
            sumS += s(i - 1, ss) * s(k - i, ss);
        }

        ss[k] = sumS;
        return sumS;
    }

    /**
     * The method implements the iterative formula <p>
     * <pre>
     *         n/2
     * S[n] =( sum S[i-1]*S[n-i] )**2
     *         i=1
     *
     * if (n is odd) S[n]+= S[n/2]*S[n/2]
     *
     * </pre>
     *
     * @param k the positive integer
     * @param ss the array of values ​​of the right bracket expression
     * @return the values ​​of the right bracket expression for integer k
     */
    private static long s1(int k, long[] ss) {
        if (k == 0) {
            return ss[0];
        }
        if (k == 1) {
            return ss[1];
        }
        if (ss[k] != 0) {
            return ss[k];
        }

        long sumS = 0;
        for (int i = 1; i <= (k >> 1); i++) {
            sumS += s1(i - 1, ss) * s1(k - i, ss);
        }
        sumS <<= 1;

        if ((k & 1) != 0) {
            long pow = s1((k >> 1), ss);
            sumS += pow * pow;
        }

        ss[k] = sumS;
        return sumS;
    }
}
