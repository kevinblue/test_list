package com.reckon.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tenwa.business.entity.AppImage;

public class ScaleImageUtils {
	
	private static int widthAndHeight = 200;
	
	public static void createThumbAppImage(AppImage appImage) throws Exception {
		Log logger = LogFactory.getLog(ScaleImageUtils.class);
		
		File imageFile = new File(appImage.getImagePath());
		appImage.setImageSize(imageFile.getTotalSpace());
		logger.info("图片上传之文件全名:" + imageFile.getAbsolutePath());
		
		String fileName = imageFile.getName();
		int splitIndex = fileName.lastIndexOf(".");
		appImage.setFileName(fileName);
		String type = fileName.substring(splitIndex + 1, fileName.length()).toUpperCase();
		appImage.setImageType(type);
		
		logger.info("图片上传之文件标题:" + appImage.getTitle());
		logger.info("图片上传之文件类型:" + type);
		
		Image image = ImageIO.read(imageFile);
		
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		appImage.setImageWidth(imageWidth);
		appImage.setImageHeight(imageHeight);
		
		int thumbWidth = appImage.getThumbImageWidth();
		int thumbHeight = appImage.getThumbImageHeight();
		
		double imageRatio = (double) imageWidth / (double) imageHeight;
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}

		if (imageWidth < thumbWidth && imageHeight < thumbHeight) {
			thumbWidth = imageWidth;
			thumbHeight = imageHeight;
		} else if (imageWidth < thumbWidth) {
			thumbWidth = imageWidth;
		} else if (imageHeight < thumbHeight) {
			thumbHeight = imageHeight;
		}
		
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		ImageIO.write(thumbImage, type, new File(appImage.getThumbImagePath()));
	}
	
	public static void main(String[] args) {
		System.out.println("123".matches("1234|234"));
	}
}