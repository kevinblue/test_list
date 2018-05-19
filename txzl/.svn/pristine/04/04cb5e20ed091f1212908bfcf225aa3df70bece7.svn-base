define(function(require,exports,module){

	exports.ajax = function(config){		
		
		var url = config.url;
		var sendParams = null;		
		var params = config.params || config.data || {};		
		var dataType = (config.dataType || 'JSON').toUpperCase();
		var encodedParams = encodeParams(params);

		var hasContext = (config.hasContext) ? true : false;

		var pathAdd = "/";
		if(url.substr(0,1) == '/'){
			pathAdd = "";
		}
		if(!hasContext){
			url = getWebRoot() + pathAdd + url;
		}
		url = getRandomUrl(url);
		url += ("&decorate=none");

		jQuery.ajax({
			url: url,
			type: (config.method || config.type || 'POST').toUpperCase(),
			contentType: config.contenType || 'application/x-www-form-urlencoded;charset=UTF-8',
			data: encodedParams,
			dataType: 'text',
			async : (config.async == false) ? false : true,			
			success:function(data,statusText){
				if(!needLogin(data)){
					if(config.success){
						switch(dataType){						
						case "TEXT":
							config.success(data,statusText);	
							break;	
						case "JSON":
						default:
							config.success(TWJSON.decode(data),statusText);							
						}
					}
				}
			},
			error:function(xhr,status,thrown){
				if(config.error){
					config.error(xhr,status,thrown);
				}else if(config.faliure){
					config.failure(xhr);
				}else{
					alert('Status:' + status + "Error:" + thrown);
				}
			}

		});

	}

	function needLogin(data){
		if(/^\s*<\s*html/gim.test(data)){
			
			window.top.location = getWebRoot() + "/login.jsp";
			return true;
		}else if(0==data.indexOf("X-Requested-With-Error")){
			 alert(judgeText.substring("X-Requested-With-Error".length, data.length));			 
			 return true;
		}
		return false;

	}

	function encodeParams(params){
		var encodedParams = new Object();
		for(var param in params){
			encodedParams[param] = encodeURIComponent(params[param]);			
		}		
		
		return encodedParams;
	}

	function getRandomUrl(url){
		var tempUrl;

		tempUrl = "_sed_=" +  (new Date()).valueOf();
		
		if(url.indexOf('?')>-1){
			url += ("&" + tempUrl);		
		}else {
			url += ("?" + tempUrl);
		}
		return url;
	}

	var getWebRoot = function(){
		var rootPath = "";
		if(window.globalWebRoot){
			rootPath = window.globalWebRoot;
		}else{
			var href = window.document.location.href;
			var path = window.document.location.pathname;
			var pos = href.indexOf(path);
			var prePath = href.substring(0,pos);
			var postPath = path.substring(0,path.substr(1).indexOf('/')+1);
			rootPath = prePath = postPath
		}
		
		return rootPath;
	}

	exports.getWebRoot = getWebRoot;


	var TWJSON = new (function(){
		var sb = [];
	    var _dateFormat = null;
	    var useHasOwn = !!{}.hasOwnProperty,
        	replaceString = function (a, b) {
	            var c = m[b];
	            if (c) {
    	            return c;
	            }
        	    c = b.charCodeAt();
            	return "\\u00" + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
        	},

        	doEncode = function (o, field) {

	            if (o === null) {
	                sb[sb.length] = "null";
	                return;
	            }
	            var t = typeof o;
	            if (t == "undefined") {
	                sb[sb.length] = "null";
	                return;
	            } else if (o.push) {    

	                sb[sb.length] = '[';
	                var b, i, l = o.length, v;
	                for (i = 0; i < l; i += 1) {
	                    v = o[i];
	                    t = typeof v;
	                    if (t == "undefined" || t == "function" || t == "unknown") {
	                    } else {
	                        if (b) {
	                            sb[sb.length] = ',';
	                        }
	                        doEncode(v);

	                        b = true;
	                    }
	                }
	                sb[sb.length] = ']';
	                return;
	            } else if (o.getFullYear) {
	                if (_dateFormat) {
	                    sb[sb.length] = '"';
	                    if (typeof _dateFormat == 'function') {
	                        sb[sb.length] = _dateFormat(o, field);
	                    } else {
	                        sb[sb.length] = mini.formatDate(o, _dateFormat);
	                    }
	                    sb[sb.length] = '"';
	                } else {
	                    var n;
	                    sb[sb.length] = '"';
	                    sb[sb.length] = o.getFullYear();
	                    sb[sb.length] = "-";
	                    n = o.getMonth() + 1;
	                    sb[sb.length] = n < 10 ? "0" + n : n;
	                    sb[sb.length] = "-";
	                    n = o.getDate();
	                    sb[sb.length] = n < 10 ? "0" + n : n;
	                    sb[sb.length] = "T"
	                    n = o.getHours();
	                    sb[sb.length] = n < 10 ? "0" + n : n;
	                    sb[sb.length] = ":"
	                    n = o.getMinutes();
	                    sb[sb.length] = n < 10 ? "0" + n : n;
	                    sb[sb.length] = ":"
	                    n = o.getSeconds();
	                    sb[sb.length] = n < 10 ? "0" + n : n;
	                    sb[sb.length] = '"';
	                }
	                return;
	            } else if (t == "string") {
	                if (strReg1.test(o)) {
	                    sb[sb.length] = '"';

	                    sb[sb.length] = o.replace(strReg2, replaceString);
	                    sb[sb.length] = '"';
	                    return;
	                }
	                sb[sb.length] = '"' + o + '"';
	                return;
	            } else if (t == "number") {
	                sb[sb.length] = o;
	                return;
	            } else if (t == "boolean") {
	                sb[sb.length] = String(o);
	                return;
	            } else {    
	                sb[sb.length] = "{";
	                var b, i, v;
	                for (i in o) {
	                    
	                    if (!useHasOwn || Object.prototype.hasOwnProperty.call(o, i)) {
	                    
	                        v = o[i];
	                        t = typeof v;
	                        if (t == "undefined" || t == "function" || t == "unknown") {
	                        } else {
	                            if (b) {
	                                sb[sb.length] = ',';
	                            }
	                            doEncode(i);
	                            sb[sb.length] = ":";
	                            doEncode(v, i)

	                            b = true;
	                        }
	                    }
	                }
	                sb[sb.length] = "}";
	                return;
	            }
	        },

	        m = {
	            "\b": '\\b',
	            "\t": '\\t',
	            "\n": '\\n',
	            "\f": '\\f',
	            "\r": '\\r',
	            '"': '\\"',
	            "\\": '\\\\'
	        },
	        strReg1 = /["\\\x00-\x1f]/,
	        strReg2 = /([\x00-\x1f\\"])/g;

	    this.encode = function () {
	        var ec;
	        return function (o, dateFormat) {
	            sb = [];

	            _dateFormat = dateFormat;
	            doEncode(o);

	            _dateFormat = null;

	            return sb.join("");
	        };
	    } ();
    	this.decode = function () {
	        var dateRe1 = /^(\d{4})-(\d{2})-(\d{2})[T ](\d{2}):(\d{2}):(\d{2}(?:\.*\d*)?)Z*$/;
	        
	        var dateRe2 = new RegExp('^\/+Date\\((-?[0-9]+)\.*\\)\/+$', 'g');

	        var re = /[\"\'](\d{4})-(\d{2})-(\d{2})[T ](\d{2}):(\d{2}):(\d{2})[\"\']/g;
	        return function (json, parseDate) {
	            if (json === "" || json === null || json === undefined) return json;

	            if (typeof json == 'object') {  
	                json = this.encode(json);
	            }

	            function evalParse(json) {
	                if (parseDate !== false) {
	                    json = json.replace(__js_dateRegEx, "$1new Date($2)");
	                    json = json.replace(re, "new Date($1,$2-1,$3,$4,$5,$6)");
	                    json = json.replace(__js_dateRegEx2, "new Date($1)");
	                }
	                return eval('(' + json + ')');
	            }


	            var data = null;


	            if (window.JSON && window.JSON.parse) {

	                var dateReviver = function (key, value) {
	                    if (typeof value === 'string' && parseDate !== false ) {
	                        
	                        dateRe1.lastIndex = 0;
	                        var a = dateRe1.exec(value);
	                        if (a) {
	                            value = new Date(a[1], a[2] - 1, a[3], a[4], a[5], a[6]);

	                            return value;
	                        }
	                        
	                        dateRe2.lastIndex = 0;
	                        var a = dateRe2.exec(value);
	                        if (a) {
	                            value = new Date(parseInt(a[1]));

	                            return value;
	                        }
	                    }
	                    return value;
	                };
	                
	                try {
	                    var json2 = json.replace(__js_dateRegEx, "$1\"\/Date($2)\/\"");
	                    data = window.JSON.parse(json2, dateReviver);
	                } catch (ex) {
	                    data = evalParse(json);
	                }

	            } else {

	                data = evalParse(json);
	            }
	            return data;
	        };
   		 } ();
	})();
	var __js_dateRegEx = new RegExp('(^|[^\\\\])\\"\\\\/Date\\((-?[0-9]+)(?:[a-zA-Z]|(?:\\+|-)[0-9]{4})?\\)\\\\/\\"', "g");
	var __js_dateRegEx2 = new RegExp('[\"\']\/Date\\(([0-9]+)\\)\/[\"\']', 'g');
	exports.encode = TWJSON.encode;
	exports.decode = TWJSON.decode;

	exports.log = function(obj){
		if(window.console){
			if(window.console.debug){
				window.console.debug(obj);
			}else if(window.console.log){
				window.console.log(obj);
			}
		}
	}

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
	exports.browser = SysBrowser;
});