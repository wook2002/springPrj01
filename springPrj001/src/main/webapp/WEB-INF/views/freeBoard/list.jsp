<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>list임</p>
	<p>${FBlist}</p>
	<c:forEach var="n" items="${FBlist}">
		<tr>
			<td>${n.freeNum}</td>
			<td>${n.freeName}</td>
			<td>${n.freeComment}</td>
			<td>${n.freeDate}</td>
		</tr>
	</c:forEach>
</body>
</html>
