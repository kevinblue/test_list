/*******************************************************************************
 * 
 * url : 'ajax.jsp' 请求地址
 * timeout : 30*1000 请求超时时间(默认30s)ms(毫秒)为单位
 * method : post/get 发送方式
 * contentType : 'application/x-www-form-urlencoded;charset=UTF-8'
 * async : true/false 是否异步
 * success(response) : ajax成功返回的回调函数reponse.responseText
 * failure(response) : ajax请求失败的回调函数reponse.responseText
 * params : 参数{method:'remode'}，JS对象
 * 
 ******************************************************************************/
var ajaxRequest = function(config) {
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
                    //项目根路径
                    var rootPath = "";
                    if (window.getRootPath) {
                        rootPath = getRootPath();
                    } else {
                        var strFullPath = window.document.location.href;
                        var strPath = window.document.location.pathname;
                        var pos = strFullPath.indexOf(strPath);
                        var prePath = strFullPath.substring(0, pos);
                        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
                        rootPath = (prePath + postPath);
                    }
                    window.top.location.href = rootPath + "/login.jsp";
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
            	if(config.failure){
            		config.failure(xmlHttpRequest);
            	}
            }
        }
    };
    xmlHttpRequest.send(sendParams);
};