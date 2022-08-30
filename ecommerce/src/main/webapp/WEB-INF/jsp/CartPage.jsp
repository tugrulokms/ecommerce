<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<a href="/">Anasayfa</a>
	<br>
	<br>
	Sepet içeriği:
	<c:forEach var="cartProduct" items="${cart.cartProducts}">
			<br>
			-------------
			<br>
			Ürün adı: ${cartProduct.product.productName}
			<br>
			Sayısı: ${cartProduct.quantity}
			<br>
			Fiyatı: ${cartProduct.salesPrice}
			<br><br>
			<a href="/cart-product/delete/${cartProduct.cartProductId}">Ürünü kaldır</a>
	</c:forEach>
	<br>
	-------------
</body>
</html>