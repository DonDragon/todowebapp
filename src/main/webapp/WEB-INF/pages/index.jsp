 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<h1>Your TODO lists</h1>



  <tr>
   <td>
       <a href="/lists/create" class="btn btn-primary">+ Add new TODO list</a>
   </td>
    <td>
           <a href="/lists/delete" class="btn btn-danger"> delete </a>
       </td>
  </tr>

  <div style="padding: 50px"/>

  <table class="table table-striped table-bordered table-hover ">

  <tr align="center">
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
   </tr>
   </c:forEach>
   </table>
