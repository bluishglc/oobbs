<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>

<body>
	<form action="<c:url value='/j_spring_security_check'/>" method="post">
		User Name:<input name="j_username" id="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"/>
		Password:<input type="password" name="j_password" id="j_password"/>
		<input type="submit"/>
	</form>
</body>
</html>