<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <table style="border-collapse: collapse">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach var="meal" items="${requestScope.get('meals')}">
            <tr style="background-color: ${meal.isExcess() ? '#FF6D6F' : '#9BEE55'}">
                <td>${meal.getDateTime().format(requestScope.get('dateFormat'))}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
