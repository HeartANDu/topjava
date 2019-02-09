<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Meals</title>
    <script>
        function submitDeleteForm(id) {
            document.getElementById('delete-' + id).submit();
        }
    </script>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <table style="border-collapse: collapse">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2">Options</th>
        </tr>
        <c:forEach var="meal" items="${requestScope.get('meals')}">
            <tr style="background-color: ${meal.isExcess() ? '#FF6D6F' : '#9BEE55'}">
                <td>${meal.getDateTime().format(requestScope.get('dateFormat'))}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td><a href="meals?id=${meal.getId()}">Edit</a></td>
                <td>
                    <form method="post" action="meals" id="delete-${meal.getId()}">
                        <input type="hidden" name="operation" value="delete" />
                        <input type="hidden" name="id" value="${meal.getId()}" />
                    </form>
                    <a href="javascript:void(0);" onclick="submitDeleteForm(${meal.getId()})">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="margin-top: 10px">
        <h3 style="margin-bottom: 2px">Add a meal</h3>
        <form method="post" action="meals">
            <label for="dateTime">Date</label><br />
            <input type="datetime-local" name="dateTime" id="dateTime" /><br />
            <label for="description">Description</label><br />
            <input type="text" name="description" id="description" /><br />
            <label for="calories">Calories</label><br />
            <input type="number" name="calories" id="calories" /><br />
            <input type="hidden" name="operation" value="create" />
            <input type="submit" value="Add" />
        </form>
    </div>
</body>
</html>
