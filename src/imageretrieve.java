import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class imageretrieve
 */
@WebServlet("/imageretrieve")
public class imageretrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageretrieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Blob image = null;
	      Connection con = null;
	      Statement stmt = null;
	      ResultSet rs = null;
	      ServletOutputStream out = response.getOutputStream();
	      try {
	    	  String name=request.getParameter("photo");
	      Class.forName("com.mysql.jdbc.Driver");
	      con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root",""); 
	      stmt = con.createStatement();
	      rs = stmt.executeQuery("select photo from missingcases where  name='"+name+"'");
	      if (rs.next()) {
	      image = rs.getBlob(1);
	      } else {
	      response.setContentType("text/html");

	      out.println("<font color='red'>image not found for given id</font>");

	      return;
	      }
	      response.setContentType("image/jpg");
	 InputStream in = image.getBinaryStream();
	  int length = (int) image.length();
	  int bufferSize = 1024;
	  byte[] buffer = new byte[bufferSize];
	  while ((length = in.read(buffer)) != -1) {
	  out.write(buffer, 0, length);
	  }
	  in.close();
	  out.flush();

	} catch (Exception e) {
	  response.setContentType("text/html");
	  out.println("<html><head><title>Unable To Display image</title></head>");
	  out.println("<body><h4><font color='red'>Image Display Error=" + e.getMessage() +"</font></h4></body></html>");
	  return;
	  } 
	 finally {
	  try {
	  rs.close();
	  stmt.close();
	  con.close();
	  }
	  catch(Exception e) {
		  
	  }
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
