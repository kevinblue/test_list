<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<style type="text/css">
<!--
#id_image_workflowAttachmentListContainer .img-box{
	border: 2px solid silver;
	width: 165px;
	height: 160px;
	overflow: hidden;
	margin-bottom: 3px;
	text-align: center;
	cursor: pointer;
}

#id_image_workflowAttachmentListContainer .img-hover{
	border: 2px solid orange;
}
#id_image_workflowAttachmentListContainer .img-unhover{
	border: 2px solid silver;
}
#id_image_workflowAttachmentListContainer .img-click{
	border: 2px solid red;
}
-->
</style>

<script type="text/javascript">

	var imageMap = {};
	var currentImageId = "";
	
	// 点击左侧图片列表时把图片展示到右侧
	function __showImageById(id){
		if(id){
			var appImage = imageMap[id];
			if(appImage){
				currentImageId = id;
				var box = jQuery("#id_image_workflowAttachmentContainerDetail");
				var imageHeight = appImage.imageheight;
				var imageWidth = appImage.imagewidth;
				var heightRatio = decimal(imageHeight/box.height(),3);//高度缩放比例，适应div宽高，以把整个图片展示出来
				var widthRatio = decimal(imageWidth/box.width(),3);//宽度缩放比例，适应div宽高，以把整个图片展示出来
				var ratio = heightRatio > widthRatio ? heightRatio : widthRatio;//取较大的缩放比例
				var $image = jQuery("<img/>");
				$image.attr("id", appImage.id);
				$image.attr("height", imageHeight/ratio);
				$image.attr("width", imageWidth/ratio);
				$image.attr("imageHeight", imageHeight);
				$image.attr("imageWidth", imageWidth);
				$image.attr("src", appImage.imagepath);
				$image.attr("alt", appImage.title);
				$image.attr("thumbUrl", appImage.thumbimagepath);
				box.html('');
				box.append($image);
				mini.get('panelRight').setTitle(workFlowlocale['PictureDetails'] + appImage.title + "）");
			}
		}
	}

	// 根据单个图片数据获取一个document
	function createAttachmentImageDiv(appImage){
		var $div = jQuery('<div/>');
		if(appImage){
			$div.addClass("img-box");
			$div.attr("imageid", appImage.id);
			var $image = jQuery("<img/>");
			$image.attr("height", "160px");
			$image.attr("id", appImage.id);
			$image.attr("realUrl", appImage.imagepath);
			$image.attr("alt", appImage.title);
			$image.attr("src", appImage.thumbimagepath);
			$image.appendTo($div);
			$div.on('click',(function(id){
				return function(){
					$div.parent().find('div').removeClass('img-click');
					$div.addClass('img-click');
					__showImageById(id);
				};
			})(appImage.id));
			$div.hover((function(id){
				return function(){
					var box = jQuery('#id_image_workflowAttachmentListContainer');
					box.find('div[imageid="' + id + '"]').addClass('img-hover').removeClass('img-unhover');
				};
			})(appImage.id),(function(id){
				return function(){
					var box = jQuery('#id_image_workflowAttachmentListContainer');
					box.find('div[imageid="' + id + '"]').addClass('img-unhover').removeClass('img-hover');
				};
			})(appImage.id));
		}
		return $div;
	}

	function __loadWorkflowAttachmentImage() {
		var url = getRootPath() + '/table/getTableData.action';
		var docId= __getDocId();
		var t = new Date().getTime();
		var params = {"docid": docId, xmlFileName:'/jbpm/queryWorkflowAttachmentImages.xml',t : t};
		mini.mask({
		    el: document.body,
		    cls: 'mini-mask-loading',
		    html: workFlowlocale['shadeInfo']
		});
		tenwa.ajaxRequest({
	        url:url,
	        params:params,
	        method:'post',
	        success:function(res){
	        	debugger;
	            var result = JsonUtil.decode(res.responseText);
			    var datas = result['datas'];
			    if(datas && datas.length > 0){
			    	jQuery("#id_image_workflowAttachmentListContainer").html('');
			    	jQuery.each(datas, function(index, appImage){
			    		var img = createAttachmentImageDiv(appImage);
			    		debugger;
			    		if(img){
				    		jQuery("#id_image_workflowAttachmentListContainer").append(img);
				    		imageMap[appImage.id] = appImage;
			    		}
			    	});
			    } else {
			    	jQuery("#id_image_workflowAttachmentContainer").html('');
			    	jQuery("#id_image_workflowAttachmentListContainer").html('');
			    }
			    mini.unmask(document.body);
	        },
	        failure:function(res){
	        	mini.unmask(document.body);
	        	mini.alert(workFlowlocale['GetPictureFail']);
	        }
	    });
	}
	
	function uploadSuccess(appImage){
		mini.unmask(document.body);
		if(appImage.error){
			mini.alert(workFlowlocale['UploadPicFail']);
		} else {
			var img = createAttachmentImageDiv(appImage);
    		jQuery("#id_image_workflowAttachmentListContainer").prepend(img);
    		imageMap[appImage.id] = appImage;
    		mini.alert(workFlowlocale['UploadPicSucess']);
    		mini.get('uploadWindow').hide();
		}
	}
	
	function __uploadImage(){
		var form1 = document.getElementById("uploadImageForm");
		form1.reset();
		mini.get('uploadWindow').show();
	}
	
	function __cancelUpload(){
		mini.get('uploadWindow').hide();
	}
	
	function __submitForm() {
	    var form1 = document.getElementById("uploadImageForm");
	    mini.mask({
		    el: document.body,
		    cls: 'mini-mask-loading',
		    html: workFlowlocale['PictureShadeInfo'] 
		});
	    form1.submit();
	}
	
	function __deleteImage(){
		if(currentImageId){
			mini.confirm(workFlowlocale['removePicInfo'] +"？",workFlowlocale['remove']+'？',function(action){
				if(action == 'ok'){
					mini.mask({
					    el: document.body,
					    cls: 'mini-mask-loading',
					    html: workFlowlocale['removeShadeInfo']
					});
					tenwa.ajaxRequest({
				        url:"acl/removeAppImage.acl",
				        params:{id:currentImageId},
				        method:'post',
				        success:function(res){
				        	var result = JsonUtil.decode(res.responseText);
						    mini.unmask(document.body);
				        	if(result.error){
				        		mini.alert(workFlowlocale['RemovePicFail']);
				        	} else {
				        		mini.alert(workFlowlocale['RemovePicSucess'] );
				        		var box1 = jQuery('#id_image_workflowAttachmentListContainer');
								box1.find('div[imageid="' + currentImageId + '"]').remove();
								var box2 = jQuery('#id_image_workflowAttachmentContainerDetail');
								box2.find('img').remove();
				        		currentImageId = '';
				        	}
				        },
				        failure:function(res){
				        	mini.unmask(document.body);
				        	mini.alert(workFlowlocale['RemovePicFail']);
				        }
				    });
				}
			});
		}
	}
	function __makeImageBig(){
		if(currentImageId){
			var img = jQuery('#id_image_workflowAttachmentContainerDetail').find('img');
			img.attr('width',img.attr('width')*1.1);
			img.attr('height',img.attr('height')*1.1);
		}
	}
	function __makeImageSmale(){
		if(currentImageId){
			var img = jQuery('#id_image_workflowAttachmentContainerDetail').find('img');
			img.attr('width',img.attr('width')/1.1);
			img.attr('height',img.attr('height')/1.1);
		}
	}
	function __originalImage(){
		if(currentImageId){
			var img = jQuery('#id_image_workflowAttachmentContainerDetail').find('img');
			img.attr('width',imageMap[currentImageId].imagewidth);
			img.attr('height',imageMap[currentImageId].imageheight);
		}
	}
	function __defaultImage(){
		if(currentImageId){
			var box = jQuery('#id_image_workflowAttachmentContainerDetail');
			var img = box.find('img');
			var imageHeight = img.attr('imageHeight');
			var imageWidth = img.attr('imageWidth');
			var heightRatio = decimal(imageHeight/box.height(),3);//高度缩放比例，适应div宽高，以把整个图片展示出来
			var widthRatio = decimal(imageWidth/box.width(),3);//宽度缩放比例，适应div宽高，以把整个图片展示出来
			var ratio = heightRatio > widthRatio ? heightRatio : widthRatio;//取较大的缩放比例
			img.attr('height', imageHeight/ratio);
			img.attr('width', imageWidth/ratio);
		}
	}
	
	jQuery(function(){
		jQuery('#uploadImageDocId').val(__getDocId());
		mini.parse();
	});
	
	/*var checkAttachmentInfoFunc = function(){
		return true;
	};*/
</script>

<div id="panelLeft" class="mini-panel" maskOnLoad="false" title='<spring:message code="PicList" text="附件列表"/>' style="position: relative;float: left;">
	<%-- <div style="position: fixed;margin-top: -30px;width: 100%;text-align: right;">
		 <a class="mini-button" onclick="__uploadImage" iconCls="icon-upload"><spring:message code="UploadPicture" text="上传图片"/></a> 
	</div> --%>
	<div id="id_image_workflowAttachmentListContainer" style="width: 100%;">
		<!-- 图片列表 -->
	</div>
</div>
<div id="panelRight" class="mini-panel" maskOnLoad="false" title="<spring:message code="AttachmentDetail" text="附件明细"/>" style="position: relative;float: right;">
	<div style="position: fixed;margin-top: -31px;width: 100%;text-align: right;">
		<a class="mini-button" onclick="__deleteImage" iconCls="icon-remove"><spring:message code="RemovePicture" text="删除图片"/></a> 
		<a class="mini-button" onclick="__makeImageBig" iconCls="icon-zoomin"><spring:message code="EnlargPicture" text="放大图片"/></a>
		<a class="mini-button" onclick="__makeImageSmale" iconCls="icon-zoomout"><spring:message code="LessenPicture" text="缩小图片"/></a>
		<a class="mini-button" onclick="__originalImage" iconCls="icon-tip"><spring:message code="OrinalSize" text="原始尺寸"/></a>
		<a class="mini-button" onclick="__defaultImage" iconCls="icon-reload"><spring:message code="DefaultSize" text="默认尺寸"/></a>
	</div>
	<div id="id_image_workflowAttachmentContainerDetail" style="text-align: center;">
		<!-- 图片明细 -->
	</div>
</div>

<iframe name="file_upload_submit_return_frame" width="100%" style="display: none;"></iframe>

<div id="uploadWindow" class="mini-window" title="<spring:message code="UploadDetail" text="上传信息"/>" style="width: 550px;height: 160px;display: none;">
	<form id="uploadImageForm" enctype="multipart/form-data" target="file_upload_submit_return_frame" method="post" action="acl/addAppImage.acl">
		<input id="uploadImageDocId" name="docid" value="" type="hidden">
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width: 100%;">
			<tr class="tr-even">
				<td class="td-content-title"><spring:message code="PictureHeadline" text="图片标题"/>:</td>
				<td class="td-content">
					<input type="text" name="title" style="width:405px;border: 1px solid #ccc;height: 23px;line-height: 25px;"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title"><spring:message code="PictureFile" text="图片文件"/>:</td>
				<td class="td-content">
					<input type="file" name="name_upload_file_name" style="width:407px;border: 1px solid #ccc;height: 25px;line-height: 25px;"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content" colspan="2" style="text-align: center;">
					<a class="mini-button" onclick="__submitForm" iconCls="icon-ok"><spring:message code="Confirm" text="确定"/></a>
					<a class="mini-button" onclick="__cancelUpload" iconCls="icon-cancel"><spring:message code="Cancel" text="取消"/></a>
				</td>
			</tr>
		</table>
	</form>
</div>


<script type="text/javascript">
	var pageWidth = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight;
	
	jQuery("#panelLeft").css("width", "200px");
	jQuery("#panelRight").css("width", (pageWidth - 230) + "px");
	jQuery("#panelLeft>div:first").css("width", "190px");
	jQuery("#panelRight>div:first").css("width", (pageWidth - 240) + "px");
	
	jQuery("#panelLeft").css("height", (pageHeight - 105) + "px");
	jQuery("#panelRight").css("height", (pageHeight - 105) + "px");

	jQuery("#panelLeft").css("height", (pageHeight - 105) + "px");
	jQuery("#panelRight").css("height", (pageHeight - 105) + "px");
	
	jQuery("#id_image_workflowAttachmentListContainer").css("height", (pageHeight - 145) + "px");
	jQuery("#id_image_workflowAttachmentContainerDetail").css("height", (pageHeight - 145) + "px");
</script>