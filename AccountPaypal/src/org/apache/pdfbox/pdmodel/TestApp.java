package org.apache.pdfbox.pdmodel;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class TestApp {
	
	  private static String pop3Host = "pop.gmail.com";//change accordingly
	  private static String mailStoreType = "pop3";	
	  private static final String userName = "shivam.project123@gmail.com";//change accordingly
	  private static  final String password = "project@123";//change accordingly
	 
	

	static String rawText;
	
	private static final String invoiceNo = "Invoice No";
	private static final String invoiceDate = "Invoice Date";
	private static final String customerPO = "Customer P.O.";
	private static final String customerAddress = "Ship To";
	
	public static void main(String[] args) throws InvalidPasswordException, IOException, SQLException {

		MailRead.receiveEmail(pop3Host, mailStoreType, userName, password);
		PdfReader details = new PdfReader();
		Scanner sc = new Scanner(System.in);
		File file = new File("C:\\Users\\stapade\\Desktop\\ProjectPdf\\Acushnet1.pdf");
		PDDocument pdfDocument = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		rawText = pdfStripper.getText(pdfDocument);

		System.out.println("Invoice No: " + details.findInvoiceNo(invoiceNo));
		System.out.println("Invoice date: " + details.findInvoiceDate(invoiceDate));
		System.out.println("Customer PO: " + details.findCustomerPO(customerPO));
		System.out.println("Customer Address: " + details.findCustomerAddress(customerAddress));
		System.out.println("Total Invoice: " + details.findTotalInvoice());
		
		pdfDocument.close();
		
		
		Database.databaseConnection();
		
		Database.insertDataIntoDB(details.findInvoiceNo(invoiceNo), details.findInvoiceDate(invoiceDate), 
		details.findCustomerPO(customerPO), details.findCustomerAddress(customerAddress),
				details.findTotalInvoice());
//		SendEmail.sendmail(details.findInvoiceNo(invoiceNo),details.findTotalInvoice());
		
	
		long invoicenumber;
		System.out.println("Enter Invoice Number On which Action has to be performed");
		invoicenumber=sc.nextLong();
		if(invoicenumber==details.invoiceNo){
		System.out.println("------------Select Your Choice----------");
		System.out.println("1.AcceptInvoice");
		System.out.println("2.RejectInvoice");
		int ch;
		ch=sc.nextInt();
		switch(ch)
		{
		 case 1:
			 Database.update(invoicenumber);
			 SendEmail.sendmail(details.findInvoiceNo(invoiceNo),details.findTotalInvoice());
			 break;
		 case 2:	 
			 System.out.println("sdfgh");
		}
		}
		else
			System.out.println("Give Valid Invoice Number");
		Database.databaseConnectionClose();
		
//		Database.displayData();
//		System.out.print("Enter Invoice No :");
//		long findInvoice = sc.nextLong();
//		Database.searchData(findInvoice);
//		Database.approver(findInvoice);
	}

}

