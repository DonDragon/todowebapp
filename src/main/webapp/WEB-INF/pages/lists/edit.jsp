<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit todo list</title>
</head>
<body>
<h2>Edit todo</h2>
<form method="post" action="/editList">
 <input name="id" type="hidden" value="${command.id}"/>
 <input name="checked" type="hidden" value="${command.checked}"/>
    <textarea name="title" class="form-control" rows="1"/><c:out value="${command.title}" /></textarea>
      <br/>
     <textarea name="description" class="form-control" rows="5" ><c:out value="${command.description}" /></textarea>
        <br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form>
</body>
</html>