<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>${list.title}</h1>
           <table>
              <c:forEach var="e" items="${list.entries}">
                 <tr>
                  <td width="25%">
                  </td>
                  <td >
                      ${e.content}
                  </td>
                  <td>
                  </td>
                  <td>
                  </td>

                 </tr>
                 </c:forEach>
              </table>


<h2>TODO</h2>
<form:form method="POST" action="/entries/new">
    <form:input path="list.id" type="hidden" value="${list.id}"/>
    <form:input path="content" type="text" class="form-control" placeholder="Enter Content" /><br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form:form>
</body>
</html>