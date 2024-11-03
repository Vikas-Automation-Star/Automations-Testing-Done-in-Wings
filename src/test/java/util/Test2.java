package util;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test2 {
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before locationMaster method 2");

    }

    @Test
    public void Test2(){
        System.out.println("Test method 2");
//        Assert.assertFalse(true);
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After locationMaster method 2");
    }
}
