 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<h1>Your TODO lists</h1>


    <p>
        <a href="/lists/create" class="btn btn-primary">+ Add new TODO list</a>
    </p>

  <table class="table table-striped table-bordered table-hover ">

 <c:forEach var="l" items="${lists}">
   <tr style="cursor:pointer" onclick="window.location='/lists/${l.id}'">
    <td>
         ${l.title}  ${l.id}
    </td>
    <td>
        ${l.description}
    </td>
    <td>
         ${fn:length(l.entries)}
    </td>
    <td>
        <a href="/lists/delete/${l.id}" class="btn btn-primary"> delete </a>
    </td>

   </tr>
   </c:forEach>
      </table>
