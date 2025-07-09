package com.wings.Technical;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;

public class ChangePassword extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ChangePassword(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void changePassword() throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Change Password']");
        enterData("xpath","//Window[@Name='Change Password']/Pane/Edit[@Name='Old Password']",dataFile,"changePassword","oldPassword");
        enterData("xpath","//Window[@Name='Change Password']/Pane/Edit[@Name='New Password']",dataFile,"changePassword","newPassword");
        enterData("xpath","//Window[@Name='Change Password']/Pane/Edit[@Name='Confirm Password']",dataFile,"changePassword","newPassword");
        common.clickElement("xpath","//Pane/Button[@Name='Ok']");
        String textMessage=common.getText("xpath","//Text[@Name='Password changed successfully.']");
        if (textMessage.equals(common.getData(dataFile,"changePassword","confirmationText"))){
            System.out.println("Password changed successfully. Re-Login!");
            Thread.sleep(1500);
            common.clickElement("xpath","//Window[@Name='Change Password']/*/Button[@Name='OK']");
        }
        else {
            Assert.fail("Password is not changed. pls check :(");
        }
    }
}
