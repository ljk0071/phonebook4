<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../update" method="post">
	<input type="hidden" name="personId" value="${pVo.personId }">
				이름(name) <input type="text" name="name" value="${pVo.name }">
				<br>
				<br>
				핸드폰(hp) <input type="text" name="hp" value="${pVo.hp }">
				<br>
				<br>
				회사(company) <input type="text" name="company" value="${pVo.company }">
				<br>
				<br>
		<button type="submit">등록</button>
		<a href="./list">목록</a>
	</form>
	

</body>
</html>