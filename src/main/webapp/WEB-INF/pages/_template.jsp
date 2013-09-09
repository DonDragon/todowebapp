<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
          <link rel="stylesheet" href="http://bootswatch.com/united/bootstrap.min.css">
           <style>
                body { background-color: #eee; font: helvetica; }
                #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
                .green { font-weight: bold; color: green; }
                .red { font-weight: bold; color: red; }
                .message { margin-bottom: 10px; }
                label {width:70px; display:inline-block;}
                form {line-height: 160%; }
                .hide { display: none; }
           </style>
</head>

    <body style=" padding-top: 50px;">


    <div class="navbar  navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">TODO$</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li <c:if test="${pageContext.request.requestURI == '/'}">  class="active"   </c:if> ><a href="/">Home</a></li>
            <li <c:if test="${pageContext.request.requestURI == '/about'}">  class="active"   </c:if> ><a href="/about">About</a></li>
             <sec:authorize access="isAnonymous()">
                   <li <c:if test="${pageContext.request.requestURI == '/login'}">  class="active"   </c:if> ><a href="/login">Login</a></li>
                  <li <c:if test="${pageContext.request.requestURI == '/register'}">  class="active"   </c:if> ><a href="register">Register</a></li>
             </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li> <a><sec:authentication property="principal.username"/></a> </li>
                <li <c:if test="${pageContext.request.requestURI == '/logout'}">  class="active"   </c:if> ><a href="<c:url value="/j_spring_security_logout" />" > Logout </a></li>

            </sec:authorize>

          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

	<div class="container">
        <jsp:include page="${partial}" />
        <div>
            <hr>
            <footer>
                    <p>&copy; Hillel 2013</p>
            </footer>
        </div>
	</div>
</div>
<!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="http://getbootstrap.com/assets/js/jquery.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>

