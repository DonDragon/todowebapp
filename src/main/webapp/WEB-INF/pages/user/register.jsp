<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
<title>Register Page</title>
<script type="text/javascript" src="jquery-1.2.6.min.js"></script>
</head>
  <div id="container" >

    <h2>Enter registration data</h2>
    <c:if test="${not empty message}"><div class="alert alert-success">${message}</div></c:if>
    <c:if test="${not empty errorMessage}"><div class="alert alert-danger">${errorMessage}</div></c:if>

    <form:form modelAttribute="UploadUser" method="POST" action="register" enctype="multipart/form-data">
      <label for="nameInput">Name: </label>
      <br/>
      <font color='red'><form:errors path="userName" /> </font>
      <form:input class="form-control" path="userName" id="nameInput" value="${User.username}"/>

      <label for="passwordInput">Password: </label>
      <br/>
      <font color='red'><form:errors path="password" /> </font>
      <form:password class="form-control" path="password" id="passwordInput" />

      <label for="ageInput">Age: </label>
      <br/>
      <font color='red'><form:errors path="age" /> </font>
      <form:input class="form-control" path="age" id="ageInput" value="${User.age}"/>

      <label for="emailInput">Email: </label>
      <br/>
      <font color='red'><form:errors path="email"/> </font>
      <form:input class="form-control" path="email" id="emailInput" value="${User.email}" />
      <br/>

      <h5>Choose your avatar:</h5>

      <font color='red'><form:errors path="file" id="chooseFile"/> </font>
      <input type="file" name="file" />
      <br/>

      <input type="submit" value="Submit" class="btn btn-success" />
    </form:form>
  </div>
