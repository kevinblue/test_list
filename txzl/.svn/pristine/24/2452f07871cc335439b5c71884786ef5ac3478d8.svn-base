
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.test
 * 文件名：         TreeNode.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-3-下午10:57:41
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.test.test;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonProperty;


 /**
 * 类名称：     TreeNode
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-3 下午10:57:41
 * 修改备注：
 * @version 1.0.0
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
public class TreeNode {  
	@JsonProperty(value="name")
    String Name;  
    @JsonBackReference  
    //@JsonIgnore  
    TreeNode parent;  
    @JsonManagedReference  
    List<TreeNode> children = new ArrayList<TreeNode>();  
  
    public TreeNode() {  
    }  
  
    public TreeNode(String name) {  
        this.Name = name;  
    }  
  
    public String getName() {  
        return Name;  
    }  
  
    public void setName(String name) {  
        this.Name = name;  
    }  
  
    public TreeNode getParent() {  
        return parent;  
    }  
  
    public void setParent(TreeNode parent) {  
        this.parent = parent;  
    }  
  
    public List<TreeNode> getChildren() {  
        return children;  
    }  
  
    public void setChildren(List<TreeNode> children) {  
        this.children = children;  
    }  
  
    public void addChild(TreeNode child) {  
        if (children == null)  
            children = new ArrayList<TreeNode>();  
        children.add(child);  
    }  
}  
