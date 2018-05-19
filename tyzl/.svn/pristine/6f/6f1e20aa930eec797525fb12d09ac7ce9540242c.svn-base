<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyMultiTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/ajaxfileupload.js"></script>
    <style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
<script type="text/javascript">
	function validataTotalMoney(){//验证金额，add by Jason
		if(chenckCondiotn){
			//计算租赁物的总交易价格
			var currentTable1 = getTracywindyObject('table_id_table_leasing_proj_equip_container');
			var data = currentTable1.tableData;
			var totalPrice = 0;
			for(var i=0;i<data.length;i++){
				totalPrice += parseFloat(data[i]['price']);
			}
			//计算商务条件的设备款
			var equipAmt = parseFloat($("#equipAmt").val());
			if(totalPrice !=  equipAmt){//如果不相等，则跳出执行
				alert("<租赁物明细>标签的合计交易价格和<拟商务条件>标签中的设备款不相等,请检查.");
				return false;
			} else{
				return true;
			}
		}else{
			alert("您修改过商务条件中的值且未进行过测算!");
			return false;
		}
	}
</script>    
<script>
jQuery(function(){
	    var currentIsNeedTools = true;
	    if(('true'!='${isFirstTask}')||('true' == '${isViewHistoryTask}')){
	    	currentIsNeedTools = false;
	    }
	    new tracywindyMultiTable({
	    renderTo:'id_table_leasing_proj_equip_container',
	    width:1000,
	    isAutoHeight:true,
	    isNeedTools:true,
	    isFit:true,
	    datas:JsonUtil.decode('${empty json_proj_equip_str ? "[]" : json_proj_equip_str }'),
	    columns:[
	       {name:'id',  header:'id',hidden:true},
	       {name:'eqipname',  header:'设备名称',nullable:false,readOnly:false},
	       {name:'model',  header:'规格型号'},
	       {name:'equipnum',  header:'数量',nullable:false,type:'number',defaultValue:1},
	       {name:'total',  header:'设备原值',nullable:false,type:'double'/*,otherAttributes:" onpropertychange='document.all.price.value=document.all.equip_num.value*this.value' "*/},
	       {name:'nowtotal',  header:'设备现值',nullable:false,type:'double'},
	       {name:'price',  header:'交易价格',nullable:false,type:'double'},
	       {name:'brand',  header:'品牌',nullable:false},
	       {name:'vndr', header:'供应商',nullable:false,type:'combobox',defaultRawValue:'',
			    config:{
				       width:165,
				       xmlFileName:'\\eleasing\\workflow\\proj_approval\\simpleCustSelect.xml',
				       loadMode:'ajax',
				       readOnly:false,
				       isAjaxLenovo:true,
				       displayField:'name',
				       valueField:'code',
				       params:{
	    	   				custType:'cust_type.vndr'
				       } 
		   }},
	       {name:'manufacturer',  header:'制造商',nullable:false,type:'combobox',
			    config:{
			       width:165,
			       xmlFileName:'\\eleasing\\workflow\\proj_approval\\simpleCustSelect.xml',
			       loadMode:'ajax',
			       readOnly:false,
			       isAjaxLenovo:true,
			       displayField:'name',
			       valueField:'code',
			       params:{
			  			custType:'cust_type.manufacturer'
			       }
   			}},
	       {name:'devicetype',  header:'设备类型',nullable:false,type:'combobox',
			    config:{
			       width:165,
			       xmlFileName:'\\combos\\comboDict.xml',
			       loadMode:'ajax',
			       readOnly:true,
			       displayField:'name',
			       valueField:'code',
			       params:{
			          pid:'equip_type'
			       } 
			}},
	       {name:'eqipid',  header:'设备序号'},
	       {name:'equipdeliveryplace',  header:'交付地点'},
	       {name:'equipdeliverydate',  header:'交付时间',nullable:false,type:'date'},
	       {name:'equipplace',  header:'设备设置地址'},
	       {name:'cenote', header:'备注',type:'textarea'}
	     ]
	 });
});

</script>
</head>
<body>
<div id="id_table_leasing_proj_equip_container"></div>
</body>
</html>