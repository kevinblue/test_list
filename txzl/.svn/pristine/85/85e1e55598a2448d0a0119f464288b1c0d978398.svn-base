<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="form1" action="${pageContext.request.contextPath}/leasing/template/uploadEidtFile.action" enctype="multipart/form-data" style="padding:0px;margin:0px;">
<input name="fileid" id="id_fileid" type="hidden" value="<%= request.getParameter("id")%>" />
<div id="officecontrol">
   <div id="id_officeheight">
       <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/ntko/officecontrol/ntkoofficecontrol.js"></script>
   </div> 
   <div id=statusBar style="height:20px;width:100%;background-color:#c0c0c0;font-size:12px;"></div>
<script language="JScript" for=NTKO_OCX event="OnDocumentClosed()">
     setFileOpenedOrClosed(false);
</script>
<script language="JScript" for=TANGER_OCX event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
                                   SetReviewMode(true); //修改留痕
					                if(status!="2"){
								       setFileNew(false);  //禁止新建
								       setFileOpen(false);//禁止打开文件
								       setFileSaveAs(false);//禁止另存为
								       OFFICE_CONTROL_OBJ.SetReadOnly(true,"123");//禁止编辑
								       OFFICE_CONTROL_OBJ.Menubar=false;
									   OFFICE_CONTROL_OBJ.ToolBars=false;//菜单不显示
									   OFFICE_CONTROL_OBJ.activeDocument.saved=true;//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
					                }
									//获取文档控件中打开的文档的文档类型
									switch (OFFICE_CONTROL_OBJ.doctype)
									{
										case 1:
											fileType = "Word.Document";
											fileTypeSimple = "wrod";
											break;
										case 2:
											fileType = "Excel.Sheet";
											fileTypeSimple="excel";
											break;
										case 3:
											fileType = "PowerPoint.Show";
											fileTypeSimple = "ppt";
											break;
										case 4:
											fileType = "Visio.Drawing";
											break;
										case 5:
											fileType = "MSProject.Project";
											break;
										case 6:
											fileType = "WPS Doc";
											fileTypeSimple="wps";
											break;
										case 7:
											fileType = "Kingsoft Sheet";
											fileTypeSimple="et";
											break;
										default :
											fileType = "unkownfiletype";
											fileTypeSimple="unkownfiletype";
									}
									setFileOpenedOrClosed(true);
								</script>
									<script language="JScript" for=TANGER_OCX event="BeforeOriginalMenuCommand(TANGER_OCX_str,TANGER_OCX_obj)">
									alert("BeforeOriginalMenuCommand事件被触发");
								</script>
								<script language="JScript" for=TANGER_OCX event="OnFileCommand(TANGER_OCX_str,TANGER_OCX_obj)">
									if (TANGER_OCX_str == 3) 
									{
										alert("不能保存！");
										CancelLastCommand = true;
									}
								</script>
								<script language="JScript" for=TANGER_OCX event="AfterPublishAsPDFToURL(result,code)">
									result=trim(result);
									document.all("statusBar").innerHTML="服务器返回信息:"+result;
									OFFICE_CONTROL_OBJ.activeDocument.saved=true;
								</script>
								<script language="JScript" for=TANGER_OCX event="OnCustomMenuCmd2(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID)">
								alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
			</script>
       </div>
</form>
 <script language="javascript">
         var height=document.documentElement.clientHeight;
         var width=document.documentElement.clientWidth;
         $("#id_officeheight").css('height',height-40);
         $("#id_officeheight").css('width',width);
   </script>