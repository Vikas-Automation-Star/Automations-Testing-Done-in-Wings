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

public class SalesOrdersMobile extends BaseHelper {
    String dataFile;

    public SalesOrdersMobile(WebDriver driver, String file) {
        super(driver);
        dataFile = file;
    }

    public void salesOrders() throws InterruptedException, IOException, ParseException {
        findWebElementWithTime("xpath", "//*[text()='Explorer']", 10).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.xpath("//div[4]//ion-grid[1]//ion-row[1]//ion-col[3]//div[1]//ion-label[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        findWebElementInMobile("xpath", "//div[4]//ion-grid[1]//ion-row[1]//ion-col[3]//div[1]//ion-label[1]").click();

        //genInfo
        findWebElementWithTime("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-router-outlet:nth-child(1) > app-transaction:nth-child(3) > ion-content:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ion-list:nth-child(1) > ion-list-header:nth-child(1) > ion-button:nth-child(2)", 10).click();
        Thread.sleep(2000);
        inputText("xpath", "//*[@placeholder='Enter Date']", Time.getFormattedFutureDate(0));
        Thread.sleep(500);
        findWebElementWithTime("xpath", "//*[@placeholder='Select Branch']", 10).click();
        findWebElementWithTime("xpath", "//*[text()='AT_Branch 1_Reg']", 10).click();
        findWebElementWithTime("xpath", "//*[@placeholder='Select Location']", 10).click();
        Thread.sleep(500);
        WebElement clickHold=findWebElementInMobile("xpath", "//*[@placeholder='Search Location']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(clickHold).moveByOffset(0, -200) .release().perform();
        findWebElementWithTime("xpath", "//*[text()='AT_Branch 1_Reg Default Location']", 10).click();
//        create party if required
        findWebElementWithTime("xpath", "//*[@placeholder='Select Party Account']", 10).click();
        findWebElementWithTime("xpath", "//ion-label[normalize-space()='At_Cus_Reg_Intra']", 10).click();
        findWebElementWithTime("xpath", "//*[text()='Intra State Sales to Registered Dealers']", 10).click();
        findWebElementWithTime("xpath", "(//button[@type='button'])[11]", 10).click();
        findWebElementWithTime("xpath", "//*[@placeholder='Select Price List']", 10).click();
        findWebElementWithTime("xpath", "//*[text()='AT_Exclusive Sales Price List 1']", 10).click();
        findWebElementWithTime("xpath", "//*[@placeholder='Select Executive']", 10).click();
        findWebElementWithTime("xpath", "//*[text()='AT_Executive 1']", 10).click();
        findWebElementWithTime("xpath", "//*[@placeholder='Enter Remarks']", 10).click();
        inputText("xpath", "//*[@placeholder='Enter Remarks']", getData(dataFile, "GenInfo", "remarks"));
        findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();

        //items
        for (int i = 0; i < Integer.parseInt(getData(dataFile, "items", "itemsSizeCount")); i++) {
            findWebElementWithTime("xpath", "(//ion-button[@size='small'][normalize-space()='Edit'])[2]", 10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Product')]", 10).click();
            Thread.sleep(500);
            addData("xpath", "(//input[@placeholder='Search Product'])[1]", dataFile, "items", "product", i);
            Thread.sleep(500);
            searchAndClick(dataFile, "items", "productPath", i);
            Thread.sleep(1000);
            addData("xpath", "//*[contains(@placeholder,'Enter Quantity')]", dataFile, "items", "quantity", i);
            inputText("xpath", "//*[@placeholder='Enter Delivery Date']", Time.getFormattedFutureDate(0));
//        Thread.sleep(500);
//        inputText("xpath","//*[contains(@placeholder,'Enter Minimum Rate')]",getData(dataFile,"items","minRate"));
//        Thread.sleep(500);
//        inputText("xpath","//*[contains(@placeholder,'Enter Maximum Rate')]",getData(dataFile,"items","maxRate"));
//        Thread.sleep(500);
//        inputText("xpath","//*[contains(@placeholder,'Enter Unit Rate')]",getData(dataFile,"items","unitRate"));
            WebElement comments = driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", comments);
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "items", "comments"));
            Thread.sleep(2000);
            findWebElementInMobile("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[2]").click();
            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
        }


        for (int i = 0; i < Integer.parseInt(getData(dataFile, "otherCharges", "otherChargesRows")); i++) {
            //Other charges
            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[3]", 10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Account')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Charges  Acc 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "otherCharges", "otherChargesAmount"));
            WebElement comments1 = driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", comments1);
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "otherCharges", "comments"));
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[2]").click();
            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
        }

        //Cash
        for (int i = 0; i < Integer.parseInt(getData(dataFile, "Cash", "cashRows")); i++) {
            Thread.sleep(3000);
            WebElement charges = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[9]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", charges);
            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[4]", 10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "//*[contains(@placeholder,'Select Cash Account')]").click();
            findWebElementWithTime("xpath", "//*[text()='AT_Cash Acc 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "Cash", "cashAmount"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "Cash", "comments"));
            Thread.sleep(2000);
            findWebElementInMobile("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)").click();
            findWebElementWithTime("xpath", "//*[text()='Done']", 10).click();
        }

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "Cheques", "chequesRows")); i++) {
            //Cheques
            WebElement PDC = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PDC);
            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[5]", 10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "//*[contains(@placeholder,'Select Bank Account')]").click();
            findWebElementWithTime("xpath", "//*[text()='AT_Bank Acc 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "Cheques", "chequesAmount"));
            findWebElementWithTime("xpath", "//*[@placeholder='Enter Cheque/EFT No']", 10).click();
            inputText("xpath", "//*[@placeholder='Enter Cheque/EFT No']", getData(dataFile, "Cheques", "chequesEFT/No"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Cheque Date')]", getData(dataFile, "Cheques", "chequesDate"));
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Drawn On Bank')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Drawn On Bank 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Drawn On Bank Branch')]", getData(dataFile, "Cheques", "drawnOnBranch"));
//        findWebElementInMobile("xpath","//*[contains(@placeholder,'Select Charges Account')]").click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[text()='AT_Charges  Acc 1']")).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Charges')]", getData(dataFile, "Cheques", "chequesCharges"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "Cheques", "chequesComments"));
            Thread.sleep(1000);
            findWebElementWithTime("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)", 10).click();
            findWebElementWithTime("xpath", "//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']", 10).click();
        }

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "PostDatedCheque", "postDatedChequesRows")); i++) {
//            PostDatedCheques
            Thread.sleep(2000);
            WebElement PDC1 = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PDC1);
            findWebElementInMobile("xpath", "(//ion-button[@size='small'][normalize-space()='Edit'])[6]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select PDC Account')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Cheques In Hand Acc 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[@placeholder='Enter Amount']", getData(dataFile, "PostDatedCheque", "postDCAmount"));
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Enter Cheque/EFT No')]", 10).click();
            inputText("xpath", "//*[contains(@placeholder,'Enter Cheque/EFT No')]", getData(dataFile, "PostDatedCheque", "postDCChequesEFT/No"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Cheque Date')]", getData(dataFile, "PostDatedCheque", "postDChequesDate"));
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Drawn On Bank')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Drawn On Bank 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Drawn On Bank Branch')]", getData(dataFile, "PostDatedCheque", "drawnOnBranch"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "PostDatedCheque", "postDCChequesComments"));
            Thread.sleep(1000);
            findWebElementWithTime("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)", 10).click();
            findWebElementWithTime("xpath", "//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']", 10).click();
        }

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "cheque[PDC]", "cheque[PDC]Rows")); i++) {
            //Cheques[PDC]
            Thread.sleep(2000);
            WebElement creditCard = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[7]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", creditCard);
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[7]").click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Bank Account')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Bank_PDC_Acc 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[@placeholder='Enter Amount']", getData(dataFile, "cheque[PDC]", "PDCAmount"));
            findWebElementWithTime("xpath", "//*[@placeholder='Enter Cheque/EFT No']", 10).click();
            inputText("xpath", "//*[@placeholder='Enter Cheque/EFT No']", getData(dataFile, "cheque[PDC]", "PDCChequesEFT/No"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Cheque Date')]", getData(dataFile, "cheque[PDC]", "PDChequesDate"));
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Drawn On Bank')]", 10).click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath", "//*[text()='AT_Drawn On Bank 1']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Drawn On Bank Branch')]", getData(dataFile, "cheque[PDC]", "drawnOnBranch"));
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "cheque[PDC]", "PDCChequesComments"));
            Thread.sleep(1000);
            findWebElementWithTime("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)", 10).click();
            findWebElementWithTime("xpath", "//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']", 10).click();
        }

        for (int i = 0; i < Integer.parseInt(getData(dataFile, "creditCard", "creditCardRows")); i++) {
            //CreditCard
            findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[8]", 10).click();
            Thread.sleep(1000);
            findWebElementInMobile("xpath", "(//ion-button[normalize-space()='+ New'])[1]").click();
            Thread.sleep(1000);
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Swipe Machine Type')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Swipe Machine Type 2']", 10).click();
            findWebElementWithTime("xpath", "//*[contains(@placeholder,'Select Swipe Type')]", 10).click();
            findWebElementWithTime("xpath", "//*[text()='AT_Swipe Type 2']", 10).click();
            Thread.sleep(1000);
            inputText("xpath", "//*[contains(@placeholder,'Enter Amount')]", getData(dataFile, "creditCard", "cardAmount"));
            Thread.sleep(500);
            inputText("xpath", "//input[@placeholder='Enter Card No']", getData(dataFile, "creditCard", "cardNumber"));
            Thread.sleep(500);
            inputText("xpath", "//*[@placeholder='Enter Expiry Date']", Time.getFormattedFutureDate(10));
            Thread.sleep(500);
            inputText("xpath", "//*[contains(@placeholder,'Enter Approval No')]", getData(dataFile, "creditCard", "cardApprovalNumber"));
            Thread.sleep(500);
            inputText("xpath", "//*[contains(@placeholder,'Enter Comments')]", getData(dataFile, "creditCard", "creditCardComments"));
            findWebElementWithTime("cssselector", "body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)", 10).click();
            findWebElementWithTime("xpath", "//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']", 10).click();
        }

//        //Summary
        WebElement summary = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[9]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summary);
        findWebElementWithTime("xpath", "(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[9]", 10).click();
        findWebElementWithTime("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]", 10).click();
        Thread.sleep(1500);
        findWebElementInMobile("xpath", "(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar in-toolbar-color ion-activatable ion-focusable'])[1]").click();
        Thread.sleep(1500);
        WebElement validateTransaction = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/ion-app[1]/ion-alert[1]/div[2]/div[2]"));
        if (validateTransaction.getText().contains("Transaction saved successfully")) {
            findWebElementsInMobile("xpath", "//button[.//span[normalize-space()='OK']]", 25).get(4).click();
            System.out.println("Transaction saved And it's clicked successfully");
        } else {
            Assert.fail("Transaction not saved, you missed some fields pls check again");
        }

    }
}
