<%-- 
    Document   : Portfolio
    Created on : Sep 18, 2015, 3:30:48 PM
    Author     : mccart
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="trader.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portfolio</title>
    </head>
    <body>
        <br/><table border='1' cellpadding='4'><tbody><tr>
        <td><a href='CustomerDetails'>Customer Details</td>
        <td><a href='CustomerController'>All Customers</td>
        <td><a href='Stocks.jsp'>Stocks</td>
        </tr></tbody></table>
        <br/><table border='1' cellpadding='4'><thead>
        <tr><td>Stock Symbol</td><td>Quantity</td></tr>
        </thead>
        <tbody>
    <c:choose>
        <c:when test="${requestScope.message == null}">
            <c:forEach var="share" items="${requestScope.shares}">
                <tr>
                    <td>${share.stockSymbol}</td>
                    <td>${share.quantity}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
                <tr><td colspan="2">${requestScope.message}</td></tr>
        </c:otherwise>
    </c:choose>
        </tbody>
        </table>
    </body>
</html>
