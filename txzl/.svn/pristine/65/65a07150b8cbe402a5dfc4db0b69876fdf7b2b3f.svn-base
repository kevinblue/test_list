var tracywindyObject = {};
var isEnableTracywindyRandomUrl = false;
function getTracywindyRandomUrl(url){	
	var tempUrl;
	if(!isEnableTracywindyRandomUrl){
		tempUrl = "tracywindyRandom=1";
	}else{
		tempUrl = "tracywindyRandom=" + Math.random();
	}	
	/*
	 * 资源URL 添加标志符 
	 */
	if(url.indexOf('?')>-1){
		url += ("&" + tempUrl);		
	}else {
		url += ("?" + tempUrl);
	}
	return url;
}
//格式化数字为千分位
function formatNumberThousand(s) {
if(isNaN(s)){
return s;
}
s += "";
s = s.replace(/,/g,"");
var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g; //肖遥云指点
var n1=s.replace(re,"$1,");
return n1;
} 

//判断parentObj是否是obj的上级元素
function isParent(obj,parentObj){
	while (obj != undefined && obj != null && obj.tagName.toUpperCase() != 'BODY'){
	if (obj == parentObj){
	return true;
	}
	obj = obj.parentNode;
	}
	return false;
} 
function clearForm(form)
{
	var elements = form.elements;
	for(var i=0;i<elements.length;i++)
	{
		var element = elements[i];
		var t = element.type, tag = element.tagName.toLowerCase();
	    if ( t == 'text' || t == 'hidden' || t == 'password' || tag == 'textarea')
	    {
	    	element.value = '';
	    }
		else if (t == 'checkbox' || t == 'radio')
		{
			element.checked = false;	
		}
		else if (tag == 'select')
		{
			element.selectedIndex = -1;    	
		}
	}
}       
//获取日期
function getCurDateTime(date)
{
	 var d = new Date();
	 if(date)
	 {
		 d = date;
	 }
	 var week;
	 switch (d.getDay()){
	 case 1: week="星期一"; break;
	 case 2: week="星期二"; break;
	 case 3: week="星期三"; break;
	 case 4: week="星期四"; break;
	 case 5: week="星期五"; break;
	 case 6: week="星期六"; break;
	 default: week="星期天";
	 }
	 var years = d.getFullYear();
	 var month = add_zero(d.getMonth()+1);
	 var days = add_zero(d.getDate());
	 var hours = add_zero(d.getHours());
	 var minutes = add_zero(d.getMinutes());
	 var seconds=add_zero(d.getSeconds());
	 var ndate = years+"-"+month+"-"+days+"  "+hours+":"+minutes+":"+seconds;
	return ndate;
}
function getCurDate(date)
{
	 var d = new Date();
	 if(date)
	 {
		 d = date;
	 }
	 var years = d.getFullYear();
	 var month = add_zero(d.getMonth()+1);
	 var days = add_zero(d.getDate());
	 var ndate = years+"-"+month+"-"+days;
	 return ndate;
}
//不足两位的前边补0
function add_zero(temp)
{
	 if(temp<10) return "0"+temp;
	 else return temp;
}
//折叠显示区域
function fieldsetHidden(e){
  var src=getTarget(e);
  var legend = src.parentNode.parentNode;  
  var vTag=legend.nextSibling;
  while(vTag)
  {
	  if(vTag.style)
	  {
		 vTag.style.display= (vTag.style.display=="none")?"":"none";
	  }
	  vTag = vTag.nextSibling;
  }
  src.src = src.src.indexOf("Left")!=-1?src.src.replace(/Left/g,"Down"):src.src.replace(/Down/g,"Left");
}
//判断当前使用浏览器版本类
var userAgent = navigator.userAgent.toLowerCase(); 
var SysBrowser = {
	version: (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [])[1], 
	language : (navigator.browserLanguage || navigator.language).toLowerCase(),
	msie:    /msie/.test( userAgent ) && !/opera/.test( userAgent ), 
	firefox: /firefox/.test( userAgent ) ,
	chrome:  /chrome/.test( userAgent ),
	opera:   /opera/.test( userAgent ), 
	safari:  /safari/.test( userAgent ), 
    trident : userAgent.indexOf('trident') > -1, //IE内核                                 
    presto : userAgent.indexOf('presto') > -1, //opera内核                                 
    webKit : userAgent.indexOf('applewebkit') > -1, //苹果、谷歌内核                                 
    gecko : userAgent.indexOf('gecko') > -1 && userAgent.indexOf('khtml') == -1, //火狐内核                                
    mobile : !!userAgent.match(/applewebkit.*mobile.*/)|| !!userAgent.match(/applewebkit/), //是否为移动终端                                 
    ios : !!userAgent.match(/\(i[^;]+;( u;)? cpu.+mac os x/), //ios终端                 
    android : userAgent.indexOf('android') > -1 || userAgent.indexOf('linux') > -1, //android终端或者uc浏览器                                 
    iPhone : userAgent.indexOf('iphone') > -1 /*|| userAgent.indexOf('mac') > -1*/, //是否为iPhone或者QQHD浏览器                    
    iPad: userAgent.indexOf('ipad') > -1, //是否iPad       
    webApp : userAgent.indexOf('safari') == -1,//是否web应该程序，没有头部与底部
    google:userAgent.indexOf('chrome')>-1,
	getBrowser:function(){
		     if(SysBrowser.msie)       return "IE";  
		else if(SysBrowser.firefox)    return "Firefox";
		else if(SysBrowser.chrome)     return "Chrome";
		else if(SysBrowser.opera)      return "Opera";
		else if(SysBrowser.safari)     return "Safari";
		else return "IE";
    },
    getVersion:function(){
    	return SysBrowser.version;
    },
    getLanguage:function(){
    	return SysBrowser.language;
    }
};
function getEvent(e)
{
	return window.event||e;
}
function getTarget(e)
{
	var e = getEvent(e);
	return e.srcElement||e.target||e.currentTarget;
}
function cancelBubble(e)
{
	var e = getEvent(e);
	try{
		e.cancelBubble = true;
		e.stopPropagation();
	}catch(e){
		e.cancelBubble = true;
	}
	
}
function stopDefault(e){
	try
	{
		getEvent(e).returnValue=false;
		getEvent(e).preventDefault();
		getEvent(e).stopPropagation();
	}catch(e)
	{
		getEvent(e).returnValue=false;
	}
}
//强制加n个小数30->30.00
function forcePreciseDecimal(x,scale)
{
   scale = scale||2;
   var f_x = parseFloat(x);
   if (isNaN(f_x))
   {
      alert('function:forcePreciseDecimal->parameter error');
      return false;
   }
   var f_x = Math.round(x*100)/100;
   var s_x = f_x.toString();
   var pos_decimal = s_x.indexOf('.');
   if (pos_decimal < 0)
   {
      pos_decimal = s_x.length;
      s_x += '.';
   }
   while (s_x.length <= pos_decimal + scale)
   {
      s_x += '0';
   }
   return s_x;
}
function decimalReal(num,v)
{
   var dight  =(num*Math.pow(10,v)/Math.pow(10,v)).toFixed(v);  
   return parseFloat(dight);
} 
//四舍五入
function decimal(num,v)
{	//num表示要四舍五入的数,v表示要保留的小数位数。
	if(0 == v)
	{
		return decimalReal(decimalReal(num,2),0);
	}
    return decimalReal(num,v);		
}

function getTracywindyObject(id)
{
  return tracywindyObject[id];
}
//function 处理css
String.prototype.trim=function(){
	return this.replace(/(^\s{1,})|(\s{1,}$)/gim,"");
};
Array.prototype.contains = function(item){
    for(i=0;i<this.length;i++){
        if(this[i]==item){return true;}
    }
    return false;
};
function trim(str)
{
	return str.replace(/(^\s{1,})|(\s{1,}$)/gim,"");
}
function addClass(element,classname)
{
   classname = classname.trim();
   var oldClassStr = (nullToString(element.className)).replace(/\s{1,}/gm," ");
   var oldClassArr = oldClassStr.split(" ");
   oldClassArr.push(classname);
   element.className = oldClassArr.join(" ");
}
function removeClass(element,classname)
{
   classname = classname.trim();
   var oldClassStr = (nullToString(element.className)).replace(/\s{1,}/gm," ");
   var oldClassArr = oldClassStr.split(" ");
   for(var i=0;i<oldClassArr.length;i++)
   {
	   if(oldClassArr[i]==classname)
	   {
		   oldClassArr.splice(i,1);
	   }
   }
   element.className = oldClassArr.join(" ");
}
//生成唯一标识
function Guid(){
 return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}
function GenerateGuid(){
	return Guid()+Guid()+Guid()+Guid()+Guid()+Guid()+Guid()+Guid();
}
//项目根路径
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return (prePath+postPath);
}

//复制属性
var tracywindyApplyTo=function(originalObj,targetobj){
	for(var p in originalObj)
	{
		if((typeof(targetobj[p])=='undefined')||(targetobj[p]==null))
		{
			targetobj[p] = originalObj[p];
		}
	}
};
//只能输入数字
function validateNumber()
{
	/*var e = window.event;
	var keyCode = e.keyCode;
	var pass = false;
	if(((keyCode>=48)&&(keyCode<=57))||(keyCode==8)||(keyCode==190))
	{
		pass = true;
	}
	//小键盘
	if(((keyCode>=96)&&(keyCode<=105))||(keyCode==110))
	{
		pass = true;
	}
	e.returnValue = pass;*/
}
//弹出一个全屏窗口
function openFullScreenWindow(strURL,attachmentParams,winRef)
{
	if(-1==strURL.indexOf("systemMathRandom"))
	{
		if(strURL.indexOf("?")>-1)
	    {
	    	strURL+="&systemMathRandom=systemMathRandom";
	    }
	    else
	    {
	    	strURL+="?systemMathRandom=systemMathRandom";
	    }
	}
	
	if(attachmentParams)
	{
		if('string' == typeof(attachmentParams))
		{
		   strURL += ("&"+attachmentParams.replace(/(^&)|(^\?)/,""));
		}
		else if('object' == typeof(attachmentParams))
		{
		   for(var p in attachmentParams)
		   {
			 strURL+=("&"+p+"="+attachmentParams[p]); 
		   }
		}
	}
	/*strURL=encodeURI(encodeURI(strURL));
	var params = {};
	if(attachmentParams){
		if("object" == typeof(attachmentParams)){
			for(var p in attachmentParams){
				params[p] = attachmentParams[p];//escape(encodeURIComponent(attachmentParams[p]));
			}
		}
		else if("string" == typeof(attachmentParams)){
			var paramsArr = attachmentParams.split("&");
			for(var i=0;i<paramsArr.length;i++){
				var param = paramsArr[i];
				if(param){
					var keyValueArr =  param.split("=");
					var key = "";
					var value = "";
					var keyValueArrLen = keyValueArr.length;
					if(keyValueArrLen>=1){
						key = keyValueArr[0];
						if(key){
							key = key.trim();
						}
					}
					if(keyValueArrLen>=2){
						value = keyValueArr[1];
						if(value){
							value = value;//escape(encodeURIComponent(value)).trim();
						}
					}
					if(key){
						params[key]=value;
					}
				}
			}
		}
	}*/
	var sheight = window.screen.availHeight-30;
    var swidth = window.screen.availWidth-10;
    var winoption ="left=0px,top=0px,height="+sheight+"px,width="+swidth+"px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
    if(winRef){
    	window.loc = function(){
    	   winRef.location.href = strURL;//改变页面的 location
    	};
    	setTimeout("window.loc();",800);//这个等待很重要，如果不等待的话将无法实现
    }else{
        window.open(strURL,'_blank',winoption);
    }
    /*var tempSubmitForm = document.getElementById("id_tempSubmitForm");
    if(!tempSubmitForm){
        tempSubmitForm = document.createElement("form");
        document.body.appendChild(tempSubmitForm);
    }
    with(tempSubmitForm){
    	style.display="none";
    	innerHTML = "";
    	action = strURL;
    	target = "temSubmitFormTargetWindow";
    	method = "post";
    }
    for(p in params){
    	var hiddenInput = document.createElement("input");
    	with(hiddenInput){
    		type  = "hidden";
    		name  = p;
    		value = params[p];
    	}
    	tempSubmitForm.appendChild(hiddenInput);
    }
    tempSubmitForm.submit();*/
    return false;
}
function setCookie(name,value)//两个参数，一个是cookie的名子，一个是值
{
 var Days = 30; //此 cookie 将被保存 30 天
 var exp   = new Date(); //new Date("December 31, 9998");
 exp.setTime(exp.getTime() + Days*24*60*60*1000);
 document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name)//取cookies函数       
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) return unescape(arr[2]); return "";
}
//删除数字多余的0
function nullToString(value)
{
	if((typeof(value)=='undefined')||(value==null))
	{
		return "";
	}
	return value;
}
function trimZero(numberStr)
{
   if(!numberStr)  return numberStr;
   var floatValue = parseFloat(numberStr);
   var intVar     = parseInt(numberStr);
   var resultStr  = "";
   if(intVar==floatValue)   
   {
	  resultStr= intVar+"";
   }
   else
   {
	  resultStr=(floatValue+"").replace(/(^0+)|(0+$)/gi,"");
   }
   return resultStr;
}

function empty2Other(sourceStr,replaceStr)
{
	return !sourceStr ? replaceStr : sourceStr;
}
//说明：设置某个table中的输入框为只读/可编辑
//参数1：操作table的ID
//参数2:table那个域是不操作的以“,”分隔
//参数3:操作类型  0为只读    1为编辑
function formJSPInputHideOrShow(id,exceptFfield,stype){
	if(document.getElementById(id)){
  var inputs=jQuery("#"+id+" input[type='text'],#"+id+" textarea");
  var d=0;
  var vfield=[];
  var cflag=true;
  if(exceptFfield!=""){vfield=exceptFfield.split(",");}
  for(;d<inputs.length;d++){
  	cflag=true;
      if(vfield.length>0){
        for(var i=0;i<vfield.length;i++){
          if(vfield[i]==inputs[d].name){cflag=false;}
        }
      }
      if(cflag){
      if(stype==0){
          inputs[d].readOnly = true;
      	  inputs[d].className=inputs[d].className+" element-readonly";
      	  var pnode=inputs[d].parentNode;
      	  if(pnode.innerHTML.indexOf("WdatePicker")>0){
      		//inputs[d].setAttribute("onClick","WdatePicker(inputs[d],{readOnly:true})");
      		inputs[d].onclick="";
      		removeClass( inputs[d],"Wdate");
          }
        }   
      else{
         inputs[d].readOnly = false;
         var pnode=inputs[d].parentNode;
         removeClass( inputs[d],"element-readonly");
  	     if(inputs[d].getAttribute("dataType")=="Date"){
  	    	  inputs[d].readOnly = true;
  		      inputs[d].onclick=function(){WdatePicker(this,{readOnly:true})};
  		      inputs[d].className=inputs[d].className+" Wdate ";
        }
       }
     }}
 }
}
//查询多行控件中必填项
function checkTracywindyTableData(talbeid,title){
	var sourceTable= getTracywindyObject(talbeid);
	var sourceData=sourceTable.getRowsJsonData();
	if(sourceData.length<=0){
		jQuery.messager.alert('提示',"<div style='font-size:18px;line-height:30px;'>请填写"+title+"</div>",'error'); 
		return false;
	}
	var sourceColumn=sourceTable.columns;
	var columndata;
	var onecolumn;
	for(var i=0;i<sourceColumn.length;i++){
		var hidden=false;
		var nullable=true;
		if(sourceColumn[i].hidden==true){hidden=sourceColumn[i].hidden;}
		if(sourceColumn[i].nullable==false){nullable=sourceColumn[i].nullable;}
		if((hidden!=true) && (nullable==false)){	
		for(var j=0;j<sourceData.length;j++){
			onecolumn=sourceData[j];
			columndata=onecolumn[sourceColumn[i].name];
		    columndata=columndata+"";
			if(columndata==""|| columndata=="." ||columndata=="undefined" ){
				jQuery.messager.alert('提示',"<div style='font-size:18px;line-height:30px;'>"+title+"的"+sourceColumn[i].header+"不能为空</div>",'error'); 
			    return false;
			}
		}
	}}
	return true;
}


//上传附件
function attachmentUpWithUploadDate(config){
	var _inputStyle = "width:215px;height:20px;cursor:pointer;outline:medium none;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;";
	var _div1Style = "border:1px solid #FFFFFF;float:left;height:20px;margin-left:90px;margin-top:50px;position:absolute;width:215px;z-index:999;";
	var _div2Style = "float:left;margin-left:25px;margin-top:20px;position:absolute;z-index:99;";
	
	var _uploadWindowHtml = '<div id="id_downOrLoadFormWindow" closed="true" modal="true" title="'+config.title+'" style="display:none;width:380px;text-align:center;background-color:#FDF9F8;">';
	_uploadWindowHtml += '	<center>';
	_uploadWindowHtml += '		<div style="height: 140px;width:100%;">';
	_uploadWindowHtml += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
	_uploadWindowHtml += '			<form id="id_downOrLoadForm" enctype="multipart/form-data" target="import_excel_real_submit_frame" method="post" >';
	_uploadWindowHtml += '				<div style="' + _div1Style + '"><input type="file" id="_hiddenFileName" name="tableImportTemplate" style="' + _inputStyle + '"></div>';
	_uploadWindowHtml += '				<div style="' + _div2Style + '">';
	_uploadWindowHtml += '					<table align="center" style="width:100%">';
	_uploadWindowHtml += '						<tr><td colspan="2"><textarea id="id_fileparames" name="parames" style="display:none"></textarea></td></tr>';
	_uploadWindowHtml += '						<tr><td>收款时间：</td><td><input class="Wdate td-content-input" onclick="WdatePicker(this,{readOnly:true});" id="id_uploadDate" name="uploadDate"/></td></tr>';
	_uploadWindowHtml += '						<tr><td>上传文件：</td><td><input class="td-content-input td-content-readonly" id="_showFileName" style="cursor:pointer;" readonly><input type="button" class="btn btn-primary" style="padding:1px 5px;margin:-1px 2px;" value="浏览" id="_browserFileButton"></td></tr>';
	_uploadWindowHtml += '						<tr class="content-separator">';
	_uploadWindowHtml += '							<td colspan="2">';
	_uploadWindowHtml += '								<a id="_uploadSubmit" href="javascript:void(0);" class="btn btn-primary"><span>确定</span></a>';
	_uploadWindowHtml += '								<a id="_uploadCancel" href="javascript:void(0);" class="btn btn-primary" style="margin-left:20px;"><span>取消</span></a>';
	_uploadWindowHtml += '							</td>';
	_uploadWindowHtml += '						</tr>';
	_uploadWindowHtml += '					</table>';
	_uploadWindowHtml += '				</div>';
	_uploadWindowHtml += '			</form>';
	_uploadWindowHtml += '		</div>';
	_uploadWindowHtml += '	</center>';
	_uploadWindowHtml += '</div>';
	
	jQuery('body').find("#id_downOrLoadFormWindow").remove();
	jQuery('body').append(jQuery(_uploadWindowHtml));
	
	var _formBox = jQuery('body').find("#id_downOrLoadFormWindow");
	_formBox.find('#_uploadCancel').bind('click',function(){
		_formBox.window("close");
		_formBox.remove();
	});
	
	_formBox.find('#_hiddenFileName').bind('change',function(){
		var fileName = _formBox.find('#_hiddenFileName').val();
		_formBox.find('#_showFileName').val(fileName);
	});
	
	_formBox.find('#_uploadSubmit').bind('click',(function(config){
		return function(){
			var uploadDate = _formBox.find('#id_uploadDate').val();
			var file = _formBox.find('#_hiddenFileName').val();
			if(file == false){
				alert("请选择上传文件");
				return false;
			} else if(uploadDate == false){
				alert("请输入收款日期");
				return false;
			}
			config.parames = config.parames ? config.parames : {};
			//config.parames.uploadDate = uploadDate;
			_formBox.find("#id_fileparames").val(JsonUtil.encode(config.parames));
			var openurl = getRootPath() + config.url;
			var param = fileDownLoadParams(config, "url,title,parames", "str");
			if(param != ""){
				if(openurl.indexOf("?") < 0){
					openurl = openurl + "?";
				}
				openurl = openurl + "&" + param;
			}
			attachmentFormSubmit(openurl);
			_formBox.hide();
			_formBox.window("close");
		};
	})(config));
	_formBox.show();
	_formBox.window({top:150});
	_formBox.window("open");
}


//上传附件
function attachmentUpMini(config){
	var openurl = config.url;
	var param = fileDownLoadParams(config,"url,title,parames","str");
	if(param != ""){
		if(openurl.indexOf("?")<0){
			openurl = openurl + "?" + "&" + param;
		}else{
			openurl = openurl + "&" + param;
		}
	}
	var trnode = document.getElementById("id_downOrLoadFormWindow"); 
	if(trnode){
		trnode.parentNode.removeChild(trnode);
	}
	
	var uploadAttachmentFileWindow_html = "";
	uploadAttachmentFileWindow_html += '<div id="id_downOrLoadFormWindow" class="mini-window"  closed="true" modal="true" title=\"'+config.title+'\" style="display:none;width:300px;text-align:center;background-color:#FDF9F8;">';
	uploadAttachmentFileWindow_html += '	<center>';
	uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
	uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
	uploadAttachmentFileWindow_html += '			<form id="id_downOrLoadForm" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
	uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%">';
	uploadAttachmentFileWindow_html += '					<tr><td colspan=2><textarea id="id_fileparames" name="parames" style="display:none"></textarea></td></tr>';
	uploadAttachmentFileWindow_html += '					<tr><td colspan=2><input type="file" id="id_tableImportTemplate" name="tableImportTemplate" style="border:1px solid #DDD;cursor:pointer;"></td></tr>';
	uploadAttachmentFileWindow_html += '					<tr class="content-separator">';
	uploadAttachmentFileWindow_html += '						<td colspan="2">';
	uploadAttachmentFileWindow_html += '							<a href="javascript:void(0);" class="btn btn-primary" onclick="javascript:attachmentFormSubmit(&quot;'+getRootPath()+openurl+'&quot;);"><span>确定</span></a>';
	uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"  onclick=\'javascript:mini.get("id_downOrLoadFormWindow").hide();\'><span>取消</span></a>';
	uploadAttachmentFileWindow_html += '						</td>';
	uploadAttachmentFileWindow_html += '					</tr>';
	uploadAttachmentFileWindow_html += '				</table>';
	uploadAttachmentFileWindow_html += '			</form>';
	uploadAttachmentFileWindow_html += '		</div>';
	uploadAttachmentFileWindow_html += '	</center>';
	uploadAttachmentFileWindow_html += '</div>';
	
	$(document.body).append(uploadAttachmentFileWindow_html);
	
	if(config.parames){
		$("#id_fileparames").val(JsonUtil.encode(config.parames));
	}
	mini.parse();
	mini.get("id_downOrLoadFormWindow").show();
//	jQuery("#id_downOrLoadFormWindow").show();
//	jQuery("#id_downOrLoadFormWindow").window({top:200});
//	jQuery("#id_downOrLoadFormWindow").window("open");
}

//上传附件
function attachmentUp(config){

	var openurl = config.url;
	var param = fileDownLoadParams(config,"url,title,parames","str");
	if(param != ""){
		if(openurl.indexOf("?")<0){
			openurl = openurl + "?" + "&" + param;
		}else{
			openurl = openurl + "&" + param;
		}
	}
	var trnode = document.getElementById("id_downOrLoadFormWindow"); 
	if(trnode){
		trnode.parentNode.removeChild(trnode);
	}
	
	var uploadAttachmentFileWindow_html = "";
	uploadAttachmentFileWindow_html += '<div id="id_downOrLoadFormWindow" class="mini-window"  closed="true" modal="true" title=\"'+config.title+'\" style="display:none;width:300px;text-align:center;background-color:#FDF9F8;">';
	uploadAttachmentFileWindow_html += '	<center>';
	uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
	uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
	uploadAttachmentFileWindow_html += '			<form id="id_downOrLoadForm" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
	uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%">';
	uploadAttachmentFileWindow_html += '					<tr><td colspan=2><textarea id="id_fileparames" name="parames" style="display:none"></textarea></td></tr>';
	uploadAttachmentFileWindow_html += '					<tr><td colspan=2><input type="file" id="id_tableImportTemplate" name="tableImportTemplate" style="border:1px solid #DDD;cursor:pointer;"></td></tr>';
	uploadAttachmentFileWindow_html += '					<tr class="content-separator">';
	uploadAttachmentFileWindow_html += '						<td colspan="2">';
	uploadAttachmentFileWindow_html += '							<a  class="mini-button" iconCls="icon-add" onclick="javascript:attachmentFormSubmit(&quot;'+getRootPath()+openurl+'&quot;);"><span>确定</span></a>';
	uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;"  class="mini-button" iconCls="icon-close"  onclick=\'javascript:mini.get("id_downOrLoadFormWindow").hide();\'><span>取消</span></a>';
	uploadAttachmentFileWindow_html += '						</td>';
	uploadAttachmentFileWindow_html += '					</tr>';
	uploadAttachmentFileWindow_html += '				</table>';
	uploadAttachmentFileWindow_html += '			</form>';
	uploadAttachmentFileWindow_html += '		</div>';
	uploadAttachmentFileWindow_html += '	</center>';
	uploadAttachmentFileWindow_html += '</div>';
	
	$(document.body).append(uploadAttachmentFileWindow_html);
	
	if(config.parames){
		$("#id_fileparames").val(JsonUtil.encode(config.parames));
	}
	mini.parse();
	mini.get("id_downOrLoadFormWindow").show();
//	jQuery("#id_downOrLoadFormWindow").show();
//	jQuery("#id_downOrLoadFormWindow").window({top:200});
//	jQuery("#id_downOrLoadFormWindow").window("open");

}

function attachmentFormSubmit(url){
     
	if(!window.currentImportExcelLoadMask)
	{
		window.currentImportExcelLoadMask = new tracywindyLoadMask(document.body,"操作进行中 请稍等...");
	}
	 
	 if(window.currentImportExcelLoadMask){
	    window.currentImportExcelLoadMask.show();
	 }
	 
	var importExcelForm = document.getElementById("id_downOrLoadForm");
	with(importExcelForm){
		action = url;
		submit();
	}
	  
	try
	{
		mini.get("id_downOrLoadFormWindow").hide();
	}catch(e){}
	return false;
}

function attachmentDown(config){
	
	if(!window.currentImportExcelLoadMask)
	{
		window.currentImportExcelLoadMask = new tracywindyLoadMask(document.body,"操作进行中 请稍等...");
	}
	var tempurl=config.url;
	window.currentImportExcelLoadMask.show();
	var returnType=config.returnType;
	if(config.returnType=="file"){
        attachmentUp(config);
       
        var param=fileDownLoadParams(config,"url,title,parames","strs");
       
    	if(param!=""){
    		if(tempurl.indexOf("?")<0){
    			tempurl=tempurl+"?"+"&"+param;
    		}else{
    			tempurl=tempurl+"&"+param;
    		}
    	}
        var newAction = getRootPath()+tempurl+"&browserType="+SysBrowser.getBrowser().toLowerCase();
        mini.get("id_downOrLoadFormWindow").hide();
        attachmentFormSubmit(newAction);
       
        if(window.currentImportExcelLoadMask){
           window.currentImportExcelLoadMask.hide();
       }
    }else{
    	 var param=fileDownLoadParams(config,"url,title","array");
     	 param["browserType"]=SysBrowser.getBrowser().toLowerCase();
     	ajaxRequest({
		     url:getRootPath()+tempurl,
		     method:'POST',
		     success:function(rs){
     		   var tempreturnType=returnType;
     		   var updatetable=config.uploadid;
     		   var contractlist= rs.responseText;
	   	          contractlist=contractlist.replace(/(^\s+)|(\s+$)/g, ""); 
	   	         var vlist=eval("("+contractlist+")");
	   	         if(window.currentImportExcelLoadMask){
	               window.currentImportExcelLoadMask.hide();
	             }
	   	         if(vlist.result=="success"){
     		      if(tempreturnType=="listtocurpaget"){
     		    	   var list=getTracywindyObject(updatetable);
     	   	            list.AddRows(vlist.message);
	   		         }else{
	   		         window.open(getRootPath()+'/leasing/common/templateManager/list.bi?id='+vlist.message[0].id,'newfilelist','height=300,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
     		      }
	   	         }else{
	   	        	 alert(vlist.message);
	   	         }
     	     },
		     async:false,
		     failure:function(res){},
		     params:param
	   });
    }
}
function fileCreateWordByIds(config,uploadid){
	 if(!window.currentDeleteFileLoadMask)
	  {window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"正在合同 请稍等..."); } 
      window.currentDeleteFileLoadMask.show();
      var url="/leasing/template/CreateFileByTemplate.action";
      tempurl=config.url||url;
      var param=fileDownLoadParams(config,"url,title","array");
  	  param["browserType"]=SysBrowser.getBrowser().toLowerCase();
  	  ajaxRequest({
		     url:getRootPath()+tempurl,
		     method:'POST',
		     success:function(rs){
  		      var contractlist= rs.responseText;
  	   	          contractlist=contractlist.replace(/(^\s+)|(\s+$)/g, ""); 
  	   	      if(window.currentDeleteFileLoadMask){
  	   	    	window.currentDeleteFileLoadMask.hide();
              }
  	   	     if(contractlist!=""){
  	   	 	      var vlist=eval("("+contractlist+")");
  	   	 	      if(vlist.result=="success"){
  	   	             var list=getTracywindyObject(uploadid);
  	   	             list.AddRows(vlist.message);
  	   	             alert("文件生成成功");
  	   	 	      }else{
  	   	 	    	  alert(vlist.message);
  	   	 	      }
  	          } 
  	   	   },
		     async:false,
		     failure:function(res){
  	   		 if(window.currentDeleteFileLoadMask){
  	   	    	window.currentDeleteFileLoadMask.hide();
              }
  	     },
		     params:param
	   });
}
function fileDownLoadParams(config, exectFeild, reType) {
	var sfield = exectFeild || "";
	sfield = "," + sfield + ",";
	var parames = "";
	var vparames = {};
	for ( var field in config) {
		if (parames != "") {
			parames = parames + "&";
		}
		if (sfield.indexOf(field) >= 0) {
			
		} else {
			if (typeof (config[field]) == "string") {
				parames = parames + field + "=" + config[field];
				vparames[field] = config[field];
			} else {
				parames = parames + field + "=" + JsonUtil.encode(config[field]);
				vparames[field] = JsonUtil.encode(config[field]);
			}
		}
	}
	if (reType == "array") {
		return vparames;
	} else {
		return parames;
	}
}


function getTaskTypeChineseName(taskType){
	var chineseMapping = {
		"PENDING":"待办任务",
		"DELEGATE":"委托任务",
		"ASSIGNMENTPENDING":"指派待处理任务",
		"ASSIGNMENTCOMPLETED":"指派已处理任务",
		"READ":"传阅任务",
		"SIGNATURE":"会签任务"
	};
	return chineseMapping[taskType.toUpperCase()];
}

//加载模板
function loadTemplate(config){
	//注：config参数
	//templateDiv：加载的模板放在位置
	//oneClassify:一级分类
	//twoClassify:二级分类
	//threethClassify:三级分类
	//fourClassify:四级分类
	//fiveClassify:五级分类
	var params=fileDownLoadParams(config,"","array");
	var templateDiv=config.templateDiv;
	config.changeRowNum = config.changeRowNum || 4; 
	window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"正在加载模板 请稍等..."); 
	currentDeleteFileLoadMask.show();
	ajaxRequest({
	     url:getRootPath()+'/leasing/template/loadTemplateByClassify.action',
	     method:'POST',
	     success:function(rs){
		   var svote=rs.responseText;
		   svote=svote.replace(/(^\s+)|(\s+$)/g, ""); 
		   currentDeleteFileLoadMask.hide(); 
		   $("#"+templateDiv).html(svote);
	    },
	     async:false,
	     failure:function(res){
	    	 currentDeleteFileLoadMask.hide(); 
		     alert("加载模板失败!");},
	     params:params
   });
}	
function createWordByIds(config){
	   //fileListTable:"取文件件名列的ID"
	   //modename:'上一级分类'
	   config.fileListTable = config.fileListTable||"table_id_table_word_list_container";
	    var chk_value=[];
	    var tempid="";
	   $('input[name="contract_word_list_check_box"]:checked').each(function(){    
		   chk_value.push($(this).val());
		   if(tempid.length>0){
		   tempid=tempid+",";
		   }
		   tempid=tempid+$(this).val();
	   });
	   $('input[name="contract_word_list_check_box"]:checked').each(function(){    
		   chk_value.push($(this).attr("checked",false));    
	   });
	   var filelist = getTracywindyObject(config.fileListTable).getRowsJsonData();//合同清单
	   if(chk_value.length<=0){
	   	jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>请勾选模板！</div>",'error')
	       return false;
	   }else{
		  for(var i=0;i<chk_value.length;i++){
	          for(var j=0;j<filelist.length;j++){
	              if(chk_value[i]==filelist[j].filetemplate){
	            	  jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>不能重复生成"+filelist[j].templatename+"！</div>",'error')
	            	    return false;
	              }
	          }
	      }
		 var tempconfig={};
		 tempconfig.templateid=tempid;
		 tempconfig.flowunid=flowUnid;
		 for(var t in config){
			 tempconfig[t]=config[t];
		 }
		 fileCreateWordByIds(tempconfig,config.fileListTable);
	   }
}
function downloadFile(Id)
{
  attachmentDown({url:"/leasing/template/downLoadAttachById.action",id:Id,returnType:'file'});
} 
function showContractFile(value,tableObj,columnName,columnIndex,rowData){
    var base = "<a href='#' onclick='showWORDFile(\""+rowData.id+"\")'>{1}</a>{2}";
    var base2 = "<a href='#' onclick='downloadFile(\""+rowData.id+"\")'>{1}</a>{2}";
    var separator = "&nbsp;&nbsp;";
    var updateFlag="编辑";
    var field=base.replace("{1}",updateFlag).replace("{2}",separator);
    field=field+base2.replace("{1}","下载").replace("{2}",separator);
    return field;
}
function showWORDFile(doc_id){
	var URL=getRootPath()+"/leasing/common/templateManager/editWordTemplate.bi";
	var params={id:doc_id}
	 openFullScreenWindow(URL,params);
}

function importFinanceExcel(custid,initConfig){
	attachmentUp({url:"/leasing/finance/importFinacne.acl",modelname:'cardupload',custid:custid,title:'财务报表中传',parames:initConfig});
}
function DefaultAjaxCallBack(message){
	if(window.currentImportExcelLoadMask){
	    window.currentImportExcelLoadMask.hide();
	 }
	var vobject=eval("("+message+")");
    alert(vobject.message);
}
function getFuncRightManage(userid,deptcolumn,managecolumn){
	var params = {};
	var datas = [];
	var conditon = "";
	params["userid"] = userid || "${sessionScope['login_userid']}";	//登陆人ID
	params["deptcolumn"] = deptcolumn;	//部门条件字段名（可含别名，如：'contract_info.proj_dept'）
	params["managecolumn"] = managecolumn;	//担当条件字段名（可含别名，如：'contract_info.proj_manage'）
	
	params["xmlFileName"] = "/eleasing/jsp/base/funcright_manage.xml";
	$.ajax({
		url:(getRootPath()+"/table/getTableData.action"),
		async:false,
		params:params,
		success:function(res){
			var jsonData1 = '';
			jsonData1=eval("(" + res.responseText + ")");
			datas = jsonData1["datas"];
			condition = datas[0]["condition"];
		},
		failure:function(res){
			condition = " and 1=2 ";
		}
	});
	return condition;
}
var util = {   
	    clone : function(obj) {   
	        if (typeof (obj) != 'object')   
	            return obj;   
	  
	        var re = {};   
	        if (obj.constructor==Array)   
	            re = [];   
	  
	        for ( var i in obj) {   
	            re[i] = util.clone(obj[i]);   
	        }   
	  
	        return re;   
	  
	    }   
	}; 
