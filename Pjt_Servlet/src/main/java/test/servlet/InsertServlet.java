package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      // 1. �Ķ���ͷ� ���۵� ���� ������.
      request.setCharacterEncoding("UTF-8");
      String id = request.getParameter("id");
      String pwd = request.getParameter("pwd");
      String pwdc = request.getParameter("pwdc");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      
      // navbar ���� �ڵ�
	  
	  response.setContentType("text/html;charset=utf-8");
	  PrintWriter pw = response.getWriter();
	  
	  pw.println("<html>");
	  pw.println("<head></head>");
	  
	  pw.println("<style>"
			  + "@font-face {\r\n"
			  + "   font-family: 'SANJUGotgam';\r\n"
			  + "   src:\r\n"
			  + "      url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/SANJUGotgam.woff')\r\n"
			  + "      format('woff');\r\n"
			  + "   font-weight: normal;\r\n"
			  + "   font-style: normal;\r\n"
			  + "}\r\n"
			  + "\r\n"
			  + "* {\r\n"
			  + "   margin: 0;\r\n"
			  + "   padding: 0;\r\n"
			  + "   box-sizing: border-box;\r\n"
			  + "}\r\n"
			  + "\r\n"
			  + ".nav-menu {\r\n"
			  + "   display: flex;\r\n"
			  + "   justify-content: space-around;\r\n"
			  + "   align-items: center;\r\n"
			  + "   height: 100px;\r\n"
			  + "   background-color: #e3e3e3;\r\n"
			  + "}\r\n"
			  + "\r\n"
			  + ".nav-menu a {\r\n"
			  + "   font-family: 'SANJUGotgam';\r\n"
			  + "   font-size: 20px;\r\n"
			  + "   text-decoration: none;\r\n"
			  + "   position: relative;\r\n"
			  + "   padding: 6px 12px;\r\n"
			  + "   color: black;\r\n"
			  + "}\r\n"
			  + "\r\n"
			  + ".nav-menu a::after {\r\n"
			  + "   content: \"\";\r\n"
			  + "   position: absolute;\r\n"
			  + "   bottom: 0;\r\n"
			  + "   left: 50%;\r\n"
			  + "   transform: translateX(-50%);\r\n"
			  + "   width: 0;\r\n"
			  + "   height: 4px;\r\n"
			  + "   background: #40E0D0;\r\n"
			  + "   transition: all .5s ease-out;\r\n"
			  + "}\r\n"
			  + "\r\n"
			  + ".nav-menu a:hover::after {\r\n"
			  + "   width: 100%;\r\n"
			  + "}\r\n"
			  
           + "</style>");
//     pw.println("<body>");
	  pw.println("<body>\r\n"
			  + "   <nav class=\"nav-menu\">\r\n"
			  + "      <a href=\"index.html\">HOME</a>\r\n"
			  + "      <a href=\"insert.html\">ȸ������</a>\r\n"
			  + "      <a href=\"list.do\">ȸ�����</a>\r\n"
			  + "      <a href=\"near_store.html\">�ֺ�����</a>\r\n"
			  + "   </nav>\r\n"
			  + "   \r\n"
			  + "</body>\r\n");
	  // ���̵� �ߺ�Ȯ��
	  Connection IDcon = null;
	  PreparedStatement IDpstmt = null;
	  int findID = 0;
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		IDcon = DriverManager.getConnection("jdbc:mysql://54.180.125.242:3306/test?useUnicode=true&serverTimezone=Asia/Seoul", "aws", "1234");
		String IDsql = "select * from member where id=?";
	    IDpstmt = IDcon.prepareStatement(IDsql);
	  	IDpstmt.setString(1, id);
	  	System.out.println("idcheck DB����");
	  	ResultSet rs = IDpstmt.executeQuery();
	  	findID = rs.next() ? 1 : 0; 
	  } catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	  } finally {
		  try {
			  if (IDpstmt != null)
				  IDpstmt.close();
			  
			  if (IDcon != null)
				  IDcon.close();
			  
		  } catch (SQLException se) {
			  System.out.println(se.getMessage());
		  } 
	  }
	  if (findID == 1) {
		  pw.println(
				  "<html>" + 
						  "<head>"+
						  "</head>" +
						  "<body>" + 
						  "<h3 style=\"font-size: 20; text-align:center; \">�ߺ��� ���̵� �Դϴ�.</h3>"+
						  "<a href='javascript:history.go(-1)' style=\"display:block; width:100%; color: black; font-size: 20; text-align:center \">������������ ����</a>" +
						  "</body>" + 
						  "</html>"
				  );
		  pw.close();
		  // ��й�ȣ ��ġ Ȯ��
      } else if (pwd.equals(pwdc)) {
    	  int n = 0;
    	  PreparedStatement pstmt = null;
    	  Connection con = null;
    	  
    	  try {
    		  
    		  // 2. ���۵� ���� db�� ����.
    		  
    		  // ���� Ŭ���� �ε�
    		  Class.forName("com.mysql.cj.jdbc.Driver"); // DB���� ��ü ����
    		  con = DriverManager.getConnection("jdbc:mysql://54.180.125.242:3306/test?characterEncoding=euckr&useUnicode=true&mysqlEncoding=euckr&serverTimezone=UTC", "aws", "1234");
    		  String sql = "insert into member values( ?,?,?,?, sysdate());";
    		  pstmt = con.prepareStatement(sql);
    		  System.out.println("insert DB����");
    		  
    		  pstmt.setString(1, id);
    		  pstmt.setString(2, pwd);
    		  pstmt.setString(3, email);
    		  pstmt.setString(4, phone);
    		  
    		  // sql���� �����ϱ�
    		  
    		  n = pstmt.executeUpdate();
    	  } catch (ClassNotFoundException ce) {
    		  System.out.println(ce.getMessage());
    	  } catch (SQLException se) {
    		  System.out.println(se.getMessage());
    	  } finally {
    		  try {
    			  
    			  if (pstmt != null)
    				  pstmt.close();
    			  
    			  if (con != null)
    				  con.close();
    			  
    		  } catch (SQLException se) {
    			  System.out.println(se.getMessage());
    		  }
    	  }
    	  
    	  // 3. �����(Ŭ���̾�Ʈ)�� ����� �����ϱ�.

    	  if (n > 0) {
    		  pw.println(
    				  "<html>" + 
    						  "<head>"+
    						  "</head>" +
    						  "<body>" + 
    						  "<h3 style=\"font-size: 20; text-align:center; \">���Կ� �����߽��ϴ�.</h3>"+
    						  "<a href='index.html' style=\"display:block; width:100%; color: black; font-size: 20; text-align:center \">Ȩ���� ����</a>" +
    						  "</body>" + 
    						  "</html>"
    				  );
    		  pw.close();
    	  } else {
    		  pw.println(
    				  "<html>" + 
    						  "<head>"+
    						  "</head>" +
    						  "<body>" + 
    						  "<h3 style=\"font-size: 20; text-align:center; \">���Կ� �����߽��ϴ�.</h3>"+
    						  "<a href='javascript:history.go(-1)' style=\"display:block; width:100%; color: black; font-size: 20; text-align:center \">������������ ����</a>" +
    						  "</body>" + 
    						  "</html>"
    				  );
    		  pw.close();
    	  }
      } else {
		  pw.println(
				  "<html>" + 
						  "<head>"+
						  "</head>" +
						  "<body>" + 
						  "<h3 style=\"font-size: 20; text-align:center; \">��й�ȣ�� ��ġ���� �ʽ��ϴ�.</h3>"+
						  "<a href='javascript:history.go(-1)' style=\"display:block; width:100%; color: black; font-size: 20; text-align:center \">������������ ����</a>" +
						  "</body>" + 
						  "</html>"
				  );
		  pw.close();
      }
   }
}