<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Meal: ${requestScope.get('meal').getDescription()}</title>
</head>
<body>
    <h3><a href="index.html">Home</a> - <a href="meals">Meals</a></h3>
    <h2>Meal: ${requestScope.get('meal').getDescription()}</h2>
    <div style="margin-top: 10px">
        <h3 style="margin-bottom: 2px">Update a meal</h3>
        <form method="post" action="meals">
            <label for="dateTime">Date</label><br />
            <input type="datetime-local" name="dateTime" id="dateTime" value="${requestScope.get('meal').getDateTime().format(requestScope.get('dateFormat'))}" /><br />
            <label for="description">Description</label><br />
            <input type="text" name="description" id="description" value="${requestScope.get('meal').getDescription()}" /><br />
            <label for="calories">Calories</label><br />
            <input type="number" name="calories" id="calories" value="${requestScope.get('meal').getCalories()}" /><br />
            <input type="hidden" name="operation" id="operation" value="update" />
            <input type="hidden" name="id" id="id" value="${requestScope.get('meal').getId()}" />
            <input type="submit" value="Update" />
        </form>
    </div>
</body>
</html>
