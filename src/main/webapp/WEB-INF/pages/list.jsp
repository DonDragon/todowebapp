<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new todo list</title>
</head>
<body>
<h2>Add new todo</h2>
<form:form method="post" action="addlist">
    <table>
        <tr>
            <td><form:input path="title" type="text" class="form-control" placeholder="Enter title" /></td>
        </tr>
        <tr>
            <td><form:textarea path="description" class="form-control" placeholder="Enter description" rows="5"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form:form>
</body>
</html>