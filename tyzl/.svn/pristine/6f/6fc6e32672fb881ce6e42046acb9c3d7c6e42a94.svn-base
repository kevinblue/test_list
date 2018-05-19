//tenwa对象声明
window.tenwaAjax = window.tenwaAjax || new (function(){
	var $tenwaAjax = this;
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
	this.showLoadMask=function(){mini.mask({el: document.body, cls: 'mini-mask-loading',html: '数据加载中  请稍后...'});}
	this.hideLoadMask=function(){mini.unmask(document.body);}
	return this;
});
//显示流程类型
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
