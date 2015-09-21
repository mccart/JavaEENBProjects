
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Information</title>
    </head>
    <body>

    <c:choose>
        <c:when test="${not empty requestScope.accounts}">
            
            <h1>Customers with over $${param.amount} dollars</h1>
            
            <table border="1" cellpadding="4">
                <thead>
                    <tr>
                        <th>User Id</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Balance</th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach var="customerdata" items="${requestScope.accounts}">

                    <tr>
                        <td>${customerdata.userid}</td>
                        <td>${customerdata.firstname} ${customerdata.lastname}</td>
                        <td>${customerdata.address}</td>
                        <td>${customerdata.phone}</td>
                        <td>${customerdata.balance}</td>
                    </tr>
                    
                </c:forEach>

                </tbody>
            </table>

        </c:when>
        <c:otherwise>
            <font color='red'>Missing Accounts!</font>
        </c:otherwise>
    </c:choose>
    
    </body>
</html>
