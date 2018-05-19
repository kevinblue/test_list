<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>法人客户信息管理</title>
<%@include file="/base/mini.jsp"%>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
    	<div class="mini-panel" title="法人客户" showCollapseButton="false" style="width: 100%;">
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table" style="width:95%">
	                <tr>
	                    <td style="width:100px;">客户名称：</td>
	                    <td> <input name="custname" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                    <td style="width:100px;" >组织机构代码：</td>
	                    <td><input name="orgcode" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                    <td style="width:100px;" >客户内部行业：</td>
	                    <td><mini:dict  name="custkind"  parentid="cust_kind"/>
	                    </td>
	                </tr>   
	                <tr>
	                    <td class="miniext-form-td">纳税人识别号：</td>
	                    <td><input name="taxregcode" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                    <td class="miniext-form-td">客户类别：</td>
	                    <td><mini:dict  name="custtype"  parentid="cust_type"/></td>
	                    <td class="miniext-form-td">是否草稿：</td>
	                    <td><input name="draft" class="mini-combobox miniext-form-fit" textField="text" valueField="value"   data="[{text:'是',value:'是'},{text:'否',value:'否'},{text:'全部',value:''}]" allowInput="false" />
	                    </td>
	                </tr>   
	                <tr>
	                    <td class="miniext-form-td">是否废弃：</td>
	                    <td > <input name="invalid" class="mini-combobox miniext-form-fit" textField="text"   valueField="value"    data="[{text:'是',value:'是'},{text:'否',value:'否'},{text:'全部',value:''}]"   allowInput="false"    value="否"	  text="否"/> </td>
	                    <td colspan="4">
	                    	<a class='mini-button query-button' iconCls='icon-search' onclick="search">搜索</a>
	                    	<a  class='mini-button query-button' onclick="clear" iconCls='icon-remove' style='color:#FFA5A5;border: 1px solid #FFA5A5;'>清空</a>
	                    </td>
	                </tr>   
	            </table>
	        </div>
	    </div>
    </div>
    <!-- 按钮部分 -->
    <div id="miniuiextbuttons"></div>
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">      
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="${urlPrefix}/eleasing/jsp/cust_info/cust_company/cust_company_list.xml"  idField="id"
            sizeList="[5,10,20,50]"  allowAlternating="true" pageSize="20"
            dataField="datas" showColumnsMenu="true" >
            <div property="columns">
                <div type="indexcolumn" width="35"></div>
                <div type="checkcolumn" width="35"></div>
                <div field="custname" width="200" headerAlign="center" allowSort="true" renderer="onActionRenderer">客户名称</div>                            
                <div field="custnumber" width="100" align="center" allowSort="true" headerAlign="center">客户编号</div>
                <div field="orgcode" width="100" headerAlign="center" allowSort="true" >组织机构代码</div>                                    
                <div field="rawValue_custtype" width="150" headerAlign="center" allowSort="true" >客户类别</div>
                <div field="rawValue_custkind"  headerAlign="center" allowSort="true" >客户内部行业</div>
                <div field="rawValue_draft"  headerAlign="center" allowSort="true">是否草稿</div>
                <div field="invalid"  headerAlign="center" allowSort="true">是否废弃</div>
                <div field="rawValue_ownership"  headerAlign="center" allowSort="true" >企业性质</div>
                <div field="rawValue_custsource"  headerAlign="center" allowSort="true" >客户来源</div>
                <div field="rawValue_taxregtype"  headerAlign="center" allowSort="true" >纳税人类别</div>
                <div field="taxregcode"  headerAlign="center" allowSort="true" >纳税人识别号/国税登记号</div>
                <div field="personrep"  headerAlign="center" allowSort="true" >法人代表</div>
                <div field="belongingpeople"  headerAlign="center" allowSort="true" >归属人</div>
                <div field="custactualpeople"  headerAlign="center" allowSort="true" >客户实际负责人</div>
                <div field="companyaddress"  headerAlign="center" allowSort="true" >经营地址</div>
                <div field="creatorname"  headerAlign="center" allowSort="true" >登记人</div>
                <div field="deptmentname"  headerAlign="center" allowSort="true" >登记部门</div>
                <div field="createdate"  headerAlign="center" allowSort="true" renderer="miniextonDateRenderer">登记时间</div>
                
            </div>
        </div> 
    </div>
    <div id="id_table_change_manage" class="mini-window" title="客户经理分配"
		style="width: 400px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<table style="width: 100%">
			<tr style="width: 100%;">
			    <td style="width: 20%;">客户经理：</td>
				<td style="width: 70%;"><input name="changemanage" id='changemanage'
					class="mini-combobox" label="客户经理" textField="name" required="true"
					valueField="id" dataField="datas" allowInput="false"
					data-options="{_xmlFileName:'/eleasing/workflow/proj/proj_common/creator_combox.xml',_params:{role:'r_business_manager'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="comboboxChanged" style="width: 90%;"/> <font class="required-tag">*</font>
					<input id="rawValue_changemanage"
					name="rawValue_changemanage"
					class="mini-textbox" style="display: none" /></td>
			</tr>
			<tr style="width: 100%;">
			    <td colspan="2" style='text-align: center;'> <a class="mini-button" iconCls="icon-save" plain="true" onclick="changemanage">确定</a>
			    &nbsp;&nbsp;
			    <a class="mini-button" iconCls="icon-save" plain="true" onclick="javascript:{mini.get('id_table_change_manage').hide()}">取消</a>
			    </td>
			</tr>
		</table>
	</div>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("datagrid1");
        var form = new mini.Form("form1");
        var custData = form.getData(true,true);
        miniui_ext.addOnEnter("form1");
        grid.load(custData);
        grid.frozenColumns(0, 2);
        
	    //表格加载之前发生，还未向后台发送参数，此时可修改往后台传的参数
	    function onBeforeCustGridLoad(e){
	    	e.data.TableRemoteSortField = e.data.sortField;
	    	e.data.TableRemoteSortDir = e.data.sortOrder;
	    }
        
        function search(e) {
        	custData = form.getData(true,true);
 		    grid.load(custData);
        }
        function clear(e) {
        	form.clear();
        	mini.getbyName("invalid").setValue("否");
        	mini.getbyName("invalid").setText("否");
        	custData = form.getData(true,true);
 		    grid.load(custData);
        }
        
        function opencustdetail(id,name){
        	var params = "id="+id;
      		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
      		var sheight = window.screen.availHeight - 30;
    		var swidth = window.screen.availWidth - 10;
    		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
      		window.open(url, '_blank', winoption);
        }
        function onActionRenderer(e) {
        	var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;
            var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.id + '\')">' + e.value + '</a>';
            return s;
        }
        function changemanage(){
        	var custid=mini.get("datagrid1").getSelected().id;
        	var userid=mini.get("changemanage").getValue();
        	$.ajax({
    	        url: getRootPath()+"/acl/changemanage.acl",
    	        data :{ "custid": custid,"userid":userid},
    	        async : false,
    	        success: function (text) {
    	        	mini.alert("分配成功！");
    	        	mini.get('id_table_change_manage').hide();
    	        	mini.get("datagrid1").reload();
    	        }
    	    });
        }
        
        var button = new miniuiExtButtons({
    		url:'/leasing/cust_info/cust_company/input_cust_company_list.bi',
    		title : '法人客户信息', 
    		width :  800, 
    		height : globalClientHeight - 50,  
    	    datagridId : "datagrid1",
    	    renderTo : "miniuiextbuttons",
    	    constantFlagTable : "CustLaw",
    	    tools :['新增', '修改', {
    	    	html : '废弃',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-remove',//按钮的图标
				handler : function(miniTable, buttonText) {//按钮响应函数
					var row = miniTable.getSelected();
					if (null != row) {
				    	mini.confirm("确定废弃选中记录？","提示：",function(data){
				        	if("ok" == data){
				        		var custidvalue = row.id;
				        		var str = "";
				        		$.ajax({
				        	        url: getRootPath()+"/acl/checkCustByProjInfo.acl",
				        	        type: "post",
				        	        cache: false, 
				        	        data :{ "custId": custidvalue},
				        	        async : false,
				        	        success: function (text) {
				        	        	var obj=mini.decode(text);
				        	        	if("repeat" == obj.message){
				        	        		mini.alert("该客户已有项目立项记录，不能修改为草稿，并且不能废弃！");
				        	        	}
				        	        	str = obj.message;
				        	        }
				        	    });
				        		if("" == str){
					        		grid.loading("操作中，请稍后......");
						            $.ajax({
						                url: "<%=request.getContextPath() %>/acl/removeCustLaw.acl?id="+row.id,
						                data: {}  ,
						                success: function (text) {
						                	miniTable.reload();
						                },
						                error: function () {
						                }
						            });
				        		}
				        	}
				        });
				    } else {
				        mini.alert("请选中一条记录");
				    }
				}
    	    }, {
    	    	html : '分配归属人',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-edit',//按钮的图标
				handler : function(miniTable, buttonText) {
					var rentRowData = miniTable.getSelected();
					if(rentRowData){
						contractId=rentRowData.id;
						mini.get("id_table_change_manage").show();
					}else{
						mini.alert("请选中要操作的数据！");
					}
				}
    	    }]
    	});
    </script>
</body>
</html>