

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updatecases
 */
@WebServlet("/updatecases")
public class updatecases extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatecases() {
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
		PrintWriter out = response.getWriter();  
		 response.setContentType("text/html");
		String name=request.getParameter("update");
		int pincode=Integer.valueOf(request.getParameter("pincode"));
		String address=request.getParameter("address");
		int age=Integer.valueOf(request.getParameter("age"));
		String date=request.getParameter("date");

		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject", "root", "");
		  PreparedStatement ps=con.prepareStatement("update  missingcases set pincode=?,address=?,age=?,date=? where name=?");  
         ps.setString(5,name);
         ps.setInt(1,pincode);
         ps.setString(2,address);
         ps.setInt(3,age);
         ps.setString(4,date);
         int i=ps.executeUpdate();
				            if(i!=0) {
			            		out.print("<header><center><h3>Updated row...</h3></center></header>");

				                    request.getRequestDispatcher("home.html").include(request, response);  

				            }
				            		else if (i==0)
				            		{
				            		out.println("<br>Row has been updated successfully.");
				            		
				            		}
				            con.close();
				            		
	  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
	}

}
