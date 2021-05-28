<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/27/2021
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            text-align: center;
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even){background-color: #f2f2f2}
        th {
            background-color: #4CAF50;
            color: white;
        }
        button{
            border-radius: 8px;
            background: aqua;
        }
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<h2>
    <a href="students?action=create">Thêm người dùng mới </a>
</h2>

<table>
    <tr>
        <th>ID học sinh</th>
        <th>Tên học sinh</th>
        <th>Ngày sinh</th>
        <th>Địa chỉ</th>
        <td></td>
        <td></td>
<%--        <th>Lớp học</th>--%>

    </tr>
    <c:forEach items='${requestScope["viewAllList"]}' var="u">
        <tr>
            <td>${u.getStudent().getId()}</td>
            <td><a href="/students?action=view&id=${u.getStudent().getId()}">${u.getStudent().getName()}</a></td>
            <td>${u.getStudent().getDateOfBirth()}</td>
            <td>${u.getStudent().getAddress()}</td>
            <td><a href="/students?action=edit&id=${u.getStudent().getId()}">Chỉnh sửa</a></td>
            <td><a href="/students?action=delete&id=${u.getStudent().getId()}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
