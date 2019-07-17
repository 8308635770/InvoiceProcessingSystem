package org.apache.pdfbox.pdmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

//	public static final String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
//	public static final String username = "hr";
//	public static final String password = "hr";
	public static Connection con;
	public static Statement stmt;
	public static PreparedStatement pstmt;

	public static void main(String[] args) {

	}

	public static void databaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/srt", "root","");
			System.out.println(con);
//			stmt = con.createStatement();
			if (con != null) {
				System.out.println("Connect OK");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void databaseConnectionClose() {
		if(con != null) {
			try {
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public static void insertDataIntoDB(long invoiceNo, String InvoiceDate, long CustomerPo, String Address,
			double TotalInvoice) throws SQLException {

		String SQL = "insert into invoice_info " + "VALUES(?, ?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(SQL);
		pstmt.setLong(1, invoiceNo);
		pstmt.setString(2, InvoiceDate);
		pstmt.setLong(3, CustomerPo);
		pstmt.setString(4, Address);
		pstmt.setDouble(5, TotalInvoice);
		pstmt.setString(6, "Waiting for approval");
		pstmt.executeUpdate();

	}
//	
//	public static void displayData() throws SQLException { 
//        ResultSet rs = stmt.executeQuery("select * from NEWINFOTABLE"); 
//        while (rs.next()) 
//        { 
//            System.out.println("Invoice No : " + rs.getString(1)); 
//            System.out.println("Invoice Date :" + rs.getString(2)); 
//            System.out.println("Customer PO :" + rs.getString(3));
//            System.out.println("Address :" + rs.getString(4));
//            System.out.println("Total Invoice :" + rs.getString(5));
//            System.out.println("Status :"+rs.getString(6));
//        } 
//	}
	 
	public static void update(long invoiceNo) throws SQLException {
		 String SQL_UPDATE = "UPDATE Invoice_info SET Status='Approved' WHERE invoiceNO=invoiceNo";
		  PreparedStatement preparedStatement = con.prepareStatement(SQL_UPDATE);
		 System.out.println(invoiceNo);
	
	}
	/*
	public static void approver(long invoiceNo) throws SQLException {
		String SQL = "UPDATE NEWINFOTABLE SET Status = ? WHERE INVOICE_NO = ?";
		pstmt = con.prepareStatement(SQL);
		pstmt.setString(1, "Accept");
		pstmt.setLong(2, invoiceNo);
		pstmt.executeUpdate();
	}*/

}