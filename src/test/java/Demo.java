
import com.wings.utils.APIClient;

import org.testng.annotations.Test;


public class Demo {

    //Sales
    private static final String TEMP_API_BODY_SALES_ENQUIRY="./output/temp_api_request_bodies/salesEnquiries.json";
    private static final String API_RESPONSE_SALES_ENQUIRY="./output/api_responses/salesEnquires.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_ENQUIRY_CANCELLATION="./output/temp_api_request_bodies/salesEnquiriesCancellation.json";
    private static final String API_RESPONSE_SALES_ENQUIRY_CANCELLATION="./output/api_responses/salesEnquiriesCancellation.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/460469 - Sales Enquiries Cancellation-AC_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/temp_api_request_bodies/salesQuotationsAgainstEnquiries.json";
    private static final String API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/api_responses/salesQuotationsAgainstEnquiries.json";
    private static final String OUTPUT_FILE3="./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE4="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_RETURNS="./output/temp_api_request_bodies/SalesReturns.json";
    private static final String API_RESPONSE_SALES_RETURNS="./output/api_responses/SalesReturns.json";
    private static final String OUTPUT_FILE5="./src/main/resources/menuItems/Sales/Transactions/480463 - Sales Returns-AC_Output.xlsx";

    private static final String TEMP_API_BODY_SRWIR="./output/temp_api_request_bodies/salesReturnsWithInvoiceReference.json";
    private static final String API_RESPONSE_SRWIR="./output/api_responses/salesReturnsWithInvoiceReference.json";
    private static final String OUTPUT_FILE6="./src/main/resources/menuItems/Sales/Transactions/480464 - Sales Return with Invoice Reference-AC_Output.xlsx";

    //Purchase

    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY="./output/temp_api_request_bodies/purchaseEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY="./output/api_responses/purchaseEnquiries.json";
    private static final String OUTPUT_FILE7="./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY_CANCELLATIONS="./output/temp_api_request_bodies/purchaseEnquiriesCancellations.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY_CANCELLATIONS="./output/api_responses/purchaseEnquiriesCancellations.json";
    private static final String OUTPUT_FILE8="./src/main/resources/menuItems/purchase/transactions/461400 - Purchase Enquiries Cancellation-AC_PEC_1_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY="./output/temp_api_request_bodies/purchaseQuotationsAgainstEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY="./output/api_responses/purchaseQuotationsAgainstEnquiries.json";
    private static final String OUTPUT_FILE9="./src/main/resources/menuItems/purchase/transactions/461887 - Purchase Quotations against Enquiries-AC_PQAPE_1_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_ORDERS_AGAINST_QUOTATIONS="./output/temp_api_request_bodies/PurchaseOrdersAgainstQuotations.json";
    private static final String API_RESPONSE_PURCHASE_ORDERS_AGAINST_QUOTATIONS="./output/api_responses/PurchaseOrdersAgainstQuotations.json";
    private static final String OUTPUT_FILE10="./src/main/resources/menuItems/purchase/transactions/480465 - Purchase Orders against Quotations-AC_POAQ 1_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE11="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/temp_api_request_bodies/PurchaseVouchersAgainstOrders.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/api_responses/PurchaseVouchersAgainstOrders.json";
    private static final String OUTPUT_FILE12="./src/main/resources/menuItems/purchase/transactions/479082 - Purchase Vouchers against Orders-AC_PVAO_1_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE="./output/temp_api_request_bodies/PurchaseReturnsWithInvoiceReferences.json";
    private static final String API_RESPONSE_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE="./output/api_responses/PurchaseReturnsWithInvoiceReferences.json";
    private static final String OUTPUT_FILE13="./src/main/resources/menuItems/purchase/transactions/461323 - Purchase Returns with Invoice Reference-AC_PRWIF_5_Output.xlsx";

    //Finance
    private static final String TEMP_API_BODY_RECEIPTS_FROM_PARTIES="./output/temp_api_request_bodies/ReceiptsFromParties.json";
    private static final String API_RESPONSE_RECEIPTS_FROM_PARTIES="./output/api_responses/ReceiptsFromParties.json";
    private static final String OUTPUT_FILE14="./src/main/resources/menuItems/finance/transaction/465649 - Receipts from Parties-AC_PREC_3_Output.xls";

    private static final String TEMP_API_BODY_CASH_RECEIPTS="./output/temp_api_request_bodies/CashReceipts.json";
    private static final String API_RESPONSE_CASH_RECEIPTS="./output/api_responses/CashReceipts.json";
    private static final String OUTPUT_FILE15="./src/main/resources/menuItems/finance/transaction/458632 - Cash Receipts-AC_CR_2_Output.xls";

    private static final String TEMP_API_BODY_BANK_RECEIPTS="./output/temp_api_request_bodies/BankReceipts.json";
    private static final String API_RESPONSE_BANK_RECEIPTS="./output/api_responses/BankReceipts.json";
    private static final String OUTPUT_FILE16="./src/main/resources/menuItems/finance/transaction/456086 - Bank Receipts-AC_BR_4_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_CARD_RECEIPTS="./output/temp_api_request_bodies/CreditCardReceipts.json";
    private static final String API_RESPONSE_CREDIT_CARD_RECEIPTS="./output/api_responses/CreditCardReceipts.json";
    private static final String OUTPUT_FILE17="./src/main/resources/menuItems/finance/transaction/456088 - Credit Card Receipts-AC_CCR_2_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_CARD_COMPANIES="./output/temp_api_request_bodies/ReceiptsFromCreditCardCompanies.json";
    private static final String API_RESPONSE_CREDIT_CARD_COMPANIES="./output/api_responses/ReceiptsFromCreditCardCompanies.json";
    private static final String OUTPUT_FILE18="./src/main/resources/menuItems/finance/transaction/458629 - Receipts from Credit Card Companies-AC_RFCCC_2_Output.xls";

    private static final String TEMP_API_BODY_ADJUST_PARTY_BILLS="./output/temp_api_request_bodies/AdjustPartyBills.json";
    private static final String API_RESPONSE_ADJUST_PARTY_BILLS="./output/api_responses/AdjustPartyBills.json";
    private static final String OUTPUT_FILE19="./src/main/resources/menuItems/finance/transaction/458148 - Adjust Party Bills-AC_AB_2_Output.xls";

    private static final String TEMP_API_BODY_DEBIT_NOTE="./output/temp_api_request_bodies/DebitNote.json";
    private static final String API_RESPONSE_DEBIT_NOTE="./output/api_responses/DebitNote.json";
    private static final String OUTPUT_FILE20="./src/main/resources/menuItems/finance/transaction/475827 - Debit Note-AC_DN_2_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_NOTE="./output/temp_api_request_bodies/CreditNote.json";
    private static final String API_RESPONSE_CREDIT_NOTE="./output/api_responses/CreditNote.json";
    private static final String OUTPUT_FILE21="./src/main/resources/menuItems/finance/transaction/477090 - Credit Note-AC_CN_2_Output.xls";

    private static final String TEMP_API_DEBIT_NOTE_ON_CUSTOMERS="./output/temp_api_request_bodies/DebitNoteOnCustomers.json";
    private static final String API_RESPONSE_DEBIT_NOTE_ON_CUSTOMERS="./output/api_responses/DebitNoteOnCustomers.json";
    private static final String OUTPUT_FILE22="./src/main/resources/menuItems/finance/transaction/475830 - Debit Note on Customers-AC_DNOC_1_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_NOTE_ON_CUSTOMERS="./output/temp_api_request_bodies/CreditNoteOnCustomer.json";
    private static final String API_RESPONSE_CREDIT_NOTE_ON_CUSTOMERS="./output/api_responses/CreditNoteOnCustomer.json";
    private static final String OUTPUT_FILE23="./src/main/resources/menuItems/finance/transaction/475832 - Credit Note on Customers-AC_CNOC_1_Output.xls";


    private static final String TEMP_API_BODY_CREDIT_NOTE_FROM_SUPPLIER="./output/temp_api_request_bodies/CreditNoteFromSupplier.json";
    private static final String API_RESPONSE_CREDIT_NOTE_FROM_SUPPLIER="./output/api_responses/CreditNoteFromSupplier.json";
    private static final String OUTPUT_FILE24="./src/main/resources/menuItems/finance/transaction/475833 - Credit Note from Suppliers-AC_CNFS_1_Output.xls";

    private static final String TEMP_API_BODY_DEBIT_NOTE_FROM_SUPPLIER="./output/temp_api_request_bodies/DebitNoteFromSupplier.json";
    private static final String API_RESPONSE_DEBIT_NOTE_FROM_SUPPLIER="./output/api_responses/DebitNoteFromSupplier.json";
    private static final String OUTPUT_FILE25="./src/main/resources/menuItems/finance/transaction/460743 - Debit Note from Suppliers-AC_DNFS_1_Output.xls";

    private static final String TEMP_API_BODY_JOURNAL_ENTRIES="./output/temp_api_request_bodies/journalEntries.json";
    private static final String API_RESPONSE_JOURNAL_ENTRIES="./output/api_responses/journalEntries.json";
    private static final String OUTPUT_FILE126="./src/main/resources/menuItems/finance/transaction/464159 - Journal Entries-AC_JE_3_Output.xls";

    private static final String TEMP_API_BODY_COMPLEX_JOURNAL_ENTRIES="./output/temp_api_request_bodies/ComplexJournalEntries.json";
    private static final String API_RESPONSE_COMPLEX_JOURNAL_ENTRIES="./output/api_responses/ComplexJournalEntries.json";
    private static final String OUTPUT_FILE27="./src/main/resources/menuItems/finance/transaction/485215 - Complex Journal Entries -AC_CJE_1_Output.xls";

    private static final String TEMP_API_BODY_BOOK_INCOMES_OR_RECEIVABLE="./output/temp_api_request_bodies/BookIncomesOrReceivable.json";
    private static final String API_RESPONSE_BOOK_INCOMES_OR_RECEIVABLE="./output/api_responses/BookIncomesOrReceivable.json";
    private static final String OUTPUT_FILE28="./src/main/resources/menuItems/finance/transaction/475835 - Book Incomes or Receivables-AC_BIR_1_Output.xls";

    private static final String TEMP_API_BODY_BOOK_EXPENSES_OR_PAYABLE="./output/temp_api_request_bodies/BookExpensesOrPayable.json";
    private static final String API_RESPONSE_BOOK_EXPENSES_OR_PAYABLE="./output/api_responses/BookExpensesOrPayable.json";
    private static final String OUTPUT_FILE29="./src/main/resources/menuItems/finance/transaction/462279 - Book Expenses or Payables-AC_BEP_1_Output.xls";

    private static final String TEMP_API_BODY_BOOKING_OF_OTHER_COSTS="./output/temp_api_request_bodies/BookingOfOtherCosts.json";
    private static final String API_RESPONSE_BOOKING_OF_OTHER_COSTS="./output/api_responses/BookingOfOtherCosts.json";
    private static final String OUTPUT_FILE30="./src/main/resources/menuItems/finance/transaction/479089 - Booking Of Other Costs-AC_BOC_2_PV_11_Output.xls";

    private static final String TEMP_API_BODY_ASSIGN_STANDARD_RATES="./output/temp_api_request_bodies/AssignStandardRates.json";
    private static final String API_RESPONSE_ASSIGN_STANDARD_RATES="./output/api_responses/AssignStandardRates.json";
    private static final String OUTPUT_FILE31="./src/main/resources/menuItems/production/transactions/163139 - Assign Standard Rates-AC_ASR_1_Output.xls";

    private static final String TEMP_API_BODY_PRODUCTION_ORDERS="./output/temp_api_request_bodies/ProductionsOrders.json";
    private static final String API_RESPONSE_PRODUCTION_ORDERS="./output/api_responses/ProductionsOrders.json";
    private static final String OUTPUT_FILE32="./src/main/resources/menuItems/production/transactions/475293 - Production Orders-AC_PRO_1_Output.xls";

    private static final String TEMP_API_BODY_MATERIAL_ISSUES_PRODUCTION="./output/temp_api_request_bodies/MaterialIssuesToProduction.json";
    private static final String API_RESPONSE_MATERIAL_ISSUES_PRODUCTION="./output/api_responses/MaterialIssuesToProduction.json";
    private static final String OUTPUT_FILE33="./src/main/resources/menuItems/production/transactions/461542 - Material Issues to Production-AC_MITP_1_Output.xls";

    private static final String TEMP_API_BODY_MATERIAL_RETURNS_FROM_PRODUCTION="./output/temp_api_request_bodies/TestMaterialReturnsFromProduction.json";
    private static final String API_RESPONSE_MATERIAL_RETURNS_FROM_PRODUCTION="./output/api_responses/TestMaterialReturnsFromProduction.json";
    private static final String OUTPUT_FILE34="./src/main/resources/menuItems/production/transactions/441733 - Material Returns from Production-AC_MRTFP_1_Output.xls";

    private static final String TEMP_API_BODY_MATERIAL_RECEIPTS_FROM_PRODUCTION="./output/temp_api_request_bodies/MaterialReceiptsFromProduction.json";
    private static final String API_RESPONSE_MATERIAL_RECEIPTS_FROM_PRODUCTION="./output/api_responses/MaterialReceiptsFromProduction.json";
    private static final String OUTPUT_FILE35="./src/main/resources/menuItems/production/transactions/475297 - Material Receipts from Production-AC_MRFP_1_Output.xls";

    private static final String TEMP_API_BODY_CLOSE_PRODUCTION_ORDERS="./output/temp_api_request_bodies/CloseProductionsOrders.json";
    private static final String API_RESPONSE_CLOSE_PRODUCTION_ORDERS="./output/api_responses/CloseProductionsOrders.json";
    private static final String OUTPUT_FILE36="./src/main/resources/menuItems/production/transactions/456300 - Close Production Order-AC_CPO_1_Output.xls";

    private static final String TEMP_API_BODY_MATERIAL_ISSUES_RECEIPTS_FROM_PRODUCTION="./output/temp_api_request_bodies/MaterialIssuesReceiptsFromProduction.json";
    private static final String API_RESPONSE_MATERIAL_ISSUES_RECEIPTS_FROM_PRODUCTION="./output/api_responses/MaterialIssuesReceiptsFromProduction.json";
    private static final String OUTPUT_FILE37="./src/main/resources/menuItems/production/transactions/465120 - Material Issues and Receipts from Production-AC_MIRFP_1_Output.xls";

    @Test
    public void salesEnquiryCancellation() throws Exception {
        //Sales

        APIClient.validateAPIWithExcel("SE 14",TEMP_API_BODY_SALES_ENQUIRY,API_RESPONSE_SALES_ENQUIRY,OUTPUT_FILE1,"SalesEnquiries");
        APIClient.validateAPIWithExcel("SEC 10",TEMP_API_BODY_SALES_ENQUIRY_CANCELLATION,API_RESPONSE_SALES_ENQUIRY_CANCELLATION,OUTPUT_FILE2,"SalesEnquiriesCancellations");
        APIClient.validateAPIWithExcel("SQAE 6",TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES,API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES,OUTPUT_FILE3,"SalesQuotationsAgainstEnquiries4");
        APIClient.validateAPIWithExcel("SI 18",TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_INVOICES,OUTPUT_FILE4,"SalesInvoices");
        APIClient.validateAPIWithExcel("SR 18",TEMP_API_BODY_SALES_RETURNS,API_RESPONSE_SALES_RETURNS,OUTPUT_FILE5,"SalesReturns");
        APIClient.validateAPIWithExcel("SRWIR 18",TEMP_API_BODY_SRWIR,API_RESPONSE_SRWIR,OUTPUT_FILE6,"SalesReturnsWithInvoiceReference");

        //purchase

        APIClient.validateAPIWithExcel("PE 10",TEMP_API_BODY_PURCHASE_ENQUIRY,API_RESPONSE_PURCHASE_ENQUIRY,OUTPUT_FILE7,"PurchaseEnquiries");
        APIClient.validateAPIWithExcel("PEC 3",TEMP_API_BODY_PURCHASE_ENQUIRY_CANCELLATIONS,API_RESPONSE_PURCHASE_ENQUIRY_CANCELLATIONS,OUTPUT_FILE8,"PurchaseEnquiriesCancellations1");
        APIClient.validateAPIWithExcel("PQAE 18",TEMP_API_BODY_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY,API_RESPONSE_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY,OUTPUT_FILE9,"PurchaseQuotationsAgainstEnquiries");
        APIClient.validateAPIWithExcel("POAQ 18",TEMP_API_BODY_PURCHASE_ORDERS_AGAINST_QUOTATIONS,API_RESPONSE_PURCHASE_ORDERS_AGAINST_QUOTATIONS,OUTPUT_FILE10,"PurchaseOrdersAgainstTheQuotations");
        APIClient.validateAPIWithExcel("PV 18",TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE11,"PurchaseVouchers");
        APIClient.validateAPIWithExcel("PVAO 18",TEMP_API_BODY_PURCHASE_VOUCHERS_AGAINST_ORDERS,API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS,OUTPUT_FILE12,"PurchaseVouchersAgainstTheOrders");
        APIClient.validateAPIWithExcel("PRWIR 18",TEMP_API_BODY_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE,API_RESPONSE_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE,OUTPUT_FILE13,"PurchaseReturnsWithInvoiceReferences");

        //finance

        APIClient.validateAPIWithExcel("",TEMP_API_BODY_RECEIPTS_FROM_PARTIES,API_RESPONSE_RECEIPTS_FROM_PARTIES,OUTPUT_FILE14,"ReceiptsFromParties");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CASH_RECEIPTS,API_RESPONSE_CASH_RECEIPTS,OUTPUT_FILE15,"CashReceipts");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_BANK_RECEIPTS,API_RESPONSE_BANK_RECEIPTS,OUTPUT_FILE16,"BankReceipts");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CREDIT_CARD_RECEIPTS,API_RESPONSE_CREDIT_CARD_RECEIPTS,OUTPUT_FILE17,"CreditCardReceipts");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CREDIT_CARD_COMPANIES,API_RESPONSE_CREDIT_CARD_COMPANIES,OUTPUT_FILE18,"ReceiptsFromCreditCardCompanies");

        APIClient.validateAPIWithExcel("",TEMP_API_BODY_ADJUST_PARTY_BILLS,API_RESPONSE_ADJUST_PARTY_BILLS,OUTPUT_FILE19,"AdjustPartyBills");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_DEBIT_NOTE,API_RESPONSE_DEBIT_NOTE,OUTPUT_FILE20,"DebitNote");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CREDIT_NOTE,API_RESPONSE_CREDIT_NOTE,OUTPUT_FILE21,"CreditNote");
        APIClient.validateAPIWithExcel("",TEMP_API_DEBIT_NOTE_ON_CUSTOMERS,API_RESPONSE_DEBIT_NOTE_ON_CUSTOMERS,OUTPUT_FILE22,"DebitNoteOnCustomer");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CREDIT_NOTE_ON_CUSTOMERS,API_RESPONSE_CREDIT_NOTE_ON_CUSTOMERS,OUTPUT_FILE23,"CreditNoteOnCustomer");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CREDIT_NOTE_FROM_SUPPLIER,API_RESPONSE_CREDIT_NOTE_FROM_SUPPLIER,OUTPUT_FILE24,"CreditNoteFromSuppliers");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_DEBIT_NOTE_FROM_SUPPLIER,API_RESPONSE_DEBIT_NOTE_FROM_SUPPLIER,OUTPUT_FILE25,"DebitNoteFromSuppliers");

        APIClient.validateAPIWithExcel("",TEMP_API_BODY_JOURNAL_ENTRIES,API_RESPONSE_JOURNAL_ENTRIES,OUTPUT_FILE126,"JournalEntries");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_COMPLEX_JOURNAL_ENTRIES,API_RESPONSE_COMPLEX_JOURNAL_ENTRIES,OUTPUT_FILE27,"ComplexJournalEntries");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_BOOK_INCOMES_OR_RECEIVABLE,API_RESPONSE_BOOK_INCOMES_OR_RECEIVABLE,OUTPUT_FILE28,"BookIncomesOrReceivable");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_BOOK_EXPENSES_OR_PAYABLE,API_RESPONSE_BOOK_EXPENSES_OR_PAYABLE,OUTPUT_FILE29,"BookExpensesOrPayable");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_BOOKING_OF_OTHER_COSTS,API_RESPONSE_BOOKING_OF_OTHER_COSTS,OUTPUT_FILE30,"BookingOfOtherCosts");

        //Production
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_ASSIGN_STANDARD_RATES,API_RESPONSE_ASSIGN_STANDARD_RATES,OUTPUT_FILE31,"AssignStandardRates");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_PRODUCTION_ORDERS,API_RESPONSE_PRODUCTION_ORDERS,OUTPUT_FILE32,"ProductionOrders");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_MATERIAL_ISSUES_PRODUCTION,API_RESPONSE_MATERIAL_ISSUES_PRODUCTION,OUTPUT_FILE33,"MaterialIssuesToProduction");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_MATERIAL_RETURNS_FROM_PRODUCTION,API_RESPONSE_MATERIAL_RETURNS_FROM_PRODUCTION,OUTPUT_FILE34,"MaterialReturnsFromProduction");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_MATERIAL_RECEIPTS_FROM_PRODUCTION,API_RESPONSE_MATERIAL_RECEIPTS_FROM_PRODUCTION,OUTPUT_FILE35,"MaterialReceiptsFromProduction");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_CLOSE_PRODUCTION_ORDERS,API_RESPONSE_CLOSE_PRODUCTION_ORDERS,OUTPUT_FILE36,"CloseProductionOrders");
        APIClient.validateAPIWithExcel("",TEMP_API_BODY_MATERIAL_ISSUES_RECEIPTS_FROM_PRODUCTION,API_RESPONSE_MATERIAL_ISSUES_RECEIPTS_FROM_PRODUCTION,OUTPUT_FILE37,"MaterialIssuesAndReceiptsFromProduction");

    }

}