package com.tenwa.leasing.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.tenwa.kernal.annotation.FieldName;

@FieldName(name = "MiniUi的数据字典标签")
public class MiniDictTag extends TagSupport {

	@FieldName(name = "属性名")
	private String name;

	@FieldName(name = "其他属性")
	private String otherproperties;

	@FieldName(name = "父结点的ID")
	private String parentid;

	@FieldName(name = "值")
	private String value;

	@FieldName(name = "显示值")
	private String text;

	@FieldName(name = "其它类样式")
	private String otherClass;
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		 return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		 try {
		     JspWriter out = this.pageContext.getOut();
		     out.print("<input id=\"id_"+this.getName()+"\" name=\""+this.getName()+"\" class=\"mini-combobox miniext-form-fit "+this.getOtherClass()+"\" textField=\"name\"");	
		     out.print("  valueField=\"value\"  dataField=\"datas\" allowInput=\"false\" onbeforeshowpopup=\"miniextonbeforeshowpopup\"");
		     out.print(" data-options=\"{_params:{pid:'"+this.getParentid()+"'}}\" ");
		     out.print(" showNullItem=\"true\" nullItemText=\"\" emptyText=\"\" ");
		     //out.print( "onvaluechanged=\"comboboxChanged\"" );
		     out.print( this.getOtherproperties());
		     out.print("  value=\""+this.getValue()+"\"   text=\""+this.getText()+"\"/>"  );
		     //out.println("<input id=\"rawValue_"+this.getName()+"\" name=\"rawValue_"+this.getName()+"\" value=\""+this.getText()+"\" class=\"mini-textbox\" style=\"display:none\"/>");	     
		 } catch(Exception e) {
	       throw new JspException(e.getMessage());
	     }
	     return SKIP_BODY;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		super.release();
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getOtherproperties() {
		if(null==otherproperties){
			otherproperties="";
		}
		return otherproperties;
	}

	public void setOtherproperties(String otherproperties) {
		this.otherproperties = otherproperties;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getValue() {
		if(null==value){
			value="";
		}
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		if(null==text){
			text="";
		}
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOtherClass() {
		if(null==otherClass){
			otherClass="";
		}
		return otherClass;
	}

	public void setOtherClass(String otherClass) {
		this.otherClass = otherClass;
	}

}
