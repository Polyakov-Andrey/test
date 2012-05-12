
package com.slobodastudio.test;

import com.slobodastudio.solution.Task02S;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit test with parameters. This test unit is designed for the task02
 * 
 * @author Andrey Polyakov
 * @version 1.0
 */
@RunWith(value = Parameterized.class)
public class Task02STest {

    private int n;
    private int k;

    public Task02STest(int n, int k) {
        this.n = n;
        this.k = k;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{3,5},{4,14},{5,42}};
        return Arrays.asList(data);
    }

    @Test
    public void test01() {
        System.out.printf("n=%d, k=%d\n", n, k);
        assertTrue(Task02S.solution01(n) == k);
    }
    
        @Test
    public void test02() {
        System.out.printf("n=%d, k=%d\n", n, k);
        assertTrue(Task02S.solution02(n) == k);
    }
}
