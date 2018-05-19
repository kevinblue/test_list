package com.tenwa.report.export;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.tenwa.kernal.utils.ExcelUtil;
import com.tenwa.kernal.utils.ExcelUtil.STYLE;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.query.CellData;

public class ExcelExportService implements ExportService {
	private ExcelUtil util = new ExcelUtil();

	@Override
	public void addTableData(ReportTable table, List<Map<String, CellData>> tableDatas) {
		if (table == null)
			return;

		// util.addSheet(UUID.randomUUID().toString());
		List<ReportColumn> needExportColumns = new ArrayList<ReportColumn>();
		for (ReportColumn c : table.getReportColumns()) {
			if (c.getIsVisible()) {
				needExportColumns.add(c);
			}
		}
		util.addSheet(table.getName());

		// Add Report Name
		util.addCell(table.getName(), STYLE.REPORT);
		util.merge(0, 0, 0, needExportColumns.size() - 1); // Merge Report Name Row
		util.addNewRow();

		int exportUserPos;
		int exportDatePos;
		if (needExportColumns.size() % 2 == 0) {
			exportUserPos = exportDatePos = needExportColumns.size() / 2;
		} else {
			exportUserPos = needExportColumns.size() / 2; // 浮点转为整形时，向下取整
			exportDatePos = needExportColumns.size() / 2 + 1;
		}

		util.merge(util.getCurrentRowIndex(), 0, util.getCurrentRowIndex(), exportUserPos - 1);
		util.merge(util.getCurrentRowIndex(), exportUserPos, util.getCurrentRowIndex(), exportUserPos + exportDatePos - 1);
		util.addCell(util.getCurrentRowIndex(), 0, "导出人:" + SecurityUtil.getPrincipal().getUsername(), STYLE.TEXT);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		util.addCell(util.getCurrentRowIndex(), exportUserPos, "导出时间:" + format.format(Calendar.getInstance().getTime()), STYLE.TEXT);
		// Add Export User and Date
		util.addNewRow();
		for (ReportColumn c : needExportColumns) {
			if (c.getIsVisible()) {
				// util.enableColumnAutoSize();
				util.addCell(c.getLabel(), STYLE.TITLE);
			}
		}

		for (Map<String, CellData> rows : tableDatas) {
			util.addNewRow();
			for (ReportColumn c : table.getReportColumns()) {

				if (c.getIsVisible()) {
					CellData value = rows.get(c.getName().toLowerCase());
					switch (c.getColumnDataType()) {
					case STRING:

						util.addCell(value.getDisplayValue(), STYLE.TEXT);
						break;
					case NUMBER:
						util.addCell(value.getDisplayValue(), STYLE.NUMBER, c.getFormater());
						//moditify by zhangc
						/*if(value != null){
							util.addCell(value.getDisplayValue()==null ? "0":value.getDisplayValue()+"", STYLE.TEXT);
						}else{
							util.addCell("0", STYLE.TEXT);
						}*/
						break;
					case DATE:
						util.addCell(value.getDisplayValue(), STYLE.DATE, c.getFormater());
						break;
					case DICT:
						util.addCell(value.getDisplayValue(), STYLE.TEXT);
						break;
					default:
						break;
					}
				}
			}
		}
		util.autoSizeColumn(0, needExportColumns.size() - 1);

	}

	@Override
	public void doExport(HttpServletResponse response, String fileName) throws IOException {
		if (!(fileName.indexOf(".xlsx") > 0))
			fileName += ".xlsx";
		response.reset();
		response.setContentType("application/vnd.ms-excel);charset=UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		OutputStream os = response.getOutputStream();
		doExport(os);
	}

	@Override
	public void doExport(OutputStream outputStream) throws IOException {
		util.getWorkbook().write(outputStream);
	}
}
