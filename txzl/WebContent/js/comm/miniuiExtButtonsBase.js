	var ApTableBase = {
		tableAddOper : function(miniTable, columnsConfig, rowData, config) {
			miniTable.editFormItemOperFlag = "add";
			if (true == config.isRowEditing) {
				miniTable.addRow(rowData, 0);
				miniTable.commitEdit();
				miniTable.beginEditRow(rowData);
			} else {
				var columnsEditFormWindow = this.initColumnsEditFormWindowPanel(miniTable, columnsConfig, config);
				var editFormPopupWindowFormId = config.editFormId;
				var editFormItemOperFlagId = editFormPopupWindowFormId + "_editFormItemOperFlag";
				jQuery("#" + editFormItemOperFlagId).val("add");
				columnsEditFormWindow.showAtPos('center', config.top || 150);
				this.lazyRenderCombobox(config.comboRenders);
			}
		}
	};
