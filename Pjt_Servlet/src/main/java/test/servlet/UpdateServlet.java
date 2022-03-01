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

public class UpdateServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
      // 1. parameter�� ���۵� id���.
      String id = req.getParameter("id");
      
      resp.setContentType("text/html;charset=UTF-8");
      PrintWriter pw = resp.getWriter();
      pw.println("<html>");
      pw.println("<head>"
            +"<style type=\"text/css\">\r\n"
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
            + "   font-family: 'SANJUGotgam';\r\n"
            + "   margin: 0;\r\n"
            + "   padding: 0;\r\n"
            + "   box-sizing: border-box;\r\n"
            + "}\r\n"
            + "\r\n"
            
            + ".input {\r\n"
            + "   width: 50%;\r\n"
            + "   height: 25px;\r\n"
            + "   border: none;\r\n"
            + "   /*    border-bottom: 1px solid black; */\r\n"
            + "   margin-top: 15px;\r\n"
            + "   margin-bottom: 15px;\r\n"
            + "   border-radius: 5px;\r\n"
            + "}\r\n"
            + "\r\n"
            + "input::placeholder {\r\n"
            + "   opacity: 0.5;\r\n"
            + "   text-align: center;\r\n"
            + "}\r\n"
            + "\r\n"
            + ".inputBtn {\r\n"
            + "   margin-top:15px;\r\n"
            + "   width: 140px;\r\n"
            + "   height: 30px;\r\n"
            + "   border: solid 2px white;\r\n"
            + "   border-radius: 5px;\r\n"
            + "   background-color: #5ad3c0;\r\n"
            + "   color: white;\r\n"
            + "   \r\n"
            + "}\r\n"
            + ".inputBtn:hover {\r\n"
            + "   color: orange;\r\n"
            + "   border: solid 2px orange;\r\n"
            + "}\r\n"
            + "\r\n"
            + "\r\n"
            + "</style>"
            + "</head>");
      pw.println("<body>");

      PreparedStatement pstmt = null;
      Connection con = null;
      ResultSet rs = null;

      try {
         Class.forName("com.mysql.cj.jdbc.Driver"); // DB���� ��ü ����
         con = DriverManager.getConnection("jdbc:mysql://54.180.125.242:3306/test?useUnicode=true&serverTimezone=Asia/Seoul", "aws", "1234");
         String sql = "select * from member where id=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         System.out.println("update �ϱ��� ������ ������ �������� ���� select DB����");

         // sql���� �����ϱ�
         rs = pstmt.executeQuery();
         rs.next();
         String pwd = rs.getString("pwd");
         String email = rs.getString("email");
         String phone = rs.getString("phone");
         pw.println("<br/><br/><br/>");
         pw.println("<div style=\"padding-bottom: 50px; margin-top: 50px;\">");
         pw.println("<form method='post' action='updateok.do'>");
         pw.println("<fieldset id=\"regbox\" style=\"text-align: center; border: none;\"><br/><br/>");
         pw.println("<input type='hidden' name='id' value='" + id + "'/>");      
         pw.println("���̵�<input type='text' name='id' value='" + id + "' disabled='disabled'/><br/><br/>");
         pw.println("��й�ȣ<input type='password' name='pwd' required=\"true\" maxlength=\"20\" title=\"20�� �̸����� �ۼ����ּ���.\" pattern=\"^[\\S]+$\" placeholder=\"���ο� ��й�ȣ�� �Է����ּ���.\" value='" + pwd + "'/><br/><br/>");
         pw.println("email<input type='email' name='email' required=\"true\" maxlength=\"30\" title=\"30�� �̸��� �̸��� �ּҸ� ��밡���մϴ�.\" pattern=\"^[\\S]+@.+[.][\\S]+$\" placeholder=\""+ email +"\" value='" + email + "'/><br/><br/>");
         pw.println("phone<input type='tel' name='phone' pattern=\"^[0-9]{3}-[0-9]{4}-[0-9]{4}$\" title=\"������ ����� �����ּ���.\" required=\"true\" placeholder=\"" + phone + "\" value='" + phone + "'/><br/><br/>");
         pw.println("<input class=\"inputBtn\" type='submit' value='����'/><br/>");
         pw.println("</fieldset>");
         pw.println("</form>");
         pw.println("</div>");

      } catch (ClassNotFoundException | SQLException ce) {
         System.out.println(ce.getMessage());
        pw.println(
              "<html>" + 
                    "<head>"+
                    "</head>" +
                    "<body>" + 
                    "<h3 style=\"font-size: 20; text-align:center; \">ȸ�� ������ �ҷ����� ���߽��ϴ�.</h3><br/>"+
                    "<a href='index.html' style=\"display:block; width:100%; color: black; font-size: 20; text-align:center \">Ȩ����</a>" +
                    "</body>" + 
                    "</html>"
              );
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException se) {
            System.out.println(se.getMessage());
         }
         pw.println("</body>");
         pw.println("</html>");
         pw.close();
      }

   }

}