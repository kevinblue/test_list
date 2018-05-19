//IE遮罩
function tracywindyLoadMask(renderTo,msg,config){
   
   this.show = function(){
	   if(!this.createdObj) {
		   this.createdObj = new createTracywindyLoadMaskObj(renderTo,msg,config);
	   }  
	   this.createdObj.show();
   };
   this.hide = function(){
	   if(this.createdObj){
		   this.createdObj.hide();
	   }
   };
}
function createTracywindyLoadMaskObj(renderTo,msg,config){ 
	   config = config||{};
	   this.objectType = "loadmask";
	   this.id = config.id||GenerateGuid();
	   tracywindyObject[this.id]=this;
	   var sWidth,sHeight;  //背景遮罩的宽度和高度
	   var msgw = config.width||200;//消息框的宽度
	   var msgh = config.height||30;//Height //消息框的高度
	   var renderObj = null;

	   if(typeof(renderTo)=='object')
	   {
		   renderObj = renderTo;
	   }
	   else
	   {
		   renderObj = document.getElementById(renderTo);
	   }
	   this.renderObj = renderObj;
	   sWidth=Math.max(renderObj.clientWidth,renderObj.offsetWidth); 
	   sHeight=Math.max(renderObj.clientHeight,renderObj.offsetHeight);
	   var tempCurrentClientWidth = renderObj.clientWidth==0?sWidth:renderObj.clientWidth;
	   var tempCurrentClientHeight = renderObj.clientHeight==0?sHeight:renderObj.clientHeight;
	   if(renderObj.tagName=='BODY')
	   {
	        tempCurrentClientWidth =  document.documentElement.clientWidth;//Math.max(document.documentElement.clientWidth,document.body.clientWidth);
	        tempCurrentClientHeight = document.documentElement.clientHeight;//Math.max(document.documentElement.clientHeight,document.body.clientHeight);
	        sWidth = tempCurrentClientWidth;//Math.max(Math.max(sWidth,document.body.scrollWidth),document.documentElement.scrollWidth);
	        sHeight = tempCurrentClientHeight;//Math.max(Math.max(sHeight,document.body.scrollHeight),document.documentElement.scrollHeight);
	        if(config.isClient)
	        {
	        	sWidth  = tempCurrentClientWidth+17;
	        	sHeight = tempCurrentClientHeight;
	        }
	   }
	   else
	   {
		   sWidth+=1;
	   }
	   var bgObj=document.createElement("div"); 
	   this.bgObj = bgObj;
	   bgObj.className="x-panel-loadmask-background-div";
	   var t = 0;
	   var l = 0;
	   var e = renderObj;
	  /* while(e)
	   {
	  	  t+=e.offsetTop;
	  	  l+=e.offsetLeft;
	  	  e=e.offsetParent;
	   }*/
	   while(e)
	   {
	  	  t+=e.offsetTop;
	  	  l+=e.offsetLeft;
	  	  //t-=e.scrollTop;
	  	  //l-=e.scrollLeft;
	  	  e=e.offsetParent;
	   }
	   if(document.body)
	   {
	  	 l+=Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
	  	 t+=Math.max(document.body.scrollTop,document.documentElement.scrollTop);
	   }
	   bgObj.style.top=t+"px"; 
	   bgObj.style.left=l+"px"; 
	   bgObj.style.width=((sWidth-1)>0?(sWidth-1):0) + "px"; 
	   bgObj.style.height=((sHeight-1)>0?(sHeight-1):0)+ "px"; 
	   document.body.appendChild(bgObj); 
	   
	   var msgObj=document.createElement("div"); 
	   this.msgObj = msgObj;
	   msgObj.className = "x-panel-loadmask-msg-background-div";
	   msgObj.style.left = ((tempCurrentClientWidth-msgw)/2 -1) + "px"; 
	   msgObj.style.top = ((tempCurrentClientHeight-msgh)/2 -1)+ "px";
	   msgObj.style.width = msgw + "px"; 
	   msgObj.style.height= msgh + "px"; 
	   this.bgObj.appendChild(msgObj); 
	   this.constHeight = ((tempCurrentClientHeight-msgh)/2 -1);
	   this.constWidth = ((tempCurrentClientWidth-msgw)/2 -1);
	   var txt=document.createElement("p"); 
	   this.txt = txt;
	   txt.className = "x-panel-loadmask-msg-txt";
	   /*var txtMsgImg = document.createElement("img");
	   with(txtMsgImg)
	   {
		   className = "x-panel-loadmask-msg-txt-img";
		   src = getRootPath()+"/images/loading.gif";
	   }
	   txt.appendChild(txtMsgImg);*/
	   /*var txtMsgTextNode = document.createTextNode(msg);
	   txt.appendChild(txtMsgTextNode);*/
	   txt.innerHTML = msg;
	   txt.style.marginTop=(msgh-20)/2+"px";
	   msgObj.appendChild(txt); 
      //创建loader
      /*var loaderDiv=document.createElement("div");
      loaderDiv.className = "x-panel-loadmask-progress-container-div";
	  loaderDiv.style.left = ((msgw*0.1/2)-1)+"px";
      msgObj.appendChild(loaderDiv); 
      var progressDiv=document.createElement("div");
      progressDiv.className = "x-panel-loadmask-progress-div";
	  this.progress = progressDiv;
	  loaderDiv.appendChild(progressDiv);*/
      var $me = this;
	  /*this.animate = function(){
	        var elem = $me.progress;
			if(elem != null) 
			{ 
				if ($me.pos==0) $me.len += $me.dir; 
				if ($me.len > 25 || $me.pos>(0.9*msgw-26)) $me.pos += $me.dir; 
				if ($me.pos>(0.9*msgw-26)) $me.len -= $me.dir; 
				if ($me.pos>(0.9*msgw-26) && $me.len==0) $me.pos=0; 
				 elem.style.left = $me.pos+"px"; 
				 elem.style.width = $me.len+"px"; 
			} 
	   };*/
	   this.bgObj.style.display='none';
	   this.show=function(){
	       /*if($me.progressRun)
			{
			   window.clearInterval($me.progressRun); 
			}
		   $me.pos=0; 
		   $me.dir=2; 
		   $me.len=0;
	       $me.progressRun= window.setInterval($me.animate,20); */
		   $me.bgObj.style.display='block';
		   //$me.bgObj.style.display='block';
		   removeClass($me.bgObj,"x-panel-loadmask-hide");
		   return $me;
	   };
	  this.hide=function(){
	      /*if($me.progressRun)
		  {
	         window.clearInterval($me.progressRun); 
		  }*/
		  $me.bgObj.style.display='none'; 
		  addClass($me.bgObj,"x-panel-loadmask-hide"); 
		  return $me;
	  };
	  var remainFunc = renderObj.onscroll; 
	  if(renderObj.tagName=='BODY')
	  {
		window.onscroll=function(){
			 if(remainFunc)
			 {
				remainFunc();
			 }
			 msgObj.style.left = ($me.constWidth +Math.max(document.body.scrollLeft,document.documentElement.scrollLeft))+"px"; 
	         msgObj.style.top = ($me.constHeight + Math.max(document.body.scrollTop,document.documentElement.scrollTop))+ "px";
		 };
	  }
} 

var tenwa_loadMask = null;
if(typeof(loadMask) != 'undefined'){
	if(loadMask){
		tenwa_loadMask = loadMask;
	}
}

function showLoadMask(){
	if(!tenwa_loadMask){
		tenwa_loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	}
	if(tenwa_loadMask){
		tenwa_loadMask.show();
	}
}
function hideLoadMask(){
	if(tenwa_loadMask){
		tenwa_loadMask.hide();
	}
}