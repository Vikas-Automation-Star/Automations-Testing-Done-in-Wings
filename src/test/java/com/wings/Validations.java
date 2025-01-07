package com.wings;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Validations {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/GSTCalculationCheck.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            Allure.step("Before Test");
        }

        @Test
        public void validate() throws IOException, ParseException, InterruptedException, AWTException {
            SalesInvoiceAlongwithAllCalculations calculations=new SalesInvoiceAlongwithAllCalculations(driver,dataFile);
            calculations.salesInvoice1();
        }

        @AfterTest
        public void afterTest() throws IOException{
//            appLogin.logout();
            Allure.step("After Test");
        }
    }

