(function(window){
	var $ = window.jQuery;
	window['isInArray'] = function(obj,array){
		for(var i = 0 ; i < array.length ; i++){
			var flag  = false;
			if(obj == array[i]){
				flag =  true;
				break;
			}
		}
		return flag;
	}
	/**
	 * 树转表行的隐藏控制
	 * @param index 类型array,隐藏
	 * @param tableId 只读的table的id
	 */
	window['hiddenColumn'] = function(index,tableId){
		var trs  = $('#'+tableId).find('table tr');
		for(var i = 0 ; i < trs.length;i++){
			if(i > 0 ){
				var tds = jQuery(trs[i]).find('td');
				for(var j = 0 ; j < tds.length;j++){
					if(isInArray(j+1,index)){
						jQuery(tds[j]).hide();
					}
				}
			}
		}
	};
	
	
	
	/**
	 * 树转表列的隐藏控制
	 * @param index 类型array,隐藏
	 * @param tableId 只读的table的id
	 */
	window['hiddenRow'] = function(index,tableId){
		var trs  = $('#'+tableId).find('table tr');
		for(var i = 0 ; i < trs.length;i++){
			if(isInArray(i-1,index)){
				var tds = jQuery(trs[i]).find('td');
				for(var j = 0 ; j < tds.length;j++){
						jQuery(tds[j]).hide();
				}
			}
			
		}
	};
	
	/**
	 * 树转表列的只读控制
	 * @param index 类型array,只读的列
	 * @param tableId 只读的table的id
	 */
	window['readOnlyColumn'] = function(index,tableId){
		var trs  = $('#'+tableId).find('table tr');
		for(var i = 0 ; i < trs.length;i++){
			if(i > 1 ){
				var tds = jQuery(trs[i]).find('td');
				for(var j = 0 ; j < tds.length;j++){
					if(isInArray(j+1,index)){
						var input = jQuery(jQuery(tds[j]).find('input'));
						var textarea = jQuery(jQuery(tds[j]).find('textarea'));
						if(input && input.length > 0 ){
							var type = input.attr('type');
							var html = '';
							var checked = input.attr('checked');
							var value = input.attr('value');
							var dictId = input.attr('dictId');
							var columnId = input.attr('columnId');
							var name = input.attr('name');
							var styleStr = typeof(input.attr('style')) == 'undefined' ? '' :  input.attr('style');
							var className = "";
							if(type == 'checkbox'){
								className = "tree2table-checkbox";
							}else if(type == 'radio'){
								className = "tree2table-radio";
							}else if(type == "text"){
								html = "<label  value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'>"+value+"</label>";
							}else{
								return;
							}
							if(checked){
								className += '-checked';
							}
							if(type != "text"){
								html = "<label><span  style='"+styleStr+"' class='"+className+"' value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'></span></label>";
							}
							jQuery(tds[j]).html(html);
						}
						if(textarea && textarea.length > 0 ){
							var html = '';
							var value = typeof(textarea.attr('value')) == 'undefined' ? '' : textarea.attr('value');
							var dictId = textarea.attr('dictId');
							var columnId = textarea.attr('columnId');
							var name = textarea.attr('name');
							var styleStr = typeof(textarea.attr('style'))  == 'undefined' ? '' :  textarea.attr('style');
							html = "<label style='"+styleStr+"' value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'>"+value+"</label>";
							jQuery(tds[j]).html(html);
						}
					}
				}
			}
		}
	};
	
	/**
	 * 树转表行的只读控制
	 * @param index 类型array,只读的行
	 * @param tableId 只读的table的id
	 */
	window['readOnlyRow'] = function(index,tableId){
		var trs  = $('#'+tableId).find('table tr');
		for(var i = 0 ; i < trs.length;i++){
			if(isInArray(i-1,index)){
				var tds = jQuery(trs[i]).find('td');
				for(var j = 0 ; j < tds.length;j++){
					var input = jQuery(jQuery(tds[j]).find('input'));
					var textarea = jQuery(jQuery(tds[j]).find('textarea'));
					if(input && input.length > 0 ){
						var type = input.attr('type');
						var html = '';
						var checked = input.attr('checked');
						var value = input.attr('value');
						var dictId = input.attr('dictId');
						var columnId = input.attr('columnId');
						var name = input.attr('name');
						var styleStr = typeof(input.attr('style')) == 'undefined' ? '' :  input.attr('style');
						var className = "";
						if(type == 'checkbox'){
							className = "tree2table-checkbox";
						}else if(type == 'radio'){
							className = "tree2table-radio";
						}else if(type == "text"){
							html = "<label  value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'>"+value+"</label>";
						}else{
							return;
						}
						if(checked){
							className += '-checked';
						}
						if(type != "text"){
							html = "<label><span  style='"+styleStr+"' class='"+className+"' value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'></span></label>";
						}
						jQuery(tds[j]).html(html);
					}
					if(textarea && textarea.length > 0 ){
						var html = '';
						var value = typeof(textarea.attr('value')) == 'undefined' ? '' : textarea.attr('value');
						var dictId = textarea.attr('dictId');
						var columnId = textarea.attr('columnId');
						var name = textarea.attr('name');
						var styleStr = typeof(textarea.attr('style'))  == 'undefined' ? '' :  textarea.attr('style');
						html = "<label style='"+styleStr+"' value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'>"+value+"</label>";
						jQuery(tds[j]).html(html);
					}
				}
			}
		}
	};
	/**
	 * 树转表精确定位到某个td的隐藏
	 * @param rowIndex 第几行 columnIndex 第几列
	 * @param tableId 只读的table的id
	 */
	window['readOnlyRowColumn'] = function(rowIndex,columnIndex,tableId){
		var trs  = $('#'+tableId).find('table tr');
		for(var i = 0 ; i < trs.length;i++){
			if(i-1 == rowIndex){
				var tds = jQuery(trs[i]).find('td');
				for(var j = 0 ; j < tds.length;j++){
					if(j+1 == columnIndex){
						var input = jQuery(jQuery(tds[j]).find('input'));
						var textarea = jQuery(jQuery(tds[j]).find('textarea'));
						if(input && input.length > 0 ){
							var type = input.attr('type');
							var html = '';
							var checked = input.attr('checked');
							var value = input.attr('value');
							var dictId = input.attr('dictId');
							var columnId = input.attr('columnId');
							var name = input.attr('name');
							var styleStr = typeof(input.attr('style')) == 'undefined' ? '' :  input.attr('style');
							var className = "";
							if(type == 'checkbox'){
								className = "tree2table-checkbox";
							}else if(type == 'radio'){
								className = "tree2table-radio";
							}else if(type == "text"){
								html = "<label  value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'>"+value+"</label>";
							}else{
								return;
							}
							if(checked){
								className += '-checked';
							}
							if(type != "text"){
								html = "<label><span  style='"+styleStr+"' class='"+className+"' value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'></span></label>";
							}
							jQuery(tds[j]).html(html);
						}
						if(textarea && textarea.length > 0 ){
							var html = '';
							var value = typeof(textarea.attr('value')) == 'undefined' ? '' : textarea.attr('value');
							var dictId = textarea.attr('dictId');
							var columnId = textarea.attr('columnId');
							var name = textarea.attr('name');
							var styleStr = typeof(textarea.attr('style'))  == 'undefined' ? '' :  textarea.attr('style');
							html = "<label style='"+styleStr+"' value='"+value+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+name+"'>"+value+"</label>";
							jQuery(tds[j]).html(html);
						}
					}
				}
			}
		}
	};
})(window); 
