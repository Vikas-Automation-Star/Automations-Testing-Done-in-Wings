package com.wings.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
//    // Connection object
//    static Connection con = null;
//    // Statement object
//    private static Statement stmt;
//    // Constant for Database URL
//    public static String DB_URL = "jdbc:mysql://EC2AMAZ-MITG6MD//WINGS";
//    //Database Username
//    public static String DB_USER = "Manoj";
//    // Database Password
//    public static String DB_PASSWORD = "NopassworD@123";
//
//    @BeforeTest
//    public void setUp() throws Exception {
//        try {
//// Database connection
//            String dbClass = "com.mysql.cj.jdbc.Driver";
//            Class.forName(dbClass).newInstance();
//// Get connection to DB
//            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//// Statement object to send the SQL statement to the Database
//            stmt = con.createStatement();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test() {
//        try {
//            String query = "Select * from ABCD.dbo.Company_FinancialYears_Table";
//// Get the contents of userinfo table from DB
//            ResultSet res = stmt.executeQuery(query);
//// Print the result until all the records are printed
//// res.next() returns true if there is any next record else returns false
//            System.out.println("Here");
//            while (res.next()) {
//                System.out.print(res.getString(1));
//                System.out.print(" " + res.getString(2));
//                System.out.print(" " + res.getString(3));
//                System.out.println(" " + res.getString(4));
//            }
//        } catch (Exception e) {
//            System.out.println("Exception");
//            e.printStackTrace();
//        }
//    }
//
//    @AfterTest
//    public void tearDown() throws Exception {
//// Close DB connection
//        if (con != null) {
//            con.close();
//        }
//    }
}
