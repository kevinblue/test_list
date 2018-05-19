package com.tenwa.report.formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.tenwa.report.query.CellData;

public class FormatterUtil {

	private static final Logger log = LoggerFactory.getLogger(FormatterUtil.class);
	
	protected static final Pattern parameter_pattern = Pattern.compile("\\{.*?\\}");

	public static Object evaluate(String expression, Map<String, CellData> datas) {
		Matcher m = null;
		Map<String, Object> values = new HashMap<String, Object>();

		while ((m = parameter_pattern.matcher(expression)).find()) {
			String paramName = expression.substring(m.start() + 1, m.end() - 1);
			CellData cellData = datas.get(paramName.toLowerCase());
			expression = m.replaceFirst(paramName);
			if (cellData.getRawValue() == null)
				return null;

			try {
				values.put(paramName, Double.parseDouble(cellData.getRawValue()));
			} catch (Exception e) {
				return null;
			}
		}

		try {
			Expression exp = AviatorEvaluator.compile(expression, true);
			return exp.execute(values);
		} catch (Exception e) {
			log.error("", e);

		}
		return null;
	}
}
