<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>

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

        		<table>
        			<tr style="height: 60">
        				<td>User:</td>
        				<td><input class="form-control" type='text' name='j_username' value=''>
        				</td>
        			</tr>
        			<tr style="height: 60">
        				<td>Password:</td>
        				<td><input class="form-control" type='password' name='j_password' />
        				</td>
        			</tr>
        			<tr>
        				<td colspan='2'><input class="btn btn-success" name="submit" type="submit"
        					value="submit" />
        				</td>
        			</tr>
        		</table>

        	</form>
    </div>
</body>
</html>