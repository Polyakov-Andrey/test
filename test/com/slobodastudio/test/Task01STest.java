
package com.slobodastudio.test;

import com.slobodastudio.solution.Task01S;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit test with parameters. This test unit is designed for the task01
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
@RunWith(value = Parameterized.class)
public class Task01STest {

    private BigInteger a;
    private BigInteger b;
    private boolean rez;

    public Task01STest(String a, String b, boolean rez) {
        this.a = new BigInteger(a);
        this.b = new BigInteger(b);
        this.rez = rez;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{"1234", "1234", true}, 
                       {"1234", "991882778903098746789", true},
                       {"1234", "0978678198775426445786333379675675373", false}};
        return Arrays.asList(data);
    }

    @org.junit.Test
    public void test01() {
//      Logger.getLogger(Task01S.class.getName()).log(Level.INFO, "a = " + a);
//      Logger.getLogger(Task01S.class.getName()).log(Level.INFO, "b = " + b);
        System.out.printf("a = %s, b = %s", a, b);
        assertTrue(Task01S.solution(a, b) == rez);
    }
    
    @org.junit.Test
    public void test02() {
//      Logger.getLogger(Task01S.class.getName()).log(Level.INFO, "a = " + a);
//      Logger.getLogger(Task01S.class.getName()).log(Level.INFO, "b = " + b);
        System.out.printf("a = %s, b = %s", a, b);
        assertTrue(Task01S.solution01(a, b) == rez);
    }
    
}
