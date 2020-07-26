<%-- 
    Document   : search
    Created on : Jul 9, 2020, 12:47:58 AM
    Author     : babyz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tg"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
        <form action="logout" method="POST">
            <tg:set var="user" value="${sessionScope.USER_INFORMATION}"/>
            Welcome, ${user.uFullname} |  
            <input type="submit" value="Logout" name="Logout" />
        </form>
        </font>
        <h1>Search Page</h1>
        <form action="search">
            Search Value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" />
        </form> <br/>

        <tg:set var="informationErrors" value="${requestScope.USER_INFORMATION_ERROR}" />
        <tg:if test="${not empty informationErrors.passwordLengthErr}">
            <font color="red">
            * ${informationErrors.passwordLengthErr}
            </font> <br/>
        </tg:if>
        <tg:if test="${not empty informationErrors.fullNameLengthErr}">
            <font color="red">
            * ${informationErrors.fullNameLengthErr}
            </font> <br/>
        </tg:if>
        <tg:if test="${not empty informationErrors.addressLengthErr}">
            <font color="red">
            * ${informationErrors.addressLengthErr}
            </font> <br/>
        </tg:if>

        <tg:set var="searchValue" value="${param.txtSearchValue}" />
        <tg:if test="${not empty searchValue}">
            <tg:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <tg:if test="${empty result}">
                <h2>No record is matched!</h2>
            </tg:if>
        </tg:if>
        <tg:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                            <tg:if test="${user.isAdmin == true}">
                            <th>Password</th>
                            </tg:if>
                        <th>Full name</th>
                        <th>Address</th>
                            <tg:if test="${user.isAdmin == true}">
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                            </tg:if>
                    </tr>
                </thead>
                <tbody>
                    <tg:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="update" method="POST">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.uUsername}
                                <input type="hidden" name="txtUsername" 
                                       value="${dto.uUsername}" />
                            </td>
                            <tg:if test="${user.isAdmin == true}">
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.uPassword}" />
                                </td>
                            </tg:if>
                            <td>
                                <input type="text" name="txtFullname" 
                                       value="${dto.uFullname}" />
                            </td>
                            <td>
                                <input type="text" name="txtAddress" 
                                       value="${dto.uAddress}" />
                            </td>
                            <tg:if test="${user.isAdmin == true}">
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <tg:if test="${dto.isAdmin}">
                                               checked="checked"
                                           </tg:if>
                                           />
                                </td>
                                <td>
                                    <tg:url var="deleteLink" value="delete">
                                        <tg:param name="pk" value="${dto.uUsername}" />
                                        <tg:param name="lastSearchValue" value="${searchValue}" />
                                    </tg:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
                                </td>
                            </tg:if>
                        </tr>
                    </form>
                </tg:forEach>

            </tbody>
        </table>

    </tg:if>
    <br/>
    <a href="onlinestorepage">Click here to go to Online Store</a>
</body>
</html>
