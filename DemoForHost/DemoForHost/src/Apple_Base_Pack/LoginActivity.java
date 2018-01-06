package Apple_Base_Pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;




/**
 * Servlet implementation class LoginActivity
 */
@WebServlet("/LoginActivity")
public class LoginActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JFrame frame;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public LoginActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		
		//System.out.println(response.getWriter().append("Served at: ").append(request.getContextPath()));
		String jdbcUrl = "jdbc:postgresql://localhost:5432/Apple_Maps";
	    String username = "postgres";
	    String password = "Apple@1";

	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    frame = new JFrame("Show Message Box");
	    //frame.setVisible(true);
	      try {
		    	    // Get Details from page
		    	    String EmpID = request.getParameter("username");
		    	    System.out.println("given EmpID = " + EmpID);
		  		String PWD = request.getParameter("password");
		  		System.out.println("given Pwd = " + PWD);
		  		
		  		
		  	    // Open connection
		        conn = DriverManager.getConnection(jdbcUrl, username, password);
		        // Execute statement
		        stmt = conn.createStatement();
		        
		        //rs = stmt.executeQuery("SELECT version()");
		        rs = stmt.executeQuery("SELECT * FROM \"Apple\".\"User_Details\" where \"Emp_ID\" =" + EmpID +";");	
		        System.out.println("SELECT * FROM \"Apple\".\"User_Details\" where \"Emp_ID\" =" + EmpID +";");	
		        // Get result
	           // while (rs.next() ) {
		        if (rs.next() ) 
		        {
		            String empid = rs.getString("Emp_ID");
		            System.out.println("DB EmpID = " + empid);
		            String  emppwd = rs.getString("Password");
		            System.out.println("DB Pwd = " + emppwd);
		            String empname = rs.getString("Emp_Name");
		            
		            
		            System.out.println(EmpID + "-" + empid + "---" + PWD + "-" + emppwd);
		            
		            //if((EmpID.trim() == empid.trim()) && (PWD.trim() == emppwd.trim()))
		            if(EmpID.equals(empid) && PWD.equals(emppwd))
		            {
		              	//System.out.println(rs.getString(1));
			            //String a=request.getParameter("username");
		            		//request.setAttribute("name", a);
			            	//RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
			            	//rd.forward(req, resp);
	                    String empdetails = empid +","+ empname;   
	                    System.out.println("Emp details = " + empdetails);
	                    JOptionPane.showMessageDialog (frame,"LoginSuccess");
	                    request.setAttribute("EmpDtls", empdetails);
		            		RequestDispatcher rd = request.getRequestDispatcher("Attribute.jsp");
		            		rd.forward(request, response);
		            		
			    			//response.sendRedirect("success.html");
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
		        conn.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         //System.exit(0);
	      }	

		
		
	}

}
