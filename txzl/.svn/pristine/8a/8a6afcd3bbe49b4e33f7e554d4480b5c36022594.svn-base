package com.tenwa.leasing.utils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.tenwa.kernal.utils.StringUtil;

public class wordtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Object> model = new HashMap<String, Object>();
		String ffile = "d:\\a.zip";
		String tfile = "d:\\d.docx";
		List cdate = new ArrayList();
		for (int j = 0; j < 3; j++) {
			List onedate = new ArrayList();
			for (int i = 0; i < 3; i++) {
				onedate.add("xu"+i);
			}
			cdate.add(onedate);
		}
		model.put("table8", cdate);
		try {
			wordtest.writeDatatoTemplateWordbyBookMark(ffile, model, tfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeDatatoTemplateWordbyBookMark(String tempfile,Map<String, Object> model, String targetFile) throws Exception {
		 Document document = null; 
		 File file = new File(tempfile);
		 ZipFile docxFile = new ZipFile(file);
		 ZipEntry documentXML = docxFile.getEntry("word/document.xml");
		 InputStream documentXMLIS = docxFile.getInputStream(documentXML); 
   	 SAXReader saxReader = new SAXReader();  
	      document  = saxReader.read(documentXMLIS);
		 if(null!=model){  
		 for (Iterator it = model.entrySet().iterator(); it.hasNext();) {
			Map.Entry element = (Map.Entry) it.next();
			String book_name = element.getKey().toString().trim();// 获得列名
			Object t_value = element.getValue();
			System.out.println(book_name);
			if (null != t_value){
				if (t_value instanceof String) {
					WordBookMarkXmlUtil.replaceBookMark(document,book_name,StringUtil.nullToString( t_value));
			    }else{
			    	List templistvalue = (List) t_value;
				    if (templistvalue.size() > 0 && templistvalue.get(0) instanceof List) {
				    	//写入表格
				    	if(book_name.startsWith("MT_")){//限制长度的表格
				    		WordBookMarkXmlUtil.replaceMTTableBookMark(document, book_name, templistvalue,true,5);
				    	}else if(book_name.startsWith("MS_")){
				    		WordBookMarkXmlUtil.replaceMSTableBookMark(document, book_name, templistvalue);
				    	}else {
				    		WordBookMarkXmlUtil.replaceTableBookMark(document, book_name, templistvalue);
				    	}
				    	//表格拷贝套打
				    	
				    }else{
				    	if(book_name.startsWith("MC_")){
				    		//表格方式只插数，不插表格，把多余的表格删除
				    		WordBookMarkXmlUtil.replaceMCListBookMark(document, book_name, templistvalue, true, 5);
				    	}else{
				    	//写入列表
				    	for(int j=0;j<templistvalue.size();j++){
				    		WordBookMarkXmlUtil.replaceBookMark(document,book_name+"_"+j+"",StringUtil.nullToString(templistvalue.get(j)));
				    	}}
				    }
				}
		 }
	  }}
	  //保存文档
		 WordBookMarkXmlUtil.saveWordXML(docxFile,document, targetFile); 	 
	}

}
