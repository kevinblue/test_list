package com.tenwa.kernal.utils;
import org.apache.commons.el.Expression;
import org.apache.commons.el.ExpressionString;
import org.apache.commons.el.Logger;
import org.apache.commons.el.parser.ELParser;
import org.apache.commons.el.parser.ParseException;
  





import com.mockrunner.mock.web.MockFunctionMapper;
import com.mockrunner.mock.web.MockVariableResolver;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.el.ELException;

@SuppressWarnings("deprecation")
public class ELUtil {
	private static Logger	logger	= new Logger(System.out);
	public static String evaluate(String input,Map<String,String> modelData) {
		MockVariableResolver resolver = new MockVariableResolver();
		for(String key : modelData.keySet()){
			resolver.addVariable(key, modelData.get(key));
		}
		resolver.addVariable("requestScope", modelData);
		MockFunctionMapper mapper = new MockFunctionMapper();
	    try {
	        StringReader rdr = new StringReader(input);
	        ELParser parser = new ELParser(rdr);
	        Object result = parser.ExpressionString();
	        if(result instanceof String) {
	            return (String) result;
	        } else if(result instanceof Expression) {
	            Expression expr = (Expression) result;
	            result = expr.evaluate(resolver, mapper, logger);
	            return result == null ? null : result.toString();
	        } else if(result instanceof ExpressionString) {
	            Expression expr = (Expression) result;
	            result = expr.evaluate(resolver, mapper, logger);
	            return result == null ? null : result.toString();
	        } else {
	            throw new RuntimeException("Incorrect type returned; not String, Expression or ExpressionString");
	        }
	    } catch(ParseException pe) {
	        throw new RuntimeException("ParseException thrown: " + pe.getMessage(), pe);
	    } catch(ELException ele) {
	        throw new RuntimeException("ELException thrown: " + ele.getMessage(), ele);
	    }
	}
	public static void main(String args[]) throws Exception{
		Map<String,String> modelData = new HashMap<String,String>();
		modelData.put("a", "test");
		modelData.put("b", "testa");
		System.out.println(ELUtil.evaluate("${( (b == a) && (1 == 1) ) ? '测试' : '好'}", modelData));
		System.out.println(ELUtil.evaluate("${true}", modelData));
		System.out.println(ELUtil.evaluate("${'\"'}", modelData));
		System.out.println(ELUtil.evaluate("${requestScope['a'] == 'test' ? 'aaaa':'bbbb'}", modelData));
		System.out.println(ELUtil.evaluate("${a== 'test' ? 'aaaa':'bbbb'}", modelData));
	}
}
