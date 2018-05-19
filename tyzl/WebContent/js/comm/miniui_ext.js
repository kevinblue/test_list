(function() {
	miniui_ext = {
		//获取mini对象
		get : function(id){
			return mini.get(id);
		},
		//combobox懒加载
		onbeforeshowpopup : function(e){
			var cb=e.sender;
			var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';//url 
		    if (url && !cb._loaded) {
		        var xmlFileName = cb._xmlFileName ||'/combos/comboDict.xml';//xmlFileName
		        var params = cb._params || '' ;//params参数
		        var temp="";
		        if('object' == typeof(params))
				{
				   for(var p in params)
				   {
					   temp+=("&"+p+"="+escape(encodeURIComponent(params[p]))); 
				   }
				}
		        var pageSize = cb._pageSize ||  100;//显示行数
		    	if(url.indexOf("&") == -1){
		    		cb.setUrl(url+"?xmlFileName="+xmlFileName+temp+"&pageSize="+pageSize);
		    	}else{
		    		cb.setUrl(url+"&xmlFileName="+xmlFileName+temp+"&pageSize="+pageSize);
		    	}
		        cb._loaded = true;
		    }
		},
		//根据表单ID获取combobox Text值、传后台 以rawValue_为前缀
		gettextsbyfromid : function(formid){
			var form = new mini.Form(formid);
			var o = form.getData(); 
			var fields = form.getFields();
			for(var index =0;index<fields.length;index++){
				 //判断是否是下拉框控件，是则同时把text属性传入后台
				if(fields[index].uiCls == "mini-combobox"){
					 o["rawValue_"+fields[index].name]=fields[index].text;
				}
			}
			return o;
		},
		//表单验证
		fromvalidation : function(formid){
			var form = new mini.Form(formid);
			form.validate();
			var o = form.getData(); 
			var fields = form.getFields();
			for(var index =0;index<fields.length;index++){
				 //fields[index].validate();//验证表单
				 //验证没通过、弹出验证错误信息
				 if(fields[index].isValid() == false){
					 //mini.alert(fields[index].errorText);
					 return false;
				 }
			}
			return true;
		},
		//多行验证、grid编辑
		gridvalidation : function(gridid){
			var grid = mini.get(gridid);
        	grid.validate();
        	if (grid.isValid() == false) {
                var error = grid.getCellErrors()[0];
                mini.alert(error.column.errorText,"提示：",function(data){
                	grid.beginEditCell(error.record, error.column);
                });
   			 	return false;
            }
        	return true;
		},
		//格式化日期
		onDateRenderer : function(e){
			var value = e.value;
		    if (value) return mini.formatDate(mini.parseDate(value), 'yyyy-MM-dd HH:mm:ss');
		    return "";
		},
		//初始化combobox加载数据 以 rawValue_为前缀
		//param 1 :表单ID
		//param 2 :数据（json对象）
		initcomboboxloaddata : function(formid,data){
			 var form = new mini.Form(formid);
			 var o = form.getData(); 
			 var fields = form.getFields();
			 for(var index =0;index<fields.length;index++){
				 //判断是否是下拉框控件，是则同时把text属性设置combobox
				 if(fields[index].uiCls == "mini-combobox"){
					var temp = "rawValue_"+fields[index].name;
					fields[index].setText(data[temp]);
				 }
			 }
			 
		},
		//表单只读
		initformenabled : function(formid){
			 var form = new mini.Form(formid);
			 form.setEnabled(false);
		},
		//根据字答串，禁用文本框
		initformenabledbystr : function(formid,str){
			 mini.parse(formid);
			var form = new mini.Form(formid);
			var fields = form.getFields();
			var strs = str.split(",");
			for(var j =0;j<strs.length;j++){
				 mini.getbyName(strs[j]).setEnabled(false);
		    }
		},
		initformreadOnly : function(formid){
			 var form = new mini.Form(formid);
			 var fields = form.getFields();
			 for(var i =0;i<fields.length;i++){
				 fields[i].setReadOnly(true);
			 }
		},
		//回车查询
		addOnEnter : function(divID){
	        var input=$('#'+divID+' input');
	        for (var i=0;i<input.length;i++){
	           var inputobj = input[i];
	           inputobj.setAttribute("onEnter","search");
	        }
	    },
	    //初始化验证
	    //param 1 : 表单ID
	    //param 2 : 属性字符串 -- args1,args2,args3...
	    //param 3 : isRequired 是否验证 取值false||true  默认true 
	    initrequired : function(formid,data,isRequired){
		   	 var form = new mini.Form(formid);
			 var o = form.getData(); 
			 var fields = form.getFields();
			 var strs = data.split(",");
			 for(var i =0;i<fields.length;i++){
				 if("all"== data){
					 fields[i].setRequired(isRequired ==false ? isRequired :true);
					 if(!isRequired){
						 fields[i].setIsValid(true);
					 }
					 continue;
				 }
				 for(var j =0;j<strs.length;j++){
					 if(fields[i].name == strs[j]){
						 if(!isRequired){
							 fields[i].setIsValid(true);
						 }
						 fields[i].setRequired(isRequired ==false ? isRequired :true);
					 }
				 }
			 }
		},
		//页面初始化数据
		//param 1 : 数据源XML
	    //param 2 : 表单ID
	    //param 3 : searchConditions 检索条件 
	    initPageData : function (xmlFileName,formid,searchConditions){
	    	var $me = this;
	    	var objData;
	    	if("null" == searchConditions||""==searchConditions) return;
	    	$.ajax({
		        url: urlPrefix+xmlFileName+"&id="+searchConditions+"&random="+Math.round(),
		        type: "post",
		        cache: false,
		        async : false,
		        success: function (text) {
		        	var form = new mini.Form(formid);
		        	var result = mini.decode(text);
		        	var data = result.datas[0];
		        	$me.restoreFromDataSpecialChar(data);
		            form.setData(result.datas[0]);
		            objData = result.datas[0];
		            miniui_ext.initcomboboxloaddata(formid,result.datas[0]);
		            form.setChanged(false);
		        }
		    });
	    	return objData;
	    },
	    //保存数据
		//param 1 : URL
	    //param 2 : 数据
		saveDataAjax : function (url,data){
			$.ajax({
		        url: url,
		        type: "post",
		        data:  data ,
		        success: function (text) {
		        	CloseWindow("savesuccess");
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
		    });
		},
		replaceFromDataSpecialChar:function(object){
			for(var item in object){
				if(typeof object[item]=="string"){
					object[item] = object[item].replace(/%/g,"%25").replace(/(\n)|(\r)/g,"<br>");
				}
			}
		},
		restoreFromDataSpecialChar:function(object){
			for(var item in object){
				if(typeof object[item]=="string"){
					object[item] = object[item].replace(/<br>/g, '\r\n');
				}
			}
		},
		//流程保存数据
		//param 1 : tabs的ID属性
	    saveData : function (tabsid){
	    	this.saveTabsData(tabsid,"save");
	    },
	    //流程提交数据
		//param 1 : tabs的ID属性
		submitData : function (tabsid){
			this.saveTabsData(tabsid,"submit");
		},
		//流程保存数据
		//param 1 : tabs的ID属性
	    //param 2 : 操作类型（保存：save,提交：submit）
		saveTabsData :function (tabsid,opertype){
	    	var tabDeatils = mini.get(tabsid);
	    	var tabs = tabDeatils.tabs;
        	for(var i =0;i<tabs.length;i++){
        		var tab = tabDeatils.getTab(tabs[i].name);
        		//var tabBody = tabDeatils.getTabBodyEl(tab);
        		//alert(tabBody.innerHtml);debugger;
        		var iframe = tabDeatils.getTabIFrameEl(tab);
        		if(typeof(iframe)!='undefined'){
            		var grid = iframe.contentWindow.returnTab();
        		    grid.validate();
        		    if("submit"==opertype){
	        		    if (grid.isValid() == false) {
	        			    mini.alert(tabs[i].title+"验证错误！");
	        			    return false;
	        		    }
        		    }
            		jQuery("#id_json_"+tabs[i].name+"_str").val(mini.encode(grid.getData()));
            	}
        	}
	    },
	    //流程保存数据
		//param 1 : tabs的ID属性
	    saveIncludeData : function (tabsid){
	    	this.saveIncludeTabsData(tabsid,"save");
	    },
	    //流程提交数据
		//param 1 : tabs的ID属性
		submitIncludeData : function (tabsid){
			this.saveIncludeTabsData(tabsid,"submit");
		},
	    saveIncludeTabsData :function (tabsid,opertype){
	    	var tabDeatils = mini.get(tabsid);
	    	var tabs = tabDeatils.tabs;
        	for(var i =0;i<tabs.length;i++){
        		var grid = mini.get(tabs[i].name);
        		if(typeof(grid)!='undefined'){
        		    grid.validate();
        		    if("submit"==opertype){
	        		    if (grid.isValid() == false) {
	        			    mini.alert(tabs[i].title+"验证错误！");
	        			    return false;
	        		    }
        		    }
        		    //if(grid.isinitData==1){
        		      if( mini.get("id_json_"+tabs[i].name+"_str")){
        		    	  mini.get("id_json_"+tabs[i].name+"_str").setValue(mini.encode(grid.getData()));
        		      }else if($("#id_json_"+tabs[i].name+"_str")){
        		    	  $("#id_json_"+tabs[i].name+"_str").val(mini.encode(grid.getData()));
        		      }	  
            		 
        		    //}
            	}
        	}
	    },
	    submitFormValidation : function(formidArrays){
	    	var flag = true;
	    	var errorText = "";
	    	var htmlArr = [];
	    	htmlArr.push("<div id='errorWindow'  class='mini-window' title='检验提示' style='width:400px;' showModal='true' allowResize='true' allowDrag='true'><div class='mini-fit' style='padding:10px 30px;'>");
	    	htmlArr.push("<table class='fillTable' align='center' cellspacing='0' cellpadding='0' style='border:1px solid #cccccc;'>");
	    	for(var j =0;j<formidArrays.length;j++){
	    		var formId = formidArrays[j];
	    		var form = new mini.Form("#" + formId);
	    		form.validate();
	    		if(form.isValid() == false){
	    			var errorFields = form.getErrors();
	    			htmlArr.push("<tr class='tr-project-info'>");
	    				htmlArr.push("<td rowspan="+ errorFields.length+" style='font-size:15px;font-weight:bold;border-bottom:1px dotted #cccccc;width:150px;text-align:center;'>" +(form.el.title+"" + '</td>'));
	    			for(var i = 0;i < errorFields.length;i ++){
	    				if(i>0){htmlArr.push("<tr>");}
	    				htmlArr.push("<td style='border-left:1px dotted #cccccc;border-bottom:1px dotted #cccccc;color:red;height:25px;line-height:25px;text-indent: 5px;'>" + (errorFields[i].label + " " + errorFields[i].errorText + '</td></tr>'));
	    			}
	    			
	    			flag = false;
	    		}
	    	}
	    	if(!flag){
	    		htmlArr.push( "</table>");
	    		htmlArr.push("</div><div class='mini-toolbar miniext-toolbar-bottom'>");
				htmlArr.push("<table class='miniext-form-fit'>");
				htmlArr.push("<tr>");
				htmlArr.push("<td>");
				htmlArr.push('<a class="mini-button" onclick=closeErrorWindow("errorWindow") style="width:80px">确定</a>');
				htmlArr.push(" </td>");
				htmlArr.push(" </tr>");
				htmlArr.push(" </table>");      
				htmlArr.push("</div>");
		    	htmlArr.push("</div>");
		    	var $errorPopupWindow = jQuery(htmlArr.join(""));
				jQuery(document.body).append($errorPopupWindow);
		    	mini.parse($errorPopupWindow);
		    	var editWindow = mini.get("errorWindow");
				editWindow.show();
	    	}
        	return flag;
	    },
	    //window添加行
		//param 1 : 表单ID
	    //param 2 : window的ID
	    addWindowRow : function (formid,windowid){
	    	var form = new mini.Form(formid);
	    	form.clear();
	    	mini.get(windowid).show();
	    },
	    //window修改行
	    //param 1 : 多表列表ID
		//param 2 : 表单ID
	    //param 3 : window的ID
	    updWindowRow : function (gridid,formid,windowid){
	    	var grid = mini.get(gridid);
	    	var row = grid.getSelected();
	    	var form = new mini.Form(formid);
	    	form.setData(row);
	    	this.initcomboboxloaddata(formid,row);
	    	mini.get(windowid).show();
	    },
	    //window保存行
	    //param 1 : 多表列表ID
		//param 2 : 表单ID
	    //param 3 : window的ID
	    //param 4 : 操作ID（主要分两种、新增add、修改update）
	    saveWindowRow : function (gridid,formid,windowid,opertype){
	    	var form = new mini.Form(formid);
	    	var row = mini.get(gridid).getSelected();
    	    var o = form.getData();
    	    var g = miniui_ext.gettextsbyfromid(formid);
    	    if("add" == opertype){
    	    	grid.addRow(mini.decode(g));
    	    }else{
    	    	grid.updateRow(row,mini.decode(g));
    	    }
    	    mini.get(windowid).hide(); 
	    },
	    //tabs设置高度 
	    //param 1 : 页签ID
		//param 2 : 高度字符窜
	    miniSetHeight : function (tabsid,str){
	    	var tabs = mini.get(tabsid);
	    	var activeIndex = tabs.activeIndex;
	    	var strs = str.split(","); //字符分割 
	    	tabs.setHeight(strs[activeIndex]);
	    },
	    setOddEvenRowsStyle : function(tableid){
	    	var table = document.getElementById(tableid);
	    	for (i = 0; i < table.rows.length; i++) {
	    		(i % 2 == 0) ? (table.rows[i].className = "row-odd") : (table.rows[i].className = "row-even");
	    	}
	    },
		disableFormFields : function(tform, excludeFields) {
			mini.parse(tform);
			var form = new mini.Form(tform);
			var fields = form.getFields();
			excludeFields = excludeFields || "";
			for (var index = 0; index < fields.length; index++) {
				if (excludeFields.indexOf(fields[index].name) < 0) {
					fields[index].disable();
				}
			}
		}
	   ,setFieldsRequired:function(tform,flag,excludeFields){
		   var tempExcludeFields=excludeFields||"";
		   mini.parse(tform);
		   var form = new mini.Form(tform);
		   var fields = form.getFields();
		   for(var index =0;index<fields.length;index++){
			   if(tempExcludeFields.indexOf(fields[index].name)<0){
			    fields[index].setRequired(flag);
			   }
		    }
	   },getGridLazyData:function(grid){
		   if(grid.isinitData==1){
			   return grid.getData();
		   }else{
			   var gridName=grid.id;
			   var values = mini.get("id_json_"+gridName+"_str").getValue();
			   return mini.decode(values);
		   }   
	   },loadinitGridData:function(grid){
		   var gridName=grid.id;
		   var values = mini.get("id_json_"+gridName+"_str").getValue();
		   grid.set({data:JsonUtil.decode(values)});
	   },getTalbeColumnData:function(Curtableid,column){//获得指定table的指定列的值
		   var curtable=mini.get(Curtableid);
		   var datas=[];
		   var talbdata=curtable.getData();
		   for(i=0;i<talbdata.length;i++){
			   datas.push(talbdata[i][column])
		   }
		   return datas;
	   },join:function(arr){
		   var s="";
		   if(arr.length>0){
			   for(var i=0;i<arr.length;i++){
				   if(i>0){s=s+",";}
				   s=s+"'"+arr[i]+"'";
			   }
	       }else{
	    	   return "";
	       }
		   return s;
		   },
		updateEnabledTable : function (tableId,entityClassName,dataId,enabled){
			mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在更新状态 请稍等...'});
			jQuery.ajax({
				url : getRootPath() + "/table/updateEnabledTable.action",
				method : 'POST',
				success : function(rs) {
					mini.unmask(document.body);
					mini.alert('更新成功！！！',"提示：",function(){
						mini.get(tableId).reload();
					});
				},
				async : false,
				failure : function(res) {
					mini.unmask(document.body);
				},
				dataType : 'json',
				data : {
					entityClassName : entityClassName,
					dataId : dataId,
					enabled : enabled
				}
			});
		}	   
	};
})();

var changTab = function (e){
	// var tabs = e.sender;
	 var tab =e.tab /* tabs.getActiveTab() */;
 	 var gridName = tab.name;
 	 var gridNames=gridName.split(",")
     for(var i=0;i<gridNames.length;i++){
 	 var grid = mini.get(gridNames[i]);
 	 if(typeof(grid) != 'undefined'&&null!=grid){
 		 var datas = grid.data;
 		 if(datas && grid.isinitData==0){
 			grid.isinitData=1;
 			var values = '';
 			if (typeof(mini.get("id_json_"+gridNames[i]+"_str")) != 'undifined'){
 				values = mini.get("id_json_"+gridNames[i]+"_str").getValue()||"";
 			}else{
 				values = jQuery('#'+"id_json_"+gridNames[i]+"_str").val()||"";
 			}
 			if(values!=""){
 		 	  grid.set({data:JsonUtil.decode(values)});
 			}
 		 }else{
 			grid.isinitData=1;
 		 }
 	 }}
 	 
}

function downLoadFileTemplate(fileTemplateNo){
	var uploadutil=new uploadUtil({
		url:'/leasing/template/leasing/template/downLoadTemplateAttach.action',
        parames:{templateNo:fileTemplateNo}}
	);
	uploadutil.createFormAndDown();
}

function closeErrorWindow(windowid){
	var closeErrorWindow = mini.get(windowid);
	closeErrorWindow.hide();
}

var fileTemplateConfig=function (config){
	this.url=config.url||"/leasing/template/createFileByTemplateClass.action";
	this.templateno=config.templateno||"";
	this.returntype=config.returntype||"listtocurpage";//file(文件下面),listtocurpage(当前页),listtonewpage(新页面),'jscallback'
    this.tableid=config.tableid||"createfiletable";
    this.modelname=config.modelname||"根据配置生成文档";
    this.jscallbak=config.jscallbak||function(){};
    this.browserType=SysBrowser.getBrowser().toLowerCase();
    this.parames=config.parames||{};
}

fileTemplateConfig.prototype.getParametetoStr=function(){
	var tempInnerHtml=""; 
	tempInnerHtml+= "<input type='hidden' name='templateno' value='" + this.templateno+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='returntype' value='" + this.returntype+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='tableid' value='" + this.tableid+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='modelname' value='" + this.modelname+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='browserType' value='" + this.browserType+ "'/>";
    for(var param in this.parames){
      if (typeof (this.parames[param]) == 'object'){
    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value=\"" + mini.decode(this.parames[param])+ "\"/>";
    	}else{
    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value=\"" + this.parames[param]+ "\"/>";	
      }
    }
    return tempInnerHtml;
}

fileTemplateConfig.prototype.getParametetoArray=function(){
	this.parames["templateno"]=this.templateno;
	this.parames["tableid"]=this.tableid;
	this.parames["returntype"]=this.returntype;
	this.parames["modelname"]=this.modelname;
	this.parames["browserType"]=this.browserType;	
	return this.parames;
}
fileTemplateConfig.prototype.createFile=function(){
	
	if(this.returntype=="file"){
		this.createFileAndDown();
	}else{
		this.createFileAndShow();
	}
}
fileTemplateConfig.prototype.createFileAndDown=function(){
	$form = jQuery("#" + "id_createAndDown_"+this.tableid);
	if (0 < $form.length) {
		document.body.removeChild($form[0]);
	}
	var tempInnerHtml = "<form action='" +getRootPath()+ this.url + "' id='" + "id_createAndDown_"+this.tableid + "' method='POST'>";
	tempInnerHtml+=this.getParametetoStr();
	tempInnerHtml += "</form>";
	$form = jQuery(tempInnerHtml);
	jQuery(document.body).append($form);
	$form.submit();
}
fileTemplateConfig.prototype.createFileAndShow = function() {
	mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'生成文件中...'});
	var tempid = this.tableid;
	var tempreturntype = this.returntype;
	var $me=this;
	ajaxRequest({
		url : getRootPath() + this.url,
		method : 'POST',
		success : function(rs) {
			var returnMessage = rs.responseText;
			var tableid = tempid;
			var returntype = tempreturntype;
			returnMessage = returnMessage.replace(/(^\s+)|(\s+$)/g, "");
			if (returnMessage != "") {
				mini.unmask(document.body);
				var vlist = eval("(" + returnMessage + ")");
				if (vlist.result == "success") {
					if (returntype == "listtocurpage") {
						var list = mini.get(tableid);
						list.addRows(vlist.message);
					} else {
						var ids = "";
						for (var i = 0; i < +vlist.message.length; i++) {
							if (i > 0) {
								ids += ",";
							}
							ids += "'" + vlist.message[i].id + "'";
						}
						$me.jscallbak();
						mini.open({
					        url: getRootPath() + '/leasing/common/templateManager/list.bi?id=' + ids,
					        title: "文件下载信息", width: 800, height: 400,
					        showModal: true
						});

					}
				} else {
					alert(vlist.message);
				}
			}
		},
		async : false,
		failure : function(res) {
			mini.unmask(document.body);
		},
		params : this.getParametetoArray()
	});
}


var fileOperator={
		downFile:function(id){
				$form = jQuery("#" + id);
				if (0 < $form.length) {document.body.removeChild($form[0]);}
				var url="/leasing/template/downLoadAttachById.action";
				var tempInnerHtml = "<form action='" +getRootPath()+ url + "' id='" + id + "' method='POST'>";
				tempInnerHtml+="<input type='hidden' name='id' value='" + id+ "'/>";
				tempInnerHtml += "</form>";
				$form = jQuery(tempInnerHtml);
				jQuery(document.body).append($form);
				$form.submit();
      },
      showOReditFile:function(id,otype){
    		var URL=getRootPath()+"/leasing/common/templateManager/editWordTemplate.bi";
    		var params={id:id,status:otype};
    	    openFullScreenWindow(URL,params);
    	},
      showfileRecoder:function(id){
    		var URL=getRootPath()+"/leasing/common/templateManager/fileRecoderlist.bi";
    		window.open(URL+'?id='+id,'newfilelist1','height=300,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
    	},
     dropFile:function(rowDatas){
    		var plandata = rowDatas;
    		var ids=[];
    		for(var i=0;i<plandata.length;i++){
    			ids.push(plandata[i].id);
    		}
    	    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除合同 请稍等...'});
    		var url="/leasing/template/dropCreateFile.action";
    		var param=[];
    		param["ids"]=ids+"";
    		ajaxRequest({
    			url:getRootPath()+url,
    			method:'POST',
    			success:function(rs){
    			var message= rs.responseText;
    				message=message.replace(/(^\s+)|(\s+$)/g, ""); 
    				mini.unmask(document.body);
    				alert(message);
    			},
    			async:false,
    			failure:function(res){
    				mini.unmask(document.body);
    			},
    			params:param
    		});
    	}		
};

var uploadUtil=function (config){
	this.id=config.id ||"id_uploadfile";
	this.title=config.title||"上传文件"; //标题 
	this.url=config.url||"";//url
	this.jscallback=config.jscallback||"";//回调
	this.parames=config.parames||{};
	this.modelname=config.modelname||"财务报表";
	this.browserType=SysBrowser.getBrowser().toLowerCase();
}

uploadUtil.prototype.createFileAndShow=function(){
	var $form = jQuery("#" + this.id);
	if (0 < $form.length) {
		document.body.removeChild($form[0]);
	}
	var importExcelUrl = getRootPath()+this.url;
	var uploadAttachmentFileWindow_html = "";
	uploadAttachmentFileWindow_html += '<div id="'+this.id+'" class="mini-window"  closed="true" modal="true" title="上传文件('+this.title+')" style="display:none;width:400px;text-align:center;background-color:#FDF9F8;">';
	uploadAttachmentFileWindow_html += '	<center>';
	uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
	uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
	uploadAttachmentFileWindow_html += '			<form id="'+this.id+'Form" action="'+importExcelUrl+'" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
	uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%"><tr><td colspan=2>';
	uploadAttachmentFileWindow_html +=this.getParametetoStr();
	uploadAttachmentFileWindow_html += "</td></tr>";
	uploadAttachmentFileWindow_html += '					<tr><td colspan=2><input type="file" id="id_tableImportTemplate" name="tableImportExcel" style="width:350px;border:1px solid #DDD;cursor:pointer;"></td></tr>';
	uploadAttachmentFileWindow_html += '					<tr class="content-separator">';
	uploadAttachmentFileWindow_html += '						<td colspan="2" align="center">';
	uploadAttachmentFileWindow_html += '							<a href="javascript:void(0);" id="'+this.id+'_save" class="mini-button" iconCls="icon-add"  onclick=\'javascript:document.getElementById("'+this.id+'Form").submit();\'><span class="mini-button-text">确定</span></a>';
	uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;" id="'+this.id+'_cancle" href="javascript:void(0);" class="mini-button" iconCls="icon-close"  onclick=\'javascript:mini.get("'+this.id+'").hide();\'><span class="mini-button-text" >取消</span></a>';
	uploadAttachmentFileWindow_html += '						</td>';
	uploadAttachmentFileWindow_html += '					</tr>';
	uploadAttachmentFileWindow_html += '				</table>';    
	uploadAttachmentFileWindow_html += '			</form>';
	uploadAttachmentFileWindow_html += '		</div>';
	uploadAttachmentFileWindow_html += '	</center>';
	uploadAttachmentFileWindow_html += '</div>';
	$(document.body).append(uploadAttachmentFileWindow_html);
	mini.parse();
	var formid=this.id+"Form";
	var id=this.id;
	$("#"+this.id+"_save").bind('click',function(){
		var filename=$("#id_tableImportTemplate").val();
		if(filename==""){alert("请选择上传的文件");return false;}
		mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'上传中...'});
		$("#"+formid).submit();});
	$("#"+this.id+"_cancle").bind('click',function(){mini.get(id).hide()});
	mini.get(this.id).show();
}
uploadUtil.prototype.getParametetoStr=function(){
	var tempInnerHtml=""; 
	tempInnerHtml+= "<input type='hidden' name='browserType' value='" + this.browserType+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='currentTableId' value='" + this.id+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='jscallback' value='" + this.jscallback+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='modelname' value='" + this.modelname+ "'/>";
    for(var param in this.parames){
      if (typeof (this.parames[param]) == 'object'){
    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value=\"" + mini.decode(this.parames[param])+ "\"/>";
    	}else{
    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value=\"" + this.parames[param]+ "\"/>";	
      }
    }
    return tempInnerHtml;
}
uploadUtil.prototype.createFormAndDown=function(){
	$form = jQuery("#" + this.id);
	if (0 < $form.length){document.body.removeChild($form[0]);}
	var uploadAttachmentFileWindow_html = "";
	uploadAttachmentFileWindow_html += '<div id="'+this.id+'" class="mini-window"  closed="true" modal="true" title="上传文件('+this.title+')" style="display:none;width:300px;text-align:center;background-color:#FDF9F8;">';
	uploadAttachmentFileWindow_html += '	<center>';
	uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
	uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
	uploadAttachmentFileWindow_html += '			<form id="'+this.id+'Form" action="'+getRootPath()+ this.url+'" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
	uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%"><tr><td colspan=2>';
	uploadAttachmentFileWindow_html +=this.getParametetoStr();
	uploadAttachmentFileWindow_html += "</td></tr>";
	uploadAttachmentFileWindow_html += '				</table>';
	uploadAttachmentFileWindow_html += '			</form>';
	uploadAttachmentFileWindow_html += '		</div>';
	uploadAttachmentFileWindow_html += '	</center>';
	uploadAttachmentFileWindow_html += '</div>';
	$(document.body).append(uploadAttachmentFileWindow_html);
	$form = jQuery("#" + this.id+"Form");
	$form.submit();	
}
function getMiniEditFormField(fieldName){
	var fields=fieldName.split(".");
	return mini.get(fields[0]+"_editFormPopupWindow_form_"+fields[1]);
}

function comboboxChanged(e){
	 var cbb=e.sender;
	 mini.get("rawValue_"+cbb.name).setValue(cbb.text);
} 
function onbeforeshowpopup(e) {
    miniui_ext.onbeforeshowpopup(e);
 }
function updateEnabledTable(tableId,entityClassName,dataId,enabled){
	miniui_ext.updateEnabledTable(tableId,entityClassName,dataId,enabled);
}
