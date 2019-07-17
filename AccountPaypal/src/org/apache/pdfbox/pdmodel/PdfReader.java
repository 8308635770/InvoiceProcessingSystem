package org.apache.pdfbox.pdmodel;



public class PdfReader {

	public long invoiceNo;
	public String invoiceDate;
	public long customerPO;
	public String address;
	public double totalInvoice;
	
	public long findInvoiceNo(String invoiceNum) {
		if (TestApp.rawText.contains(invoiceNum)) {
			invoiceNo = Long.parseLong(TestApp.rawText.substring(TestApp.rawText.indexOf(invoiceNum) + invoiceNum.length(),
					TestApp.rawText.indexOf("Invoice Date")).trim());
			return invoiceNo;
		} else
			return -1;
	}

	public String findInvoiceDate(String invoiceDateArg) {
		if (TestApp.rawText.contains(invoiceDateArg)) {
			invoiceDate = TestApp.rawText.substring(TestApp.rawText.indexOf(invoiceDateArg) + invoiceDateArg.length(), 
					TestApp.rawText.indexOf(invoiceDateArg) + invoiceDateArg.length() + 10).trim();
			return invoiceDate;
		} else
			return "Error";
	}

	public long findCustomerPO(String customerPOArg) {
		if (TestApp.rawText.contains(customerPOArg)) {
			customerPO = Long.parseLong(TestApp.rawText.substring(TestApp.rawText.indexOf(customerPOArg) + customerPOArg.length(),
					TestApp.rawText.indexOf("Account No")).trim());
			return customerPO;
		} else
			return -1;
	}

	public String findCustomerAddress(String customerAddress) {
		if (TestApp.rawText.contains(customerAddress)) {
			address = TestApp.rawText.substring(TestApp.rawText.indexOf(customerAddress) + customerAddress.length(),
					TestApp.rawText.indexOf("Remit To")).replaceAll("\\s{2,}", " ").trim();
			return address;
		} else
			return "Error";
	}

	public double findTotalInvoice() {
		String tempAmount[] = TestApp.rawText.split("\\$");
//		System.out.println(tempAmount[tempAmount.length-1]);
		String tempInvoice[] = tempAmount[tempAmount.length - 1].split("\\s");
		totalInvoice = Double.parseDouble(tempInvoice[0].replace(",", ""));
		return totalInvoice;
	}

}