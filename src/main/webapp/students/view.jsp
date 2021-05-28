<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/28/2021
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fielset>
    <legend>Thông tin học sinh</legend>
    <table>
        <tr>
            <td>ID học sinh:</td>
            <td>${requestScope["viewAll"].getStudent().getId()}</td>
        </tr>
        <tr>
            <td>Tên học sinh:</td>
            <td>${requestScope["viewAll"].getStudent().getName()}</td>
        </tr>
        <tr>
            <td>Ngày sinh:</td>
            <td>${requestScope["viewAll"].getStudent().getDateOfBirth()}</td>
        </tr><tr>
            <td>Địa chỉ:</td>
            <td>${requestScope["viewAll"].getStudent().getAddress()}</td>
        </tr>
        <tr>
            <td>Lớp học:</td>
            <td>${requestScope["viewAll"].getLophoc().getName()}</td>
        </tr>
    </table>
</fielset>
</body>
</html>
