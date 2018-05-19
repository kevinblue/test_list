package com.tenwa.business.serviceImpl;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.ChartDao;
import com.tenwa.business.model.Chart;
import com.tenwa.business.service.ChartService;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.FreeMarkerUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.kernal.utils.XMLDataUtil;

import freemarker.template.Template;

@Service(value="chartService")
public class ChartServiceImpl implements ChartService 
{
	@Resource(name="chartDao")
    private ChartDao chartDao;
	@Override
	public void getChartXmlData(String xmlFileNameOrPath, Map<String, String> model,OutputStream os) throws Exception 
	{
        Chart chart  = new Chart();
        String chartId = model.get("chartId");
        String chartTitle = model.get("chartTitle");
        chart.setFltLoadingDirectoryFilePath(ResourceUtil.getFltSourceDirectoryPath());
        chart.setChartXmlLoadingDirectoryFilePath(ResourceUtil.getChartsDataSourceDirectoryPath());
        chart.setChartXmlChartFileNameOrPath(xmlFileNameOrPath);
        chart.setModel(model);
        chart.setChartId(chartId);
        chart.setChartTitle(chartTitle);
        this.readChartXmlInfo(chart);
		 if(ResourceUtil.isDebug())
		 {
			 if(chart.isShowSql())
			 {
				 System.out.println("###chartXml:"+chart.getChartXmlChartFileNameOrPath());
				 System.out.println("###chartSql:"+chart.getTargetSql());
			 }
		 }
        this.chartDao.getChartData(chart);
        this.getUnionData(os, chart);
	}
	@Override
	public void readChartXmlInfo(Chart chart) throws Exception
	{
		String chartXmlLoadingDirectoryFilePath = chart.getChartXmlLoadingDirectoryFilePath();
		String chartXmlChartFileNameOrPath = chart.getChartXmlChartFileNameOrPath();
		
		Map<String,String> chartInfoFromXmlFile = null;
		if(ResourceUtil.isDebug())
		{
			String chartXmlfileNameFullPath = chartXmlLoadingDirectoryFilePath+"/"+chartXmlChartFileNameOrPath;
			chartXmlfileNameFullPath = FileUtil.getFilePathString(chartXmlfileNameFullPath);
			chartInfoFromXmlFile = XMLDataUtil.readChartInfoFromXmlFile(chartXmlfileNameFullPath);
		}
		else
		{
			chartInfoFromXmlFile = WebUtil.getAllXMLChart().get(chart.getXmlChartFileFullPathWithoutFileSeparator());
		}
		
		String chartAttributs = chartInfoFromXmlFile.get("chartAttributs");
		String sql = chartInfoFromXmlFile.get("sql");
		String flt = chartInfoFromXmlFile.get("flt");
		String dataSource = chartInfoFromXmlFile.get("dataSource");
		String other = chartInfoFromXmlFile.get("other");
		String showSql = chartInfoFromXmlFile.get("showSql");
		
		chart.setChartAttributs(chartAttributs);
		chart.setSourceSql(sql);
		chart.setFltName(flt);
		chart.setDataSourceName(dataSource);
		chart.setShowSql(showSql);
		chart.setOther(other);
	}
	@Override
	public  void getUnionData(OutputStream os,Chart chart) throws Exception
	{
		Map<String,Object> rootMap = new HashMap<String,Object>();
		rootMap.put("base",WebUtil.getWebContextPath());
		Map<String,String> chartInfo = new HashMap<String,String>();
		chartInfo.put("chart", chart.getChartAttributs());
		chartInfo.put("chartId", chart.getChartId());
		chartInfo.put("chartTitle", chart.getChartTitle());
		chartInfo.put("other",chart.getOther());
		rootMap.put("chart",chartInfo);
		Template template = FreeMarkerUtil.getConfiguration(FileUtil.getFilePathString(chart.getFltLoadingDirectoryFilePath())).getTemplate(chart.getFltName());
		rootMap.put("list", chart.getDatas());
		Writer out = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
		template.process(rootMap, out);
		out.flush();
		out.close();
		os.flush();
		os.close();
	}
}
