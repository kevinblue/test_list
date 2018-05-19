<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表组件测试</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css"/>
	<style type="text/css">
	  html,body{
	    overflow:hidden;
	  }
	</style>
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyQueryInput.js"></script>
    <script type="text/javascript">
    jQuery(function(){
	    var table = new tracywindyTable({
	    renderTo:'id_tableContainer_2',
	    width:1000,
	    height:500,
	    border:true,
	    //isFit:true,
	    title:'测试',
	    tools:[{
    	      html:'<font color="red">测试</font>',
    	      handler:function(table){
        	    //alert( JsonUtil.encode(table.getCheckedRowDatas()));
          	    table.setParams({
                     proj_id:'P-2013-000022'
              	});
              	table.reload();
        	  },
        	  iconCls:'icon-add'
    	    },'-',{
    	      html:'<font color="red">测试2</font>',
    	      handler:function(table){
        	    //alert( JsonUtil.encode(table.getCheckedRowDatas()));
          	    table.setParams({
                     proj_id:''
              	});
              	table.reload();
        	  },
        	  iconCls:'icon-add'
    	    }],
    	/*isPage:true,*/
    	pageSize:5,
    	isRank:true,
    	rankSize:50,
	    isCheck:true,
	    emptyChar:'--',
	    checkType:'radio',
	    //xmlFileName:'test/test.xml',
	   // loadMode:'local',
	   // queryArea:'',
	   // datas:[{name:"张三",value:'1'},{name:"里斯",value:''},{name:"里斯",value:'2'}],
	    //statisticColumnNames:['value'] ,
	    //isStatistic:true,
	    loadMode:'local',
	    //isExportTitle:false,
	    lockedNames:['name'],
	    lockedComplexHeaders:[[{
           colspan:1,
           rowspan:4,
           header:'合并aaa',
           headerAlign:'center', 
           isFillEmpty:true    
    	}],[],[],[]],
	    complexHeaders:[[{
            colspan:7,
            rowspan:2,
            header:'全部合并',
            headerAlign:'center' 
     	},],[],[{
           colspan:7,
           header:'合并',
           headerAlign:'center' 
    	}],[{
            colspan:1,
            header:'合并1',
            headerAlign:'center' 
     	},{
            colspan:6,
            header:'合并2',
            headerAlign:'center' 
     	}]],
     	exportComplexHeaders:[[{
            colspan:1,
            rowspan:4,
            header:'合并aaa',
            headerAlign:'center' 
     	},{
           colspan:7,
           rowspan:2,
           header:'全部合并',
           headerAlign:'center' 
    	}],[],[{
    		   colspan:7,
               rowspan:1,
               startColNum:2,
               header:'合并',
               headerAlign:'center' 
        }],[{
 		   colspan:1,
           rowspan:1,
           startColNum:2,
           header:'合并1',
           headerAlign:'center' 
        },{
 		   colspan:6,
           rowspan:1,
           header:'合并2',
           headerAlign:'center' 
        }]],
	   columns:[{
    	                 header:'显示列',
    	                 name:'name',
    	                 queryConfig:{}
    	          },{
     	                 header:'因曾列',
     	                 name:'value',
     	                 hidden:false,
     	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
  	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
    	          		 },
    	          		queryConfig:{}
        	       },{
   	                 header:'测试',
 	                 name:'test',
 	                 hidden:false,
 	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
        	       },
        	       queryConfig:{}
    	       },{
   	                 header:'测试1',
 	                 name:'test',
 	                 hidden:false,
 	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
        	       },
        	       queryConfig:{}
    	       },{
   	                 header:'测试2',
 	                 name:'test',
 	                 hidden:false,
 	                 width:200,
 	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
        	       },
        	       queryConfig:{}
    	       },{
   	                 header:'测试3',
 	                 name:'test',
 	                 hidden:false,
 	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
        	       },
        	       queryConfig:{}
    	       },{
   	                 header:'测试4',
 	                 name:'test',
 	                 hidden:false,
 	                 width:150,
 	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
        	       },
        	       queryConfig:{}
    	       },{
   	                 header:'测试5',
 	                 name:'test',
 	                 hidden:false,
 	                 renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                       return "<a href='#'>"+rowData['value']+rowData['name']+"</a>";
        	       },
        	       queryConfig:{}
    	       }],
    	       datas:[{name:'张三',value:'决定将大家',test:'咚咚咚'}],
	           isExcel:true,
	           params:{
	    	    	//proj_id:'P-2013-000022'
	            }
	 });
});
    </script>
</head>
<body>
<div id="info"></div>
<table>
   <tr>
   <td><div id="id_tableContainer"></div></td>
   <td><span id="id_tableContainer_1"></span></td>
   <td><span id="id_tableContainer_2"></span></td>
   </tr>
   <table>
</body>
</html>