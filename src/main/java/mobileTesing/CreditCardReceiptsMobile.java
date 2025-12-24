package mobileTesing;

import com.wings.utils.Time;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class CreditCardReceiptsMobile extends BaseHelper{
    String dataFile;
    public CreditCardReceiptsMobile(WebDriver driver, String file){
        super(driver);
        dataFile=file;
    }

    public void creditCardReceiptsMobile() throws InterruptedException, IOException, ParseException {
        findWebElementWithTime("xpath","//*[text()='Explorer']",10).click();
        Thread.sleep(2000);
        findWebElementInMobile("xpath","(//ion-label[@class='sc-ion-label-ios-h sc-ion-label-ios-s ios'][normalize-space()='Credit Card Receipts'])[3]").click();
        Thread.sleep(2000);
        findWebElementInMobile("xpath","//div[@class='transaction-tables']//div[1]//ion-list[1]//ion-list-header[1]//ion-button[1]").click();
        inputText("id","ion-input-6", Time.getFormattedFutureDate(0));
        Thread.sleep(1500);
        findWebElementInMobile("xpath","//*[@placeholder='Select Branch']").click();
        Thread.sleep(500);
        findWebElementInMobile("xpath","//*[text()='AT_Branch 1_Reg']").click();
        Thread.sleep(1500);
        findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Swipe Machine Type')]").click();
        Thread.sleep(500);
        findWebElementWithTime("xpath","//*[text()='AT_Swipe Machine Type 1']",10).click();
        Thread.sleep(500);
        findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Swipe Type')]").click();
        findWebElementWithTime("xpath","//*[text()='AT_Swipe Type 1']",10).click();
        findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Executive')]").click();
        Thread.sleep(500);
        findWebElementInMobile("xpath","//*[text()='AT_Executive 1']").click();
        Thread.sleep(500);
        inputText("xpath","//*[contains(@placeholder,'Enter Remarks')]",getData(dataFile,"GenInfo","remarks"));
        findWebElementWithTime("xpath","//*[text()='Done']",10).click();

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "parties", "partiesRows")); i++) {
            //Parties
            findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[2]",10).click();
            Thread.sleep(1000);
            findWebElementInMobile("cssselector","ion-buttons[class='buttons-last-slot sc-ion-buttons-ios-h sc-ion-buttons-ios-s ios'] ion-button[class='sc-ion-buttons-ios ios button button-clear in-toolbar in-buttons ion-activatable ion-focusable']").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Party Account')]").click();
            Thread.sleep(500);
            addData("xpath", "//*[@placeholder='Search Party Account']", dataFile, "parties", "partyAccount", i);
            Thread.sleep(3000);
            searchAndClick(dataFile, "parties", "partyAccountPath", i);
            Thread.sleep(500);
            addData("xpath","//*[contains(@placeholder,'Enter Amount')]",dataFile,"parties","partyAmount",i);
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Transaction Nature')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Transaction Nature 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Paid Acc 1']",10).click();
            Thread.sleep(500);
            addData("xpath","//*[contains(@placeholder,'Enter TDS Amount')]",dataFile,"parties","tdsAmount",i);
            Thread.sleep(500);
            inputText("xpath","//*[contains(@placeholder,'Enter Credit Card No')]",getData(dataFile,"parties","creditCardNum"));
            Thread.sleep(500);
            inputText("xpath","//*[contains(@placeholder,'Enter Expiry Date')]", Time.getFormattedFutureDate(10));
            Thread.sleep(500);
            inputText("xpath","//*[contains(@placeholder,'Enter Approval No')]",getData(dataFile,"parties","approvalNum"));
//        findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Charges Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_Charges  Acc 1']",10).click();
            Thread.sleep(500);
            addData("xpath","//*[contains(@placeholder,'Enter Percentage')]",dataFile,"parties","percentage",i);
            Thread.sleep(500);
            addData("xpath","//*[contains(@placeholder,'Enter Charges')]",dataFile,"parties","charges",i);
            Thread.sleep(500);
            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"parties","comments"));
            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
            findWebElementWithTime("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]",10).click();

        }

        //Summary
        findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[3]",10).click();
        findWebElementWithTime("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]",10).click();


        findWebElementWithTime("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar in-toolbar-color ion-activatable ion-focusable'])[1]",10).click();
        Thread.sleep(1500);
        WebElement validateTransaction=driver.findElement(By.xpath("//*[contains(text(),'Transaction saved successfully')]"));
        if (validateTransaction.getText().contains("Transaction saved successfully")) {
            findWebElementsInMobile("xpath","//button[.//span[normalize-space()='OK']]",25).get(4).click();
            System.out.println("Transaction saved And it's clicked successfully");
        }
        else {
            Assert.fail("Transaction not saved pls check again");
        }

    }
}
