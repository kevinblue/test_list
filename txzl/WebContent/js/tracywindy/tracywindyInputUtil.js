var allInputHintsArr= [];
//js给input加水印方法
//el--加水印的对象
//hint--水印文字
function inputHint(el, hint) {
	this.hint = hint;
	this.el = el;
	this.el.style.color = '#aaa';//水印样式
	this.el.value = hint;
	allInputHintsArr.push(this);
	//进入焦点事件
	this.el.onfocus = function () {
		if (el.value == hint) {//文本框值为水印文字，则清空
			el.value = '';
			el.style.color = '';
		}
	};
	this.el.onblur = function () {
		if (el.value == '') {//文本框值空，则赋值为水印文字
			el.style.color = '#aaa';
			el.value = hint;
	   }
	};
}
//清空水印input数值
function removeAllInputHint(){
	for(var i=0;i<allInputHintsArr.length;i++){
		var inputHint = allInputHintsArr[i];
		var hint      = inputHint.hint;
		var el		  = inputHint.el;
		if (el.value == hint) {//文本框值为水印文字，则清空
			el.value = '';
			el.style.color = '';
		}
	}
}
//恢复水印input数值
function resumeAllInputHint(){
	for(var i=0;i<allInputHintsArr.length;i++){
		var inputHint = allInputHintsArr[i];
		var hint      = inputHint.hint;
		var el        = inputHint.el;
		if (el.value == '') {//文本框值空，则赋值为水印文字
			el.style.color = '#aaa';
			el.value = hint;
	   }
	}
}