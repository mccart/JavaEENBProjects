<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<jsp:useBean id="customerdata" scope="request" class="bank.Customer" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Information</title>
    </head>
    <body>

    <h1>Customer Information for <jsp:getProperty name="customerdata" property="firstname" /> <jsp:getProperty name="customerdata" property="lastname" /></h1>
    
    <table border="1">
        <tr>
            <td>userid</td>
            <td><jsp:getProperty name="customerdata" property="userid" /></td>
        </tr>
        <tr>
            <td>name</td>
            <td><jsp:getProperty name="customerdata" property="firstname" /> <jsp:getProperty name="customerdata" property="lastname" /> </td>
        </tr>
        <tr>
            <td>address</td>
            <td><jsp:getProperty name="customerdata" property="address" /></td>
        </tr>
        <tr>
            <td>phone</td>
            <td><jsp:getProperty name="customerdata" property="phone" /></td>
        </tr>
        <tr>
            <td>balance</td>
            <td><jsp:getProperty name="customerdata" property="balance" /></td>
        </tr>
    </table>
    </body>
</html>
