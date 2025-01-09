package com.wings.tests;

public class ReportVerify {
    public static void main(String[] args) {
        String str = "2;07-01-2025;SI2;AT_Branch 1_Reg;INR;1.00;At_Cus_Reg_Intra;At_Cus_Reg_Intra;            ;Sales Invoices;5.00;0.00;2,750.00;0.00;2,750.00;0.00;0.00;0.00;0.00;0.00;0.00;2,750.00;2,750.00;0.00;0.00;0.00;137.50;0.50;2,888.00;2,888.00;AT_Executive 2;";
        String[] columns = str.split(";");
        for (int i = 0; i < columns.length; i++) {
            System.out.println(columns[i]);
        }
    }
}
