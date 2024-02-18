

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Usercase
 */
@WebServlet("/Usercase")
public class Usercase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usercase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		 response.setContentType("text/html");
		String username=login.username;
	
		try{  
			 Class.forName("com.mysql.jdbc.Driver");
			   Connection con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");
				            PreparedStatement ps = con.prepareStatement("select pincode,name,address,age,date from missingcases where username=? ");
				            ps.setString(1, username);
    out.print("<html>"); 
	out.print("<head>"); 
	out.print("<style>"); 
	out.print("html{background-image:url(download.png);-webkit-background-size: cover; background-size: cover;} td,th{background-color:white} td{text-align:center;} .photo{size:100%;background-color:green; border-color:green;border-radius:1em; font-weight:bolder;border: 1px solid green;}.delete{size:100%;background-color:red; border-color:green;border-radius:1em; font-weight:bolder;border: 1px solid green;}");
	
	out.print(".update{size:100%;background-color:red; border-color:green;border-radius:1em; font-weight:bolder;border: 1px solid green;}");
	out.print("table{border-color:white;}"); 

	out.print("</style>"); 

	out.print("</head>"); 


	out.print("<body >"); 

		out.print("<center><table width=50% border=5>");  
		 out.print("<tr><th>PIN-CODE</th>");
		 out.print("<th>NAME</th>");
		 out.print("<th>ADDRESS</th>");
		 out.print("<th>AGE</th>");
		 out.print("<th>Date of Missing</th>");
		 out.print("<th>UPDATE DETAILS </th>");
		 out.print("<th>IMAGE OF THE PERSON</th>");
		 out.print("<th>DELETE CASE</th></tr>");
		 ResultSet rs =ps.executeQuery();
		while(rs.next())  
		{  
		  
			  out.print("<tr><form action=updatecases method=post><td><input   type=int class=pincode name=pincode  id=pincode value="+rs.getInt(1)+"></td><td background-color=gray>"+rs.getString(2)+"</td><td><input   type=text class=address name=address value="+rs.getString(3)+" id=address></td><td background-color=gray><input   type=int class=age name=age value="+rs.getInt(4)+"></td><td><input   type=text class=date name=date value="+rs.getString(5)+"></td><td><input  id=update type=submit class=update name=update value="+rs.getString(2)+"></form></td><td>"+"<form action=./imageretrieve><input   type=submit class=photo name=photo value="+rs.getString(2)+" id=photo></form></td><td>"+"<form action=./deletecases method=post><input   type=submit class=delete name=delete value="+rs.getString(2)+" id=delete></form></td></tr>");
		  
		}
		out.print("</table></center>");  
		out.print("</body>"); 

		out.print("</html>"); 

		rs.close();
		con.close();
		}
		 
		
		
		              
		catch(Exception e2) { 
			e2.printStackTrace();
		}  
	}

}
