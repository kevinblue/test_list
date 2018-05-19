define(function(require,exports,module){
	bindEvent();

	exports.addReport = function(reportType){		
		var tree = mini.get('report_tree');
		var node = tree.getSelectedNode();
		if(!node){
			return;
		}
		var dialog = mini.get('id_form_report');
		var form = new mini.Form('#form_report');
		form.clear();
		$('#form_report #parentReport').val(node.id);		
		$('#form_report #reportType').val(reportType);
		dialog.show();
	}


	function bindEvent(){
		//Bind Report Form Event
		mini.get('report_saveButton').set({
			onclick:function(){
				var report = new Object();
				report.id = $('#form_report #id').val();
				report.parentReport = $('#form_report #parentReport').val();
				report.reportMenuRoot = $('#form_report #reportMenuRoot').val();
				report.name = $('#form_report #name').val();
				report.code = $('#form_report #code').val();
				report.reportType = $('#form_report #reportType').val();
				ajaxRequest({
				url: globalWebRoot + "/report/config/saveReport.action",
				params: report,
				method:'POST',
				success:function(res){
					var data = res.responseText;
					if(data != null){
						alert("保存成功");
						mini.get('id_form_report').hide();
						loadReportTree();
					}else{
						alert("保存失败");
					}
				}
			});
			}
		});
		mini.get('report_closeButton').set({
			onclick:function(){
				mini.get('id_form_report').hide();
			}
		});
	}
});