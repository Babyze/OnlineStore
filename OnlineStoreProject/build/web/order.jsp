<%-- 
    Document   : checkout.jsp
    Created on : Jul 9, 2020, 9:32:23 PM
    Author     : babyz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tg" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Cart</title>
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
                <div>
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
                    </table> <br />
                    <tg:set var="errors" value="${requestScope.ERROR_INFORMATION}"/>
                    <tg:if test="${not empty errors.emptyParameter}">
                        <font color="red">
                        * ${errors.emptyParameter}
                        </font> <br/>
                    </tg:if>
                    <tg:if test="${not empty errors.fullNameLengthErr}">
                        <font color="red">
                        * ${errors.fullNameLengthErr}
                        </font> <br/>
                    </tg:if>
                    <tg:if test="${not empty errors.addressLengthErr}">
                        <font color="red">
                        * ${errors.addressLengthErr}
                        </font> <br/>
                    </tg:if>
                    <form action="checkout" method="POST">
                        <tg:set var="user" value="${sessionScope.USER_INFORMATION}"/>
                        Enter Your Name: <input type="text" placeholder="Your Name"
                                                name="txtFullname" value="${user.uFullname}" /> <br/>
                        Enter Your Address: <input type="text" placeholder="Your Address"
                                                   name="txtAddress" value="${user.uAddress}" /> <br/>
                        <input type="submit" value="Checkout" /> <br/>
                    </form>
                    <a href="viewcart">Edit Cart</a> |
                    <a href="onlinestorepage">Online Store</a>
                </div>
            </tg:if>
        </tg:if>
    </body>
</html>
