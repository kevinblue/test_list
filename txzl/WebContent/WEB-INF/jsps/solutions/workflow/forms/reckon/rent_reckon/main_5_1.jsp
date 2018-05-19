<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
 <c:choose>
	<c:when test="${requestScope['proj_info.businesstype'] eq 'business_type.lease'||requestScope['contract_info.businesstype'] eq 'business_type.lease'}">
		<jsp:include page="main_5_1_convert.jsp" ></jsp:include>
		<jsp:include page="rent_custom_info.jsp" ></jsp:include>
	</c:when>
	<c:otherwise> 	
		 <jsp:include page="../../reckon_factoring/rent_reckon/main_5_1.jsp" ></jsp:include>
	</c:otherwise>
</c:choose>

