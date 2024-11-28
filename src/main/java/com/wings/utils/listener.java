package com.wings.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class listener implements ITestListener,ISuiteListener,IExecutionListener {
    int totalTest, passed, failed, skipped = 0;
    int suiteTotalTest=0;
    int suitePassed=0;
    int suiteFailed=0;
    int suiteSkipped=0;
    ITestContext tc=null;
    ISuiteListener suiteResults =null;
    JSONArray results=new JSONArray();


    @Override
    public void onStart(ITestContext context) {
        Reporter.log("Test Name: " + context.getName(), true);
    }

    @Override
    public void onTestStart(ITestResult result) {
        totalTest++;
        Reporter.log("Test is started : " + result.getName(), true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Time time=new Time();
        testScript ts=new testScript();
        JSONObject jsonObject=new JSONObject();
        if (result.getStatus() == ITestResult.SUCCESS) {
            ts.setTestName(result.getName());
            ts.setStatus("SUCCESS");
            ts.setExecutionStartTime(time.getLocalDateTime(result.getStartMillis()));
            ts.setExecutionEndTime(time.getLocalDateTime(result.getEndMillis()));
            ts.setExetime(String.valueOf(result.getEndMillis()-result.getStartMillis()));

            jsonObject.put("TestName",result.getName());
            jsonObject.put("Status",ts.status);
            jsonObject.put("ExecutionStartTime",time.getLocalDateTime(result.getStartMillis()));
            jsonObject.put("ExecutionEndTime",time.getLocalDateTime(result.getEndMillis()));
            jsonObject.put("ExeTime",String.valueOf(result.getEndMillis()-result.getStartMillis()));

            results.add(jsonObject);

            System.out.println("start milli: "+ time.getLocalDateTime(result.getStartMillis()));
            System.out.println("End millis: "+time.getLocalDateTime(result.getEndMillis()));
            System.out.println("exec time: "+ (result.getEndMillis()-result.getStartMillis()));
            Reporter.log("Status code: " + result.getStatus(), true);
            Reporter.log("Test execution is success:-" + result.getName(), true);
        }
        passed++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Time time=new Time();
        testScript ts=new testScript();
        JSONObject jsonObject=new JSONObject();
        if (result.getStatus() == ITestResult.FAILURE) {
            ts.setTestName(result.getName());
            ts.setStatus("FAILED");
            ts.setExecutionStartTime(time.getLocalDateTime(result.getStartMillis()));
            ts.setExecutionEndTime(time.getLocalDateTime(result.getEndMillis()));
            ts.setExetime(String.valueOf(result.getEndMillis()-result.getStartMillis()));

            jsonObject.put("TestName",result.getName());
            jsonObject.put("Status",ts.status);
            jsonObject.put("ExecutionStartTime",time.getLocalDateTime(result.getStartMillis()));
            jsonObject.put("ExecutionEndTime",time.getLocalDateTime(result.getEndMillis()));
            jsonObject.put("ExeTime",String.valueOf(result.getEndMillis()-result.getStartMillis()));

            results.add(jsonObject);

            System.out.println("start milli: "+ time.getLocalDateTime(result.getStartMillis()));
            System.out.println("End millis: "+time.getLocalDateTime(result.getEndMillis()));
            System.out.println("exec time: "+ (result.getEndMillis()-result.getStartMillis()));
            Reporter.log("Status code: " + result.getStatus(), true);
            Reporter.log("Test execution is success:-" + result.getName(), true);
        }
        failed++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Time time=new Time();
        testScript ts=new testScript();
        JSONObject jsonObject=new JSONObject();
        if (result.getStatus() == ITestResult.SKIP) {
            ts.setTestName(result.getName());
            ts.setStatus("FAILED");
            ts.setExecutionStartTime(time.getLocalDateTime(result.getStartMillis()));
            ts.setExecutionEndTime(time.getLocalDateTime(result.getEndMillis()));
            ts.setExetime(String.valueOf(result.getEndMillis()-result.getStartMillis()));

            jsonObject.put("TestName",result.getName());
            jsonObject.put("Status",ts.status);
            jsonObject.put("ExecutionStartTime",time.getLocalDateTime(result.getStartMillis()));
            jsonObject.put("ExecutionEndTime",time.getLocalDateTime(result.getEndMillis()));
            jsonObject.put("ExeTime",String.valueOf(result.getEndMillis()-result.getStartMillis()));

            results.add(jsonObject);

            System.out.println("start milli: "+ time.getLocalDateTime(result.getStartMillis()));
            System.out.println("End millis: "+time.getLocalDateTime(result.getEndMillis()));
            System.out.println("exec time: "+ (result.getEndMillis()-result.getStartMillis()));
            Reporter.log("Status code: " + result.getStatus(), true);
            Reporter.log("Test execution is success:-" + result.getName(), true);
        }
        skipped++;
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("COMPLETED " + context.getName());
    }

    @Override
    public void onExecutionStart() {
        IExecutionListener.super.onExecutionStart();
        Reporter.log("Execution is started");
    }

    @Override
    public void onExecutionFinish() {
        IExecutionListener.super.onExecutionFinish();
        Reporter.log("Execution is finished");
    }

    @Override
    public void onStart(ISuite suite) {
        Reporter.log(suite.getXmlSuite().getParameters().toString());
        System.out.println("On suite start");
        try {
            FileUtils.moveDirectory(new File("./allure-results"),new File("./backup"));
            FileUtils.deleteDirectory(new File("./allure-results"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onFinish(ISuite suite) {
        suiteTotalTest = suite.getResults().size();
//        suite.getXmlSuite()

        for (ISuiteResult sr : suite.getResults().values()) {
            tc = sr.getTestContext();
            suiteFailed+=tc.getFailedTests().getAllResults().size();
            suitePassed+=tc.getPassedTests().getAllResults().size();
            suiteSkipped+=tc.getSkippedTests().getAllResults().size();
            suiteTotalTest=suiteFailed+suitePassed+suiteSkipped;
        }
        JSONObject obj=new JSONObject();
        obj.put("Tests",results);


        FileUtil file=new FileUtil();
        file.createJson(obj);

        System.out.println("On suite finish");
        System.out.println(("Total tests: " + totalTest));
        System.out.println(("Total test passed: " + passed));
        System.out.println(("Total test failed: " + failed));
        System.out.println(("Total test skipped: " + skipped));

        System.out.println(("Total suite tests: " + suiteTotalTest));
        System.out.println(("Total suite passed: " + suitePassed));
        System.out.println(("Total suite failed: " + suiteFailed));
        System.out.println(("Total suite skipped: " + suiteSkipped));
    }
}
