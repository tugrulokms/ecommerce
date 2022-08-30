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
	----------
	<br>
	<table border="1">
	<c:forEach var="productDto" items="${productDtos}">
		<tr>
			<td><a href="/product/find/${productDto.productId}">${productDto.productName}</td>
			<td>${productDto.salesPrice}</td>
			<td>${productDto.category.categoryName}</td>
		</tr>
		
	</c:forEach>
	</table>
</body>
</html>