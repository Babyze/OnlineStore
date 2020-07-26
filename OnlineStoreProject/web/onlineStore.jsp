<%-- 
    Document   : onlineStore
    Created on : Jul 9, 2020, 7:37:58 PM
    Author     : babyz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tg" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Store</title>
    </head>
    <body>
        <h1>Shopping Center</h1>
        <tg:set var="listProducts" value="${sessionScope.LIST_PRODUCT}" />
        <tg:if test="${empty listProducts}">
            <h3>Out of stock!</h3>
        </tg:if>
        <tg:if test="${not empty listProducts}">
            <form action="cart">
                Choose Product: <select name="cboProduct">
                    <tg:forEach var="product" items="${listProducts}">
                        <option value="${product.pid}">${product.pname}</option>
                    </tg:forEach>
                </select>
                <input type="submit" value="Add to cart" name="btnAction" />
                <input type="submit" value="View cart" name="btnAction"/>
            </form>
        </tg:if>
        <br/> <a href="searchpage">Click here to return home page</a>
    </body>
</html>
