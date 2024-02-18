

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class retrievepassword
 */
@WebServlet("/retrievepassword")
public class retrievepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retrievepassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();  
        String email = request.getParameter("email");
      
        try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");  
    		
    		Statement ps=con.createStatement();
    		ResultSet rs=ps.executeQuery("select * from users where mail like '"+email+"'");
           if(rs.next()) {
        		mail(email);
out.print("CHECK YOUR E-MAIL");
           }
           else
           {
        	   out.print("wrong email");
           }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        	
        	}
        

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

}
	public static void mail(String to) {
		
		String from = "tarunpinnem@gmail.com";

		String host = "smtp.gmail.com";

				        Properties properties = System.getProperties();

				 
				        properties.put("mail.smtp.host", host);
				        properties.put("mail.smtp.port", "465");
				        properties.put("mail.smtp.ssl.enable", "true");
				        properties.put("mail.smtp.auth", "true");

				        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

				            protected PasswordAuthentication getPasswordAuthentication() {

				                return new PasswordAuthentication(from, "tarun@18");

				            }

				        });


				        session.setDebug(true);

				        try {
				            MimeMessage message = new MimeMessage(session);

				            message.setFrom(new InternetAddress(from));

				            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				            message.setSubject("PASSWORD CHANGE");

				            message.setText("(http://localhost:8081/missing/passwordchange.html)");

				            System.out.println("sending...");
				     
				            Transport.send(message);
				            System.out.println("Sent message successfully....");
				        } catch (MessagingException mex) {
				            mex.printStackTrace();
				        }

				    }

				}
