<%-- 
    Document   : checkout
    Created on : Jul 11, 2020, 5:21:15 PM
    Author     : babyz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tg" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Successfully</title>
    </head>
    <body>
        <tg:set var="items" value="${requestScope.CART}"/>
        <tg:if test="${empty items}" >
            <h1>You don't have any item in cart!</h1> <br/>
        </tg:if>
        <tg:if test="${not empty items}" >
            <h1>Check out successfully!</h1>
            <h3>Your Information: </h3>
            <h5>Full name: ${param.txtFullname}</h5>
            <h5>Address: ${param.txtAddress}</h5>
            <h3>Your Order: </h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <tg:forEach var="item" items="${items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${item.key.pname}
                            </td>
                            <td>
                                ${item.value}
                            </td>
                        </tr>
                    </tg:forEach>
                </tbody>
            </table>
            <h5>Thank you for shopping!</h5>
        </tg:if>
        <a href="searchpage">Home page</a> |
        <a href="onlinestorepage">Online Store</a>
    </body>
</html>
