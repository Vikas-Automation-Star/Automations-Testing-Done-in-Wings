package com.wings.utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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
    Process process;

    private static Process winAppDriverProcess;


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
        Reporter.log("Test Completed " + context.getName());
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
    }

//    @Override
//    public void onBeforeClass(ITestClass testClass) {
//        System.out.println("[WinAppDriverListener] Starting WinAppDriver before class: " + testClass.getName());
//        startWinAppDriver();
//    }
//
//    @Override
//    public void onAfterClass(ITestClass testClass) {
//        System.out.println("[WinAppDriverListener] Stopping WinAppDriver after class: " + testClass.getName());
//        stopWinAppDriver();
//    }
//
//    private void startWinAppDriver() {
//        try {
//            // Start WinAppDriver process
//            winAppDriverProcess = new ProcessBuilder("cmd.exe", "/c", "start", "\"\"", "\"C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe\"", "127.0.0.1", "4723").start();
////            Runtime.getRuntime().exec(
//                    "cmd.exe /c start \"\" \"C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe\"");
//
//            waitForWinAppDriver();
//            System.out.println("✅ WinAppDriver started successfully.");
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to start WinAppDriver", e);
//        }
//    }
//
//    private void stopWinAppDriver() {
//        try {
//            // Kill the WinAppDriver process
////            if (winAppDriverProcess != null) {
////                winAppDriverProcess.destroy();
////                System.out.println("✅ WinAppDriver process terminated.");
////            } else {
//            // Fallback: ensure process is killed in case of orphaned process
//            Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
//            Thread.sleep(5000);
//            System.out.println("✅ WinAppDriver forcibly terminated (fallback).");
////            winAppDriverProcess = new ProcessBuilder("cmd.exe", "/c", "start", "\"\"", "\"C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe\"", "127.0.0.1", "4723").start();
////            System.out.println("✅ WinAppDriver started successfully.");
//
////            }
//
//        } catch (IOException | InterruptedException e) {
//            System.err.println("⚠️ Failed to stop WinAppDriver cleanly: " + e.getMessage());
//        }
//    }
//
//    private void waitForWinAppDriver() throws IOException {
//        int retries = 10;
//        int waitTime = 1000; // milliseconds
//        boolean isReady = false;
//
//        while (retries-- > 0) {
//            try {
//                URL url = new URL("http://127.0.0.1:4723/status"); // ✅ Fixed path
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setConnectTimeout(1000);
//                connection.connect();
//
//                if (connection.getResponseCode() == 200) {
//                    isReady = true;
//                    break;
//                }
//            } catch (IOException ignored) {
//                try {
//                    Thread.sleep(waitTime);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//
//        if (!isReady) {
//            throw new RuntimeException("❌ WinAppDriver is not ready at http://127.0.0.1:4723");
//        }
//    }



}
