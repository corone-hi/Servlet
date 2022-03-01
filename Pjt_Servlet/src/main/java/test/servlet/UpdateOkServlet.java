package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOkServlet extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)
         throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      String id = req.getParameter("id");
      String pwd = req.getParameter("pwd");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");

      PreparedStatement pstmt = null;
      Connection con = null;
      int n = 0;
      resp.setContentType("text/html;charset=UTF-8");
      PrintWriter pw = resp.getWriter();

      try {

         Class.forName("com.mysql.cj.jdbc.Driver"); // DB연결 객체 생성
         con = DriverManager.getConnection("jdbc:mysql://54.180.125.242:3306/test?useUnicode=true&serverTimezone=Asia/Seoul", "aws", "1234");
         String sql = "update member set pwd=?,email=?,phone=? where id=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, pwd);
         pstmt.setString(2, email);
         pstmt.setString(3, phone);
         pstmt.setString(4, id);

         n = pstmt.executeUpdate();

         if (n > 0) {

            resp.sendRedirect("list.do");

         } else {
           throw new Exception();


         }

      } catch (Exception e) {
         System.out.println(e.getMessage());
         pw.println(
                 "<html>" + 
                       "<head>"+
                       "</head>" +
                       "<body>" + 
                          "<h3 style=\"font-size: 20; text-align:center; \">정보 수정에 실패했습니다.</h3>"+
                          "<a href='index.html' style=\"display:block; width:100%; color: black; font-size: 20; text-align:center \">홈으로 가기</a>" +
                       "</body>" + 
                 "</html>"
           );
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException se) {
            System.out.println(se.getMessage());
         }
          pw.close();
      }

   }

}