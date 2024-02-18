

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.*;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public static  String user="";
    
     public static String username="";
     /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	     username = request.getParameter("username");
	        user=user+username;
	        String pass = request.getParameter("password");
	        String key=request.getParameter("key");
	        user=user+username;
	        try {
    
	            Class.forName("com.mysql.jdbc.Driver");
   Connection con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");
	            PreparedStatement ps = con.prepareStatement("select * from users where username=? and password=?");
	            ps.setString(1, username);
	            ps.setString(2, pass);
	            ResultSet rs1 =ps.executeQuery();
	           if(rs1.next()) {
	        	if(key.equals("missing@"+rs1.getString("username"))) {
	            RequestDispatcher rs = request.getRequestDispatcher("home.html");
	            rs.forward(request, response);
	        	}
	        	
	        }
	        else
	        {
	           out.println("Username or Password or key incorrect");
	           RequestDispatcher rs = request.getRequestDispatcher("login.html");
	           rs.include(request, response);
	        }

	        }
	        catch(Exception e) {
	        	out.print("something went wrong");
	        }
	}
	public static  String getUsername(){
		return user;
	}
	
}
