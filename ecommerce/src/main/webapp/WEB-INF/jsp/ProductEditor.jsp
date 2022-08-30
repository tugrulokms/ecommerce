<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<form:form method="POST" modelAttribute="productDto">
<form:errors/>
<fieldset class="form-group">
	<form:label path="productId">Ürün Özdeşliği</form:label>
	<form:input path="productId" type="text" disabled="true"/>
</fieldset>

<fieldset class="form-group">
	<form:label path="productName">Ürün Adı</form:label>
	<form:input path="productName" type="text"/>
	<form:errors path="productName"/>
</fieldset>

<fieldset class="form-group">
	<form:label path="salesPrice">Ürün Fiyatı</form:label>
	<form:input path="salesPrice" type="text"/>
	<form:errors path="salesPrice"/>
</fieldset>

<form:select path="department.departmentId">
			<form:option value="0">
				--Seçiniz--
			</form:option>
			<c:forEach items="${departments}" var="department">
				<form:option value="${department.departmentId}">
					${department.departmentName}
				</form:option>
			</c:forEach>
</form:select>

<button type="submit">Sakla</button> 

</form:form>

</body>
</html>