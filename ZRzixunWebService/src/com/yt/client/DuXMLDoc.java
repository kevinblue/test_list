package com.yt.client;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
public class DuXMLDoc {
    public List xmlElements(String xmlDoc) {
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        List<String> list_result=new ArrayList<String>();
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
//            System.out.println(root.getName());//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            //获得XML中的命名空间（XML中未定义可不写）
            Namespace ns = root.getNamespace();
            Element et = null;
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
//              System.out.println(et.getChild("Flag",ns).getText());
//              System.out.println(et.getChild("Desc",ns).getText());
              list_result.add(0, et.getChild("Flag",ns).getText());
              list_result.add(1,et.getChild("Desc",ns).getText());
            }
           
            //获取最里面的元素的名称
//            et = (Element) jiedian.get(0);
//            List zjiedian = et.getChildren();
//            for(int j=0;j<zjiedian.size();j++){
//                Element xet = (Element) zjiedian.get(j);
//                System.out.println(xet.getName());
//            }
            
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return list_result;
    }
    public static void main(String[] args){
        DuXMLDoc doc = new DuXMLDoc();
        String xml="<TranData>"+
        				"<Head>"+
        					"<Flag>1</Flag>"+
							"<Desc>回访数据处理失败</Desc>"+
						"</Head>"+
        			"</TranData >";
        List<String> list_result=doc.xmlElements(xml);
        System.out.println(list_result.get(0));
        System.out.println(list_result.get(1));
    }
}