<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>${list.title}</h1>
             <ul>
              <c:forEach var="e" items="${list.entries}">

                <li>
                    <input onclick="window.location='/entries/${e.id}/toggle'" type="checkbox" style="width:25px; height:25px"
                         <c:if test="${e.done}">
                             checked
                         </c:if>
                     />

                      ${e.content}
                </li>

                 </c:forEach>
              </table>
             </ul>

<h2>TODO</h2>
<form method="POST" action="/entries/new">
    <input name="list.id" type="hidden" value="${list.id}"/>
    <input name="content" type="text" class="form-control" placeholder="Enter Content" /><br/>
    <input type="submit" value="Submit" class="btn btn-success"/>
</form>
</body>
</html>