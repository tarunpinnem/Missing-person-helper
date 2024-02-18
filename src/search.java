
import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		 response.setContentType("text/html");
		String pincode=request.getParameter("pincode");  
		int pin=Integer.valueOf(pincode);  
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");  
		
		Statement ps=con.createStatement();
	ResultSet rs=ps.executeQuery("select pincode,name,address,age,date from missingcases where pincode="+pin+"");	
	
	out.print("<html>"); 
	out.print("<head>"); 
	out.print("<style>"); 
	out.print("html{background-color:black;} td,th{background-color:white} td{text-align:center;} .photo{background-color:green; border-color:green;border-radius:1em; font-weight:bolder;border: 1px solid green;}"); 
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
		 out.print("<th>IMAGE OF THE PERSON</th></tr>");
		
		while(rs.next())  
		{  
		  
		  out.print("<tr><td>"+rs.getInt(1)+"</td><td background-color=gray>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td background-color=gray>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td><td>"+"<form action=./imageretrieve><input   type=submit class=photo name=photo value="+rs.getString(2)+" id=photo></form></td></tr>");
		  
		}
		out.print("</table></center>");  
		out.print("</body>"); 

		out.print("</html>"); 

		
		}
		 
		
		
		              
		catch(Exception e2) { 
		}  
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
