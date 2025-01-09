package com.wings.pages.audit.reports;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.awt.*;

public class LoginStatusReportCode extends Transaction {
    WindowsDriver driver;
    Common common;

    public LoginStatusReportCode(WindowsDriver driver) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void loginStatus() throws InterruptedException, AWTException {
        common.clickElement("name", "Audit");
        common.clickElement("name", "Login Status");

        //tabclose
        super.closeReport("Login Status");
    }
}
