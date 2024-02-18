




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
import java.io.*;
import java.sql.*;
import java.util.Properties;
/**
 * Servlet implementation class userRegistration
 */
@WebServlet("/userRegistration")
public class userRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	
		PrintWriter out = response.getWriter();  
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String username= request.getParameter("username");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/csiproject","root","");

            PreparedStatement ps = con.prepareStatement("insert into users(username,mail,password) values(?,?,?)");

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pass);
       int i =  ps.executeUpdate();
         if(i>0) {
            	 
            	mail(email,username);
            	out.print("<h1> THANK YOU FOR REGISTERING :)</h1>");
            	out.print("To Login please check your E-Mail ");
         }
            	
        }
        catch(Exception se) {
            se.printStackTrace();
        }
	}
	public static void mail(String to,String user) {
		
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

		            message.setSubject("E-MAIL VERIFICATION");

		            message.setText("YOUR SPECIAL LOGIN IS missing@"+user+"HERE IS THE LINK TO GET LOGGED IN:"+user+"(http://localhost:8081/missing/login.html)");

		            System.out.println("sending...");
		     
		            Transport.send(message);
		            System.out.println("Sent message successfully....");
		        } catch (MessagingException mex) {
		            mex.printStackTrace();
		        }

		    }

		}

