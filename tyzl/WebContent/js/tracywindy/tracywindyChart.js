//fusioncharts 封装类
var tracywindyChart = function(config){       
         this.height = config.height;
         this.width  = config.width;
         this.chartHeight = config.height;
         this.chartWidth  = config.width;
         this.id     = config.id||GenerateGuid();
         tracywindyObject[this.id]= this;
         this.chartId = config.id + "_chart";
         this.xmlFileName = config.xmlFileName;
         this.params = config.params||{};
         this.border = config.border||false;
         this.url=config.url||(getRootPath()+'/fusioncharts/chart/getChartData.action?method=getChartData');
         this.swf    = config.swf; 
         this.renderTo = config.renderTo;
         this.title = config.title;
         this.createCompleted = false;
         this.createFrameObj();
         this.load();
     };
     tracywindyChart.prototype.setTitle=function(newTitle){
          if(this.titleObj){
        	  this.titleObj.innerHTML = newTitle;
          }
     };
     tracywindyChart.prototype.createFrameObj=function(){
         //初始化主组件
    	 if(typeof(this.renderTo)=='object')
    	 {
    		 this.mainObj = this.renderTo;
    	 }
    	 else
    	 {
             this.mainObj = document.getElementById(this.renderTo);
    	 }
         this.mainObj.style.width = (this.width +"px");
         this.mainObj.style.height = (this.height +"px");
         addClass(this.mainObj,"x-panel-chart-div-frame");
         //初始化标题组件
         if(this.title){
        	this.titleObj = document.createElement("div");
        	this.titleObj.style.width = (this.width - 2 )+"px";
        	this.titleObj.className = "x-panel-chart-div-title";
        	this.titleObj.innerHTML = this.title;
        	this.mainObj.appendChild(this.titleObj);
        	
        	this.exportDivContainer = document.createElement("span");
        	addClass(this.exportDivContainer,"x-panel-chart-div-title-export-container");
        	this.exportDivContainer.id = "exportContainer_"+this.chartId;
        	this.titleObj.appendChild(this.exportDivContainer);
         }
         
         //初始化图形组件
         this.chartObj = document.createElement("div");
         this.chartObj.className ="x-panel-chart-div-chart";
         if(this.border)
         {
        	 addClass(this.chartObj,"x-panel-chart-div-chart-border");
         }
         this.chartObj.id=this.chartId+"_container";
         this.mainObj.appendChild(this.chartObj);
         this.attachParam = '?a=fusioncharts.com';
         this.attachChartConfigure = { 
            	 "PBarLoadingText"       : "图形加载中,请稍等..." , 
            	 "XMLLoadingText"        : "图形加载中,请稍等...", 
            	 "ParsingDataText"       : "图形加载中,请稍等...", 
            	 "RenderingChartText"    : "图形加载中,请稍等...", 
            	 "LoadDataErrorText"     : "没有符合条件的数据", 
            	 "InvalidXMLText"        : "没有符合条件的数据", 
            	 "ChartNoDataText"       : "没有符合条件的数据"
         };
         //初始化标题组件
         if(this.title){
        	//设置图形高度
        	this.chartHeight = this.height-36;
         }
         this.chartObj.style.height=(this.chartHeight)+"px";
     };
     tracywindyChart.prototype.loadXml=function(){
    	 if(!this.loadMask)
    	 {
    		 this.loadMask = new tracywindyLoadMask(this.chartObj,"图形加载中  请稍等...");
    	 }
    	 this.loadMask.show();
    	 if(FusionCharts(this.chartId))
    	 {
    		 FusionCharts(this.chartId).dispose();
    	 }
    	 this.chart = new FusionCharts(getRootPath()+'/charts/swf/'+this.swf+this.attachParam,this.chartId,this.chartWidth,this.chartHeight, "0", "1", "FFFFFF",null); 
         var chart = this.chart;
         chart.configure(this.attachChartConfigure);
         var renderTo = this.chartObj;
         var params = this.params;
         var xmlFileName = this.xmlFileName;
         var $me = this;
         params['xmlFileName'] = xmlFileName;
         params['chartId'] = this.chartId;
         var tempTitle = "";
     	 if(this.titleObj){
     		tempTitle = this.titleObj.innerText||this.titleObj.textContent;// encodeURI(titleSpan.innerText||titleSpan.textContent);
         }
     	 params['chartTitle'] = tempTitle||" ";
    	 ajaxRequest({
             url:$me.url,
             method:'post',
             success:function(res){
    		       if(chart)
    		       {
    	    		   chart.initialDataSet=false;//关键
    	    		   $me.chartData_xml = res.responseText;
    	    		   chart.setTransparent(true);
    	          	   chart.setDataXML($me.chartData_xml);
    	          	   chart.render(renderTo);
    		       }
	          	   $me.loadMask.hide();
             },
             failure:function(res){},
             params:params
         });
     };
     tracywindyChart.prototype.setXmlFileName=function(xmlFileName){
    	 this.xmlFileName = xmlFileName;
     };
     tracywindyChart.prototype.setSwf=function(swf){
         this.swf = swf;
     };
     tracywindyChart.prototype.reload=function(){
         this.loadXml();
      };
     tracywindyChart.prototype.load=function(){
         this.loadXml();
     };
     tracywindyChart.prototype.setParam=function(name,value){
         this.params[name]=value;
     };
     tracywindyChart.prototype.setParams=function(params){
         for(var param in params)
         {
             this.params[param] = params[param];
         }
     };
     function FC_Rendered(chartId){
    	 var exportContainerId ="exportContainer_"+chartId;
    	 var exportDivContainer = document.getElementById(exportContainerId);
    	 if(exportDivContainer){
         	var myExportComponent = new FusionChartsExportObject("fcExporter_"+chartId, getRootPath()+"/charts/swf/FCExporter.swf");
             //Customize the component properties        
         	//Width and height        
         	myExportComponent.componentAttributes.width = '81';        
         	myExportComponent.componentAttributes.height = '21';        
         	//Background color       
         	//myExportComponent.componentAttributes.bgColor = '#F8F8F8';        
         	myExportComponent.componentAttributes.vMargin = '0';        
         	myExportComponent.componentAttributes.hMargin = '0';        
         	//Border properties        
         	//myExportComponent.componentAttributes.borderThickness = '0';        
         	//myExportComponent.componentAttributes.borderColor = '0372AB';        
         	//Font properties        
         	//myExportComponent.componentAttributes.fontFace = 'Arial';        
         	//myExportComponent.componentAttributes.fontColor = '#DDDDDD';        
         	//myExportComponent.componentAttributes.fontSize = '12';        
         	//Message - caption of export component        
         	myExportComponent.componentAttributes.showMessage = '0';        
         	myExportComponent.componentAttributes.message = 'Export the chart first, and then click on this button to save';        
         	//Button visual configuration        
         	myExportComponent.componentAttributes.btnWidth = '80';        
         	myExportComponent.componentAttributes.btnColor = 'E1f5ff';        
         	myExportComponent.componentAttributes.btnBorderColor = 'silver';        
         	//Button font properties        
         	myExportComponent.componentAttributes.btnFontFace = 'Verdana';        
         	myExportComponent.componentAttributes.btnFontColor = '#000000';        
         	myExportComponent.componentAttributes.btnFontSize = '12';        
         	//Title of button        
         	myExportComponent.componentAttributes.btnsavetitle     = '保存图表';        
         	myExportComponent.componentAttributes.btndisabledtitle = '导出图表'; 
         	myExportComponent.Render(exportDivContainer);
         	
         	var onMouseDownFunc = (function(chartId){
         		   return function(e){
         			   cancelBubble(e);
         			   var target  = getTarget(e);
         			   if(("fcExporter_"+chartId) == target.id){
             			   this.onmousedown = null;
             			   var chartObject = getChartFromId(chartId);            
             			   if(chartObject.hasRendered())chartObject.exportChart( {exportAtClient: '1',exportFormat:'JPG'} );  
         			   }
         		   };
         	})(chartId);
         	exportDivContainer.onmousedown=onMouseDownFunc;
    	 }
     }
     function FC_ExportReady(chartId){          
    	 var exportContainerId ="exportContainer_"+chartId;
    	 var exportDivContainer = document.getElementById(exportContainerId);
    	 if(exportDivContainer){
    		 exportDivContainer.onmousedown = null; 
    	 }
    }