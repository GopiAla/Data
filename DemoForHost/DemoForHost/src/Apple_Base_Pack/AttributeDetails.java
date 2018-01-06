package Apple_Base_Pack;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.*;
import java.util.*;
 

/**
 * Servlet implementation class AttributeDetails
 */
@WebServlet("/AttributeDetails")
public class AttributeDetails extends HttpServlet {
	JFrame frame1;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttributeDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println(response.getWriter().append("Served at: ").append(request.getContextPath()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String jdbcUrl = "jdbc:postgresql://localhost:5432/Apple_Maps";
	    String username = "postgres";
	    String password = "Apple@1";

	    frame1 = new JFrame("Show Message Box");
	    Object[] options = {"Yes","No","ask later"};
	    int n = JOptionPane.showOptionDialog(frame1,"Would you like to save it",null, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
	    
	    String [] array = new String[3];
	    Connection conn = null;
	    Statement stmt = null;
	    Statement stmt2 = null;
	    ResultSet rs = null;
	    ResultSet rs2 = null;
	    String tablename="";
	    String filename = "/Users/chandrasekarm/Chandru/output/CreateWorkbook2.xls";
	      try {
	    	  
	    	// Get Details from page
	    	    String Emp_ID = request.getParameter("username");
	    	    String Layer = request.getParameter("username");
	    	    String Process = request.getParameter("username");
	    	    String Status = request.getParameter("username");
	    	    System.out.println("given Emp_id = " + Emp_ID + "--" + Layer + "--" + Process + "--" + Status);
	    	    
	  		
	  	    // Open connection
	        conn = DriverManager.getConnection(jdbcUrl, username, password);
	        // Execute statement
	      //  stmt = conn.createStatement();
	      //  s = stmt.executeQuery("SELECT * FROM public.\"User_Details\" where \"Emp_ID\" =" + EmpID +";");	
		    //System.out.println("SELECT * FROM public.\"User_Details\" where \"Emp_ID\" =" + EmpID +";");
	    	  
	    	  
	    	  //create blank workbook
	  	/*	HSSFWorkbook workbook = new HSSFWorkbook();
	  		HSSFSheet spreadsheet = workbook.createSheet("Sheet1");
	  		try{
	  			HSSFRow row = spreadsheet.createRow(0);
	  			HSSFCell cell = row.createCell(0);
	  			String result = array[0] + " " + array[1] + " " + array[2];
	  			cell.setCellValue(result);
	  			FileOutputStream out = new FileOutputStream(new File("/Users/chandrasekarm/Chandru/CreateWorkbook1.xls"));
	  			workbook.write(out);
	  			out.close();
	  		}catch(Exception e){
	  			System.out.println("Error: " + e);
	  		}
	  		System.out.println("createworkbook.xls created!");*/
	    	  
	    	// Create new Excel workbook and sheet
	    	    HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
	    	    HSSFSheet xlsSheet = xlsWorkbook.createSheet();
	    	    short rowIndex = 0;
	    	 
	    	    // Execute SQL query
	    	   // PreparedStatement stmt = connection.prepareStatement("select * from " + tablename);
	    	   // ResultSet rs = stmt.executeQuery();
	    	 // Open connection
		        conn = DriverManager.getConnection(jdbcUrl, username, password);
		        stmt = conn.createStatement();
		        rs = stmt.executeQuery("SELECT * FROM \"Apple\".\"User_Details\";");	
	    	 
	    	    // Get the list of column names and store them as the first
	    	    // row of the spreadsheet.
	    	    ResultSetMetaData colInfo = rs.getMetaData();
	        //List colNames = new List();
	    	    List<String> colNames  = new ArrayList<String>();
	    	    HSSFRow titleRow = xlsSheet.createRow(rowIndex++);
	    	 
	    	   // for (int i = 1; i &lt;= colInfo.getColumnCount(); i++) {
	    	    for (int i = 1; i <= colInfo.getColumnCount(); i++) 
	    	    {
	    	        colNames.add(colInfo.getColumnName(i));
	    	        titleRow.createCell((short) (i-1)).setCellValue(
	    	          new HSSFRichTextString(colInfo.getColumnName(i)));
	    	        xlsSheet.setColumnWidth((short) (i-1), (short) 4000);
	    	      }
	    	 
	    	    // Save all the data from the database table rows
	    	    while (rs.next()) {
	    	      HSSFRow dataRow = xlsSheet.createRow(rowIndex++);
	    	      short colIndex = 0;
	    	      for (String colName : colNames) 
	    	      {
	    	        dataRow.createCell(colIndex++).setCellValue(new HSSFRichTextString(rs.getString(colName)));
	    	      }
	    	    }
	    	 
	    	    // Write to disk
	    	    xlsWorkbook.write(new FileOutputStream(filename));
	    	    JOptionPane.showMessageDialog(null, "Exported Successfully", "InfoBox: Excel File", JOptionPane.INFORMATION_MESSAGE);
	    	    response.sendRedirect("Attribute.jsp");
	    	  
	    	  
		    	   /* // Get Details from page
		    	    String EmpID = request.getParameter("username");
		    	    System.out.println("given EmpID = " + EmpID);
		  		String PWD = request.getParameter("password");
		  		System.out.println("given Pwd = " + PWD);
		  		
		  		
		  	    // Open connection
		        conn = DriverManager.getConnection(jdbcUrl, username, password);
		        // Execute statement
		        stmt = conn.createStatement();
		        
		        //rs = stmt.executeQuery("SELECT version()");
		        rs = stmt.executeQuery("SELECT * FROM public.\"User_Details\" where \"Emp_ID\" =" + EmpID +";");	
		        System.out.println("SELECT * FROM public.\"User_Details\" where \"Emp_ID\" =" + EmpID +";");	
		        // Get result
	            while (rs.next() ) {
		        //if (rs.next() ) {
		        
		            String empid = rs.getString("Emp_ID");
		            System.out.println("DB EmpID = " + empid);
		            String  emppwd = rs.getString("Password");
		            System.out.println("DB Pwd = " + emppwd);
		            
		            System.out.println(EmpID + "-" + empid + "---" + PWD + "-" + emppwd);
		            
		            //if((EmpID.trim() == empid.trim()) && (PWD.trim() == emppwd.trim()))
		            if(EmpID.equals(empid) && PWD.equals(emppwd))
		            {
		              	//System.out.println(rs.getString(1));	
			    			response.sendRedirect("success.html");
			    			return;
			    		}
			    		else
			    		{
			    			response.sendRedirect("error.html");
			    			return;
			    		}	            
	            
		        }
		        rs.close();
		        stmt.close();
		        conn.close();*/
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         //System.exit(0);
	      }	
		
	}
}
