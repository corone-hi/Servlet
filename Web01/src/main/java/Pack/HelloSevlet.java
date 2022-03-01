package Pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/Web01/HelloSevlet

//@WebServlet("/HelloSevlet")
public class HelloSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public HelloSevlet() {
        super();
    }

	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Call");
		//out을 이용해서 html을 만든다.
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
//		out.println("<html>");
//			out.println("<head>");
//			out.println("</head>");
//			out.println("<body>");
//				out.println("<h1>원숭이</h1>");				
//			out.println("</body>");
//		out.println("</html>");
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		out.println(
				"<html>" + 
						"<head>"+
						"</head>" +
						"<body>" + 
							"<h1>하마 1마리</h1>"+
						"</body>" + 
				"</html>"
		);
		
		out.close();
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//	
}
