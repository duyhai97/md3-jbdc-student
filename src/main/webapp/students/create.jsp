<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/28/2021
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<form  method="post">
    <c:if test='${requestScope["message"] != null}'>
        <h2 class="message">${requestScope["message"]}</h2>
    </c:if>
    <table>
        <tr>
            <td>Tên học sinh:</td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td>Ngày sinh:</td>
            <td><input type="text" name="dob" id="dob"></td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td><input type="text" name="address" id="address"></td>
        </tr>
        <tr>
            <td>Lớp học</td>
            <td>
                <select name="lophocList" id="" >
                    <c:forEach var="l" items="${lophocList}">
                        <option value="${l.getId()}">${l.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Thêm mới"></td>
        </tr>
    </table>
</form>
</body>
</html>
