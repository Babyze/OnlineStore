<%-- 
    Document   : createNewAccount
    Created on : Jul 9, 2020, 5:33:51 PM
    Author     : babyz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tg" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create New Account!</h1>
        <form action="createNewAccount" method="POST">
            <tg:set var="errors" value="${requestScope.CREATE_ERROR}" />
            <tg:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                * ${errors.usernameIsExisted}
                </font> <br/>
            </tg:if>
            <tg:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                * ${errors.usernameLengthErr}
                </font> <br/>
            </tg:if>
            Username *: <input type="text" name="txtUsername" 
                               value="${param.txtUsername}" /> (6 - 20 chars) <br/>
            <tg:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                * ${errors.passwordLengthErr}
                </font> <br/>
            </tg:if>
            Password *: <input type="password" name="txtPassword" value="" /> (6 - 20 chars)<br/>
            <tg:if test="${not empty errors.confirmPasswordMatched}">
                <font color="red">
                * ${errors.confirmPasswordMatched}
                </font> <br/>
            </tg:if>
            Confirm Password *: <input type="password" name="txtConfirmPassword" 
                                       value="" /> (6 - 20 chars)<br/>
            <tg:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                * ${errors.fullNameLengthErr}
                </font> <br/>
            </tg:if>
            Full name *: <input type="text" name="txtFullname" 
                                value="${param.txtFullname}" /> (2 - 50 word chars)<br/>
            <input type="submit" value="Create" />
            <input type="reset" value="Reset" />
        </form> <br/>
        Already have an account? <a href="try">Login</a>
    </body>
</html>
