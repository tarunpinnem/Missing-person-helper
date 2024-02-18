

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deletecases
 */
@WebServlet("/deletecases")
public class deletecases extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletecases() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		 response.setContentType("text/html");
		String name=login.username;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject", "root", "");
		  PreparedStatement ps=con.prepareStatement("delete from missingcases where name=?");  
          ps.setString(1,name);
          int i=ps.executeUpdate();
				            if(i!=0) {
				            	
			                    out.print("<header><center><h3>Deleting row...</h3></center></header>");

				                    request.getRequestDispatcher("home.html").include(request, response);  
				                    out.print("Deleting row...");
				            }
				            		else if (i==0)
				            		{
				            		out.print("<br>Row has been deleted successfully.");
				            		
				            		}
				            con.close();
				            		
	  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
	}

}
