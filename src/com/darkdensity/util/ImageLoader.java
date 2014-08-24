package com.darkdensity.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * @ClassName: ImageLoader
 * @Description: Load Image
 * @author Team A1,Birmingham University
 * @date 2014/2/1 16:57:41
 * 
 * **************************
 * Attributes:
 * private static Map<String, Image> IMAGE_CACHE = new HashMap<String, Image>();
 * **************************
 * Methods:
 * public static Image loadImage(String filePath) {}
 */
public class ImageLoader {

	// create cache for the images
	private static Map<String, Image> IMAGE_CACHE = new HashMap<String, Image>();

	public static Image loadImage(String filePath) {
		if (!IMAGE_CACHE.containsKey(filePath)) {
			Image image = new ImageIcon(filePath).getImage();
			if (image != null) {
				IMAGE_CACHE.put(filePath, image);
			} else {
				return null;
			}
		}
		return IMAGE_CACHE.get(filePath);
	}

}
