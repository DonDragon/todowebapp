 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Your TODO lists</h1>

 <table>
    <tr>
        <td><a href="/lists/create" class="btn btn-success">+ Add new TODO list</a></td>
    </tr>
 </table>

  <div style="padding: 50px"/>

  <table class="table table-hover">

  <tr class="danger">
        <td align="center" width="5%" style="cursor:pointer">
            <input disabled type="checkbox"
                <c:if test="${allChecked}"> checked </c:if>
            />
        </td>
        <th style="cursor:pointer" onclick="window.location='/?sortby=title'">Title</th>
        <th style="cursor:pointer" onclick="window.location='/?sortby=description'">Description</th>
        <th style="cursor:pointer" onclick="window.location='/?sortby=items'">Entry</th>
        <th style="cursor:pointer">Action</th>
  </tr>
  <c:forEach var="l" items="${lists}">
   <tr>
   <td align="center" width="5%">
                 <input disabled type="checkbox" style="width:25px; height:25px"
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
    <td width="15%">
         <a href="/edit/list/${l.id}" class="btn btn-mini btn-warning">Edit</a>
         <a href="/lists/delete/${l.id}" class="btn btn-mini btn-danger">Delete</a>
    </td>
   </tr>
   </c:forEach>
   </table>
