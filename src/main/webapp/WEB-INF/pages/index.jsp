 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<h1>Your TODO lists</h1>



  <tr>
   <td>
       <a href="/lists/create" class="btn btn-primary">+ Add new TODO list</a>
   </td>
  </tr>

  <div style="padding: 50px"/>

  <table class="table table-striped table-bordered table-hover ">

  <tr align="center">
        <th style="cursor:pointer" onclick="window.location='/lists/sort/title'">Title</th>
        <th style="cursor:pointer" onclick="window.location='/lists/sort/description'">Description</th>
        <th style="cursor:pointer" onclick="window.location='/lists/sort/entry'">Entry</th>
  </tr>
  <c:forEach var="l" items="${lists}">
   <tr style="cursor:pointer;" onclick="window.location='/lists/${l.id}'">
    <td style="vertical-align:middle" width="25%">
         ${l.title}
    </td>
    <td style="vertical-align:middle">
        ${l.description}
    </td style="vertical-align:middle">
    <td style="vertical-align:middle" align="center" width="10%">
         ${fn:length(l.entries)}
    </td>
    <td align="center" width="5%">
        <a href="/lists/delete/${l.id}" class="btn btn-primary"> delete </a>
    </td>

   </tr>
   </c:forEach>
   </table>
