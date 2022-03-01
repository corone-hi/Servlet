package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {
   @Override
   protected void service(HttpServletRequest resquest, HttpServletResponse response)
         throws ServletException, IOException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter pw = response.getWriter();

      pw.println("<html>");
      pw.println("<head>");
      pw.println("<style>"
            + "table {"
            + "  border-collapse: collapse;"
            + "  width: 100%;"
            + "}"
            + "th, td {"
            + "  padding: 3px;"
            + "  text-align: center;"
            + "  border: 1px solid #DDD;"
            + "}"
            + "tr:hover {"
            + "background-color: #5AD3C0;"
            + "color: black;"
            + "}"
            + "a {"
            + "color: black;"
            + "}"
            
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
      pw.println("</head>");
      pw.println("<body>");
      
      pw.println("<body>\r\n"
               + "   <nav class=\"nav-menu\">\r\n"
               + "      <a href=\"index.html\">HOME</a>\r\n"
               + "      <a href=\"insert.html\">회원가입</a>\r\n"
               + "      <a href=\"list.do\">회원목록</a>\r\n"
               + "      <a href=\"near_store.html\">주변맛집</a>\r\n"
               + "   </nav>\r\n"
               + "   \r\n"
               + "</body>\r\n");



      try {

         Class.forName("com.mysql.cj.jdbc.Driver"); // DB연결 객체 생성
         con = DriverManager.getConnection("jdbc:mysql://54.180.125.242:3306/test?useUnicode=true&serverTimezone=Asia/Seoul", "aws", "1234");
         String sql = "select * from member;";
         pstmt = con.prepareStatement(sql);
         System.out.println("list DB접속");

         rs = pstmt.executeQuery();

         pw.println("<div>");
         pw.println("<br>");
         pw.println("<br>");
         pw.println("<table>");
         pw.println("<tr style=\"cursor: default; font-weight:bold; background-color:#5AD3C0\">");
         pw.println("<td>아이디</td>");
         pw.println("<td>비밀번호</td>");
         pw.println("<td>이메일</td>");
         pw.println("<td>전화번호</td>");
         pw.println("<td>등록일</td>");
         pw.println("<td>삭제</td>");
         pw.println("<td>수정</td>");
         pw.println("</tr>");

         while (rs.next()) {

            String id = rs.getString("id");
            String pwd = rs.getString("pwd");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            Timestamp regdate = rs.getTimestamp("regdate");

            pw.println("<tr>");
            pw.println("<td>" + id + "</td>");
            pw.println("<td>" + pwd + "</td>");
            pw.println("<td>" + email + "</td>");
            pw.println("<td>" + phone + "</td>");
            pw.println("<td>" + regdate + "</td>");
            pw.println("<td><a href='delete.do?id=" + id + "'>삭제</a></td>");
            pw.println("<td><a href='update.do?id=" + id + "'>수정</a></td>");
            pw.println("</tr>");

         }

         pw.println("</table>");
         pw.println("</div>");
         pw.println("<a href='index.html' style=\"text-aglign: center; font-weight:bold; "
               + "display:block; padding: 15px; width: 100%; text-align:center\">메인페이지로 이동</a>");

      } catch (ClassNotFoundException | SQLException e) {
         System.out.println(e.getMessage());
        pw.println(
              "<html>" + 
                    "<head>"+
                    "</head>" +
                    "<body>" + 
                    "<h3 style=\"font-size: 20; text-align:center; \">회원 목록을 불러오지 못했습니다.</h3>"+
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

      }

      pw.println("</body>");

      pw.println("</html>");
      pw.close();
   }

}