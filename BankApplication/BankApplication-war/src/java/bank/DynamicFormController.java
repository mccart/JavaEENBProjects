package bank;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DynamicFormController extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if(request.getParameter("user") != null && 
                request.getParameter("pass") != null && 
                request.getParameter("user").equals("fred") &&
                request.getParameter("pass").equals("secret")) {
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Banking Dynamic Form Demo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>You got it right</h1>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        
        } else {
            RequestDispatcher disp = getServletContext().getRequestDispatcher("/login");
            request.setAttribute("message", "The only valid user in fred with a password of secret");
            disp.forward(request,response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
