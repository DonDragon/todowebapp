<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring 3 MVC Series - Contact Manager</title>
</head>
<body>
<h2>Add new todo</h2>
<form:form method="post" action="addlist">

    <table>
    <tr>
        <td><form:label path="title">Todo title</form:label></td>
        <td><form:input path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="description">Todo description</form:label></td>
        <td><form:input path="description" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>

</form:form>
</body>
</html>