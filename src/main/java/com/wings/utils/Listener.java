package com.wings.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class Listener implements ITestListener, ISuiteListener, IExecutionListener, IConfigurationListener, IClassListener  {
    int totalTest, passed, failed, skipped = 0;
    int suiteTotalTest = 0;
    int suitePassed = 0;
    int suiteFailed = 0;
    int suiteSkipped = 0;
    ITestContext tc = null;
    ISuiteListener suiteResults = null;
    JSONArray results = new JSONArray();
    File TimeLogFile;

    @Override
    public void onExecutionStart() {
        IExecutionListener.super.onExecutionStart();
        Reporter.log("Execution is started",true);
    }

    @Override
    public void onStart(ISuite suite) {
        try {
            Reporter.log(suite.getXmlSuite().getParameters().toString());
            System.out.println("On suite start");
            Time time = new Time();
            String timeStamp = time.timeStamp();
            String filepath = String.format("./results/text/Time_%s.txt",timeStamp);
            TimeLogFile = new File(filepath);
            TimeLogFile.getParentFile().mkdirs();
            TimeLogFile.createNewFile();
//            TimeLogFile.getPath();
            suite.setAttribute("TimeLogFile",TimeLogFile.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Suite Started: " + suite.getName());
    }

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
        Time time = new Time();
        TestScript ts = new TestScript();
        JSONObject jsonObject = new JSONObject();
        if (result.getStatus() == ITestResult.SUCCESS) {
            ts.setTestName(result.getName());
            ts.setStatus("SUCCESS");
            ts.setExecutionStartTime(time.getLocalDateTime(result.getStartMillis()));
            ts.setExecutionEndTime(time.getLocalDateTime(result.getEndMillis()));
            ts.setExetime(String.valueOf(result.getEndMillis() - result.getStartMillis()));

            jsonObject.put("TestName", result.getName());
            jsonObject.put("Status", ts.status);
            jsonObject.put("ExecutionStartTime", time.getLocalDateTime(result.getStartMillis()));
            jsonObject.put("ExecutionEndTime", time.getLocalDateTime(result.getEndMillis()));
            jsonObject.put("ExeTime", String.valueOf(result.getEndMillis() - result.getStartMillis()));

            results.add(jsonObject);

            System.out.println("start milli: " + time.getLocalDateTime(result.getStartMillis()));
            System.out.println("End millis: " + time.getLocalDateTime(result.getEndMillis()));
            System.out.println("exec time: " + (result.getEndMillis() - result.getStartMillis()));
            Reporter.log("Status code: " + result.getStatus(), true);
            Reporter.log("Test execution is success:-" + result.getName(), true);
        }
        passed++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Time time = new Time();
        TestScript ts = new TestScript();
        JSONObject jsonObject = new JSONObject();
        if (result.getStatus() == ITestResult.FAILURE) {
            ts.setTestName(result.getName());
            ts.setStatus("FAILED");
            ts.setExecutionStartTime(time.getLocalDateTime(result.getStartMillis()));
            ts.setExecutionEndTime(time.getLocalDateTime(result.getEndMillis()));
            ts.setExetime(String.valueOf(result.getEndMillis() - result.getStartMillis()));

            jsonObject.put("TestName", result.getName());
            jsonObject.put("Status", ts.status);
            jsonObject.put("ExecutionStartTime", time.getLocalDateTime(result.getStartMillis()));
            jsonObject.put("ExecutionEndTime", time.getLocalDateTime(result.getEndMillis()));
            jsonObject.put("ExeTime", String.valueOf(result.getEndMillis() - result.getStartMillis()));

            results.add(jsonObject);

            System.out.println("start milli: " + time.getLocalDateTime(result.getStartMillis()));
            System.out.println("End millis: " + time.getLocalDateTime(result.getEndMillis()));
            System.out.println("exec time: " + (result.getEndMillis() - result.getStartMillis()));
            Reporter.log("Status code: " + result.getStatus(), true);
            Reporter.log("Test execution is success:-" + result.getName(), true);
        }
        failed++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String reason = "";
        Time time = new Time();
        TestScript ts = new TestScript();
        JSONObject jsonObject = new JSONObject();
        if (result.getStatus() == ITestResult.SKIP) {
            ts.setTestName(result.getName());
            ts.setStatus("SKIPPED");
            ts.setExecutionStartTime(time.getLocalDateTime(result.getStartMillis()));
            ts.setExecutionEndTime(time.getLocalDateTime(result.getEndMillis()));
            ts.setExetime(String.valueOf(result.getEndMillis() - result.getStartMillis()));

            jsonObject.put("TestName", result.getName());
            jsonObject.put("Status", ts.status);
            jsonObject.put("ExecutionStartTime", time.getLocalDateTime(result.getStartMillis()));
            jsonObject.put("ExecutionEndTime", time.getLocalDateTime(result.getEndMillis()));
            jsonObject.put("ExeTime", String.valueOf(result.getEndMillis() - result.getStartMillis()));

            results.add(jsonObject);

            System.out.println("start milli: " + time.getLocalDateTime(result.getStartMillis()));
            System.out.println("End millis: " + time.getLocalDateTime(result.getEndMillis()));
            System.out.println("exec time: " + (result.getEndMillis() - result.getStartMillis()));
            Reporter.log("Status code: " + result.getStatus(), true);
            Reporter.log("Test execution is success:-" + result.getName(), true);
        }
        skipped++;

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            reason = throwable.getMessage();
        } else if (result.getMethod().getMethodsDependedUpon().length > 0) {
            reason = "Dependent test(s) failed/skipped";
        }
        System.out.printf("SKIPPED: %s - Reason: %s%n",
                result.getMethod().getMethodName(), reason);
        // Optionally push to DB/ELK for analytics
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        System.out.println("Configuration Failure in: " +
                result.getMethod().getMethodName() +
                " Cause: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Test Completed " + context.getName(),true);
    }

    @Override
    public void onFinish(ISuite suite) {
        suiteTotalTest = suite.getResults().size();

        for (ISuiteResult sr : suite.getResults().values()) {
            tc = sr.getTestContext();
            suiteFailed += tc.getFailedTests().getAllResults().size();
            suitePassed += tc.getPassedTests().getAllResults().size();
            suiteSkipped += tc.getSkippedTests().getAllResults().size();
            suiteTotalTest = suiteFailed + suitePassed + suiteSkipped;
        }
        JSONObject obj = new JSONObject();
        obj.put("Tests", results);

        PieChartGenerator chartGenerator=new PieChartGenerator();
        chartGenerator.generatePieChart(suitePassed,suiteFailed,suiteSkipped);

        FileUtil file = new FileUtil();
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
        System.out.println("✅ Suite Finished: " + suite.getName());
    }

    @Override
    public void onExecutionFinish() {
        IExecutionListener.super.onExecutionFinish();
        Reporter.log("Execution is finished",true);
    }

}
