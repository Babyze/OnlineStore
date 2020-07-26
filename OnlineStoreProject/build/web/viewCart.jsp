<%-- 
    Document   : viewCart
    Created on : Jul 9, 2020, 8:47:03 PM
    Author     : babyz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tg" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Your Cart</title>
    </head>
    <body>
        <tg:set var="cart" value="${sessionScope.CART}"/>
        <tg:if test="${empty cart}">
            <h1>You don't have any item in cart!</h1> <br/>
            <a href="onlinestorepage">Click here to shopping.</a>
        </tg:if>
        <tg:if test="${not empty cart}">
            <tg:set var="items" value="${cart.cart}"/>
            <tg:if test="${not empty items}">
                <h1>Here is your cart!</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="removeItemFromCart">
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
                                <td>
                                    <input type="checkbox" name="chkItem" 
                                           value="${item.key.pid}" />
                                </td>
                            </tr>
                        </tg:forEach>
                        <tr>
                            <td colspan="3">
                                <a href="onlinestorepage">Add More Book To Your Cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove Selected Book" name="btnAction" />
                            </td>
                        </tr>
                    </form>
                    <tr>
                        <td colspan="4">
                            <form action="order">
                                <input type="submit" value="Order Cart" 
                                       name="btnAction" style="float: right" />
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </tg:if>
    </tg:if>
</body>
</html>
