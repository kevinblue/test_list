define(function(require , exports , module) {
	String.prototype.trim=function(){
		return this.replace(/(^\s{1,})|(\s{1,}$)/gim,"");
	};
	var sysBrowser = null;
	var userAgent = navigator.userAgent.toLowerCase();
	sysBrowser = {
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
				     if(sysBrowser.msie)       return "IE";  
				else if(sysBrowser.firefox)    return "Firefox";
				else if(sysBrowser.chrome)     return "Chrome";
				else if(sysBrowser.opera)      return "Opera";
				else if(sysBrowser.safari)     return "Safari";
				else return "IE";
		    },
		    getVersion:function(){
		    	return sysBrowser.version;
		    },
		    getLanguage:function(){
		    	return sysBrowser.language;
		    }
	};
	var ApUtil = {
		getBrowser	       : function(){
			return sysBrowser.getBrowser();
		},
		setParamByDom : function(domNode, params, isClear) {
		    if (isClear) {
		        domNode.value = "";
		    }
		    var paramName = domNode.name;
		    var paramValue = domNode.value || "";
		    if (paramName) {
		        params[paramName] = paramValue;
		    }
		},
		recusionAllChildrenNodesParams : function(parentDom, params, isClear) {
		    var allChildrenNodes = parentDom.childNodes;
		    var allChildrenNodesLen = allChildrenNodes.length;
		    if (allChildrenNodesLen > 0) {
		        for (var i = 0; i < allChildrenNodesLen; i++) {
		            var childNode = allChildrenNodes[i];
		            var nodeTagName = childNode.tagName;
		            if (!nodeTagName) {
		                continue;
		            }
		            switch (nodeTagName.toUpperCase()) {
			            case "SELECT":
			            case "TEXTAREA":{
			            	this.setParamByDom(childNode, params, isClear);
			                break;
			            }
			            default:{
			                    this.recusionAllChildrenNodesParams(childNode, params, isClear);
			            }
		            }
		        }
		    } else {
		    	this.setParamByDom(parentDom, params, isClear);
		    }
		},
		getJsonParamsByUrl : function(url) {
			url = url||"";
			var jsonObj = {};
			var pIndex = url.indexOf("?");
			if(-1 < pIndex){
				attchmentParams = url.substring(pIndex+1,url.length);
			    var keysets = attchmentParams.split("&");
			    for (var i = 0; i < keysets.length; i++) {
			        var keyset = keysets[i].split("=");
			        var key = (keyset[0]||"").trim();
			        var value = (keyset.length > 1) ? (keyset[1]||"") : "";
			        jsonObj[key] = value;
			    }
			}
		    return jsonObj;
		},
		setComboxParams:function(combox,params){
			var url=combox.getUrl()||(window.globalWebRoot + '/table/getTableData.action');
		     var oldParmas=this.getJsonParamsByUrl(url);
		     for (var param in oldParmas) {
		    	 oldParmas[param]=unescape(decodeURIComponent(oldParmas[param]));
		     }
		     for (var param in params) {
		    	 oldParmas[param]=params[param];
		     }
		     var tempurl=url;
		     var pIndex = url.indexOf("?");
			 if(pIndex>0){
				 tempurl=url.substring(0,pIndex);	
			 }
			combox.setUrl(this.getParamsUrl(tempurl,oldParmas,true));	
		},
		getParamsUrl:function(url,params,isEncode){
			   isEncode = isEncode||false;
			   url    = url||"";
			   params = params||{};
		       if (url.indexOf('?') > -1) {
		            for (var param in params) {
		            	var paramValue = "";
		            	if(isEncode){
		            		paramValue = escape(encodeURIComponent(params[param]));
		            	}else{
		            		paramValue = params[param];
		            	}
		                url += ("&" + param + "=" + paramValue);
		            }
		        } else {
		            var index = 0;
		            for (var param in params) {
		                var addFlag = "&";
		                if (1 == (++index)) {
		                    addFlag = "?";
		                }
		            	var paramValue = "";
		            	if(isEncode){
		            		paramValue = escape(encodeURIComponent(params[param]));
		            	}else{
		            		paramValue = params[param];
		            	}
		                url += (addFlag + param + "=" + paramValue);
		            }
		       }
		       return url;
		},
		//折叠显示区域
		fieldsetToggle : function (src,callBack){
			
			var toggleBtnId = $(src).parent()[0].id;
			var queryareaTableContainer = $("#" + toggleBtnId.replace("_toggleBtn", "") + " > div");
			var tableContainer = $("#" + toggleBtnId.replace("_toggleBtn", "").replace("id_queryAreaContainerId_", "id_tableContainer_"));
			
			var displayStatus = queryareaTableContainer.css("display");
			
			if(displayStatus == 'none'){
				$(src).html("折&nbsp;叠");
				tableContainer.height(tableContainer.height() - queryareaTableContainer.height() - 20);
			}else{
				$(src).html("展&nbsp;开");
			}
			queryareaTableContainer.slideToggle("fast",callBack);
			
		/*  var legend = src.parentNode;
		  while("LEGEND" != legend.tagName.toUpperCase()){
			  legend = legend.parentNode;
		  }
		  var vTag   = legend.nextSibling;
		  while(vTag)
		  {
			  if(vTag.style)
			  {
				  if("none" == vTag.style.display){
					  jQuery(vTag).show("slow"); 
				  }else{
					  jQuery(vTag).hide("slow"); 
				  }
				  jQuery(vTag).slideToggle("fast",callBack);
				 //vTag.style.display= (vTag.style.display=="none")?"":"none";
			  }
			  vTag = vTag.nextSibling;
		  }
		  if(src.className.indexOf("expand-button") == -1){
			  var tableContainerId = legend.parentNode.parentNode.id;
			  tableContainerId = tableContainerId.replace("id_queryAreaContainerId_", "id_tableContainer_");
			  $("#" + tableContainerId).height($("#" + tableContainerId).height() - 300);
		  }
		  var fieldSetDom = legend.parentNode;
		  jQuery(fieldSetDom).toggleClass("fieldset-hidden");
		  var className = src.className;
		  src.title     = className.indexOf("expand-button") != -1 ? "显示查询条件" : "隐藏查询条件";
		  src.className = className.indexOf("expand-button") != -1 ? className.replace(/expand\-button/g,"collapse-button") : className.replace(/collapse\-button/g,"expand-button");
		*/
		},
		//为对象 [replace替换属性值特殊字符][restore还原对象属性值]
		replaceFromDataSpecialChar: function(object, oper){
			for(var item in object){
				if(typeof object[item] == "string"){
					switch (oper){
						case "replace": {
							object[item] = this.replaceStrSpecialChar(object[item]);
							break;
						}
						case "restore": {
							object[item] = this.restoreStrToOriginal(object[item]);
							break;
						}
					}
				}
			}
		},
		replaceStrSpecialChar: function(str){
			return str.replace(/%/g,"%25").replace(/(\n)|(\r)/g,"<br>");
		},
		restoreStrToOriginal: function(str){
			return str.replace(/<br>/g, '\r\n');
		},
		formatNumberThousand : function(s) {
			if (isNaN(s)) {
				return s;
			}
			s += "";
			s = s.replace(/,/g, "");
			var re = /(\d{1,3})(?=(\d{3})+(?:$|\D))/g; // 肖遥云指点
			var n1 = s.replace(re, "$1,");
			return n1;
		}
		/*,
		changeColumnToI18n : function(config,ischange){
			if(!ischange){
				return config;
			}
			var columns = config.columns;
			for(var i = 0 ; columns.length ; i++){
				var column = columns[i];
				for(var attr in column){
					if(attr == 'enfield' && ischange){
						column['field'] = column[attr]
						break;
					}
				}
				columns[i] = column;
			}
		}*/
	};
	return ApUtil;
});
