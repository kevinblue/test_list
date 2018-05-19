<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
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
	    <div class="mini-panel" title="补充说明" showCollapseButton="true" style="width: 100%;">
	     <table class="fillTable" cellspacing="0" cellpadding="0"  id="proj_fileadd_info" >
	         <tr >
	             <td class="td-content" colspan=4 ><input style="width:82%;height:150px;" name="proj_fileadd.memo"   label="补充说明" value="<mini:param  name="proj_fileadd.memo" />" class="mini-textarea">  </td>
	        </tr>
	      </table>
	    </div>
	    </td>
	  </tr>
	</table>
</div>		
