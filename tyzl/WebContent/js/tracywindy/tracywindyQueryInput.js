var tracywindyQueryInput=function(config){
	this.name=config.name||"";
	this.objId=config.id||GenerateGuid();
	this.label=config.label||"";
    this.objOtherAttributes = config.otherAttributes||"";
    tracywindyObject[this.objId] = this;
	this.xmlFileName     = config.xmlFileName;
	this.displayField = config.displayField||'name';
	this.valueField  = config.valueField||'value';
	this.start   = config.start||0;
	this.limit   = config.limit||20;
	this.params  = config.params||{};
	this.url     = config.url||(getRootPath()+'/table/getTableData.action');
	this.loadComplete = config.loadComplete;
	this.readOnly = false;
	this.clearQueryInput = config.clearQueryInput || false;//是否清空查询条件重新加载所有数据
	this.hiddenInputObj  = null;
	this.displayInputObj = null;
	this.queryInputObj=null;
	this.selectDom=null;
	this.submitBtn=null;
	this.config=config;
	this.onSelect=this.onSelect||function(){};
	this.isinitwindow=false;
	this.width= config.width||400;
	 var objWindowHeight = config.height||800;
	this.initInput();
}
tracywindyQueryInput.prototype.initInput=function(){
	var hiddenInputId  = 'selectOfValue_'+this.objId;
	var displayInputId = 'selectOfRawValue_'+this.objId;
	var domInnerHTML = "";
	if(!this.config.hiddenInputId){
		 domInnerHTML+= "<input type='hidden' id='"+hiddenInputId+"' name='"+this.name+"'  value='' />";
	 }else{
		 hiddenInputId = this.config.hiddenInputId;
	 }
		 
	 if(!this.config.displayInputId){
		 domInnerHTML+= "<input type='text' id='"+displayInputId+"' name='"+this.name+"name'  "+this.objOtherAttributes+"  value='' />";
	 }else{
		 displayInputId = this.config.displayInputId;
	 }
	 if(domInnerHTML){
		 $("#"+this.config.renderTo).html(domInnerHTML)
	 }
	 this.hiddenInputObj  = getDomById(hiddenInputId);
     this.displayInputObj = getDomById(displayInputId);
     if(this.config.value){
		 this.hiddenInputObj.value = config.value;
	 }
     if(this.config.displayValue){
		this.hiddenInputObj.value = config.displayValue;
	 }
     jQuery(this.displayInputObj).attr({readOnly:true});
     if(this.readOnly==false){
	    jQuery(this.displayInputObj).addClass("queryInputClick");
	    jQuery(this.hiddenInputObj).addClass("queryInputClick");
	    var $me=this;
	    this.displayInputObj.onclick = function(){
	    	$me.initSelectWindow();
	    }
     }
}
tracywindyQueryInput.prototype.initSelectWindow=function(){
    var $me=this;
	var objWindowId     = "window_"+this.objId;
	if(this.isinitwindow==true){
		mini.get(objWindowId).show();
	}else{
	   var container = document.body;
	   var panelHtmlArr = [];
	   panelHtmlArr.push('<div id="'+objWindowId+'" buttons="#'+objWindowId+'_dlg_buttons" class="mini-window"  closed="true" modal="true" title="请选择" style="display:none;width:'+this.width+'px;height:'+this.height+'px;text-align:center;padding:10px;">');
	   panelHtmlArr.push(' <div style="padding-bottom:5px;">'+(this.config.label||"查询")+'：<input type="text" id="'+objWindowId+'QueryInput"/><input type="button" class="btn btn-primary" style="margin-left:10px;" value="查询" id="'+objWindowId+'QueryButton"/></div>');
	   panelHtmlArr.push(' <div style="overflow:auto;width:'+(this.width-10)+'px;"><select size="16" id="'+objWindowId+'SelectContainer" style="overflow:auto;width:'+(this.width-40)+'px;">');
	   panelHtmlArr.push(' </select></div>');
	   panelHtmlArr.push('<div style="text-align:center;width='+this.width+'px;height:40px;" id="'+objWindowId+'_dlg_buttons">');
	   panelHtmlArr.push('	<a href="javascript:void(0);" style="margin-top:5px;" id="'+objWindowId+"SubmitButton"+'" class="btn btn-primary"  >确定</a>');
	   panelHtmlArr.push('	<a href="javascript:void(0);" style="margin-top:5px;margin-left:20px;" id="'+objWindowId+"CancelButton"+'" class="btn btn-primary"  >取消</a>');
	   panelHtmlArr.push('</div>');
	   panelHtmlArr.push('</div>');
	   jQuery(container).append(panelHtmlArr.join(""));
	   mini.parse($("#"+objWindowId));
	   mini.get(objWindowId).show();
	   //查询的INPUT框
	   this.queryInputObj=getDomById(objWindowId+"QueryInput");
	   this.queryInputObj.onkeyup = function(evt){
			 var e  = getEvent(evt);
		     var code = e.keyCode||e.charCode;
		     if(13 == code){
		    	 queryBtnDom.click();
		     }
		 };
	   this.selectDom   = getDomById(objWindowId+"SelectContainer");
	   //取消按钮
	   var cancelBtnDom = getDomById(objWindowId+"CancelButton");
	   cancelBtnDom.onclick = function(){
		 try{mini.get(objWindowId).hide();}catch(e){};
	   };
	   //查询按钮
	   var queryBtnDom  = getDomById(objWindowId+"QueryButton");
	   queryBtnDom.onclick  = function(){
		   $me.loadQueryInputAjaxData();
		};
		//确定按钮
		var submitBtnDom = getDomById(objWindowId+"SubmitButton");
		submitBtnDom.onclick = function(){
			var options       = $me.selectDom.options;
			var selectedIndex = $me.selectDom.selectedIndex;
			var selectedOption = options[selectedIndex];
			$me.hiddenInputObj.value  = selectedOption.value;
			$me.displayInputObj.value = selectedOption.innerHTML;
			$me.selectedOption    = selectedOption;
			if($me.config.onSelect){
				$me.config.onSelect($me,selectedOption.rowData);
			}
			try{mini.get("window_"+$me.objId).hide();}catch(e){};
		};
		this.submitBtn = submitBtnDom;
       this.loadQueryInputAjaxData();
       this.isinitwindow=true;
	}
	
}
tracywindyQueryInput.prototype.loadQueryInputAjaxData = function(){
	var $me = this;
	if($me.loadMask)
	{
		$me.loadMask.show();
	}
	var params = $me.params;
    var xmlFileName = $me.xmlFileName;
    params['xmlFileName'] = xmlFileName;
    params['start'] = $me.start||0;
    params['limit'] = $me.limit||20;
    params['queryInputRawValueField'] = $me.displayField;
    params['queryInputRawValue']      = $me.queryInputObj.value||"";
	ajaxRequest({
        url:$me.url,
        success:function(res){
		   $me.jsonData = eval('('+res.responseText+')');
		   $me.datas = $me.jsonData['datas'];
		   $me.selectDom.innerHTML = "";
		   var option = document.createElement("option");
		   with(option){value = "";}
		   $me.selectDom.appendChild(option);
		   $me.selectDom.ondblclick = function(){
			   $me.submitBtn.click();
		   };
		   if(!$me.hiddenInputObj.value){
			   $me.selectDom.selectedIndex = 0;
		   }
		   for(var i =0 ;i < $me.datas.length; i++ ){
			   var data = $me.datas[i];
			   var option = document.createElement("option");
			   $me.selectDom.appendChild(option);
			   with(option){
				   value     = data[$me.valueField];
				   innerHTML = data[$me.displayField];
			   }
			   option.rowData    = data;
			   if(option.value == $me.hiddenInputObj.value){
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
        },
        params:params
    });
};
tracywindyQueryInput.prototype.getValue = function(){
	return this.hiddenInputObj.value;
};
tracywindyQueryInput.prototype.getRawValue = function(){
	return this.displayInputObj.value;
};
tracywindyQueryInput.prototype.getSelectedOptionData = function(){
	return this.selectedOption.rowData;
};
tracywindyQueryInput.prototype.getOptionDatas = function(){
	return this.datas;
};
tracywindyQueryInput.prototype.getOptionDataByAjax = function(field,value){//得到单条记录，键值对作为查询条件，需加xml配置
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
tracywindyQueryInput.prototype.setValue = function(hiddenValue){
	return this.hiddenInputObj.value  = hiddenValue;
};
tracywindyQueryInput.prototype.setRawValue = function(rawValue){
	return this.displayInputObj.value = rawValue;
};
tracywindyQueryInput.prototype.clearValue = function(){
	this.setValue("");
	this.setRawValue("");
};
tracywindyQueryInput.prototype.setReadonly = function(flag,classname){
	var $me = this;
	if(flag == true){
		removeClass($me.displayInputObj,'queryInputClick');
		addClass($me.displayInputObj,classname);
		$me.readOnly = true;
	}else{
		removeClass($me.displayInputObj,classname);
		addClass($me.displayInputObj,'queryInputClick');
		$me.readOnly = false;
	}
};
tracywindyQueryInput.prototype.loadQueryInputAjaxData = function(){
	var $me = this;
	if($me.loadMask)
	{
		$me.loadMask.show();
	}
	var params = $me.params;
    var xmlFileName = $me.xmlFileName;
    params['xmlFileName'] = xmlFileName;
    params['start'] = $me.start||0;
    params['limit'] = $me.limit||20;
    params['queryInputRawValueField'] = $me.displayField;
    params['queryInputRawValue']      = $me.queryInputObj.value||"";
	ajaxRequest({
        url:$me.url,
        success:function(res){
		   $me.jsonData = eval('('+res.responseText+')');
		   $me.datas = $me.jsonData['datas'];
		   $me.selectDom.innerHTML = "";
		   var option = document.createElement("option");
		   with(option){
			   value = "";
		   }
		   $me.selectDom.appendChild(option);
		   $me.selectDom.ondblclick = function(){
			   $me.submitBtn.click();
		   };
		   if(!$me.hiddenInputObj.value){
			   $me.selectDom.selectedIndex = 0;
		   }
		   for(var i =0 ;i < $me.datas.length; i++ ){
			   var data = $me.datas[i];
			   var option = document.createElement("option");
			   $me.selectDom.appendChild(option);
			   with(option){
				   value     = data[$me.valueField];
				   innerHTML = data[$me.displayField];
			   }
			   option.rowData    = data;
			   if(option.value == $me.hiddenInputObj.value){
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
        },
        params:params
    });
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