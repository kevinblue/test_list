<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限分配</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
    <style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight-10;
	var loadMask = null;
	jQuery(function(){
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
   });
</script>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight-10;
	var loadMask = null;
	jQuery(function(){
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
		loadMask.show();
		reloadInitTree();
		//loadMask.hide();
   });
   var currentCheckedNode = null;
   var isDeptRoleLoaded = false;
   var isGroupLoaded = false;
   function reloadInitTree(){
	   isDeptRoleLoaded = false;
	    //加载树形
		$('#id_menu_main_content_div').tree({
			url:'${pageContext.request.contextPath}/table/getUserDeptAllTreeData.action',
			checkbox:false,
			onLoadSuccess:function(a,b){
				isDeptRoleLoaded = true;
					loadMask.hide();
	       },
			onBeforeLoad:function(node,params){
	    	    loadMask.show();
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true; 
				}
		    },onClick:function(node){
		       clearDataPermissioin();
		       if("deptRole"==node.attributes['type']){
		    	   loadUserDataPermissioin(node.id);
		       }
		    }
		});
   }
   function clearDataPermissioin(){
	   $("#id_deptrole").val("");
	   getTracywindyObject("id_combo_id_mayviewtype").setValue("");
	   $("#id_deptrole").val("");
	   $("#id_column").val("");
	   $("#id_wordmemo").val("");
   }
   function loadUserDataPermissioin(id){
	   loadMask.show();
	   var deptroleid=id;
	   ajaxRequest({
			url:"${pageContext.request.contextPath}/table/getTableData.action",
			async:true,
			success:function(response) { 
				var rt =response.responseText;
				var jsonData = JsonUtil.decode(rt);
				var datas = jsonData.datas;
				if(datas.length>0){
					 getTracywindyObject("id_combo_id_mayviewtype").setValue(datas[0].mayviewtype);
					 $("#id_wordmemo").val(datas[0].memo);
					 $("#id_column").val(datas[0].condtioncolumn);				 
				}
				$("#id_deptrole").val(deptroleid);
				loadMask.hide();
			},
			failure:function(response){ },
			params:{
				xmlFileName:'combos/comboDeptRoleDataPermission.xml',
			    deptroleid:id
			}
		});	
   }
   function saveUserDataPermissioin(){
	   loadMask.show();
	   var params={};
	   params["deptrole"]= $("#id_deptrole").val()||"";
	   params["mayviewtype"]=getTracywindyObject("id_combo_id_mayviewtype").getValue()||"";
	   params["memo"]= $("#id_wordmemo").val();
	   params["condtioncolumn"]=$("#id_column").val();
	   if(params["deptrole"]==""){alert("请选择部门下的角色");loadMask.hide();return false;};
	   if(params["mayviewtype"]==""){alert("请选择访问类型");loadMask.hide();return false;};
	   ajaxRequest({
			url:"${pageContext.request.contextPath}/table/savaUserDataPermissionData.action",
			async:true,
			success:function(response) { 
				alert("配置成功")
				loadMask.hide();
			},
			failure:function(response){ },
			params:params
		});	
   }
</script>
</head>
<body>
	<div id='menu_main_oper' style="overflow:hidden;float:left;border-bottom:1px solid #DDD;padding:5px;">
	   <div class="panel-title" ><font color="red" class="flagUserSelectContainer"></font>&nbsp;&nbsp;部门角色</div>
	   <div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>
	</div>
	<div id='menu_main_oper_right' style="overflow:hidden;float:left;border:1px solid #DDD;border-left:0px;border-top:0px;padding:5px;">
	   <div class="panel-title" ><font color="red" class="flagUserSelectContainer"></font>&nbsp;&nbsp;数据权限
	      <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='saveUserDataPermissioin();'><span>保存</span></a>
	   </div>
	   <div id='id_menu_main_content_div_right' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;">
	         <table style="width:100%;" id="formDataConfig">
			        <tr><td>可访问类型</td><td><div id="id_mayviewtype"></div></td></tr>
			        <td>对应的字段</td><td><input style="width:100%" name="condtioncolumn" id="id_column" value="" ></td></tr>
			        <tr><td>备注</td>
			            <td>
			               <textarea style="width:100%" name="memo" id="id_wordmemo" class="td-content-input"  value="" ></textarea>
			               <input style="width:100%" name="deptrole" id="id_deptrole"  type="hidden"  value="" >
			            </td>
			        </tr>
	        </table>
	   </div>
	</div>
	<script type='text/javascript'>
	    var heightAdd = -2;
	    var widthAdd  = -358;
		jQuery("#menu_main_content").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#id_menu_tabs_menu").css("height",(pageHeight+heightAdd-40)+"px");
		jQuery("#id_menu_main_content_div").css("height",(pageHeight+heightAdd-40)+"px");
		jQuery("#id_menu_main_content_div_right").css("height",(pageHeight+heightAdd-40)+"px");
		
		jQuery("#menu_main_oper").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper_right").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper").css("width",((pageWidth + widthAdd)/2 )+"px");
		jQuery("#menu_main_oper_right").css("width",((pageWidth + widthAdd)/2 )+"px");
		 jQuery(function(){
			 dictOnSelect('id_mayviewtype','mayviewtype','mayviewtype','',function(combox){
				 var type=combox.getValue();
				 if(type=="mayviewtype1"){$("#id_column").val("projmanage");}
				 if(type=="mayviewtype2"){$("#id_column").val("projdept");}
				 if(type=="mayviewtype3"){$("#id_column").val("projdept");}
				 
			 },'');
		 });
	</script>
</body>
</html>
