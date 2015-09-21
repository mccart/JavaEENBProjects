/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trader.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trader.*;

/**
 *
 * @author mccart
 */
public class CustomerDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html><html><head><title>Servlet Customer Details</title></head><body>");
            out.println("<h1>Servlet CustomerDetails at " + request.getContextPath() + "</h1>");
            
            out.println("<br/><table border='1' cellpadding='4'><tbody><tr>"
                    + "<td><a href='CustomerDetails'>Customer Details</td>"
                    + "<td><a href='CustomerController'>All Customers</td>"
                    + "<td><a href='Stocks.jsp'>Stocks</td>"
                    + "</tr></tbody></table>");
            
            out.println("<br/><form action='CustomerController' method='GET'>"
                    + "<table border='1'><tbody><tr><td>Customer Name</td><td>"
                    + "<input type='text' name='customerName' size='20'/>"
                    + "</td></tr><tr><td>Customer Identity</td><td>"
                    + "<input type='text' name='customerIdentity' size='20'/>"
                    + "</td></tr><tr><td>Customer Address</td><td>"
                    + "<input type='text' name='customerAddress' size='20'/>"
                    + "</td></tr><tr>"
                    + "<td><input type='submit' name='submit' value='Get Customer'/></td>"
                    + "<td><input type='submit' name='submit' value='Update Customer'/></td>"
                    + "</tr><tr>"
                    + "<td><input type='submit' name='submit' value='Add Customer'/></td>"
                    + "<td><input type='submit' name='submit' value='Delete Customer'/></td>"
                    + "</tr></tbody></table></form>");
            
            Customer cust = (Customer) request.getAttribute("customer");
            if( cust != null ) {
                String id = cust.getId();
                out.println("<br/><a href='PortfolioController?customerIdentity="
                        + id + "'>View Portfolio</a>");
            }
            
            String msg = (String) request.getAttribute("message");
            if( msg != null ) {
                out.println("<br/>br/><p style='color:red'>" + msg + "</p>");
            }
            
            out.println("</body></html>");

            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
