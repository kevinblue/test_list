package com.tenwa.report.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.query.CellData;



public interface ExportService {
	public void addTableData(ReportTable table,List<Map<String,CellData>> tableDatas);	
	
	public void doExport(HttpServletResponse response,String fileName) throws IOException;
	
	public void doExport(OutputStream outputStream) throws IOException;
	
}
