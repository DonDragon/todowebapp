<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>${list.title}</h1>
<ul class="nav nav-pills">
  <li <c:if test="${active == 'all'}">class="active"</c:if>><a href="/lists/${list.id}">All</a></li>
  <li <c:if test="${active == 'done'}">class="active"</c:if>><a href="/lists/${list.id}/done">Done</a></li>
  <li <c:if test="${active == 'undone'}">class="active"</c:if>><a href="/lists/${list.id}/undone">Undone</a></li>
</ul>
<div class="table-responsive">
<table class="table table-hover">
<c:forEach var="e" items="${list.entries}">
	<tr>
		<td width="5%" ><input onclick="window.location='/entries/${e.id}/toggle'" type="checkbox"
             style="width:25px; height:25px; vertical-align: middle"
                <c:if test="${e.done}">
                    checked
                </c:if>
        /></td>
		<td>${e.content}</td>
		<td width="5%"><a href="/entry/delete?list_id=${list.id}&entry_id=${e.id}" class="btn btn-danger"> delete </a></td>
	</tr>
	</c:forEach>
</table>
</div>
<h2>TODO</h2>
<form method="POST" action="/entries/new">
    <input name="list.id" type="hidden" value="${list.id}"/>
    <input name="content" type="text" class="form-control" placeholder="Enter Content" /><br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form>
</body>
</html>

