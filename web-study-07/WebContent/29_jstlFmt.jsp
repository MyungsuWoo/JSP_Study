<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL과 JSTL</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	이름 : <%= request.getParameter("name") %>
</body>
</html>