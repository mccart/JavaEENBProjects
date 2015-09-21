<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Information</title>
    </head>
    <body>

    <h1>Customer Information for ${customerdata.firstname} ${customerdata.lastname}</h1>
    
    <table border="1">
        <tr>
            <td>userid</td>
            <td>${customerdata.userid}</td>
        </tr>
        <tr>
            <td>name</td>
            <td>${customerdata.firstname} ${customerdata.lastname} </td>
        </tr>
        <tr>
            <td>address</td>
            <td>${customerdata.address}</td>
        </tr>
        <tr>
            <td>phone</td>
            <td>${customerdata.phone}</td>
        </tr>
        <tr>
            <td>balance</td>
            <td>${customerdata.balance}</td>
        </tr>
    </table>
    </body>
</html>
