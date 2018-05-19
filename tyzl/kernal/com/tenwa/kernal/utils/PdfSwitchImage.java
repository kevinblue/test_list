package com.tenwa.kernal.utils;
import java.awt.image.BufferedImage; 
import java.awt.image.RenderedImage; 
import java.io.File;  
import java.io.IOException; 
import java.util.UUID;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

public class PdfSwitchImage {
	public static String getPdfPath(String pdfPath,String uploadDirPath){ 
		String newpath="";
		Document document = new Document(); 
		document.setFile(pdfPath);  
		float scale = 2.5f;//缩放比例 
		float rotation = 0f;//旋转角度  
			BufferedImage image = (BufferedImage)
			document.getPageImage(0, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
			RenderedImage rendImage = image; 
			try { 
				 String imgName = uploadDirPath+ "/"+UUID.randomUUID().toString().replace("-", "") + ".png"; //存放图片路径
				 File file = new File(imgName); //创建路径
				 ImageIO.write(rendImage, "png", file); //生成文件写入到指定的路径中,
				 newpath= imgName;
			} catch (IOException e) {
				  e.printStackTrace();  
			}
	     image.flush();  
		 document.dispose();  
		 return newpath;
	}
	
	public static void main(String[] args) {
		String filePath = "e:批次理赔个人索赔清单 .pdf";
		String newpath=getPdfPath(filePath,""); 
		System.out.println(newpath);
	}
}
