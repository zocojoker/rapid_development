package com.zoco;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public static boolean isBoolean(int args) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        if(!isBoolean(1)){
            System.out.println("what is print");
        }else {
            System.out.println("else");
        }


    }
}
