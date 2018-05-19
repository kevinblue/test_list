
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.test
 * 文件名：         JsonTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-3-下午10:58:44
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.test.test;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tenwa.kernal.utils.MD5Util;
//import org.codehaus.jackson.map.ObjectMapper;


 /**
 * 类名称：     JsonTest
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-3 下午10:58:44
 * 修改备注：
 * @version 1.0.0
 **/

public class JsonTest {  
    static TreeNode node;  
  
    @BeforeClass  
    public static void setUp() {  
        TreeNode node1 = new TreeNode("node1");  
        TreeNode node2 = new TreeNode("node2");  
        TreeNode node3 = new TreeNode("node3");  
        TreeNode node4 = new TreeNode("node4");  
        TreeNode node5 = new TreeNode("node5");  
        TreeNode node6 = new TreeNode("node6");  
  
        node1.addChild(node2);  
        node2.setParent(node1);  
        
        node2.addChild(node3);  
        node3.setParent(node2);  
        
        node2.addChild(node4);  
        node4.setParent(node2);  
        
        node3.addChild(node5);  
        node5.setParent(node3); 

        node5.addChild(node6);  
        node6.setParent(node5);  
  
        node = node6;  
    }  
    //ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
    @SuppressWarnings("unchecked")
	@Test  
    public void test() throws JsonGenerationException, JsonMappingException, IOException,Exception {  
//        ObjectMapper mapper = new ObjectMapper();  
//        String json = mapper.writeValueAsString(node);  
//        System.out.println(json);  
//        TreeNode readValue = mapper.readValue(json, TreeNode.class);  
//        System.out.println(readValue.getChildren().get(0).getParent().getName());  
//		//System.out.println(ac.getBean("sessionFactory"));
//    	ObjectMapper mapper = new ObjectMapper() ;
//    	List<String> l = new ArrayList<String>();
//    	l.add("a");
//    	l.add("b");
//    	l.add("c");
//    	l.add("d");
//    	Map<String,Object> m = new HashMap<String,Object>();
//    	 m.put("attr", l);
//    	System.out.println(mapper.writeValueAsString(m));
//    	HashMap<String,Object> map = new HashMap<String,Object>();
//    	map.put("402881e73c36d093013c36d6b4170003", "a");
//    	System.out.println(map.containsKey("402881e73c36d093013c36d6b4170003"));
//    	Class clazz = ProjInfo.class;
//    	clazz.getDeclaredField("id");
    	//System.out.println("29001".matches("^\\d+$"));
//    	ObjectMapper mapper = new ObjectMapper();  
//    	//String json = mapper.writeValueAsString(node);  
//    	//System.out.println(json);
//    	String json = "{'name':'node11','test':'01'}".replaceAll("'", "\"");
//    	TreeNode tn = mapper.readValue(json, TreeNode.class);
//    	System.out.println(tn.getName());
    	//new JSONObject("{'position':'0','managerUserRealName':'','description':'组织机构','managerUserName':'','managerUserId':'','pid':'-1','code':'department','type':'department'}");
//    	File fp = new File("D:\\eclipse_svnProjects\\leasing4.5\\WebContent\\menuIcons");
//    	for(File f : fp.listFiles()){
//    		String fname = f.getName();
//    		String fname_ = fname.substring(0,fname.lastIndexOf("."));
//    		System.out.println(".icon-{0} {background:url('../../menuIcons/{1}') no-repeat;}"
//    				.replace("{0}", fname_)
//    				.replace("{1}", fname));
//    		System.out.println(".icon-{0}-w {background:url('../../menuIcons_w/{1}') no-repeat;}"
//    				.replace("{0}", fname_)
//    				.replace("{1}", fname)
//    				);
//    	}
    	System.out.println(MD5Util.getMD5EncodedPasswordWithSalt("111111","1"));
    }  
  
    @AfterClass  
    public static void tearDown() {  
        node = null;  
    }  
    
    
}  


