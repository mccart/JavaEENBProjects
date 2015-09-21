package bank;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DemoDynamicForm extends HttpServlet {
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String userValue = "";
        String passValue = "";
        
        if(request.getParameter("user") != null) {
            userValue = request.getParameter("user");
        }
        
        if(request.getParameter("pass") != null) {
            passValue = request.getParameter("pass");
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Banking Form Demo</title>");
        out.println("</head>");
        out.println("<body>");
        if(request.getAttribute("message") != null) {
            out.println("<h1>" + request.getAttribute("message") + "</h1>");
        }
        out.println("<form action='dyna_form' method='POST'>");
        out.println("User:<input type='text' name='user' value='" + userValue + "'>");
        out.println("Password:<input type='password' name='pass' value ='" + passValue + "'>");
        out.println("<input type='submit'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request,response);
    }
}
