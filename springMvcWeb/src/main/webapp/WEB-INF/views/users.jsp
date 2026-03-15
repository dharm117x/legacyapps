<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
    <style>
        table { width: 60%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f4f4f4; }
        tr:nth-child(even) { background-color: #f9f9f9; }
    </style>
</head>
<body>

    <h2>User Data (from H2)</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Created At</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${not empty users}">
            <c:forEach var="emp" items="${users}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.email}</td>
                    <td><fmt:formatDate value="${emp.createdAt}" pattern="dd-MM-yyyy" /></td>
                </tr>
            </c:forEach>
            </c:if>
            <c:if test="${empty users}">
                <tr>
                    <td colspan="4">No users found.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>
