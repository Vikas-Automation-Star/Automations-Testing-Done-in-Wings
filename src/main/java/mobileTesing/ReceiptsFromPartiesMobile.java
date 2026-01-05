package mobileTesing;

import com.wings.utils.Time;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;

public class ReceiptsFromPartiesMobile extends BaseHelper{
    String dataFile;
    public ReceiptsFromPartiesMobile(WebDriver driver,String file){
        super(driver);
        dataFile=file;
    }

    public void receiptsFromParties() throws InterruptedException, IOException, ParseException {
        findWebElementWithTime("xpath","//*[text()='Explorer']",10).click();
        findWebElementWithTime("xpath","(//ion-label[@class='sc-ion-label-ios-h sc-ion-label-ios-s ios'][normalize-space()='Receipts from Parties'])[2]",10).click();
        Thread.sleep(2000);
        findWebElementInMobile("xpath","//div[@class='transaction-tables']//div[1]//ion-list[1]//ion-list-header[1]//ion-button[1]").click();
        inputText("xpath","//*[@placeholder='Enter Date']", Time.getFormattedFutureDate(0));
        Thread.sleep(1000);
        findWebElementInMobile("xpath","//*[@placeholder='Select Branch']").click();
        Thread.sleep(1500);
        findWebElementInMobile("xpath","//*[text()='AT_Branch 1_Reg']").click();
        findWebElementWithTime("xpath","//*[@placeholder='Select Party Account']",10).click();
        Thread.sleep(1000);
        findWebElementWithTime("xpath","//ion-label[normalize-space()='At_Cus_Reg_Intra']",10).click();
        findWebElementWithTime("xpath","//*[text()='Inter State Sales to Registered Dealers']",10).click();
        findWebElementWithTime("xpath","(//button[@type='button'])[11]",10).click();
        findWebElementWithTime("xpath","//*[@placeholder='Select Executive']",10).click();
        findWebElementWithTime("xpath","//*[text()='AT_Executive 1']",10).click();
        findWebElementWithTime("xpath","//*[@placeholder='Enter Remarks']",10).click();
        inputText("xpath","//*[@placeholder='Enter Remarks']",getData(dataFile,"GenInfo","remarks"));
        findWebElementWithTime("xpath","//*[text()='Done']",10).click();

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "Cash", "cashRows")); i++) {
            //cash
            findWebElementWithTime("xpath","(//ion-button[@size='small'][normalize-space()='Edit'])[2]",10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Cash Account')]").click();
            Thread.sleep(500);
            addData("xpath", "//*[@placeholder='Search Cash Account']", dataFile, "Cash", "cashAccount", i);
            Thread.sleep(500);
            searchAndClick(dataFile, "Cash", "cashPath", i);
            Thread.sleep(500);
            addData("xpath", "//*[contains(@placeholder,'Enter Amount')]", dataFile, "Cash", "cashAmount", i);
            findWebElementWithTime("xpath","//*[@placeholder='Select TDS Transaction Nature']",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Transaction Nature 1']",10).click();
            findWebElementWithTime("xpath","//*[@placeholder='Select TDS Account']",10).click();
            Thread.sleep(500);
            findWebElementWithTime("xpath","//*[text()='AT_TDS Paid Acc 1']",10).click();
            Thread.sleep(1500);
            addData("xpath", "//*[contains(@placeholder,'Enter TDS Amount')]", dataFile, "Cash", "tdsAmount", i);
            inputText("xpath","//*[@placeholder='Enter Comments']",getData(dataFile,"Cash","comments"));
            Thread.sleep(2000);
            findWebElementInMobile("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[2]").click();
            findWebElementWithTime("xpath","//*[text()='Done']",10).click();
        }


        for (int i = 0; i < Integer.parseInt(getData(dataFile, "Cheques", "chequesRows")); i++) {
            //Cheques
            findWebElementWithTime("xpath","(//ion-button[@size='small'][normalize-space()='Edit'])[3]",10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Bank Account')]").click();
            findWebElementWithTime("xpath","//*[text()='AT_Bank Acc 1']",10).click();
            Thread.sleep(1000);
            inputText("xpath","//*[contains(@placeholder,'Enter Amount')]",getData(dataFile,"Cheques","chequesAmount"));
            findWebElementWithTime("xpath","//*[@placeholder='Enter Cheque/EFT No']",10).click();
            inputText("xpath","//*[@placeholder='Enter Cheque/EFT No']",getData(dataFile,"Cheques","chequesEFT/No"));
            inputText("xpath","//*[contains(@placeholder,'Enter Cheque Date')]",getData(dataFile,"Cheques","chequesDate"));
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Drawn On Bank')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_Drawn On Bank 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Transaction Nature')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Transaction Nature 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Paid Acc 1']",10).click();
            Thread.sleep(1000);
            inputText("xpath","//*[contains(@placeholder,'Enter TDS Amount')]",getData(dataFile,"Cheques","chequesTdsAmount"));
            Thread.sleep(1000);
//        findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Charges Account')]").click();
//        Thread.sleep(1000);
            driver.findElement(By.xpath("//*[text()='AT_Charges  Acc 1']")).click();
            Thread.sleep(1000);
            inputText("xpath","//*[contains(@placeholder,'Enter Charges')]",getData(dataFile,"Cheques","chequesCharges"));
            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"Cheques","chequesComments"));
            Thread.sleep(1000);
            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();
        }

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "PostDatedCheque", "postDatedChequesDateRows")); i++) {
            //PostDatedCheques
            Thread.sleep(1000);
            WebElement pdc = driver.findElement(By.xpath("//*[text()=' Post Dated Cheques ']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pdc);
            Thread.sleep(1500);
            findWebElementInMobile("xpath","(//ion-button[@size='small'][normalize-space()='Edit'])[4]").click();
            Thread.sleep(1500);
            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(500);
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select PDC Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_Cheques In Hand Acc 1']",10).click();
            Thread.sleep(1000);
            inputText("xpath","//*[@placeholder='Enter Amount']",getData(dataFile,"PostDatedCheque","postDCAmount"));
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Enter Cheque/EFT No')]",10).click();
            inputText("xpath","//*[contains(@placeholder,'Enter Cheque/EFT No')]",getData(dataFile,"PostDatedCheque","postDCChequesEFT/No"));
            inputText("xpath","//*[contains(@placeholder,'Enter Cheque Date')]",getData(dataFile,"PostDatedCheque","postDChequesDate"));
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Drawn On Bank')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_Drawn On Bank 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Transaction Nature')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Transaction Nature 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Paid Acc 1']",10).click();
            Thread.sleep(1000);
            inputText("xpath","//*[contains(@placeholder,'Enter TDS Amount')]",getData(dataFile,"PostDatedCheque","postDCTdsAmount"));
            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"PostDatedCheque","postDCChequesComments"));
            Thread.sleep(2000);
            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();
        }
        for (int i = 0; i < Integer.parseInt(getData(dataFile, "cheque[PDC]", "cheque[PDC]Rows")); i++) {
            //Cheques[PDC
            Thread.sleep(1000);
            findWebElementInMobile("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[5]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath","(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Bank Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_Bank Acc 1']",10).click();
            Thread.sleep(1000);
            inputText("xpath","//*[@placeholder='Enter Amount']",getData(dataFile,"cheque[PDC]","PDCAmount"));
            findWebElementWithTime("xpath","//*[@placeholder='Enter Cheque/EFT No']",10).click();
            inputText("xpath","//*[@placeholder='Enter Cheque/EFT No']",getData(dataFile,"cheque[PDC]","PDCChequesEFT/No"));
            inputText("xpath","//*[contains(@placeholder,'Enter Cheque Date')]",getData(dataFile,"cheque[PDC]","PDChequesDate"));
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Drawn On Bank')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_Drawn On Bank 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Transaction Nature')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Transaction Nature 1']",10).click();
            findWebElementWithTime("xpath","//*[contains(@placeholder,'Select TDS Account')]",10).click();
            findWebElementWithTime("xpath","//*[text()='AT_TDS Paid Acc 1']",10).click();
            Thread.sleep(1000);
            inputText("xpath","//*[contains(@placeholder,'Enter TDS Amount')]",getData(dataFile,"cheque[PDC]","PDCTdsAmount"));
            inputText("xpath","//*[contains(@placeholder,'Enter Comments')]",getData(dataFile,"cheque[PDC]","PDCChequesComments"));
            findWebElementWithTime("cssselector","body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)",10).click();
            findWebElementWithTime("xpath","//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']",10).click();

            WebElement element = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "creditCard", "creditCardRows")); i++) {
            //CreditCard
            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]", 10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(500);
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Swipe Machine Type')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Swipe Machine Type 1']", 10).click();
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Swipe Type')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Swipe Type 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "creditCard", "cardAmount"));
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select TDS Transaction Nature')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_TDS Transaction Nature 1']", 10).click();
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select TDS Account')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_TDS Paid Acc 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter TDS Amount')]", getData(dataFile, "creditCard", "cardTdsAmount"));
            Thread.sleep(500);
            inputText("xpath", "//input[@placeholder='Enter Card No']", getData(dataFile, "creditCard", "cardNumber"));
            Thread.sleep(500);
            inputText("xpath", "//*[contains(@placeholder,'Enter Approval No')]", getData(dataFile, "creditCard", "cardApprovalNumber"));
            findWebElementWithTime("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)", 10).click();
            findWebElementWithTime("xpath", "//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']", 10).click();
        }

//        //Summary
        Thread.sleep(1000);
        WebElement summary = driver.findElement(By.xpath("//*[text()=' Summary ']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summary);
        findWebElementWithTime("xpath","(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[7]",10).click();
        findWebElementWithTime("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]",10).click();
        Thread.sleep(1500);
        findWebElementInMobile("xpath","(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar in-toolbar-color ion-activatable ion-focusable'])[1]").click();
        Thread.sleep(1500);
        driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]")).get(4).click();
        Thread.sleep(1000);
        WebElement geninfo = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(geninfo).perform();
        Thread.sleep(1500);
        geninfo.click();
        findWebElementWithTime("xpath","//*[contains(@placeholder,'Select Party Account')]",10).click();
        findWebElementWithTime("xpath","//ion-label[normalize-space()='At_Cus_Reg_Intra']",10).click();
        findWebElementWithTime("xpath","//*[text()='Intra State Sales to Registered Dealers']",10).click();
        findWebElementWithTime("xpath","(//button[@type='button'])[11]",10).click();
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
