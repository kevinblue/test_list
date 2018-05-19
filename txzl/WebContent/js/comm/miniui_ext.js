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
		        var pageSize = cb._pageSize || 100;//显示行数
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
	    	if("null" == searchConditions) return;
	    	$.ajax({
		        url: urlPrefix+xmlFileName+"&id="+searchConditions,
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
        		//alert(tabs[i].name);
        		var grid = mini.get(tabs[i].name);
        		if(grid&&typeof(grid)!='undefined'){
        			//console.info(grid);
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
				var styleStr = $(fields[index]).attr("style");
				if(styleStr && styleStr.replace(/\s/gim, '').indexOf("display:none") > -1){continue;}
				if (excludeFields.indexOf(fields[index].name) < 0) {
					fields[index].disable();
				}
			}
		}
	   ,setFieldsRequired : function (tform, flag, excludeFields) {
			var tempExcludeFields = excludeFields || "";
			mini.parse(tform);
			var form = new mini.Form(tform);
			var fields = form.getFields();
			for (var index = 0; index < fields.length; index++) {
				if (tempExcludeFields.indexOf(fields[index].name) < 0 && fields[index].visible != false) {
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
		},
		getGridSummaryFieldValue : function(grid, summaryField){
			var summaryValue = $(grid.getSummaryCellEl(summaryField)).html();
			if(summaryValue == "&nbsp;"){summaryValue=0;}
			summaryValue = (summaryValue+'').replace(/,/g, "");
			return summaryValue;
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
  	try{
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
	 
		
	}catch (e){
		
		
	}
 	 
/* 	 if(typeof(grid) != 'undefined'){
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
 	 }*/
 	 
     
     }
 	 
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
	this.attachmentParams = config.attachmentParams||{};
	this.isAttachment = config.isAttachment || false;
}
fileTemplateConfig.prototype.getParametetoStr=function(){
	var tempInnerHtml=""; 
	tempInnerHtml+= "<input type='hidden' name='templateno' value='" + this.templateno+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='returntype' value='" + this.returntype+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='tableid' value='" + this.tableid+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='modelname' value='" + this.modelname+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='browserType' value='" + this.browserType+ "'/>";
	if(this.isAttachment){
		tempInnerHtml += "<input type='hidden' name='isAttachment' id='id_isAttachment' value='true'>";
		for(var p in this.attachmentParams){
			tempInnerHtml+= "<input type='hidden' name='"+p+"' id='"+p+"'  value=\"" + this.attachmentParams[p]+ "\"/>";	
		}
	}
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
	for(var p in this.attachmentParams){
    this.parames[p]=this.attachmentParams[p];	
	}
	this.parames["attachmentParams"]=this.attachmentParams;
	this.parames["isAttachment"]=this.isAttachment;	
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
	console.info(tempInnerHtml);
	$form = jQuery(tempInnerHtml);
	jQuery(document.body).append($form);
	$form.submit();
}
fileTemplateConfig.prototype.createFileAndShow = function() {
	//console.info(this.getParametetoArray());
	//console.info(this.url);
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
		async : true,
		failure : function(res) {
			mini.unmask(document.body);
		},
		params : this.getParametetoArray()
	});
}


var fileOperator = {
	downFile : function (id) {
		$form = jQuery("#" + id);
		if (0 < $form.length) {
			document.body.removeChild($form[0]);
		}
		var url = "/leasing/template/downLoadAttachById.action";
		var tempInnerHtml = "<form action='" + getRootPath() + url + "' id='" + id + "' method='POST'>";
		tempInnerHtml += "<input type='hidden' name='id' value='" + id + "'/>";
		tempInnerHtml += "<input type='hidden' name='browserType' value='" + SysBrowser.getBrowser().toLowerCase() + "' />"
		tempInnerHtml += "</form>";
		$form = jQuery(tempInnerHtml);
		jQuery(document.body).append($form);
		$form.submit();
	},
	downRentNoticeFile : function (rowjson) {
		var row =mini.decode(rowjson);
		var id=row.fileid;
		$form = jQuery("#" + id);
		if (0 < $form.length) {
			document.body.removeChild($form[0]);
		}
		var url = "/leasing/template/downLoadRentNoticeAttachById.action";
		var tempInnerHtml = "<form action='" + getRootPath() + url + "' id='" + id + "' method='POST'>";
		tempInnerHtml += "<input type='hidden' name='id' value='" + id + "'/>";
		tempInnerHtml += "<input type='hidden' name='contractid' value='" + row.id + "'/>";
		tempInnerHtml += "<input type='hidden' name='creator' value='" + row.creator + "'/>";
		tempInnerHtml += "<input type='hidden' name='createdate' value='" + row.createdate + "'/>";
		tempInnerHtml += "<input type='hidden' name='cfrpid' value='" + row.cfrp_id + "'/>";
		tempInnerHtml += "<input type='hidden' name='browserType' value='" + SysBrowser.getBrowser().toLowerCase() + "' />"
		tempInnerHtml += "</form>";
		$form = jQuery(tempInnerHtml);
		jQuery(document.body).append($form);
		$form.submit();
		var parentpage=window.parent.mini.get("table_id2");
		if (window.parent.mini.get("table_id2")) {
			parentpage.reload();
		}
	},
	showOReditFile : function (id, otype) {
		var URL = getRootPath() + "/leasing/common/templateManager/editWordTemplate.bi";
		var params = {
			id : id,
			status : otype
		};
		openFullScreenWindow(URL, params);
	},
	showfileRecoder : function (id) {
		var URL = getRootPath() + "/leasing/common/templateManager/fileRecoderlist.bi";
		window.open(URL + '?id=' + id, 'newfilelist1', 'height=300,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	},
	dropFile : function (rowDatas) {
		var plandata = rowDatas;
		var ids = [];
		for (var i = 0; i < plandata.length; i++) {
			ids.push(plandata[i].id);
		}
		mini.mask({
			el : document.body,
			cls : 'mini-mask-loading',
			html : '正在删除合同 请稍等...'
		});
		var url = "/leasing/template/dropCreateFile.action";
		var param = [];
		param["ids"] = ids + "";
		ajaxRequest({
			url : getRootPath() + url,
			method : 'POST',
			success : function (rs) {
				var message = rs.responseText;
				message = message.replace(/(^\s+)|(\s+$)/g, "");
				mini.unmask(document.body);
				alert(message);
			},
			async : false,
			failure : function (res) {
				mini.unmask(document.body);
			},
			params : param
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
	this.attachmentParams = config.attachmentParams;
	this.isAttachment = config.isAttachment || false;
	this.appImage = config.appImage || false;
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
	if(this.isAttachment){
		uploadAttachmentFileWindow_html += '		<input type="hidden" name="isAttachment" id="id_isAttachment" value="true">';
		for(var p in this.attachmentParams){
			uploadAttachmentFileWindow_html += '		<input type="hidden" name="' + p + '" id="id_' + p + '" value="' + this.attachmentParams[p] + '">';
		}
	}
	if(this.appImage){
		uploadAttachmentFileWindow_html += '		<input type="hidden" name="id_appImage" id="id_appImage" value="true">';
	}
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
		var filenameForcheck=$("#id_tableImportTemplate").val();
		/*if(filenameForcheck==""){mini.alert("请选择上传的文件");return false;}*/
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
/******************************导入Excel的配置[开始]*******************************/
var importExcelUtil = function(config){
	this.id = config.id;
	this.renderTo = config.renderTo;
	this.uploadButtonText = config.uploadButtonText || '上传Excel';
	this.importType = config.importType;
	this.fileKey = config.fileKey;
	this.uploadUrl = config.uploadUrl || getRootPath() + "/leasing/getUploadExcelData.action";
	this.importCompleteCallback = config.importCompleteCallback;
	this.data = config.data || [];
	this.baseFileId = config.baseFileId || '';
	this.baseFileName = config.baseFileName || '';
	this.showBtn = !(config.showBtn == false);
	this.attachmentParams = config.attachmentParams;
	this.isAttachment = config.isAttachment || false;
	miniuiExtObject[this.id] = this;
	var $me = this;
	(function(){
		var containerHtmlStr = "";
		if($me.showBtn){
			containerHtmlStr += '<a class="mini-button" plain="true" iconCls="icon-importExcel" onclick="getMiniuiExtObject(\'' + $me.id + '\').showExcelUploadWindow()">' + $me.uploadButtonText + '</a>';
		}
		containerHtmlStr += "&nbsp;&nbsp;&nbsp;&nbsp;";
		containerHtmlStr += "<span id='id_span_" + $me.id + "_download'></span>";
		containerHtmlStr += "<div id='id_div_" + $me.id + "_container_inner'></div>";
		$("#" + $me.renderTo).html(containerHtmlStr);
	})();
	this.showDownloadHref(this.baseFileId, this.baseFileName);
	this.loadExcelData(this.data);
	
}
importExcelUtil.prototype.showExcelUploadWindow = function(){
	var $me = this;
	var uploadWinStr = '';
	uploadWinStr += '<div id="id_win_uploadfile" class="mini-window" title="上传" style="width:500px;height:260px;" showModal="true" allowResize="true" allowDrag="true">'
	uploadWinStr += '	<div style="width: 100%;height: 90%;">'
	uploadWinStr += '		<iframe style="display:none;" name="uploadfile_real_submit_frame"></iframe>';
	uploadWinStr += '		<form id="id_form_uploadfile" action="' + $me.uploadUrl + '" enctype="multipart/form-data" target="uploadfile_real_submit_frame" method="post">';
	uploadWinStr += '			<table id="id_table_uploadfile" border="1" cellspacing="0" cellpadding="0" style="width: 90%;margin: 10px auto;border-collapse: collapse;border: 1px solid #99CCFF;">';
	if(this.isAttachment){
    uploadWinStr += '		<input type="hidden" name="isAttachment" id="id_isAttachment" value="true">';
		for(var p in this.attachmentParams){
    uploadWinStr += '		<input type="hidden" name="' + p + '" id="id_' + p + '" value="' + this.attachmentParams[p] + '">';
		}
	}
	uploadWinStr += '				<tr style="height: 35px;">';
	uploadWinStr += '					<td style="padding-left: 10px;">上传文件类型:</td>';
	uploadWinStr += '					<td style="padding-left: 10px;"><span id="id_span_importtype"></span></td>';
	uploadWinStr += '				</tr>';
	uploadWinStr += '				<tr style="height: 35px;">';
	uploadWinStr += '					<td style="padding-left: 10px;">选择文件:</td>';
	uploadWinStr += '					<td style="padding-left: 10px;">';
	uploadWinStr += '						<input id="import_type" name="import_type" type="hidden" />';
	uploadWinStr += '						<input id="file_key" name="file_key" type="hidden" />';
	uploadWinStr += '						<input id="call_back_func" name="call_back_func" type="hidden" />';
	uploadWinStr += '						<input id="uplaodfile" name="uplaodfile" type="file" />';
	uploadWinStr += '					</td>';
	uploadWinStr += '				</tr>';
	uploadWinStr += '				<tr style="height: 35px;">';
	uploadWinStr += '					<td colspan="2" style="text-align: center;"> <a class="mini-button" iconCls="icon-save" plain="true" onclick="mini.mask({el \: document.body,cls\:\'mini-mask-loading\',html\:\'上传中请稍后...\'});document.getElementById(\'id_form_uploadfile\').submit()">上传</a></td>';
	uploadWinStr += '				</tr>';
	uploadWinStr += '			</table>';
	uploadWinStr += '		</form>';
	uploadWinStr += '	</div>';
	uploadWinStr += '</div>';
	if(!mini.get("id_win_uploadfile")){
		$(document.body).append(uploadWinStr);
	}
	mini.parse("id_win_uploadfile");
	$("#id_span_importtype").html($me.importType);
	$("#import_type").val($me.importType);
	$("#file_key").val($me.fileKey);
	$("#call_back_func").val($me.importCompleteCallback);
	mini.get("id_win_uploadfile").show(globalClientWidth/2-200, globalClientHeight/2-100);
}
/*importExcelUtil.prototype.uploadExcelFileCallBack = function(result){

}*/
importExcelUtil.prototype.showDownloadHref = function(basefileid, basefilename){
	var $me = this;
	if(basefileid != "" && basefilename != ""){
		$("#id_span_" + $me.id + "_download").html("<a href='javascript:void(0);' onclick='fileOperator.downFile(\"" + basefileid + "\")'>" + basefilename + "</a>");
	}
}
importExcelUtil.prototype.hideUploadWindow = function(){
	mini.get("id_win_uploadfile").hide();
}
importExcelUtil.prototype.loadExcelData = function(excelData){
	var $me = this;
	if(typeof excelData == "undefined" || excelData == "" || excelData == []){
		return;
	}
	var import_type = excelData.import_type;
	var htmlStr = "<h1>" + import_type + "</h1>";
	htmlStr += "<hr/>";
	var sheets_data = excelData.sheets_data;
	var sheet_data = {};
	var sheet_name = "";
	var sheet_code = "";
	var cell_data = [];
	var table_data = [];
	for(var i = 0; i < sheets_data.length; i++){
		sheet_data = sheets_data[i];
		sheet_name = sheet_data.sheet_name;
		sheet_code = sheet_data.sheet_code;
		cell_data = sheet_data.cell_data;
		table_data = sheet_data.table_data;
		if(cell_data.length > 0 || table_data.length > 0){
			htmlStr += "<h2>Sheet:" + sheet_name + "</h2>";
			htmlStr += $me.getFieldHtmlByCellData(cell_data);
			htmlStr += $me.getTableHtmlByTableData(table_data);
		}
	}
	$("#id_div_" + $me.id + "_container_inner").html(htmlStr);

	if (mini.get("id_customworkflowattachment")) { //刷新附件一览页签
		mini.get("id_customworkflowattachment").reload();
		mini.get("id_workflowhisAttachment").reload();
	}
	
}
importExcelUtil.prototype.getFieldHtmlByCellData = function(celldata){
	var cellHtmlStr = "";
	cellHtmlStr += "<table width='800' border='1' cellpadding='0' cellspacing='0' class='cls_excel_table'>";
	for(var i = 1; i <= celldata.length; i ++){
		if(i%2 == 1){
			cellHtmlStr += "<tr>";
		}
		var displayValue = '';
		switch(celldata[i-1].displayformat){
			case "percent":
				displayValue = isNaN(celldata[i-1].value)? celldata[i-1].value : (parseFloat(celldata[i-1].value)*100).toFixed(2)+'%';
				break;
			case "currency":
				displayValue = isNaN(celldata[i-1].value)? celldata[i-1].value : formatNumberThousand(parseFloat(celldata[i-1].value).toFixed(2));
				break;
			case "int":
				displayValue = isNaN(celldata[i-1].value)? celldata[i-1].value : parseInt(celldata[i-1].value);;
				break;
			default:
				displayValue = celldata[i-1].value;
				break;
		}
		cellHtmlStr += "<td width='120' style='background:#F5F8FA;'>" + celldata[i-1].name + ":</td><td width='280'>" + displayValue + "</td>";
		if(i%2 == 0){
			cellHtmlStr += "</tr>";
		}
	}
	if(celldata.length%2 == 1){
		cellHtmlStr += "<td bgcolor='F5F8FA'>&nbsp;</td><td>&nbsp;</td></tr>";
	}
	cellHtmlStr += "</table>";
	
	return cellHtmlStr;
}
importExcelUtil.prototype.getTableHtmlByTableData = function(tabledata){
	
	var tableHtmlStr = "";
	for(var i = 0; i < tabledata.length; i++){
		tableHtmlStr += "<h4 style='margin:3px 0px;padding-left:10px;'>" + tabledata[i].name + "</h4>"
		tableHtmlStr += "<table border='1' cellpadding='0' cellspacing='0' class='cls_excel_table' style='width:" + tabledata[i].width + ";'><tr bgcolor='F5F8FA'>";
		var columns = tabledata[i].columns;
		for(var j = 0; j < columns.length; j++){
			tableHtmlStr += "<td style='width:" + columns[j].width + ";' align='center'>" + columns[j].header + "</td>";
		}
		tableHtmlStr += "</tr>";
		
		var datas = tabledata[i].datas;
		for(var j = 0; j < datas.length; j++){
			tableHtmlStr += "<tr>";
			for(var k = 0; k < columns.length; k++){
				tableHtmlStr += "<td style='text-align:" + columns[k].align + ";word-break: break-all;'>" + datas[j][columns[k].field] + "</td>";
			}
			tableHtmlStr += "</tr>";
		}
		tableHtmlStr += "</table>";
	}
	return tableHtmlStr;
}
/******************************导入Excel的配置[结束]*******************************/
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
//格式化数字为千分位
function formatNumberThousand(s) {  
	   s += "";
	   s = s.replace(/,/g,"");
	  
	   if(isNaN(s)){
		   return s;
	   }
	   //if(s==0){s="0.00";}
	   s += "";
	   //s = s.replace(/,/g,"");
	   var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g; //
	   var n1=s.replace(re,"$1,");
	   return n1;
}
//刷新附件一览和历史一览页签
function loadcustomworkflowattachment(){
	if (mini.get("id_customworkflowattachment")) {
		mini.get("id_customworkflowattachment").reload();
		mini.get("id_workflowhisAttachment").reload();
	  }
}
function getAttachmentParams(dictsid,DBID){
	var str={};
	str.module='WorkflowAttchmentFiles',
	str.jbpmWorkflowHistoryInfoId = window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
	str.attachmentFileDictId = dictsid,//动态的参数
	str.identifierOne=window.assignAttachmentKeyOne||DBID,
	str.identifierTwo=window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
	str.identifierThree=window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
	str.identifierFour=window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
	str.identifierFive=window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
	str.identifierSix=window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
	str.identifierSeven=window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
	str.identifierEight=window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
	str.identifierNine=window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
	str.identifierTen=window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val()
	return str;
}

/*function extunmask(){
	mini.mask({el : document.body,cls : 'mini-mask-loading',html : "数据上传中..请稍后"});
}
*/
function PreviewOffice(filedict,filename){
	var filenameStr = filename;
	mini.mask({
	    el: document.body,
	    cls: 'mini-mask-loading'
	});
	var filename=filename.substring(filename.lastIndexOf('.') + 1);
	var fileExtension = filename.toLowerCase();
	if(fileExtension=="jpg"||fileExtension=="jpeg"||fileExtension=="png"||fileExtension=="bmp"||fileExtension=="gif"||fileExtension=="pdf"){
		var url = getRootPath() + '/table/getTableData.action';
		var params = { xmlFileName:'/common/queryPutImages.xml',"filedict":filedict};
		tenwa.ajaxRequest({
	        url:url,
	        params:params,
	        method:'post',
	        success:function(res){
	            var result = JsonUtil.decode(res.responseText);
			    var datas = result['datas'];
			    if(datas && datas.length > 0){
			    	jQuery("#imag").html('');
			    	jQuery.each(datas, function(index, appImage){
			    		var img = OpenImageDiv(appImage,filedict);
			    		if(img){
				    		jQuery("#imag").append(img);
			    		}
			    	});
			    } else {
			    	jQuery("#imag").html('');
			    }
			    mini.unmask(document.body);
	        },
	        failure:function(res){
	        	mini.unmask(document.body);
	        }
	    });
		mini.get("base_showfile").show();
	}else{
		$.ajax({
	        url: getRootPath()+"/acl/qeryCustfile.acl",
	        type: "post",
	        cache: false, 
	        data :{"filedict":filedict,"previewtype":"second"},
	        async : false,
	        success: function (text) {
			     var str=text;
			   	 testwin= open("", "testwin","status=no,menubar=yes,toolbar=no");
				 testwin.document.open();
				 testwin.document.write(str);
				 testwin.document.title = filenameStr;
				 testwin.document.close();
	        }
	    });
		 mini.unmask(document.body);
	}
}
//根据单个图片数据获取一个document
function OpenImageDiv(appImage,filedict){
	var previewtype="second";//查询base_file表才给的默认值
	var $div = jQuery('<div/>');
	if(appImage){
		$div.addClass("img-box");
		$div.attr("imageid", appImage.id);
		var $image = jQuery("<img/>");
		$image.attr("height", appImage.height);
		$image.attr("width", appImage.width);
		$image.attr("realUrl", appImage.imagepath);
		$image.attr("alt", appImage.title);
		$image.attr("src", getRootPath()+"/acl/qeryCustfile.acl?filedict="+filedict+"&previewtype="+previewtype);
		$image.appendTo($div);
	}
	return $div;
}
