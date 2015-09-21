package bank;

import java.io.*;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.MapMessage;
import javax.jms.Queue;

import javax.servlet.*;
import javax.servlet.http.*;

public class DemoJMS extends HttpServlet {
    
    @Resource(mappedName="jms/UpdateBalanceMDBFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName="jms/UpdateBalanceMDB")
    private Queue queue;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        boolean messageSent = true;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            MapMessage mm = session.createMapMessage();
            mm.setInt("acountId", 2);
            mm.setDouble("amount", 2.0);
            
            messageProducer.send(mm);
            messageProducer.close();
            connection.close();

        } catch (JMSException ex) {
            messageSent = false;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>JMS Demo Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        
        if(messageSent) {
            out.println("<h1>Message sent</h1>");
        } else {
            out.println("<h1>Message not sent</h1>");
        }
        
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
