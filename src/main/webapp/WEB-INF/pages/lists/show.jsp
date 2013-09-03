<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>${list.title}</h1>
              <table style="width: 50%;" "table table-striped table-bordered table-hover ">
                  <c:forEach var="e" items="${list.entries}">
                    <tr>
                        <td>
                            <input onclick="window.location='/entries/${e.id}/toggle'" type="checkbox"
                                   style="width:25px; height:25px; vertical-align: middle"
                                 <c:if test="${e.done}">
                                     checked
                                 </c:if>
                             />

                        </td>
                        <td style="width: 80%">
                              ${e.content}
                        </td>
                        <td>
                             <a href="/entries/delete?list_id=${list.id}&entry_id=${e.id}" class="btn btn-danger"> delete </a>
                        </td>
                     </tr>
                  </c:forEach>
              </table>

<h2>TODO</h2>
<form method="POST" action="/entries/new">
    <input name="list.id" type="hidden" value="${list.id}"/>
    <input name="content" type="text" class="form-control" placeholder="Enter Content" /><br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form>
</body>
</html>