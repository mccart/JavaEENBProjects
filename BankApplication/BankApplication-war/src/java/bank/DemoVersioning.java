package bank;

import java.io.*;
import java.util.Collection;

import javax.servlet.*;
import javax.servlet.http.*;

public class DemoVersioning extends HttpServlet {
    
    
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Versioning Demo</title>");
        out.println("</head>");
        out.println("<body>");
        
        try {
            BankMgrDelegate bankMgrDelegate = new BankMgrDelegate();
            bankMgrDelegate.init();
            Collection accounts1 = bankMgrDelegate.getAccountsByCustomerId(1);
            Collection accounts2 = bankMgrDelegate.getAccountsByCustomerId(1);
            HttpSession session = request.getSession();
            session.setAttribute("accounts1",accounts1);
            session.setAttribute("accounts2",accounts2);
        } catch (BankException ex) {
            out.println("<h1>Error: " + ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            out.close();
            return;
        }
        
        out.println("<h2>Two seperate copies of Customer 1's accounts have been stored in a HttpSession</h2><br />");
        out.println("<a href='increment_accounts?name=accounts1' target='_blank'>Update first set of accounts</a><br />");
        out.println("<a href='increment_accounts?name=accounts2' target='_blank'>Update second set of accounts</a><br />");
        out.println("</body>");
        out.println("</html>");
        out.close();
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
