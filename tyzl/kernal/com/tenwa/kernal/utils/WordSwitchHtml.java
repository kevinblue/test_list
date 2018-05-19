package com.tenwa.kernal.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

public class WordSwitchHtml{

	/**
	 * 将word2003文件转换为html
	 * @param path
	 * @return
	 * @throws Throwable
	 */
	public static String getWordHtml(String path,String uploadDirPath) throws Throwable {
		InputStream input = new FileInputStream(path);
		HWPFDocument wordDocument = new HWPFDocument(input);
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager(){
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
				return suggestedName;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		List pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				try {
					pic.writeImageContent(new FileOutputStream(uploadDirPath + "/" + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(outStream);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		outStream.close();

		String content = new String(outStream.toByteArray(), "UTF-8");
/*          System.out.println("222"+content);
		String fileName = UUID.randomUUID().toString().replace("-", "") + ".html";
		String savaPath = ResourceUtil.getFileUploadDataPath() + ResourceUtil.getWordFilesRelativeStorePath()+"/";
		FileUtils.writeStringToFile(new File(savaPath, fileName), content, "UTF-8");*/
		return content.toString();
	}
	/**
	 * 将word2007文件转换为html
	 * @param path
	 * @return
	 * @throws Throwable
	 */
	public static String getWord2007Html(String path) throws IOException {
		 String wordhtml="";
		 String newpath="";
		 File f = new File(path);  
         if (f.exists()) {  
                 // 1) 加载word文档生成 XWPFDocument对象  
                 InputStream in = new FileInputStream(f);  
                 XWPFDocument document = new XWPFDocument(in);  
                 // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)  
                 File imageFolderFile = new File(path);  
                 XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));  
                options.setExtractor(new FileImageExtractor(imageFolderFile));  
                options.setIgnoreStylesIfUnused(false);  
                options.setFragment(true);  
                  
                  /* // 3) 将 XWPFDocument转换成XHTML  
                 OutputStream out = new FileOutputStream(new File(filepath + htmlName)); 
                  XHTMLConverter.getInstance().convert(document, out, options); */ 
                 
                //也可以使用字符数组流获取解析的内容
                ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
                XHTMLConverter.getInstance().convert(document, baos, options);  
                wordhtml = baos.toString();
           /* 	String fileName = UUID.randomUUID().toString().replace("-", "") + ".html";
        		String savaPath = ResourceUtil.getFileUploadDataPath() + ResourceUtil.getWordFilesRelativeStorePath()+"/";
        		FileUtils.writeStringToFile(new File(savaPath, fileName), wordhtml, "UTF-8");
        		newpath=savaPath+fileName;
                System.out.println(wordhtml);*/
                 baos.close();
                 
          }
		return wordhtml; 
     }  
	/**2.1
	 * 将word2003文件转换为html
	 * @param path
	 * @return
	 * @throws Throwable
	 */
	  public static String getWord2003Html(String file) throws IOException, TransformerException, ParserConfigurationException {
		  String Word2003Html="";
	        final String imagepath = "e:/test/image/";
	        InputStream input = new FileInputStream(new File(file));
	        HWPFDocument wordDocument = new HWPFDocument(input);
	        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	        //设置图片存放的位置
	        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
	            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
	                File imgPath = new File(imagepath);
	                if(!imgPath.exists()){//图片目录不存在则创建
	                    imgPath.mkdirs();
	                }
	                File file = new File(imagepath + suggestedName);
	                try {
	                    OutputStream os = new FileOutputStream(file);
	                    os.write(content);
	                    os.close();
	                } catch (FileNotFoundException e) {
	                    e.printStackTrace();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                return imagepath + suggestedName;
	            }
	        });
	        
	        //解析word文档
	        wordToHtmlConverter.processDocument(wordDocument);
	        Document htmlDocument = wordToHtmlConverter.getDocument();
	        
	   /*     File htmlFile = new File(filepath + htmlName);
	        OutputStream outStream = new FileOutputStream(htmlFile);*/
	        
	        //也可以使用字符数组流获取解析的内容
	        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	        OutputStream outStream = new BufferedOutputStream(baos);

	        DOMSource domSource = new DOMSource(htmlDocument);
	        StreamResult streamResult = new StreamResult(outStream);

	        TransformerFactory factory = TransformerFactory.newInstance();
	        Transformer serializer = factory.newTransformer();
	        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	        serializer.setOutputProperty(OutputKeys.METHOD, "html");
	        
	        serializer.transform(domSource, streamResult);

	        //也可以使用字符数组流获取解析的内容
	       Word2003Html = baos.toString();
	       baos.close();
	        outStream.close();
	        return Word2003Html;
	    }
	
}
