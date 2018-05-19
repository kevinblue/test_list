(function(){
	//创建新Word文档：
	//TANGER_OCX_OBJ.CreateNew(“Word.Document”);
	//创建新Excel电子表格：
	//TANGER_OCX_OBJ.CreateNew(“Excel.Sheet”);
	//提示用户选择本地文件打开：
	//TANGER_OCX_OBJ.ShowDialog (1);
	//提示用户选择本地文件保存：
	//TANGER_OCX_OBJ.ShowDialog (3);
	//不提示用户，直接打开指定的本地文件：
	//TANGER_OCX_OBJ.OpenLocalFile("c:\\test.doc");
	//不提示用户，直接保存为指定的本地文件：
	//TANGER_OCX_OBJ.SaveToLocal("c:\\test.doc",false);//第二个参数为true将覆盖已存在的文件
	//5：从URL打开文档：
	//以下调用从readdoc.asp中打开文件。readdoc.asp可以从数据库或者服务器磁盘中读取文件并返回。
	//TANGER_OCX_OBJ.OpenFromURL ("readdoc.asp");//可以是相对或者绝对URL
	//6：将在线编辑的文档以及FORM中的其他信息保存到URL
	//以下调用将控件中编辑的文件保存到upload.asp。从后台upload.asp程序来看，只需要处理当前HTML页面的FORM(0)的信息，以及一个名称为newdoc.doc，输入域为EDITFILE的上传文件。一般第二个参数可以被upload.asp用来区分是控件中上传的文件，还是当前HTML页面FORM中的其他上传文件。另外，后台的upload.asp程序可以将文件保存到服务器磁盘或者数据库的BLOB字段。
	//TANGER_OCX_OBJ.SaveToURL("upload.asp","EDITFILE","","newdoc.doc",0);
	//7：关闭文档
	//TANGER_OCX_OBJ.Close();
	window.OfficeUtil = {
			//以下Javasctipt函数使用控件属性来控制控件是否显示标题栏，菜单栏，状态栏，工具栏。
			ShowTitleBar:function(TANGER_OCX_OBJ,bShow)
			{
				TANGER_OCX_OBJ.Titlebar = bShow;
			},
			ShowMenubar:function(TANGER_OCX_OBJ,bShow)
			{
				TANGER_OCX_OBJ.Menubar = bShow;
			},
			ShowStatusbar:function(TANGER_OCX_OBJ,bShow)
			{
				TANGER_OCX_OBJ. Statusbar = bShow;
			},
			ShowToolbars:function(TANGER_OCX_OBJ,bShow)
			{
				TANGER_OCX_OBJ. Toolbars = bShow;
			},
			//以下函数可用于允许或禁止用户从控件拷贝数据。
			TANGER_OCX_SetNoCopy:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.IsNoCopy = boolvalue;
			},
			//以下函数可用于工具菜单项控制。
			//（隐藏工具菜单项和审阅工具栏及右键菜单可用于强制用户在痕迹保留状态下工作）
			ShowToolMenu:function(TANGER_OCX_OBJ,bShow)
			{
				TANGER_OCX_OBJ.IsShowToolMenu = bShow;
			},
			//以下函数用于对控件文件菜单的控制。
			//允许或禁止文件－>新建菜单
			TANGER_OCX_EnableFileNewMenu:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.FileNew = boolvalue;
			},
			//允许或禁止文件－>打开菜单
			TANGER_OCX_EnableFileOpenMenu:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ. FileOpen  = boolvalue;
			},
			//允许或禁止文件－>关闭菜单
			TANGER_OCX_EnableFileCloseMenu:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.FileClose = boolvalue;
			},
			//允许或禁止文件－>保存菜单
			TANGER_OCX_EnableFileSaveMenu:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.FileSave = boolvalue;
			},
			//允许或禁止文件－>另存为菜单
			TANGER_OCX_EnableFileSaveAsMenu:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.FileSaveAs = boolvalue;
			},
			//允许或禁止文件－>打印菜单
			TANGER_OCX_EnableFilePrintMenu:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.FilePrint = boolvalue;
			},
			//允许或禁止文件－>打印预览菜单
			TANGER_OCX_EnableFilePrintPreviewMenu:function(TANGER_OCX_OBJboolvalue)
			{
				TANGER_OCX_OBJ. FilePrintPreview = boolvalue;
			},
			//8：打印控制（可控制前台或者后台打印）
			//以下函数用于设置页面布局：
			TANGER_OCX_ChgLayout:function(TANGER_OCX_OBJ)
			{
			 	try
				{
					TANGER_OCX_OBJ.showdialog(5); //设置页面布局
				}
				catch(err){
					alert("错误：" + err.number + ":" + err.description);
				}
				finally{
				}
			},
			//以下Javascript函数可以用来打印当前文档。isBackground参数可以控制是前台打印还是后台打印。对于比较大的文档，如果确认必须等待用户打印完毕，可以调用TANGER_OCX_PrintDoc(false);函数来进行前台打印。
			TANGER_OCX_PrintDoc:function(TANGER_OCX_OBJ,isBackground)
			{
				var oldOption;	
				try
				{
					var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
					oldOption = objOptions.PrintBackground;
					objOptions.PrintBackground = isBackground;
				}
				catch(err){};
				TANGER_OCX_OBJ.printout(true);
				try
				{
					var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
					objOptions.PrintBackground = oldOption;
				}
				catch(err){};	
			},
			//9：痕迹保留控制相关
			//设置当前Office的用户名（痕迹的用户名）
			//设置用户名
			TANGER_OCX_SetDocUser:function(TANGER_OCX_OBJ,cuser)
			{
				with(TANGER_OCX_OBJ.ActiveDocument.Application)
				{
					UserName = cuser;
				}	
			},
			//进入或退出强制痕迹保留状态，调用该函数下面定义的两个函数。一般可直接调用本函数
			TANGER_OCX_SetMarkModify:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_SetReviewMode(TANGER_OCX_OBJ,boolvalue);
				TANGER_OCX_EnableReviewBar(TANGER_OCX_OBJ,!boolvalue);
			},
			//允许或禁止显示修订工具栏和工具菜单（保护修订,用户不能更改当前修订状态）：
			TANGER_OCX_EnableReviewBar:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.ActiveDocument.CommandBars("Reviewing").Enabled = boolvalue;
				TANGER_OCX_OBJ.ActiveDocument.CommandBars("Track Changes").Enabled = boolvalue;
				TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;	//关闭或打开工具菜单
			},
			//打开或者关闭修订模式：
			TANGER_OCX_SetReviewMode:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.ActiveDocument.TrackRevisions = boolvalue;
			},
			//显示/不显示修订文字
			TANGER_OCX_ShowRevisions:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.ActiveDocument.ShowRevisions = boolvalue;
			},
			//打印/不打印修订文字
			TANGER_OCX_PrintRevisions:function(TANGER_OCX_OBJ,boolvalue)
			{
				TANGER_OCX_OBJ.ActiveDocument.PrintRevisions = boolvalue;
			},
			//接受所有修订
			TANGER_OCX_AcceptAllRevisions:function(TANGER_OCX_OBJ)
			{
				TANGER_OCX_OBJ.ActiveDocument.AcceptAllRevisions();
			},
			//拒绝所有修订
			TANGER_OCX_RejectAllRevisions:function(TANGER_OCX_OBJ)
			{
				TANGER_OCX_OBJ.ActiveDocument.RejectAllRevisions();
			},
		    //切换文档的只读状态（文档保护状态）
		    TANGER_OCX_SetReadOnly:function(TANGER_OCX_OBJ,boolvalue)
	        {
	    		var i;
	    		try
	    		{
	    			if (boolvalue) TANGER_OCX_OBJ.IsShowToolMenu = false;
	    			with(TANGER_OCX_OBJ.ActiveDocument)
	    			{
	    				if (TANGER_OCX_OBJ.DocType == 1) //word
	    				{
	    					if ( (ProtectionType != -1) &&  !boolvalue)
	    					{
	    						Unprotect();
	    					}
	    					if ( (ProtectionType == -1) &&  boolvalue)
	    					{
	    						Protect(2,true,"");
	    					}
	    				}
	    				else if(TANGER_OCX_OBJ.DocType == 2)//excel
	    				{
	    					for(i=1;i<=Application.Sheets.Count;i++)
	    					{
	    						if(boolvalue)
	    						{
	    							Application.Sheets(i).Protect("",true,true,true);
	    						}
	    						else
	    						{
	    							Application.Sheets(i).Unprotect("");
	    						}
	    					}
	    					if(boolvalue)
	    					{
	    						Application.ActiveWorkbook.Protect("",true);					
	    					}
	    					else
	    					{
	    						Application.ActiveWorkbook.Unprotect("");
	    					}
	    				}
	    			}
	    		}
	    		catch(err){
	    			//alert("错误：" + err.number + ":" + err.description);
	    		}
	    		finally{}
	    	}
	};
})(window);