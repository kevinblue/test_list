<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
    //是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}
	
</script>
<!--多行控件  -->
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../proj_common/proj_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	   <tr>
	    <td colspan=4>
	   <div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%" onactivechanged="changTab" >
	    <div title="补充说明" name="add_file_change_info_form" iconCls="icon-node" showCollapseButton="true">
			<jsp:include page="add_file.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		
		</div>
	   </div>
	    </td>
	  </tr>
	</table>
</div>		
