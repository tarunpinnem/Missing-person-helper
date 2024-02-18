
import java.sql.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




/**
 * Servlet implementation class registercase
 */
@WebServlet(name="registercase",urlPatterns = {"/registercase"})
@MultipartConfig(maxFileSize = 16177216)
public class registercase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registercase() {
        super();
        // TODO Auto-generated constructor stub
    }
 PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		out =response.getWriter();
		int result = 0;
		Part part=request.getPart("personimage");
		String name=request.getParameter("personname");
		String address=request.getParameter("personaddress");
		String personpincode=request.getParameter("pincode");
         int pincode=Integer.valueOf(personpincode);
         String personage=request.getParameter("personage");
         int age=Integer.valueOf(personage);
         String personcontact=request.getParameter("personcontact");
         int contact=Integer.valueOf(personcontact);
 		String date=request.getParameter("persondate");
 		String username=login.username;
		if(part!=null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");
				PreparedStatement ps=con.prepareStatement("insert into missingcases(name,address,pincode,age,contact,date,photo,username) values(?,?,?,?,?,?,?,?)");
				InputStream is=part.getInputStream();
				ps.setBlob(7, is);
				ps.setString(1,name);
				ps.setString(2, address);
				ps.setInt(3, pincode);
				ps.setInt(4, age);
				ps.setInt(5, contact);
				ps.setString(6, date);
				ps.setString(8, username);
				result = ps.executeUpdate();
				if(result > 0) {
					response.sendRedirect("view.html?message=SUCCESSFULLY+REGISTERED+THE+MISSING+CASE");
				}
				else
				{
					response.sendRedirect("home.html?message=SOME+ERROR+OCCURED");
				}
			}
			catch(Exception e) {
				out.println(e);
			}
		}
}
}