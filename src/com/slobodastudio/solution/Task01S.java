package com.slobodastudio.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * This is solution of next task. <br> Let A and B is positive number. To print
 *
 * To print the word "Yes" or "No" according to, whether it is possible to
 * receive decimal record of number A and by deletion of one or more digit of
 * number B.
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
public class Task01S {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        BigInteger a = null;
        BigInteger b = null;

        Scanner s = new Scanner(new File("./data/task01.in"));
        if (s.hasNext()) {
            if (s.hasNextBigInteger()) {
                a = s.nextBigInteger();
            }

            if (s.hasNextBigInteger()) {
                b = s.nextBigInteger();
            }
        }


        boolean rez = Task01S.solution(a, b);
        System.out.printf("A = %d, B=%d ==> %s\n", a, b, rez);

        PrintWriter out = new PrintWriter(new File("./data/task01.out"));
        out.println((rez) ? "ДА" : "НЕТ");
        out.close();
    }

    /**
     * Solving task01
     *
     * @param a the BigInteger
     * @param b the BigInteger
     * @return false if you can get the BigIngeger "a" from the BigInteger "b"
     */
    public static boolean solution(final BigInteger a, final BigInteger b) {

        int n = a.compareTo(b);             // сравниваем числа A b B
        switch (n) {
            case 0:                         // числа  a и b равны
                return true;
            case 1:                         // число b меньше а, построить чичло а из чичла b не позможно
                return false;
            case -1: {                      // число b > a, значит существует возможность потроения числа a из b   
                String strA = a.toString();
                String strB = b.toString();

                int j = 0;
                for (int i = 0; i < strA.length(); i++) {          //  цикл по всем десятичным знакам числа A
                    char c = strA.charAt(i);
                    j = strB.indexOf(c, j);                        //  find the first occurrence of the decimal point "c" of A in the number of B
                    if (j == -1) {                                 //  десятичный знак с в числе B не найден    the decimal digit c didn't find into number B
                        return false;
                    }
                    if ((strB.length() - j) < (strA.length() - i)) {        // Проверяется количество оставшихся символов числел А и B  
                        return false;
                    }
                    j++;
                }
            }
        }
        return true;
    }

    /**
     * Solving task01 using regular expression.
     *
     *
     * @param a the BigInteger
     * @param b the BigInteger
     * @return false if you can get the BigIngeger "a" from the BigInteger "b"
     */
    public static boolean solution01(BigInteger a, BigInteger b) {
        String strA = a.toString();
        String strB = b.toString();

        StringBuilder rex = new StringBuilder((strA.length() + 1) * 2 + 2);

        // regular expression of type (\d*1\d*2\d*3\d*4\d*) for string "1234"
        // this expretion construct from string strA
        // (\d*1(strA[0])\d*(strA[1]) ... \d*(strA[n])\d*)

        rex.append("(\\d*");
        for (int i = 0; i < strA.length(); i++) {
            char c = strA.charAt(i);
            rex.append(c).append("\\d*");
        }
        rex.append(")");

//        System.out.print(rex);    

        return (strB.matches(rex.toString()) ? true : false);
    }
}
