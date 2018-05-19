define(function(require, exports, module) {
	
	var apUtil = require("js/apcomponent/aputil/aputil.js");
	var ApQueryInput=function(config){
		this.name=config.name||"";
		this.objId=config.id||GenerateGuid();
		this.label=config.label||"";
		this.objOtherAttributes = config.otherAttributes||"";
		miniuiExtObject[this.objId] = this;
		//this.xmlFileName     = config.xmlFileName;
		this.textField = config.textField||'name';
		this.valueField  = config.valueField||'value';
		this.start   = config.start||0;
		this.limit   = config.limit||20;
		this.params  = config.params||{};
		this.url     = config.url||(getRootPath()+'/table/getTableData.action');
		this.loadComplete = config.loadComplete;
		this.readOnly = false;
		this.clearQueryInput = (config.clearQueryInput + "") == "false" ? false : true;//是否清空查询条件重新加载所有数据
		this.inputObj = null;
		this.text = config.text;
		this.value = config.value;
		this.queryInputObj=null;
		this.selectDom=null;
		this.submitBtn=null;
		this.config=config;
		this.onSelect=this.onSelect||function(){};
		this.isinitwindow=false;
		this.windowWidth= config.windowWidth||400;
		var windowHeight = config.windowHeight||800;
		this.initInput();
	}
	

	ApQueryInput.prototype.initInput = function() {
		var $me = this;
		var inputId = this.objId;
		this.inputObj = mini.get(inputId);
		if (this.config.value) {
			this.inputObj.setValue(this.config.value);
		}
		if (this.config.text) {
			this.inputObj.setText(this.config.text);
		}
		if (this.readOnly == false) {
			var $me = this;
			this.inputObj.on("click", function() {
				if($me.config.onBeforeShowWindow){
					if(!$me.config.onBeforeShowWindow($me)){
						return;
					}
				}
				$me.initSelectWindow();
			});
			this.inputObj.on("buttonclick", function() {
				if($me.config.onBeforeShowWindow){
					if(!$me.config.onBeforeShowWindow($me)){
						return;
					}
				}
				$me.initSelectWindow();
			});
		}
	}
	ApQueryInput.prototype.initSelectWindow=function(){
		var $me=this;
		var objWindowId = "window_"+this.objId;
		if(mini.get(objWindowId) && $me.queryInputObj){
			if($me.clearQueryInput){
				getDomById(objWindowId+"QueryInput").value = "";
				this.loadQueryInputAjaxData();
			}
			mini.get(objWindowId).show();
		}else{
			if(mini.get(objWindowId) && !$me.queryInputObj){
				try{mini.get(objWindowId).destroy();}catch(e){};
			}
			var container = document.body;
			var panelHtmlArr = [];
			panelHtmlArr.push('<div id="'+objWindowId+'" class="mini-window" showCloseButton="true" showModal="true" allowDrag="true" allowResize="true" title="请选择" style="display:none;width:'+this.windowWidth+'px;height:'+this.windowHeight+'px;text-align:center;padding:10px;">');
			panelHtmlArr.push(' <div style="padding-bottom:5px;width:'+(this.windowWidth-30)+'px;margin: 0px auto;height: 30px;line-height:30px;">'+(this.config.label||"查询")+'：<input type="text" id="'+objWindowId+'QueryInput"/><a class="mini-button" iconCls="icon-search" style="margin-left:10px;" id="'+objWindowId+'QueryButton">查询</a></div>');
			panelHtmlArr.push(' <div style="overflow:auto;width:'+(this.windowWidth-10)+'px;text-align:center;"><select size="20" id="'+objWindowId+'SelectContainer" style="overflow:auto;width:'+(this.windowWidth-40)+'px;">');
			panelHtmlArr.push(' </select></div>');
			panelHtmlArr.push('<div style="text-align:center;width='+this.windowWidth+'px;height:40px;" id="'+objWindowId+'_dlg_buttons">');
			panelHtmlArr.push('	<a href="javascript:void(0);" style="margin-top:5px;" id="'+objWindowId+"SubmitButton"+'" class="mini-button"  >确定</a>');
			panelHtmlArr.push('	<a href="javascript:void(0);" style="margin-top:5px;margin-left:20px;" id="'+objWindowId+"CancelButton"+'" class="mini-button"  >取消</a>');
			panelHtmlArr.push('</div>');
			panelHtmlArr.push('</div>');
			jQuery(container).append(panelHtmlArr.join(""));
			mini.parse($("#"+objWindowId));
			mini.get(objWindowId).show();
			//查询的INPUT框
			this.queryInputObj=getDomById(objWindowId+"QueryInput");
			this.queryInputObj.onkeyup = function(evt){
				var e  = window.event||evt;
				var code = e.keyCode||e.charCode;
				if(13 == code){
					queryBtnDom.click();
				}
			};
			this.selectDom   = getDomById(objWindowId+"SelectContainer");
			//取消按钮
			var cancelBtnDom = getDomById(objWindowId+"CancelButton");
			$(cancelBtnDom).bind("click", function(){
				try{mini.get(objWindowId).hide();}catch(e){};
			});
			// 查询按钮
			var queryBtnDom = getDomById(objWindowId + "QueryButton");
			$(queryBtnDom).bind("click", function() {
				$me.loadQueryInputAjaxData();
			});
			//确定按钮
			var submitBtnDom = getDomById(objWindowId+"SubmitButton");
			$(submitBtnDom).bind("click", function(){
				var options       = $me.selectDom.options;
				var selectedIndex = $me.selectDom.selectedIndex;
				var selectedOption = options[selectedIndex];
				var inputObj = mini.get($me.objId);
				inputObj.setValue(selectedOption.value);
				inputObj.setText(selectedOption.innerHTML);
				$me.selectedOption    = selectedOption;
				if(mini.get($me.objId + "name")){
					mini.get($me.objId + "name").setValue(inputObj.text);
				}
				if($me.config.onSelect){
					$me.config.onSelect($me, inputObj, selectedOption.rowData);
				}
				try{mini.get("window_"+$me.objId).hide();}catch(e){};
			});
			this.submitBtn = submitBtnDom;
			this.loadQueryInputAjaxData();
			this.isinitwindow=true;
		}
	}
	ApQueryInput.prototype.loadQueryInputAjaxData = function(){
		var $me = this;
		if($me.loadMask){
			$me.loadMask.show();
		}
		var params = $me.params;
	    /*var xmlFileName = $me.xmlFileName;
	    if($me.xmlFileName){
	    	params['xmlFileName'] = xmlFileName;
	    }*/
		//delete params.xmlFileName;
	    params['start'] = $me.start||0;
	    params['limit'] = $me.limit||20;
	    params['queryInputRawValueField'] = $me.textField;
	    params['queryInputRawValue']      = $me.queryInputObj.value||"";
		ajaxRequest({
	        url:$me.url,
	        params: params,
	        success:function(res){
			   $me.jsonData = eval('('+res.responseText+')');
			   $me.datas = $me.jsonData['datas'];
			   $me.selectDom.innerHTML = "";
			   var option = document.createElement("option");
			   with(option){value = "";}
			   var emptobj={};
			   emptobj[$me.valueField]="";
			   emptobj[$me.textField]="";
			   option.rowData={};
			   $me.selectDom.appendChild(option);
			   $me.selectDom.ondblclick = function(){
				   $me.submitBtn.click();
			   };
			   if(!$me.inputObj.value){
				   $me.selectDom.selectedIndex = 0;
			   }
			   for(var i =0 ;i < $me.datas.length; i++ ){
				   var data = $me.datas[i];
				   var option = document.createElement("option");
				   $me.selectDom.appendChild(option);
				   with(option){
					   value     = data[$me.valueField];
					   innerHTML = data[$me.textField];
				   }
				   option.rowData    = data;
				   if(option.value == $me.inputObj.value){
					   $me.selectDom.selectedIndex = (i+1);
				   }
			   }
			   //$me.selectDom.size = ( $me.datas.length +1);
			   if($me.loadComplete)
			   {
				   $me.loadComplete($me);
			   }
			   if($me.loadMask)
			   {
				   $me.loadMask.hide();
			   }
			   $me.isComplete=true;
	        },
	        failure:function(res){
	        }
	    });
	};
	ApQueryInput.prototype.getSelectedOptionData = function(){
		return this.selectedOption.rowData;
	};
	ApQueryInput.prototype.getOptionDatas = function(){
		return this.datas;
	};
	ApQueryInput.prototype.getOptionDataByAjax = function(field,value){//得到单条记录，键值对作为查询条件，需加xml配置
		var rowData = {};
		var $me = this;
		var params = {};
		var xmlFileName = $me.xmlFileName;
		params['xmlFileName'] = xmlFileName;
		params[field] = value;
		ajaxRequest({
			url:$me.url,
			async: false,
			success:function(res){
				var jsonData = eval('('+res.responseText+')');
				if(jsonData['datas'][0]){
					rowData = jsonData['datas'][0];
				}
			},
			failure:function(res){
			},
			params:params
		});
		return rowData;
	};
	ApQueryInput.prototype.setValue = function(hiddenValue){
		return this.hiddenInputObj.value  = hiddenValue;
	};
	ApQueryInput.prototype.setText = function(rawValue){
		return this.displayInputObj.value = rawValue;
	};
	ApQueryInput.prototype.clearValue = function(){
		this.setValue("");
		this.setRawValue("");
	};
	var getDomById = function (id, context) {
	    if (typeof id == "string") {
	        if (id.charAt(0) == '#') id = id.substr(1);
	        var el = document.getElementById(id);
	        if (el) return el;
	        if (context && !getDomIsAncestor(document.body, context)) {
	            var els = context.getElementsByTagName("*");
	            for (var i = 0, l = els.length; i < l; i++) {
	                el = els[i];
	                if (el.id == id) return el;
	            }
	            el = null;
	        }
	        return el;
	    } else {
	        return id;
	    }
	};
	var getDomIsAncestor = function (p, c) {
	    var ret = false;
	    p = getDomById(p);
	    c = getDomById(c);
	    if (p === c) return true;
	    if (p && c) {
	        if (p.contains) {
	            try {
	                return p.contains(c);
	            } catch (e) {
	                return false;
	            }
	        } else
	            if (p.compareDocumentPosition) {
	                return !!(p.compareDocumentPosition(c) & 16);
	            } else {
	                while (c = c.parentNode) {
	                    ret = c == p || ret;
	                }
	            }
	    }
	    return ret;
	};
	return ApQueryInput;
});