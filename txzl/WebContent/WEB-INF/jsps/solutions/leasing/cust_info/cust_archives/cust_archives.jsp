<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">		

	var custcontactData;
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false;}
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 								
  				new ApTable({ 					
  					id:'table_id4',					
  					renderTo:'content_table_id5',
  					width:globalClientWidth,
  					height:globalClientHeight,					 															
  					iconCls:'icon-node',
  					hiddenQueryArea:false,
  					showToolbar: showTools,//是否显示表格按钮行
  					title:'档案信息查询',//修改显示页面的标题
  					queryButtonColSpan:6,  	
  					queryButtonNewLine:true,
  					remoteOper:true,//是否每次操作都交给后台，为true 时必须定义  entityClassName
  					entityClassName:'com.tenwa.leasing.entity.cust.CustArchives',
  					pageSize:15,
  					showPager:true,
  					lazyLoad:false, 	
  					xmlFileName:'/eleasing/jsp/cust_info/cust_archives/get_cust_archives.xml',  
  					
  				afterShowWindowCallBack:function(miniTable,miniForm, operType){
						 if(operType=='add'){//如果是新增就获取流水号
						     $.ajax({
							        url: getRootPath()+"/acl/getRunningWater.acl",
							        type: "post",
							        cache: false,
							        data :{},
							        async : false,
							        success: function (date) {
							        	mini.getbyName("rownumber").setValue(date);
							        }
							 });				 
						 }
					}, 
  					
  					//tools数组用来画出新增、修改、删除、查询等按钮
  					tools:[ 'add', '-', 'edit', '-', 'remove','-',
                              {
                               html:'查看所有档案资料',
                               plain:true,
                               iconCls:'icon-search',
                               handler:function(miniTable, buttonText) {
  						          showanduploadfile("xx","all");
                        }}],               
            
  					columns:[ 
						{type:'checkcolumn'}, 
  					    {type:'indexcolumn'},  					    
  						{field:'id',header:'id',headerAlign:'center',visible:false},	
  						{field:'rownumber',header:'序号',
  								
  								headerAlign:'center',
  								 width:100,
  								  allowSort:true,	
  							    queryConfig :{
  							    	
  							    },		
						    formEditorConfig:{								
							         type:'text',								
							      labelWidth:100,
							      width:150,
							      readOnly:true
  							           }},  	
  						 {field:'archivestitle',header:'档案标题',
  							      headerAlign:'center',
  							      width:100,
  							      allowSort:true ,	
  							    queryConfig : {
  							    	
  							    },	
  								  formEditorConfig:{								
  								 	   type:'text',								
  								 labelWidth:100,
  			  						 width:150}}, 
  			  			{field:'archivestype',header:'档案类型',
  			  					  headerAlign:'center',
  			  					  width:100,
  			  					  allowSort:true ,
  			  					  queryConfig : {},				
  			  				formEditorConfig:{	
  			  						newLine:true,
	  			  					 type:'text',								
	  			  			   labelWidth:100,
	  			  				    width:150}}, 	  			  						  			     
  			  			{field:'archivesexplain',header:'档案说明',
	  			  				    	headerAlign:'center',
	  			  				    	width:100,
	  			  				    	allowSort:true ,					
  			  			    formEditorConfig:{
  			  			    	newLine:true,
  			  			  				type:'textarea',								
  			  			  	  		labelWidth:100,
  			  			  		  		 width:150}}, 
  			  			
		  				 {field:'',header:'操作',
		  							  formEditorConfig:{
		  		                          fieldVisible:false},
		  							renderer:function(e){
		  								 var id = e.record.id;
		  					return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 </a>";
		 				}}
  					    
  					]					
  				});
  				
  			});  					
  			
  		});
  		function showanduploadfile(id,type){
  		
			/* var custid='${sessionScope.loginUser.id}';
  			  if(custid==""||custid.length<0){
  				  mini.alert("没有获取到当前登录人,请重新登录系统");
  					return false;  
  			  } */
  			var urlFlag = getRootPath()+"/leasing/cust_info/cust_archives/cust_archives_file_list.bi?id="+id+"&type="+type;
  			mini.open({
  		        url: urlFlag,
  		        title: "档案资料", width: 800, height: 455,
  		        onload: function () {},
  		        ondestroy: function (action) {
  		        	if("savesuccess" == action){
  		        		window.location.reload();
  		        	}
  		        }
  		    });

  	  	} 
	</script>
<div id='content_table_id5'></div>