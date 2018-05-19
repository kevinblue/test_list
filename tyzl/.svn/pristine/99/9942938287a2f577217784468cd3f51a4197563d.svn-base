/**
.x-panel-loadmask-background-div
{
	position:absolute;
	background:#FFFFFF;
	opacity:0.6;
	z-index:10000;
	overflow:hidden;
}
.x-panel-loadmask-msg-background-div
{
   align:center;
   text-align:center;
   background:#FFFFFF;
   border:2px solid #FFFFFF;
   position:relative;
   font: 12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif;
   z-index:10001;
   color:#808080;
}
.x-panel-loadmask-msg-txt-img
{
	width:14px;
	height:14px;
	margin-right:5px;
}
.x-panel-loadmask-progress-container-div
{
	background-color:#F1EDED;
    position:relative;
    width:90%;
    text-align:center;
    font-size:1px;
    top:-10px;
}
.x-panel-loadmask-progress-div
{
	position:relative;
	top:-2px;
	left:0px;
	height:10px;
	width:1px;
	font-size:1px;
	background-color:#77A9E0;
}
 * **/
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
	return(prePath+postPath);
}
var tracywindyObject = {};
//IE遮罩
function tracywindyLoadMask(renderTo,msg,config){ 
	   config = config||{};
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
	        tempCurrentClientWidth = Math.max(document.documentElement.clientWidth,document.body.clientWidth);
	        tempCurrentClientHeight = Math.max(document.documentElement.clientHeight,document.body.clientHeight);
	        sWidth = Math.max(Math.max(sWidth,document.body.scrollWidth),document.documentElement.scrollWidth);
	        sHeight = Math.max(Math.max(sHeight,document.body.scrollHeight),document.documentElement.scrollHeight);
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
	   var txtMsgImg = document.createElement("img");
	   with(txtMsgImg)
	   {
		   className = "x-panel-loadmask-msg-txt-img";
		   src = getRootPath()+"/images/loading.gif";
	   }
	   txt.appendChild(txtMsgImg);
	   var txtMsgTextNode = document.createTextNode(msg);
	   txt.appendChild(txtMsgTextNode);
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
		   return $me;
	   };
	  this.hide=function(){
	      /*if($me.progressRun)
		  {
	         window.clearInterval($me.progressRun); 
		  }*/
		  $me.bgObj.style.display='none'; 
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