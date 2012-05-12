
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

        Scanner s = new Scanner(new File("task01.in"));        
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
        
        PrintWriter out = new PrintWriter(new File("task01.out"));
        out.println(rez);
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
                    j = strB.indexOf(c, j);                        //  поиск первого вхождения десятичного знака с чила A в число B 
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
}
