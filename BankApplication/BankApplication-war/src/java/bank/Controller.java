package bank;

import java.io.*;
import java.util.Collection;

import javax.servlet.*;
import javax.servlet.http.*;

public class Controller extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action == null) {
            action = "none";
        }
        
        if(action.equals("showme")) {
            try {
                Customer cd = bankMgrDelegate.getMyData();
                displayCustomerData("/showcustomerdetails.jsp", cd, request, response);
            } catch (BankException be) {
                showError(response, be.getMessage());
            }
            return;
        } else if(action.equals("scriptlet")) {
            String useridStr = request.getParameter("userid");
            if(useridStr == null) {
                showError(response, "userid must be provided");
            }
            try {
                int userid = Integer.parseInt(useridStr);
                Customer cd = bankMgrDelegate.getCustomer(userid);
                displayCustomerData("/scriptlet.jsp", cd, request, response);
            } catch (NumberFormatException nfe) {
                showError(response, "userid is not valid");
            } catch (BankException be) {
                showError(response, be.getMessage());
            } 
            return;
        } else if(action.equals("usebean")) {
            String useridStr = request.getParameter("userid");
            if(useridStr == null) {
                showError(response, "userid must be provided");
            }
            try {
                int userid = Integer.parseInt(useridStr);
                Customer cd = bankMgrDelegate.getCustomer(userid);
                displayCustomerData("/usebean.jsp", cd, request, response);
            } catch (NumberFormatException nfe) {
                showError(response, "userid is not valid");
            } catch (BankException be) {
                showError(response, be.getMessage());
            } 
            return;
        } else if(action.equals("el")) {
            String useridStr = request.getParameter("userid");
            if(useridStr == null) {
                showError(response, "userid must be provided");
            }
            try {
                int userid = Integer.parseInt(useridStr);
                Customer cd = bankMgrDelegate.getCustomer(userid);
                displayCustomerData("/el.jsp", cd, request, response);
            } catch (NumberFormatException nfe) {
                showError(response, "userid is not valid");
            } catch (BankException be) {
                showError(response, be.getMessage());
            } 
            return;
        } else if(action.equals("jstl")) {
            String amount = request.getParameter("amount");
            if(amount == null) {
                showError(response, "amount must be provided");
            }
            try {
                Collection collection = bankMgrDelegate.getCustomerDataByBalance(Double.parseDouble(amount));
                request.setAttribute("accounts", collection);
                RequestDispatcher rd = request.getRequestDispatcher("jstl.jsp");
                rd.forward(request, response);
            } catch (BankException be) {
                showError(response, be.getMessage());
            } 
            return;
        } else if(action.equals("getbalance")) {
            String useridStr = request.getParameter("userid");
            if(useridStr == null) {
                showError(response, "userid must be provided");
            }
            try {
                int userid = Integer.parseInt(useridStr);
                Customer cd = bankMgrDelegate.getCustomer(userid);
                displayCustomerData("/showcustomerdetails.jsp", cd, request, response);
            } catch (NumberFormatException nfe) {
                showError(response, "userid is not valid");
            } catch (BankException be) {
                showError(response, be.getMessage());
            } 
            return;
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
            rd.forward(request,response);
        }
    }
    
    private void showError(HttpServletResponse response, String msg)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Error</h1>");
        out.println(msg);
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

    public void init() throws ServletException {
        try {
            bankMgrDelegate = new BankMgrDelegate();
            bankMgrDelegate.init();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void displayCustomerData(String url, Customer cd, HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        request.setAttribute("customerdata", cd);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    private BankMgrDelegate bankMgrDelegate = null;
}
