package util;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 {
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before locationMaster method");
//        Assert.assertFalse(true);
    }

    @Test
    public void Test1() {
        System.out.println("Test method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After locationMaster method");
    }


}
