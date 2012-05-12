package com.slobodastudio.solution;

import com.slobodastudio.tasks.ITask;
import com.slobodastudio.tasks.TaskAbstract;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Solution task02. Only create instance.
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
public class Task02 extends TaskAbstract implements ITask {

    /**
     * The positive integer
     */
    private int n;
    /**
     * array integer of he right bracket expression for each integer n
     */
    private long[] S;

    public Task02(int n) {
        if ((n <= 0) && (n > 20)) {
            throw new IllegalArgumentException("The number N must be positive.");
        }

        this.n = n;
        S = new long[n + 1];

        S[0] = 1;
        S[1] = 1;
    }

    public Task02(String nameFileIn, String nameFileOut) {
        if (nameFileIn == null) {
            throw new IllegalArgumentException("The nameFileIn is empty.");
        }
        if (nameFileOut == null) {
            throw new IllegalArgumentException("The nameFileOut is empty.");
        }
        File fileIn, fileOut = null;

        try {
            fileIn = new File(nameFileIn);
            if (fileIn.exists()) {
                this.setFileNameIn(fileIn);
            } else {
                throw new FileNotFoundException("The file " + nameFileIn + " is not find.");
            }

            fileOut = new File(nameFileOut);
            if (fileOut.exists()) {
                if (!fileOut.delete()) {
                    throw new IOException("The file " + fileOut.getName() + " is not delate.");
                }
            }
            
            if (!fileOut.createNewFile()) {
                throw new IOException("The file " + fileOut.getName() + " is not created.");
            }
            this.setFileNameOut(fileOut);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task02.class.getName()).log(Level.SEVERE, ex.
                    getMessage(), ex);
            assert (false) : ex.getMessage();
            System.exit(1);

        } catch (IOException ex) {
            Logger.getLogger(Task02.class.getName()).log(Level.SEVERE, ex.
                    getMessage(), ex);
            assert (false) : ex.getMessage();
            System.exit(1);
        }
    }

    public void setN(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The number N must be positive.");
        }
        this.n = n;
        S = new long[n + 1];

        S[0] = 1;
        S[1] = 1;
    }

    public long solution01() {
        return s(n);
    }

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
    private long s(int k) {
        if (k == 0) {
            return S[0];
        }
        if (k == 1) {
            return S[1];
        }
        if (S[k] != 0) {
            return S[k];
        }

        int sumS = 0;
        for (int i = 1; i <= k; i++) {
            sumS += s(i - 1) * s(k - i);
        }

        S[k] = sumS;
        return sumS;
    }

    /**
     * The method implements the iterative formula <p>
     * <pre>
     *         n/2
     * S[n] =( sum S[i-1]*S[n-i] )**2
     *         i=1
     *
     * if (n is odd) S[n]+= S[n/2+1]*S[n/2+1]
     *
     * </pre>
     *
     * @param k the positive integer
     * @return the values ​​of the right bracket expression for integer k
     */
    private long s1(int k) {
        if (k == 0) {
            return S[0];
        }
        if (k == 1) {
            return S[1];
        }
        if (S[k] != 0) {
            return S[k];
        }

//              n/2
//      S[n] =( sum S[i-1]*S[n-i] )**2
//              i=1

        long sumS = 0;
        for (int i = 1; i <= (k >> 1); i++) {
            sumS += s1(i - 1) * s1(k - i);
        }
        sumS <<= 1;

//      if (n is odd) S[n]+= S[n/2]*S[n/2]
        if ((k & 1) != 0) {
            long pow = s1(k >> 1);
            sumS += pow * pow;
        }

        S[k] = sumS;
        return sumS;
    }

    @Override
    public void loadFromFile() {
        Scanner in = null;
        try {
            in = new Scanner(getFileNameIn());
            if (in.hasNextInt()) {
                n = in.nextInt();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task02.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        S = new long[n + 1];
        S[0] = 1;
        S[1] = 1;
    }

    /**
     * The method implements the iterative formula <p>
     * <pre>
     *         n/2
     * S[n] =( sum S[i-1]*S[n-i] )**2
     *         i=1
     *
     * if (n is odd) S[n]+= S[n/2]**2
     *
     * </pre>
     *
     */
    @Override
    public void solution() {
        S[n] = s1(n);
    }

    @Override
    public void writeToFile() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(getFileNameOut());
            out.println(S[n]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task02.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void main(String[] arg) {

        Task02 task02 = new Task02("./data/task02.in", "./data/task02.out");
        task02.loadFromFile();
        task02.solution();
        task02.writeToFile();
    }
}
