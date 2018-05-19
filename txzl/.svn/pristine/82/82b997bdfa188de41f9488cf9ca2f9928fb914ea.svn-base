package com.tenwa.kernal.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import com.reckon.service.impl.RentCalculateServiceImpl;

public class XMLDataUtil 
{
	private static Logger logger = Logger.getLogger(XMLDataUtil.class);
   public static Map<String,Map<String,String>> getAllXMLTable() throws Exception
   {
	   return getAllXML("table");
   }
   public static Map<String,Map<String,String>> getAllXMLChart() throws Exception
   {
	   return getAllXML("chart");
   }
   public static Map<String,Map<String,String>> getAllXML(String flag) throws Exception
   {
	   Map<String,Map<String,String>> allXML = new HashMap<String,Map<String,String>>();
	   String fileDir = null;
	   if("table".equals(flag))
	   {
		   fileDir = ResourceUtil.getTablesDataSourceDirectoryPath();
	   }
	   else if("chart".equals(flag))
	   {
		   fileDir = ResourceUtil.getChartsDataSourceDirectoryPath();
	   }
	   File listDirFile = new File(FileUtil.getFilePathString(fileDir));
	   XMLDataUtil.recursionFiles(listDirFile,allXML,flag);
	   XmlUtil.closeLocalResources();
	   return allXML;
   }
   public static void recursionFiles(File listDirFile,Map<String,Map<String,String>> allXML,String flag) throws Exception
   {
	   String xmlFilePath   = listDirFile.getPath();
	   if(!(xmlFilePath.toLowerCase().endsWith(".svn"))&& !(xmlFilePath.toLowerCase().endsWith(".cvs")))
	   {
		   if(listDirFile.isDirectory())
		   {
			   File[] chidrenFiles = listDirFile.listFiles();
			   for(int index=0;index<chidrenFiles.length;index++)
			   {
				  File file  = chidrenFiles[index];
				  XMLDataUtil.recursionFiles(file, allXML,flag);
			   }
		   }
		   else 
		   {
			  if(xmlFilePath.toLowerCase().endsWith(".xml"))
			  {
				  String  fileFullPath  = xmlFilePath.replaceAll("(\\\\)|(/)|(%5C)","").replaceAll("\\s","");
				  if("table".equals(flag))
				  {
					  logger.info(xmlFilePath);
					  allXML.put(fileFullPath, XMLDataUtil.readTableInfoFromXmlFile(xmlFilePath));
				  }
				  else if("chart".equals(flag))
				  {
					  allXML.put(fileFullPath,XMLDataUtil.readChartInfoFromXmlFile(xmlFilePath));
				  }
				  
			  }
		   }
	   }
   }
   public static Map<String,String> readTableInfoFromXmlFile(String xmlFilePath) throws Exception
	{
		Map<String,String> m = new HashMap<String,String>();
		Document document = null;
		try {
			logger.info(xmlFilePath);
			document = XmlUtil.readXML(xmlFilePath);
		} catch (Exception e) {
			  System.out.println("格式不正确的xml文件路径:"+xmlFilePath);
			e.printStackTrace();
		}
		Element  root = document.getRootElement();
		Namespace ns = root.getNamespace();
		Element  table = root.getChild("table",ns);
		if(null == table)return null;
		Element  data = table.getChild("data",ns);
		//数据sql
		String table_sql = null;
		//modify by tracywindy 2013-08-21
		String dbType = ResourceUtil.getDBType();
		Element dbTypeData = data.getChild(dbType.toLowerCase(),ns);
		if(null == dbTypeData){
			dbTypeData = data.getChild("alldb",ns);
		}
		Element tableSql = dbTypeData.getChild("table_sql",ns);
		//Element tableSql = data.getChild("table_sql",ns);
		if(null!=tableSql)
		{
			table_sql =  tableSql.getTextTrim();
		}
		m.put("table_sql",table_sql);
		//导出sql
		String excel_sql = null;
		Element excelSql = dbTypeData.getChild("excel_sql",ns);
		//Element excelSql = data.getChild("excel_sql",ns);
		if(null!=excelSql)
		{
			excel_sql =  excelSql.getTextTrim();
		}
		excel_sql = StringUtil.empty2Other(excel_sql, table_sql);
		m.put("excel_sql",excel_sql);
		//预定义的SQL
		String predefined_sql=null;
		Element predefinedSql = dbTypeData.getChild("predefined_sql",ns);
		if(null!=predefinedSql)
		{
			predefined_sql =  predefinedSql.getTextTrim();
		}
		predefined_sql = StringUtil.empty2Other(predefined_sql,"");
		m.put("predefined_sql",predefined_sql);
		//排序
		String order_column=null;
		Element orderColumn = dbTypeData.getChild("order_column",ns);
		if(null!=orderColumn)
		{
			order_column =  orderColumn.getTextTrim();
		}
		order_column = StringUtil.empty2Other(order_column,"");
		m.put("order_column",order_column);
		//数据源
		String dataSource = null;
		Element dataSourceChild = data.getChild("dataSource",ns);
		if(null!=dataSourceChild)
		{
			dataSource =  dataSourceChild.getTextTrim();
		}
		dataSource = StringUtil.empty2Other(dataSource, "dataSource");
		m.put("dataSource",dataSource);
		//是否防止sql注入
		String isPrepared = null;
		Element isPreparedChild = data.getChild("isPrepared",ns);
		if(null!=isPreparedChild)
		{
			isPrepared =  isPreparedChild.getTextTrim();
		}
		isPrepared = StringUtil.empty2Other(isPrepared, "true");
		m.put("isPrepared",isPrepared);
		//是否在控制台打印sql
		Element  showSql = data.getChild("show_sql",ns);
		String   showSqlText = "false";
		if(null!=showSql)
		{
			showSqlText =   showSql.getTextTrim();
			if("true".equalsIgnoreCase(showSqlText))
			{
				showSqlText = "true";
			}
		}
		m.put("show_sql",showSqlText);
		//是否加数据权限
		Element  dataAuthority = data.getChild("Data_Authority",ns);
		String   dataAuthorityText = "false";
		if(null!=dataAuthority)
		{
		     dataAuthorityText =   dataAuthority.getTextTrim();
		     dataAuthorityText=dataAuthorityText.toLowerCase();
		 }
		 m.put("Data_Authority",dataAuthorityText);
		//销毁资源
		document = null;
		return m;
	}
   //图形读写
   public static Map<String,String> readChartInfoFromXmlFile(String xmlFilePath) throws Exception
	{
		Map<String,String> m = new HashMap<String,String>();
		Document document = XmlUtil.readXML(xmlFilePath);
		Element  root = document.getRootElement();
		Namespace ns = root.getNamespace();
		Element  chartElement = root.getChild("chart",ns);
		if(null == chartElement)return null;
		Element  data = chartElement.getChild("data",ns);
		String chartAttributs = XmlUtil.getAttributesString(chartElement);
		//modify by tracywindy 2013-08-21
		String dbType = ResourceUtil.getDBType();
		Element dbTypeData = data.getChild(dbType.toLowerCase(),ns);
		if(null == dbTypeData){
			dbTypeData = data.getChild("alldb",ns);
		}
		String sql = dbTypeData.getChildText("sql",ns);
		//String sql = data.getChildText("sql",ns);
		String flt = data.getChildText("flt",ns).trim();
		String dataSource = StringUtil.empty2Other(data.getChildText("dataSource"),"dataSource").trim();
		//是否在控制台打印sql
		Element  showSql = data.getChild("show_sql",ns);
		String   showSqlText = "false";
		if(null!=showSql)
		{
			showSqlText =   showSql.getTextTrim();
			if("true".equalsIgnoreCase(showSqlText))
			{
				showSqlText = "true";
			}
		}
		String other     = "";
		//其他chart特性
		Element otherChild = chartElement.getChild("other",ns);
		if(null!=otherChild)
		{
			other =  otherChild.getText();
		}
		m.put("dataSource",dataSource);
		m.put("chartAttributs", chartAttributs);
		m.put("sql", sql);
		m.put("flt", flt);
		m.put("dataSource", dataSource);
		m.put("show_sql",showSqlText);
		m.put("other", other);
		//销毁资源
		document = null;
		return m;
	}
}
