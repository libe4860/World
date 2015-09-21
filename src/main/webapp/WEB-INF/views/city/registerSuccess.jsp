<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--     다국어처리 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 에러처리, 폼처리 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerSuccess.jsp</title>
<style type="text/css">
	.box { 
		width: 500px; 
		padding-top: 20px;
		border-top: 3px solid #000; 
		border-bottom: 3px solid #000; 
		box-shadow: 20px 10px 10px #ededed;
		text-shadow : 1px 1px 1px #333;
	}
</style>
</head>
<body>
	<h1><spring:message code="city.register.success"/></h1>
	<pre class="box">
		id			= ${city.id} <br/>
		name 		= ${city.name} <br/>
		countryCode	= ${city.country.code} <br/>
		district 	= ${city.district} <br/>
		population 	= ${city.population} <br/>
	</pre>
	<a href="register">도시입력화면으로...</a>
	 
	
</body>
</html>