<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>



<script type="text/javascript">
		function updateIrrExcel(config){
			config.id=config.id ||"id_uploadfile";
			config.title=config.title||"上传文件"; //标题 
			config.url=config.url||"";//url
			config.jscallback=config.jscallback||"";//回调
			config.parames=config.parames||{};
			config.modelname=config.modelname||"财务报表";
			config.browserType=SysBrowser.getBrowser().toLowerCase();
			createFileAndShow(config);
		}
		
		function createFileAndShow(config){
			var $form = jQuery("#" + config.id);
			if (0 < $form.length) {
				document.body.removeChild($form[0]);
			}
			var importExcelUrl = getRootPath()+config.url;
			var uploadAttachmentFileWindow_html = "";
			uploadAttachmentFileWindow_html += '<div id="'+config.id+'" class="mini-window"  closed="true" modal="true" title="'+config.title+'" style="display:none;width:400px;text-align:center;background-color:#FDF9F8;">';
			uploadAttachmentFileWindow_html += '	<center>';
			uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
			uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
			uploadAttachmentFileWindow_html += '			<form id="'+config.id+'Form" action="'+importExcelUrl+'" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
			uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%"><tr><td colspan=2>';
			uploadAttachmentFileWindow_html +=getParametetoStr(config);
			uploadAttachmentFileWindow_html += "</td></tr>";
			uploadAttachmentFileWindow_html += '					<tr><td colspan=2><input type="file" id="id_tableImportTemplate_irr_calculate" name="tableImportExcel" style="width:350px;border:1px solid #DDD;cursor:pointer;"></td></tr>';
			uploadAttachmentFileWindow_html += '					<tr class="content-separator">';
			uploadAttachmentFileWindow_html += '						<td colspan="2" align="center">';
			uploadAttachmentFileWindow_html += '							<a href="javascript:void(0);" id="'+config.id+'_save" class="mini-button" iconCls="icon-add"  onclick=\'javascript:document.getElementById("'+config.id+'Form").submit();\'><span class="mini-button-text">确定</span></a>';
			uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;" id="'+config.id+'_cancle" href="javascript:void(0);" class="mini-button" iconCls="icon-close"  onclick=\'javascript:mini.get("'+config.id+'").hide();\'><span class="mini-button-text" >取消</span></a>';
			uploadAttachmentFileWindow_html += '						</td>';
			uploadAttachmentFileWindow_html += '					</tr>';
			uploadAttachmentFileWindow_html += '				</table>';    
			uploadAttachmentFileWindow_html += '			</form>';
			uploadAttachmentFileWindow_html += '		</div>';
			uploadAttachmentFileWindow_html += '	</center>';
			uploadAttachmentFileWindow_html += '</div>';
			$(document.body).append(uploadAttachmentFileWindow_html);
			mini.parse();
			var formid=config.id+"Form";
			var id=config.id;
			$("#"+config.id+"_save").bind('click',function(){
				var filename=$("#id_tableImportTemplate_irr_calculate").val();
				if(filename==""){alert("请选择上传的文件");return false;}
				mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'上传中...'});
				$("#"+formid).submit();});
			$("#"+config.id+"_cancle").bind('click',function(){mini.get(id).hide()});
			mini.get(config.id).show();
		}
		function getParametetoStr(config){
			var tempInnerHtml=""; 
			tempInnerHtml+= "<input type='hidden' name='browserType' value='" + config.browserType+ "'/>";
			tempInnerHtml+= "<input type='hidden' name='currentTableId' value='" + config.id+ "'/>";
			tempInnerHtml+= "<input type='hidden' name='jscallback' value='" + config.jscallback+ "'/>";
			tempInnerHtml+= "<input type='hidden' name='modelname' value='" + config.modelname+ "'/>";
			tempInnerHtml+= "<input type='hidden' name='_success' value='window.parent.irrCalculateSuccess'/>";
			tempInnerHtml+= "<input type='hidden' name='_failture' value='window.parent.irrCalculateFailture'/>";
		    for(var param in config.parames){
		      if (typeof (config.parames[param]) == 'object'){
		    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value='" + mini.decode(config.parames[param])+ "'/>";
		    	}else{
		    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value='" + config.parames[param]+ "'/>";	
		      }
		    }
		    return tempInnerHtml;
		}
		function irrCalculateSuccess( message){
			mini.unmask(document.body);
			var result = mini.decode(message);
			if(result.issucess == 'true'){
				$mini("leaseterm").setValue(result.leaseterm);//租赁期限
				var sender = mini.get("incomenumberyear");//租金支付类型
				var incomenumber = $minigetinputtext("incomenumber");//还租次数
				var grace = $minigetinputtext("grace")
				grace = isNaN(grace) ? 0 : grace;
				incomenumber = isNaN(incomenumber) ? 0 : incomenumber;
				var resultnumber = incomenumber + grace;
				if("income_1" == sender.value){
					$mini("leaseterm").setValue(resultnumber);
				}else if("income_3" == sender.value){
					$mini("leaseterm").setValue(resultnumber *3);
				}else if("income_6" == sender.value){
					$mini("leaseterm").setValue(resultnumber *6);
				}else if("income_12" == sender.value){
					$mini("leaseterm").setValue(resultnumber *12);
				}else if("income_2" == sender.value){
					$mini("leaseterm").setValue(resultnumber *2);
				}
				$mini("incomenumber").setValue(result.incomenumber);//还租次数
        		$mini("yearrate").setValue(result.yearrate);//年利率
            	$mini("enddate").setValue(result.enddate);//结束日期
            	
            	mini.get("fund_rent_plan_frame").isinitData=1;
            	
            	var fundrentplan =mini.decode(result.rentplanlist) ;
            	
            	mini.get("fund_rent_plan_frame").setData(fundrentplan);
            	//分别把租金计划  、现金流 、资金收付计划保存至隐藏域
            	jQuery("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
            	updateInputThousand();
            	mini.alert("测算成功！", "提示", function(){
					mini.get('irrExcelImportDiv_irr_cal').hide();
				});
			}else{
				mini.alert(result.message);
			}
		}
		function irrCalculateFailture( message){
			mini.unmask(document.body);
			mini.alert(message);
		}
</script>