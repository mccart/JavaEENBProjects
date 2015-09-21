<%-- 
    Document   : AllCustomers
    Created on : Sep 18, 2015, 2:43:48 PM
    Author     : mccart
--%>

<%@page import="java.lang.String"%>
<%@page import="trader.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! Customer[] custs = null; %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Customers</title>
    </head>
    <body>
        <br/><table border='1' cellpadding='4'><tbody><tr>
        <td><a href='CustomerDetails'>Customer Details</td>
        <td><a href='CustomerController'>All Customers</td>
        <td><a href='Stocks.jsp'>Stocks</td>
        </tr></tbody></table>
        <br/><table border='1' cellpadding='4'><thead><tr>
        <td>Customer Id</td>
        <td>Name</td>
        <td>Address</td>
        <td>Portfolio</td>
        </tr></thead><tbody>
        <%  custs = (Customer[]) request.getAttribute("customers");
            for( Customer c: custs) {
        %>        
        <tr><td><%=c.getId()%></td>
        <td><%=c.getName()%></td>
        <td><%=c.getAddr()%></td>
        <td><a href='PortfolioController?customerIdentity=<%=c.getId()%>'>View</a></td></tr>
        <%  }
        %>
        </tbody></table>
        <%  String message = (String) request.getAttribute("message");
            if( message != null ) {
                out.println("<font color='red'>" + message + "</font>");
        } %>
    </body>
</html>
