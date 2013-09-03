 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<h1>Your TODO lists</h1>

 <table>
    <tr>
        <td><a href="/lists/create" class="btn btn-success">+ Add new TODO list</a></button></td>
        <td><a href="/lists/delete" class="btn btn-danger"> Delete </a></button></td>
    </tr>
 </table>

  <div style="padding: 50px"/>

  <table class="table table-hover">

  <tr align="center" class="danger">
        <th></th>
        <th style="cursor:pointer" onclick="window.location='/lists/sort?param=title'">Title</th>
        <th style="cursor:pointer" onclick="window.location='/lists/sort?param=description'">Description</th>
        <th style="cursor:pointer" onclick="window.location='/lists/sort?param=entry'">Entry</th>
  </tr>
  <c:forEach var="l" items="${lists}">
   <tr>
   <td align="center" width="5%">
                 <input onclick="window.location='/lists/${l.id}/toggle'" type="checkbox" style="width:25px; height:25px"
                                          <c:if test="${l.checked}">
                                              checked
                                          </c:if>
                                      />
                 </td>
    <td style="cursor:pointer; vertical-align:middle" onclick="window.location='/lists/${l.id}'"  width="25%">
         ${l.title}
    </td>
    <td style="cursor:pointer; vertical-align:middle" onclick="window.location='/lists/${l.id}'">
        ${l.description}
    </td style="vertical-align:middle">
    <td style="cursor:pointer; vertical-align:middle" onclick="window.location='/lists/${l.id}'" align="center" width="10%">
         ${fn:length(l.entries)}
    </td>
    <td width="5%">
         <a href="/edit/list/${l.id}" class="btn btn-warning"> edit </a>
    </td>
   </tr>
   </c:forEach>
   </table>
