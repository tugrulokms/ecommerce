<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	${message}
	<br>
	<table border="1">
	<c:forEach var="categoryDto" items="${categoryDtos}">
		<tr>
			<td><a href="/category/${categoryDto.categoryId}">${categoryDto.categoryName}</a></td>
		</tr>
		
	</c:forEach>
	</table>
</body>
</html>