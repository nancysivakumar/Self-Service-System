import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servletextends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project","root","nancy@2522");
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String username=request.getParameter("Username");
			String password=request.getParameter("Password");
			PreparedStatement st=con.prepareStatement("select * from login where Username=?");
			st.setString(1,username);
			st.setString(2,password);
			System.out.println("connected");
			 out.print("<center><table width=25% border=1></center>");

             out.print("<center><h1>Result:</h1></center>");

             ResultSet rs=st.executeQuery();               
             ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();

             while(rs.next())
                {
                out.print("<tr></center>");
                out.print("<tr><td>"+rsmd.getColumnName(1)+"</td>");
                out.print("<td>"+rs.getInt(1)+"</td></tr></center>");
                out.print("<tr><td>"+rsmd.getColumnName(2)+"</td>");
                out.print("<td>"+rs.getString(2)+"</td></tr>");                

             }
             out.print("</table>");		
		}catch(Exception e) {
			System.out.println(e);
		}
	}	
}
