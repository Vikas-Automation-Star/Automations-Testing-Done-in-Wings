
import com.wings.utils.APIClient;

import org.testng.annotations.Test;


public class Demo {

    //Sales
    private static final String TEMP_API_BODY_SALES_ENQUIRY="./output/temp_api_request_bodies/salesEnquiries.json";
    private static final String API_RESPONSE_SALES_ENQUIRY="./output/api_responses/salesEnquires.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8_Output.xlsx";

//    private static final String TEMP_API_BODY_SALES_ENQUIRY_CANCELLATION="./output/temp_api_request_bodies/salesEnquiriesCancellation.json";
//    private static final String API_RESPONSE_SALES_ENQUIRY_CANCELLATION="./output/api_responses/salesEnquiriesCancellation.json";
//    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/460469 - Sales Enquiries Cancellation-AC_Output.xlsx";

//    private static final String TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/temp_api_request_bodies/salesQuotationsAgainstEnquiries.json";
//    private static final String API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/api_responses/salesQuotationsAgainstEnquiries.json";
//    private static final String OUTPUT_FILE3="./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC_Output.xlsx";

//    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
//    private static final String API_RESPONSE_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
//    private static final String OUTPUT_FILE4="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_Output.xlsx";

//    private static final String TEMP_API_BODY_SALES_RETURNS="./output/temp_api_request_bodies/SalesReturns.json";
//    private static final String API_RESPONSE_SALES_RETURNS="./output/api_responses/SalesReturns.json";
//    private static final String OUTPUT_FILE5="./src/main/resources/menuItems/Sales/Transactions/480463 - Sales Returns-AC_Output.xlsx";

//    //Purchase
//    private static final String TEMP_API_BODY_SRWIR="./output/temp_api_request_bodies/salesReturnsWithInvoiceReference.json";
//    private static final String API_RESPONSE_SRWIR="./output/api_responses/salesReturnsWithInvoiceReference.json";
//    private static final String OUTPUT_FILE6="./src/main/resources/menuItems/Sales/Transactions/480464 - Sales Return with Invoice Reference-AC_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY="./output/temp_api_request_bodies/purchaseEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY="./output/api_responses/purchaseEnquiries.json";
    private static final String OUTPUT_FILE7="./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";

//    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY_CANCELLATIONS="./output/temp_api_request_bodies/purchaseEnquiriesCancellations.json";
//    private static final String API_RESPONSE_PURCHASE_ENQUIRY_CANCELLATIONS="./output/api_responses/purchaseEnquiriesCancellations.json";
//    private static final String OUTPUT_FILE8="./src/main/resources/menuItems/purchase/transactions/461400 - Purchase Enquiries Cancellation-AC_PEC_1_Output.xlsx";

//    private static final String TEMP_API_BODY_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY="./output/temp_api_request_bodies/purchaseQuotationsAgainstEnquiries.json";
//    private static final String API_RESPONSE_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY="./output/api_responses/purchaseQuotationsAgainstEnquiries.json";
//    private static final String OUTPUT_FILE9="./src/main/resources/menuItems/purchase/transactions/461887 - Purchase Quotations against Enquiries-AC_PQAPE_1_Output.xlsx";

//    private static final String TEMP_API_BODY_PURCHASE_ORDERS_AGAINST_QUOTATIONS="./output/temp_api_request_bodies/PurchaseOrdersAgainstQuotations.json";
//    private static final String API_RESPONSE_PURCHASE_ORDERS_AGAINST_QUOTATIONS="./output/api_responses/PurchaseOrdersAgainstQuotations.json";
//    private static final String OUTPUT_FILE10="./src/main/resources/menuItems/purchase/transactions/480465 - Purchase Orders against Quotations-AC_POAQ 1_Output.xlsx";

//    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
//    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
//    private static final String OUTPUT_FILE11="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xlsx";

//    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/temp_api_request_bodies/PurchaseVouchersAgainstOrders.json";
//    private static final String API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/api_responses/PurchaseVouchersAgainstOrders.json";
//    private static final String OUTPUT_FILE12="./src/main/resources/menuItems/purchase/transactions/479082 - Purchase Vouchers against Orders-AC_PVAO_1_Output.xlsx";

//    private static final String TEMP_API_BODY_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE="./output/temp_api_request_bodies/PurchaseReturnsWithInvoiceReferences.json";
//    private static final String API_RESPONSE_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE="./output/api_responses/PurchaseReturnsWithInvoiceReferences.json";
//    private static final String OUTPUT_FILE13="./src/main/resources/menuItems/purchase/transactions/461323 - Purchase Returns with Invoice Reference-AC_PRWIF_5_Output.xlsx";

    private static final String TEMP_API_BODY_JOURNAL_ENTRIES="./output/temp_api_request_bodies/journalEntries.json";
    private static final String API_RESPONSE_JOURNAL_ENTRIES="./output/api_responses/journalEntries.json";
    private static final String OUTPUT_FILE14="./src/main/resources/menuItems/finance/transaction/464159 - Journal Entries-AC_JE_3_Output.xls";



    @Test
    public void salesEnquiryCancellation() throws Exception {
//        APIClient.validateAPIWithExcel("SE 14",TEMP_API_BODY_SALES_ENQUIRY,API_RESPONSE_SALES_ENQUIRY,OUTPUT_FILE1,"SalesEnquiries");
//        APIClient.validateAPIWithExcel("SEC 10",TEMP_API_BODY_SALES_ENQUIRY_CANCELLATION,API_RESPONSE_SALES_ENQUIRY_CANCELLATION,OUTPUT_FILE2,"SalesEnquiriesCancellations");
//        APIClient.validateAPIWithExcel("SQAE 11",TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES,API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES,OUTPUT_FILE3,"SalesQuotationsAgainstEnquiries");
//        APIClient.validateAPIWithExcel("SI 18",TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_INVOICES,OUTPUT_FILE4,"SalesInvoices");
//        APIClient.validateAPIWithExcel("SR 18",TEMP_API_BODY_SALES_RETURNS,API_RESPONSE_SALES_RETURNS,OUTPUT_FILE5,"SalesReturns");
//        APIClient.validateAPIWithExcel("SRWIR 18",TEMP_API_BODY_SRWIR,API_RESPONSE_SRWIR,OUTPUT_FILE6,"SalesReturnsWithInvoiceReference");
        APIClient.validateAPIWithExcel("PE 10",TEMP_API_BODY_PURCHASE_ENQUIRY,API_RESPONSE_PURCHASE_ENQUIRY,OUTPUT_FILE7,"PurchaseEnquiries");
//        APIClient.validateAPIWithExcel("PEC 18",TEMP_API_BODY_PURCHASE_ENQUIRY_CANCELLATIONS,API_RESPONSE_PURCHASE_ENQUIRY_CANCELLATIONS,OUTPUT_FILE8,"PurchaseEnquiriesCancellations");
//        APIClient.validateAPIWithExcel("PQAE 18",TEMP_API_BODY_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY,API_RESPONSE_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY,OUTPUT_FILE9,"PurchaseQuotationsAgainstEnquiries");
//        APIClient.validateAPIWithExcel("POAQ 18",TEMP_API_BODY_PURCHASE_ORDERS_AGAINST_QUOTATIONS,API_RESPONSE_PURCHASE_ORDERS_AGAINST_QUOTATIONS,OUTPUT_FILE10,"PurchaseOrdersAgainstTheQuotations");
//        APIClient.validateAPIWithExcel("PV 18",TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE11,"PurchaseVouchers");
//        APIClient.validateAPIWithExcel("PVAO 18",TEMP_API_BODY_PURCHASE_VOUCHERS_AGAINST_ORDERS,API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS,OUTPUT_FILE12,"PurchaseVouchersAgainstTheOrders");
//        APIClient.validateAPIWithExcel("PRWIR 18",TEMP_API_BODY_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE,API_RESPONSE_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE,OUTPUT_FILE13,"PurchaseReturnsWithInvoiceReferences");
//        APIClient.validateAPIWithExcel("JE 6",TEMP_API_BODY_JOURNAL_ENTRIES,API_RESPONSE_JOURNAL_ENTRIES,OUTPUT_FILE14,"JournalEntries");

    }
}