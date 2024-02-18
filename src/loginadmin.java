

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginadmin
 */
@WebServlet("/loginadmin")
public class loginadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginadmin() {
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
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
     
        try {

            Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");
            PreparedStatement ps = con.prepareStatement("select * from admins where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs1 =ps.executeQuery();
           if(rs1.next()) {
        	
            RequestDispatcher rs = request.getRequestDispatcher("adminhome.html");
            rs.forward(request, response);
      
        }
        else
        {
           out.println("Username or Password or key incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("adminlogin.html");
           rs.include(request, response);
        }

        }
        catch(Exception e) {
        	out.print("something went wrong");
        }
	}

}
