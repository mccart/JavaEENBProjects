package bank;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class DemoFormServlet extends HttpServlet {
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Banking Form Demo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> The input1 form parameter is: " + request.getParameter("input1"));
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
    
}
