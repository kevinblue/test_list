//JsonUtil实现字符串与对象之间的转化
var JsonUtil = new (function(){
        this.pad = function(n) {
            return n < 10 ? "0" + n : n;
        },
        this.doDecode = function(json){
        	try{
        		return eval("(" + json.replace(/\r/gi,"\\r").replace(/\n/gi,"\\n") + ")");  
        	}catch(e){
        		//document.body.innerHTML=json;
        		alert(json);
        	}
            return  eval("(" + json.replace(/\r/gi,"\\r").replace(/\n/gi,"\\n") + ")");    
        },
        this.decode=this.doDecode;
        this.doEncode = function(o){
            if(typeof o == "undefined" || o === null){
                return "null";
            }else if(Object.prototype.toString.apply(o) === '[object Array]'){
                return this.encodeArray(o);
            }else if(Object.prototype.toString.apply(o) === '[object Date]'){
                return this.encodeDate(o);
            }else if(typeof o == "string"){
                return this.encodeString(o);
            }else if(typeof o == "number"){
            	return this.encodeString(o).replace(/\"/g,"");
                //return isFinite(o) ? String(o) : "null";
            }else if(typeof o == "boolean"){
            	return this.encodeString(o).replace(/\"/g,"");
                //return String(o);
            }else {
                var a = ["{"], b, i, v;
                for (i in o) {
                	
                	var isOwnPropery = false;
                	try{isOwnPropery = o.hasOwnProperty(i);}catch(e){isOwnPropery = false;}
                    if(isOwnPropery) {
                        v = o[i];
                        switch (typeof v) {
                        case "undefined":
                        case "function":
                        case "unknown":
                            break;
                        default:
                            if(b){
                                a.push(',');
                            }
                            a.push(this.doEncode(i), ":",
                                    v === null ? "null" : this.doEncode(v));
                            b = true;
                        }
                    }
                }
                a.push("}");
                return a.join("");
            }    
        },
        this.m = {
            "\b": '\\b',
            "\t": '\\t',
            "\n": '\\n',
            "\f": '\\f',
            "\r": '\\r',
            '"' : '\\"',
            "\\": '\\\\'
        },
        this.encodeString = function(s){
        	var $me = this;
            if (/["\\\x00-\x1f]/.test(s)) {
                return '"' + s.replace(/([\x00-\x1f\\"])/g, function(a, b) {
                    var c = $me.m[b];
                    if(c){
                        return c;
                    }
                    c = b.charCodeAt();
                    return "\\u00" +
                        Math.floor(c / 16).toString(16) +
                        (c % 16).toString(16);
                }) + '"';
            }
            return '"' + s + '"';
        },
        this.encodeArray = function(o){
            var a = ["["], b, i, l = o.length, v;
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

    this.encodeDate = function(o){
        return '"' + o.getFullYear() + "-" +
                this.pad(o.getMonth() + 1) + "-" +
                this.pad(o.getDate()) + "T" +
                this.pad(o.getHours()) + ":" +
                this.pad(o.getMinutes()) + ":" +
                this.pad(o.getSeconds()) + '"';
    };
    /**
     * Encodes an Object, Array or other value
     * @param {Mixed} o The variable to encode
     * @return {String} The JSON string
     */
    this.encode = function(o) {
    	return this.doEncode(o);
    };
})();