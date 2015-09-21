/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trader.web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trader.*;

/**
 *
 * @author mccart
 */
public class CustomerController extends HttpServlet {

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

        BrokerDelegate instance = BrokerDelegate.getInstance();

        String identity = request.getParameter("customerIdentity");
        String name = request.getParameter("customerName");
        String address = request.getParameter("customerAddress");
        String submit = request.getParameter("submit");
//        System.err.println(" submit: [ " + submit + " ]");

// need to validate customer Id is provided always on submit...

        try {
            if (submit != null && submit.length() != 0 ) {
                switch (submit) {

                    case "Get Customer": {
                        Customer cust = instance.getCustomer(identity);
                        request.setAttribute("customer", cust);
                        System.err.println(cust.toString());
                        break;
                    }
                    case "Update Customer": {
                        Customer cust = instance.getCustomer(identity);
                        cust.setName(name);
                        cust.setAddr(address);
                        request.setAttribute("customer", cust);
                        System.err.println(cust.toString());
                        break;
                    }
                    case "Add Customer": {
                        Customer cust = new Customer(identity, name, address);
                        request.setAttribute("customer", cust);
                        break;
                    }
                    case "Delete Customer":
                        instance.deleteCustomer(identity);
                        break;

                    default:
                        throw new BrokerException("Submit option [" + submit + "] not found");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerDetails");
                dispatcher.forward(request, response);

            } else {

                Customer[] custs = instance.getAllCustomers();
                request.setAttribute("customers", custs);

                RequestDispatcher dispatcher = request.getRequestDispatcher("AllCustomers.jsp");
                dispatcher.forward(request, response);

            }

        } catch (BrokerException be) {
            request.setAttribute("message", be.getMessage());
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, be);
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
