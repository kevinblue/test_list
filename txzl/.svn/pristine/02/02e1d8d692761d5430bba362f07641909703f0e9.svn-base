<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<c:if test="${sessionScope['login_userlanguage'].language == 'zh'}">
	<c:set var="currentLocale" value="zh_CN"></c:set>
</c:if>
<c:if test="${sessionScope['login_userlanguage'].language == 'en'}">
	<c:set var="currentLocale" value="en_US"></c:set>
</c:if>

<c:set var="currentTimestamp" value="20140109000"></c:set>
<c:set var="currentSkin" value="blue3"></c:set>
<c:set var="urlPrefix" value="${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=" scope="request"></c:set>
<script type="text/javascript">
	var globalTimestamp = "${currentTimestamp}";
	var globalLocale = "${currentLocale}";
	var globalSkin = "${currentSkin}";
	var globalClientWidth = document.documentElement.clientWidth;
	var globalClientHeight = document.documentElement.clientHeight;
	var globalWebRoot = "${pageContext.request.contextPath}";
	var urlPrefix = "${urlPrefix}";
</script>