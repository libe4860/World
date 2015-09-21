<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!--     다국어처리 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 에러처리, 폼처리 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerForm.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	h1 { margin-left: 20px;}
	.form-center {
		width: 400px;
		margin-left: 20px;
		padding: 10px;
		border: 1px solid red;
		border-radius: 10px;
	}
	
	.box{ width:100px; height:100px; border-radius:10px; background: orange }
</style>

</head>
<body>
<!-- 프로퍼티 언어설정에 따라 읽어오기 -->
<!-- arguments - 메세지 파리미터의 역할을 함 -->
<h1><spring:message code="city.register.title" arguments="[world]"/></h1>
	
<!-- form:form 자동으로 form id, action, method,설정됨 -->
<form:form commandName="city" action="register" method="post" cssClass="form-center">
	<!-- Global Error -->
	<div class="text-danger text-center">
		<form:errors />
	</div>
	
	<!-- city.name -->
	<div class="form-group">
		<label for="name"><spring:message code="city.name"/></label>
		<form:input path="name" cssClass="form-control"/>
		<form:errors path="name" cssClass="text-primary bg-danger"/>
	</div>
	
	<!-- city.countryCode -->
	<div class="form-group">
		<label for="countryCode"><spring:message code="city.countryCode"/></label>
<%-- 		<form:input path="countryCode" cssClass="form-control"/> --%>
			<form:select path="countryCode" cssClass="form-control">
				<form:option value="">-- 선택하세요 --</form:option>
				<form:options items="${countryCode}" itemValue="code" itemLabel="name"/>
			</form:select>
		<form:errors path="countryCode" cssClass="text-primary bg-danger"/>
	</div>
	
	<!-- city.district -->
	<div class="form-group">
		<label for="district"><spring:message code="city.district"/></label>
<%-- 		<form:input path="district" cssClass="form-control" /> --%>
		<form:select path="district" cssClass="form-control">
			<form:option value="">-- 선택하세요 --</form:option>
			<form:options items="${districts}"/>
		</form:select>
		
		<form:errors path="district" cssClass="text-primary bg-danger"/>
	</div>
	
	<!-- city.population -->
	<div class="form-group">
		<label for="population"><spring:message code="city.population"/></label>
		<form:input path="population" cssClass="form-control"/>
		<form:errors path="population" cssClass="text-primary bg-danger"/>
	</div>
	
	<!-- Submit -->
	<input class="btn btn-primary" type="submit" value="도시추가" />
</form:form>

<c:forEach var="i" begin="0" end="10">
	<div class="box"><strong>${i}</strong></div>
</c:forEach>

<script type="text/javascript">
	var form = jQuery('.form-center');
	form.draggable();
	form.mousedown(function() {
	   console.log("form.mousedown()...");
	   $(this).css('box-shadow', '20px 20px 50px orange');
	});
	
	form.mouseup(function() {
	   console.log("form.mouseup()...");
	   $(this).css('box-shadow', 'none');
	});
	
	$('.box').draggable();
	
	
	$('#countryCode').change(function() {
	//    alert("change event called... value= " + $(this).val());
	   var countryCode = $(this).val();
	   console.log("change event called... value= " + countryCode);
	   
	
	   // Ajax (Asyncronous javascript and xml)
	   $.get("district/" + countryCode, function(data, status) {
	//       alert("data = " + data + "\n" + "status = " + status);
	      console.log("#data = " + data + "\n" + "status = " + status);
	      $('#district').html(data);
	   });
	});
	
	
	
</script>	
</body>
</html>