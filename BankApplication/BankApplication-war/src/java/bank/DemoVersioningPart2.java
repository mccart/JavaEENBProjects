package bank;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.*;
import javax.servlet.http.*;

public class DemoVersioningPart2 extends HttpServlet {
    
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
        out.println("<title>Versioning Demo Part 2</title>");
        out.println("</head>");
        out.println("<body>");
        
        try {
            HttpSession session = request.getSession();
            BankMgrDelegate bankMgrDelegate = new BankMgrDelegate();
            bankMgrDelegate.init();
            Collection accounts1 = (java.util.Collection)session.getAttribute("accounts1");
            Collection accounts2 = (java.util.Collection)session.getAttribute("accounts2");
            
            Collection accounts;
            if(request.getParameter("name").equals("account1")) {
                accounts = accounts1;
            } else {
                accounts = accounts2;
            }
            Iterator iterator = accounts.iterator();
            while(iterator.hasNext()) {
                Account account = (Account)iterator.next();
                account.setBalance(account.getBalance() + 1.0);
                bankMgrDelegate.updateAccount(account);
            }
        } catch (BankException ex) {
            out.println("<h1>Error updating " + request.getParameter("name") + ": " + ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            out.close();
            return;
        }
        
        out.println("<h1>Update of " + request.getParameter("name") + " successful</h1>");
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
