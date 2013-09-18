<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

    <div id="container">
        <h3>Login</h3>
        <form name='f' action="<c:url value='j_spring_security_check' />"
        		method='POST'>

        		<label for="userNameInput">Username </label>
        		<input autofocus id="userNameInput" class="form-control" type='text' name='j_username' value=''>

        		<label for="userPasswordInput">Password </label>
        		<input id="userPasswordInput" class="form-control" type='password' name='j_password' />
        		<br />

        		<input class="btn btn-success" name="submit" type="submit" value="submit" />
        	</form>
    </div>

