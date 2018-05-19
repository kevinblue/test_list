<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "evaluation_model_statistics1",
			renderTo: "id_evaluation_model_statistics1",
			width: globalClientWidth - 15,
			height: 615,
			lazyLoad: false,
			isClickLoad:false,
			title: "",
			remoteOper : false,
			showPager: false,
			params :{
				projectid:projectid
		            },
			xmlFileName : '/eleasing/workflow/proj/proj_common/proj_evaluation_model.xml', 
			columns: [
				{type:'indexcolumn'},
				{field:'subject_name',header:'',width:200,align:'center'},
				/* {field:'tablearea', header:'',width:'5%',
					renderer:function(e){
						var row=e.record;
						var valueStr = getValueStr(row.tablearea);
						return valueStr;
							}}, */
				{field:'file1', header:'文件一',
								columns : [ 
											{
												field : 'str1',
												header : '银行贷款',
												headerAlign : 'center',
												width : 80,
												height: 30,
												allowSort : true
											}, 
											{
												//没
												field : 'str9',
												header : '融资租赁',
												headerAlign : 'center',
												width : 80,
												height: 30,
												allowSort : true
											}
											
											]
				},
				{field:'file2',header:'文件二',
					columns : [ 
								{
									field : 'str2',
									header : '银行贷款',
									headerAlign : 'center',
									width : 80,
									height: 30,
									allowSort : true
								}, 
								{
									//没
									field : 'str10',
									header : '融资租赁',
									headerAlign : 'center',
									width : 80,
									height: 30,
									allowSort : true
								}
								
								]	    },
				{field:'file3',header:'文件三',
						columns : [ 
									{
										field : 'str3',
										header : '银行贷款',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}, 
									{
										//没
										field : 'str11',
										header : '融资租赁',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}
									
									]	    },
				{field:'file4', header:'文件四',
						columns : [ 
									{
										field : 'str4',
										header : '银行贷款',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}, 
									{
										//没
										field : 'str12',
										header : '融资租赁',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}
									
									]	    },	
				{field:'file5',header:'文件五',
						columns : [ 
									{
										field : 'str5',
										header : '银行贷款',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}, 
									{
										//没
										field : 'str13',
										header : '融资租赁',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}
									
									]	    },
				{field:'file6', header:'文件六',
							columns : [ 
										{
											field : 'str6',
											header : '银行贷款',
											headerAlign : 'center',
											width : 80,
											height: 30,
											allowSort : true
										}, 
										{
											//没
											field : 'str14',
											header : '融资租赁',
											headerAlign : 'center',
											width : 80,
											height: 30,
											allowSort : true
										}
										
										]    },
				{field:'file7',header:'文件七',
				columns : [ 
							{
								field : 'str7',
								header : '银行贷款',
								headerAlign : 'center',
								width : 80,
								height: 30,
								allowSort : true
							}, 
							{
								//没
								field : 'str15',
								header : '融资租赁',
								headerAlign : 'center',
								width : 80,
								height: 30,
								allowSort : true
							}
							
							]	    },
				{field:'file8',header:'文件八',
						columns : [ 
									{
										field : 'str8',
										header : '银行贷款',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}, 
									{
										//没
										field : 'str16',
										header : '融资租赁',
										headerAlign : 'center',
										width : 80,
										height: 30,
										allowSort : true
									}
									
									]	    }
		        ]
	});});
});
function getValueStr(valueStr){
	 var renderHtml1 = "<table style='border:0px solid #FFF;'>";
	 var fileHtmlTd = '';
     var renderHtml2 = "</table>";
	 var valuearray = valueStr.split("/");
	 fileHtmlTd +="<tr style='border:0px solid #FFF;'>"+
                      "<td style='border:0px solid #FFF;'>"+valuearray[0]+
                      "</td>"+
                      "<td style='border:0px solid #FFF;'>"+valuearray[1]+
                      "</td>"+
                 "</tr>";
       return renderHtml1+fileHtmlTd+renderHtml2;
}


</script>
<div id="id_evaluation_model_statistics1"></div>
