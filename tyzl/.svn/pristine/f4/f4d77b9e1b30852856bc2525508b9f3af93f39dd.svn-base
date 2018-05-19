<script type="text/javascript">
jQuery(function() {
    jQuery("div.leftComboContainer").css("border", "0px solid silver");
    jQuery("div.leftComboContainer").css("height", "auto");
    if (isViewHistoryTask) {
        var submitForm = document.getElementById("id_submitProcessedForm");
        var formElements = submitForm.elements;
        //jQuery("#id_submitProcessedForm input[type='text'],#id_submitProcessedForm textarea").addClass("element-readonly");
        for (var i = 0; i < formElements.length; i++) {
            var formElement = formElements[i];
            formElement.readOnly = true;
            //jQuery("#id_submitProcessedForm input[type='text'],#id_submitProcessedForm textarea").addClass("element-readonly");
            var currentIsReadOnly = true;
            if (!isCompletedTask) {
                if ("true" == ("" + formElement.getAttribute("isForceModify"))) {
                    currentIsReadOnly = false;
                }
            } else {
                var currentElementType = (formElement.getAttribute("type") || "").toLowerCase();
                if (("radio" == currentElementType) || ("checkbox" == currentElementType) || ("select" == currentElementType)) {
                    formElement.disabled = true;
                }
            }
            if (currentIsReadOnly) {
                if (formElement.className.indexOf("Wdate") > -1) {
                    formElement.onclick = null;
                }
                removeClass(formElement, "Wdate");
                if(formElement.type != 'button'){
	                addClass(formElement, "element-readonly");
                }
            }
        }
        if (!isCompletedTask) {
            if (window.formPageReadOnlyCallBack) {
                window.formPageReadOnlyCallBack(submitForm);
            }
        }
    }
    document.documentElement.style.overflow = "hidden";
    document.body.style.overflow = "hidden";
    var iframes = window.top.frames;
    var iframesLen = iframes.length;
    var index = 0;
});
</script>