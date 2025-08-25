package com.wings.pages.inventory.reports.stock;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class StockLedger extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public StockLedger(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile=file;
    }

    public void stockLedger() throws Exception {
        validateStockLedger(dataFile,"stockLedger");
    }
}



