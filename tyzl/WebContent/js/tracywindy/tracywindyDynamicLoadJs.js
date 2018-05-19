(function(window){
	window.tracywindyDynamicLoadJs = function(jsLocation){
	    ajaxRequest({
	        url:jsLocation,
	        async:false,
	        success:function(response)
	        {    
	        	source = (response.responseText);
		        var oHead = document.getElementsByTagName("HEAD").item(0); 
		        var oScript = document.createElement( "script" ); 
		        oScript.language = "javascript"; 
		        oScript.type = "text/javascript"; 
		        oScript.defer = true; 
		        oScript.text = source; 
		        oHead.appendChild( oScript ); 
	        },
	        failure:function(response){ },
	        params:{}
	     });
	};
})(window);