seajs.use(["js/tenwa/init.js"]);
// JsonUtil对象声明
window.JsonUtil = window.JsonUtil || new (function() {
	
	this.decode = function(json) {
		try {
			return eval("(" + json.replace(/\r/gi, "\\r").replace(/\n/gi, "\\n") + ")");
		} catch (e) {
			alert("JSON字符串解析错误：\n" + json);
			return '';
		}
	};
	
	this.encode = function(o) {
		if (typeof o == "undefined" || o === null) {
			return "null";
		} else if (Object.prototype.toString.apply(o) === '[object Array]') {
			return this.encodeArray(o);
		} else if (Object.prototype.toString.apply(o) === '[object Date]') {
			return this.encodeDate(o);
		} else if (typeof o == "string") {
			return this.encodeString(o);
		} else if (typeof o == "number") {
			return this.encodeString(o).replace(/\"/g, "");
		} else if (typeof o == "boolean") {
			return this.encodeString(o).replace(/\"/g, "");
		} else {
			var a = new Array();
			a.push('{');
			var b = false;
			var i = '';
			var v = null;
			for (i in o) {
				var isOwnPropery = false;
				try {
					isOwnPropery = o.hasOwnProperty(i);
				} catch (e) {
					isOwnPropery = false;
				}
				if (isOwnPropery) {
					v = o[i];
					switch (typeof v) {
					case "undefined":
					case "function":
					case "unknown":
						break;
					default:
						if (b) {
							a.push(',');
						}
						a.push(this.encode(i), ":", (v === null ? "null" : this.encode(v)));
						b = true;
					}
				}
			}
			a.push("}");
			return a.join("");
		}
	};
	
	this.m = { "\b" : '\\b', "\t" : '\\t', "\n" : '\\n', "\f" : '\\f', "\r" : '\\r', '"' : '\\"', "\\" : '\\\\'}; 
	
	this.encodeString = function(s) {
		var $me = this;
		if (/["\\\x00-\x1f]/.test(s)) {
			return '"' + s.replace(/([\x00-\x1f\\"])/g, function(a, b) {
				var c = $me.m[b];
				if (c) {
					return c;
				}
				c = b.charCodeAt();
				return "\\u00" + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
			}) + '"';
		}
		return '"' + s + '"';
	};
	
	this.encodeArray = function(o) {
		var a = new Array();
		a.push('[');
		var b = false;
		var i = '';
		var l = o.length;
		var v = null;
		
		for (i = 0; i < l; i += 1) {
			v = o[i];
			switch (typeof v) {
			case "undefined":
			case "function":
			case "unknown":
				break;
			default:
				if (b) {
					a.push(',');
				}
				a.push(v === null ? "null" : this.encode(v));
				b = true;
			}
		}
		a.push("]");
		return a.join("");
	};
	
	this.encodeDate = function(o) {
		return o.toChar();
	};
})();

//tenwa对象声明
window.tenwa = window.tenwa || new (function(){
	var $tenwa = this;

	/**
	 * 去空格函数
	 */
	this.trim = function(str){
		return str ? str.replace(/(^\s{1,})|(\s{1,}$)/gim, "") : str;
	};


	/**
	 * 返回日期
	 * 
	 * @param d
	 *            the delimiter
	 * @param p
	 *            the pattern of your date 2006-06-25 由
	 *            www.ttkc.net 修改为根据用户指定的 style 来确定；
	 */
	this.toDate = function(dateStr, style) {
		var y = dateStr.substring(style.indexOf('y'), style.lastIndexOf('y') + 1);// 年
		var M = dateStr.substring(style.indexOf('M'), style.lastIndexOf('M') + 1);// 月
		var d = dateStr.substring(style.indexOf('d'), style.lastIndexOf('d') + 1);// 日
		var h = dateStr.substring(style.indexOf('h'), style.lastIndexOf('h') + 1);// 时
		var m = dateStr.substring(style.indexOf('m'), style.lastIndexOf('m') + 1);// 分
		var s = dateStr.substring(style.indexOf('s'), style.lastIndexOf('s') + 1);// 秒

		if (s == null || s == "" || isNaN(s)) {
			s = new Date().getSeconds();
		}
		if (m == null || m == "" || isNaN(m)) {
			m = new Date().getMinutes();
		}
		if (h == null || h == "" || isNaN(h)) {
			h = new Date().getHours();
		}
		if (d == null || d == "" || isNaN(d)) {
			d = new Date().getDate();
		}
		if (M == null || M == "" || isNaN(M)) {
			M = new Date().getMonth() + 1;
		}
		if (y == null || y == "" || isNaN(y)) {
			y = new Date().getFullYear();
		}
		var dt = new Date();
		dt.setFullYear(y, M - 1, d);
		dt.setHours(h, m, s, 0);
		return dt;
	};


	/**
	 * 格式化日期
	 * 
	 * @param d
	 *            the delimiter
	 * @param p
	 *            the pattern of your date
	 * @author meizz
	 */
	this.toChar = function(date, style) {
		var o = {
			"M+" : date.getMonth() + 1, // month
			"d+" : date.getDate(), // day
			"h+" : date.getHours(), // hour
			"m+" : date.getMinutes(), // minute
			"s+" : date.getSeconds(), // second
			"w+" : "天一二三四五六".charAt(date.getDay()), // week
			"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
			"S" : date.getMilliseconds()
		};
		if (/(y+)/.test(style)) {
			style = style.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(style)) {
				style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return style;
	};


	/**
	 * 日期操作函数
	 * 
	 * @param config.type
	 *            需要计算的日期级别 1为秒 ,2为分, 3为小时, 4为天, 5为月 ,6为年
	 * @param config.count
	 *            增加或者减少的数值
	 * @param config.date
	 *            原始日期
	 * @param config.format
	 *            日期格式化style
	 * @return
	 */
	this.calculateDate = function(config) {
		if (config) {
			config.type = config.type == undefined ? 0 : parseInt(config.type);
			config.count = config.count == undefined ? 0 : parseInt(config.count);
			config.format = config.format == undefined ? "yyyy-MM-dd" : config.format;
			config.date = config.date == undefined ? new Date() : config.date.toDate(config.format);

			switch (config.type) {
			case 6:// 年
				config.date.setYear(config.date.getFullYear() + config.count);
				break;
			case 5:// 月
				config.date.setMonth(config.date.getMonth() + config.count);
				break;
			case 4:// 天
				config.date.setDate(config.date.getDate() + config.count);
				break;
			case 3:// 时
				config.date.setHours(config.date.getHours() + config.count);
				break;
			case 2:// 分
				config.date.setMinutes(config.date.getMinutes() + config.count);
				break;
			case 1:// 秒
				config.date.setSeconds(config.date.getSeconds() + config.count);
				break;
			default:
			}
			return config.date.format(config.format);
		} else {
			return "";
		}
	};


	/**
	 * 金额大写转换函数
	 */
	this.money2DX = function(money) {
		if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(money)){
			return "数据非法";
		}
		var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
		money += "00";
		var p = money.indexOf('.');
		if (p >= 0){
			money = money.substring(0, p) + money.substr(p + 1, 2);
		}
		unit = unit.substr(unit.length - money.length);
		for (var i = 0; i < money.length; i++){
			str += '零壹贰叁肆伍陆柒捌玖'.charAt(money.charAt(i)) + unit.charAt(i);
		}
		return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(壹拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
	};

	/**
	 *  金额大写升级版 我个人觉得比上面的好
	 */
	function DX(n) {
		if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n)){
			return "数据非法";
		}
		if(n==0){
			return "零";
		}
		
		var unit = "仟佰拾亿仟佰拾万仟佰拾元角分", str = "";
		n += "00";
		var p = n.indexOf('.');
		if (p >= 0){
			n = n.substring(0, p) + n.substr(p + 1, 2);
		}
		unit = unit.substr(unit.length - n.length);
		for (var i = 0; i < n.length; i++){
			str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
		}
		return str.replace(/零(仟|佰|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(壹拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
	}
	
	/**
	 * 金额大写转换函数，转换成千分位
	 */
	this.money2Thousand = function(money) {
		if (isNaN(money)) {
			return money;
		}
		money = money + "";
		money = money.replace(/,/g, "");
		var re = /(\d{1,3})(?=(\d{3})+(?:$|\D))/g; // 肖遥云指点
		var n1 = money.replace(re, "$1,");
		return n1;
	};


	/**
	 * 获取根目录
	 */
	this.getRootPath = function() {
		var strFullPath = window.document.location.href;
		var strPath = window.document.location.pathname;
		var pos = strFullPath.indexOf(strPath);
		var prePath = strFullPath.substring(0, pos);
		var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
		return (prePath + postPath);
	};


	/**
	 * 获取根目录
	 */
	this.openFullScreenWindow = function(strURL, attachmentParams, winRef) {
		if (-1 == strURL.indexOf("systemMathRandom")) {
			if (strURL.indexOf("?") > -1) {
				strURL += "&systemMathRandom=systemMathRandom";
			} else {
				strURL += "?systemMathRandom=systemMathRandom";
			}
		}

		if (attachmentParams) {
			if ('string' == typeof (attachmentParams)) {
				strURL += ("&" + attachmentParams.replace(/(^&)|(^\?)/, ""));
			} else if ('object' == typeof (attachmentParams)) {
				for ( var p in attachmentParams) {
					strURL += ("&" + p + "=" + attachmentParams[p]);
				}
			}
		}
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		if (winRef) {
			window.loc = function() {
				winRef.location.href = strURL;// 改变页面的 location
			};
			setTimeout("window.loc();", 800);// 这个等待很重要，如果不等待的话将无法实现
		} else {
			window.open(strURL, '_blank', winoption);
		}
		return false;
	};

	/**
	 * 两个参数，一个是cookie的名字，一个是值
	 */
	this.setCookie = function(name, value) {
		var Days = 30; // 此 cookie 将被保存 30 天
		var exp = new Date(); // new Date("December 31, 9998");
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	};

	/**
	 * 取cookies函数
	 */
	this.getCookie = function(name) {
		var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
		if (arr != null){
			return unescape(arr[2]);
		}
		return "";
	};


	/**
	 * ajax获取值的函数
	 */
	this.ajaxRequest = function(config) {
		var xmlHttpRequest = null;
		try {
			xmlHttpRequest = new XMLHttpRequest();
		} catch(e) {
			try {
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		if (xmlHttpRequest == null) {
			alert("您的浏览器不支持AJAX！");
			return;
		}
		var method = (config.method || 'POST').toUpperCase();
		var url = config.url;
		var contentType = config.contentType || 'application/x-www-form-urlencoded;charset=UTF-8';
		var params = config.params || {};
		var sendParams = ('&decorate=none&tracywindyRandom=' + Math.random());
		for ( var param in params) {
			sendParams += ('&' + param + '=' + escape(encodeURIComponent(params[param])));
		}
		if (method == 'GET') {
			if (url.indexOf('?') == -1) {
				url += '?';
			}
			url += sendParams;
		}
		var timeout = config.timeout || 30 * 1000;
		this.async = (config.async == false) ? false : true;
		xmlHttpRequest.open(method, url, this.async);
		xmlHttpRequest.setRequestHeader("Content-Type", contentType);
		xmlHttpRequest.setRequestHeader("X-Requested-With", "XMLHttpRequest");
		var clearTO = setTimeout(function() {
			xmlHttpRequest.abort(); //终止XMLHttpRequest对象
			config.failure(xmlHttpRequest);
		}, timeout);
		xmlHttpRequest.onreadystatechange = function() {
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					clearTimeout(clearTO);
					var judgeText = xmlHttpRequest.responseText;
					if (/^\s*<\s*html/gim.test(judgeText)) {
						window.top.location.href = $tenwa.getRootPath() + "/login.jsp";
						return;
					} else if (0 == judgeText.indexOf("X-Requested-With-Error")) {
						alert(judgeText.substring("X-Requested-With-Error".length, judgeText.length));
						if (window.tracywindyObject) {
							for (var p in window.tracywindyObject) {
								var currentObj = tracywindyObject[p];
								if ("loadmask" == currentObj['objectType']) {
									currentObj.hide();
								}
							}
						}
					} else {
						config.success(xmlHttpRequest);
					}
				} else if (xmlHttpRequest.status == 404) {
					config.failure(xmlHttpRequest);
				}
			}
		};
		xmlHttpRequest.send(sendParams);
	};


	this.createTable = function(config){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable(config);
		});
	};
	
	this.createQueryInput = function(config){
		seajs.use([ "js/apcomponent/apqueryinput/apqueryinput.js" ], function(ApQueryInput) {
			new ApQueryInput(config);
		});
	};


	this.startProcess = function(processId, params, winRef) {
		var url = $tenwa.getRootPath() + "/jbpm/startDeployedProcess.action";
		params = (params ? params : {});
		params.processDefinitionId = escape(encodeURIComponent(processId));
		$tenwa.openFullScreenWindow(url, params ? params : {}, winRef);
	};

	// 把一个字符串用0填充到一定长度，前填充
	this.fill = function(str, length){
		if(str && !liNaN(length)){
			while (str.length < length) {
				str = '0' + str;
			}
		}
		return str;
	};
	
	this.encodeJSON = JsonUtil.encode;
	this.decodeJSON = JsonUtil.decode;
	
	this.loadCombo = function(comboId, params, start){
		var combo = mini.get(comboId);
		params = params || {};
		params.start = start ? start : 0;
		
		var value = combo.getValue();
		var rawValue = combo.getText();
		combo.setUrl(aputil.getParamsUrl(this.getRootPath() + "/table/getTableData.action", params, true));
		if(value != "" && rawValue != ""){
			combo.setValue(value);
			combo.setText(rawValue);
		}
		combo.isLoadData = true;

		/*this.ajaxRequest({
			method:"post",
			url:this.getRootPath() + "/table/getTableData.action",
			async:false,
			params:params,
			success:function(data){
				combo.setData(JsonUtil.decode(data.responseText).datas);
				if(value != "" && rawValue != ""){
					combo.setValue(value);
					combo.setText(rawValue);
				}
				combo.isLoadData = true;
			},failture:function(data){
				
			}
		});*/
	}
	
	//懒加载combo数据，
	this.lazyLoadCombo = function(comboId, params, start){
		var combo = mini.get(comboId);
		var clickFunc = function(combo){
			return function(){
				if(!mini.get(comboId).isLoadData){
					tenwa.loadCombo(comboId, params, start);
				}
			}
		}(combo);
		combo.on("click", clickFunc);
	}
	//验证表单，弹出验证结果
	this.validateForm = function(formId){
		var form = new mini.Form("#" + formId);
		form.validate();
		if(form.isValid() == false){
			var errorFields = form.getErrors();
			var errorText = "";
			for(var i = 0;i < errorFields.length;i ++){
				errorText += (errorFields[i].label + " " + errorFields[i].errorText + "<br/>");
			}
			mini.alert(errorText);
			return false;
		}
		return true
	}
	
	return this;
});





// ==================================放在最后声明==================================
String.prototype.trim = function(){
	return tenwa.trim(this);
};
String.prototype.toDate = function(style){
	return tenwa.toDate(this, style);
};

Date.prototype.toChar = function(style){
	return tenwa.toChar(this, style);
};
Date.prototype.format = function(style){
	return tenwa.toChar(this, style);
};
//==================================放在最后声明==================================