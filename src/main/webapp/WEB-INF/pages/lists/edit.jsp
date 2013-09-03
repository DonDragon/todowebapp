<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>TODO Edit</h1>
<h2>${list.title}</h2>
<form method="POST" action="/lists/update">
    <input name="id" type="hidden" value=${list.id} />
    <input name="title" type="text" class="form-control" placeholder="Title" value=${list.title} /><br/>
    <textarea name="description" type="text" class="form-control" placeholder="Description" rows="5"
              rows="5">${list.description}</textarea><br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form>
</body>
</html>