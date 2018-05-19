/** 
* @author tracywindy 
* 
*/
(function($) {
    $.fn.recusionAllChildrenNodesParams = function(parentDom, params) {
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
                case "TEXTAREA":
                    {
                        this.setParamByDom(childNode, params);
                        break;
                    }
                default:
                    {
                        this.recusionAllChildrenNodesParams(childNode, params);
                    }
                }
            }
        } else {
            this.setParamByDom(parentDom, params);
        }
    };
    $.fn.setParamByDom = function(domNode, domArr) {
        var paramName = domNode.name;
        var paramValue = domNode.value;
        if (paramName && ("undefined" != typeof(paramValue))) {
            domArr.push(domNode);
        }
    };
    $.fn.extend({
        tracywindySerializeFormToJsonObject: function(allownNull, isEmitDot, encode) {
            /*if(this.length>1){  
	               return false;  
	           }  */
            //var arr=this.serializeArray();  
            var domObj = this[0];
            var arr = [];
            if ("FORM" == domObj.tagName) {
                arr = domObj.elements;
            } else {
                this.recusionAllChildrenNodesParams(domObj, arr);
            }
            var obj = new Object();
            //modify by tracywindy 2013-10-10 start
            var objArr = new Object();
            //modify by tracywindy 2013-10-10 end
            $.each(arr, function(k, v) {
                var name = v.name;
                var value = v.value;
                //modify by tracywindy 2013-10-10 start
                objArr[name] = objArr[name] || [];
                if (("INPUT" == v.tagName) && (("RADIO" == (v.getAttribute("type") || "").toUpperCase()) || ("CHECKBOX" == (v.getAttribute("type") || "").toUpperCase()))) {
                    if (v.checked) {
                        objArr[name].push(value);
                    }
                } else {
                    objArr[name].push(value);
                }
                var realValue = objArr[name].join(",");
                value = realValue;
                //modify by tracywindy 2013-10-10 end
                if (encode) {
                    value = escape(encodeURIComponent(value));
                }
                if (!name) return;
                if (!allownNull && ((null == value) || ('undefined' == typeof(value)) || ('' == value))) return;
                var currentObj = {};
                if ((name.indexOf(".") > -1) && (!isEmitDot)) {
                    var names = name.split(".");
                    var len = names.length;
                    for (var i = 0; i < len; i++) {
                        var attrName = names[i];
                        if (0 == i) {
                            if (!obj[attrName]) {
                                obj[attrName] = {};
                            }
                            currentObj = obj[attrName];
                        } else if ((len - 1) == i) {
                            currentObj[attrName] = value;
                        } else {
                            if (!currentObj[attrName]) {
                                currentObj[attrName] = {};
                            }
                            currentObj = currentObj[attrName];
                        }
                    }
                } else {
                    obj[name] = value;
                    //modify by tracywindy 2013-10-10 end
                    //obj[v.name]= v.value;
                    if (encode) {
                        //obj[v.name] = escape(encodeURIComponent(v.value));
                        obj[name] = escape(encodeURIComponent(value));
                    }
                }
            });
            return obj;
        }
    });
})(jQuery);