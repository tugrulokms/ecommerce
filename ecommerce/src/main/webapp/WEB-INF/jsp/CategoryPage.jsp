<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	Ata kategori no: ${categoryDto.parentCategory.categoryId}
	<br>
	Ata kategori adı: <a href="/category/${categoryDto.parentCategory.categoryId}">
	${categoryDto.parentCategory.categoryName}
	</a>
	<br>
	Kategori no: ${categoryDto.categoryId}
	<br>
	Kategori adı: ${categoryDto.categoryName}
	<br>
	<br>
	<table border="1">
	<c:forEach var="categoryDto" items="${subCategoryDtos}">
		<tr>
			<td><a href="/category/${categoryDto.categoryId}">${categoryDto.categoryName}</a></td>
		</tr>
		
	</c:forEach>
	</table>
	<table border="1">
	<c:forEach var="productDto" items="${productDtos}">
		<tr>
			<td><a href="/product/find/${productDto.productId}">${productDto.productName}</td>
			<td>${productDto.salesPrice}</td>
		</tr>
		
	</c:forEach>
	</table>
</body>
</html>