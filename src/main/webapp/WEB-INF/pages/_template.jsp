<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
          <link rel="stylesheet" href="http://bootswatch.com/spacelab/bootstrap.min.css">
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
</body>
</html>

