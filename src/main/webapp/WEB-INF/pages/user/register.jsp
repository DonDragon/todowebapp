<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
<title>Register Page</title>
</head>
  <div id="container">

    <h2>Enter registration data</h2>
    <c:if test="${not empty message}"><div class="message green">${message}</div></c:if>

    <form:form modelAttribute="User" method="POST" action="register">
      <label for="nameInput">Name: </label>
      <form:input class="form-control" path="username" id="nameInput" />

      <label for="passwordInput">Password: </label>
            <form:input class="form-control" path="password" id="passwordInput" />

      <label for="ageInput">Age: </label>
      <form:input class="form-control" path="age" id="ageInput" />

      <label for="emailInput">Email: </label>
      <form:input class="form-control" path="email" id="emailInput" />
      <br />

      <input type="submit" value="Submit" class="btn btn-success" />
    </form:form>
  </div>
