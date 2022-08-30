<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<form:form method="POST" action="/cart-product" modelAttribute="productDto">
<form:errors/>

		${message}
		<br>
		-------------------
		<br>
		<form:input path="productId" type="hidden" value="${productDto.productId}"/>
		
		<form:label path="productName">Ürün adı: </form:label>
		${productDto.productName}
		<form:input path="productName" type="hidden" value="${productDto.productName}"/>
		<form:errors path="productName"/>
		
		<br>
		
		<form:label path="salesPrice">Ürün fiyatı: </form:label>
		${productDto.salesPrice}
		<form:input path="salesPrice" type="hidden" value="${productDto.salesPrice}"/>
		<form:errors path="salesPrice"/>
			
		<br>
		Kategori adı: <a href="/category/${productDto.category.categoryId}">${productDto.category.categoryName}</a>
		<br>
		-------------------
		<br>
		<button type="submit">Sepete Ekle</button> 

</form:form>
	
</body>
</html>