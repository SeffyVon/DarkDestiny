package com.darkdensity.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConvertImageToBigMapMatrix {
	
	//height and width is for initialize the size of int[][]
	private static int height;
	private static int width;
	
	/**
	 * read a image file and write 0,1 into a new txt file
	 */
	public static void main(String[] args){

		//change the Image into the int[][]
		int[][] map = getImageGRB("minimaps/BigMap_BW.jpg");
		
		//path to save the result
		 String path="minimaps/map.txt";   
         try {   
              FileWriter fw=new FileWriter(path,true);   
              BufferedWriter bw=new BufferedWriter(fw);   
              //write row by row
        	  for (int r = 0; r < height; r++) {
        		  for (int c = 0; c < width; c++) {
            		//change int into char and wirte to file
					bw.write(map[c][r]==1?"1":"0"); 
				}
				bw.newLine();   
          	}   
			    bw.close();  
			    fw.close();   
       } catch (IOException e) {   
            // TODO Auto-generated catch block   
           e.printStackTrace();   
       }   
         
         System.out.println("Finished!");
		
				
	}

	/**
	 * this function convert the image into 0,1 matrix
	 */
	public static int[][] getImageGRB(String filePath) {
        File file  = new File(filePath);
        int[][] result = null;
        if (!file.exists()) {
             return result;
        }
        try {
             BufferedImage bufImg = ImageIO.read(file);
	            height = bufImg.getHeight();
	            width = bufImg.getWidth();
	         //initialize the int matrix
             result = new int[width][height];
             
        	// column means x coordinate
     		// row means y coordinate
             for (int c = 0; c < width; c++) {
            	 for (int r = 0; r < height; r++) {
                	  int rgb = bufImg.getRGB(c, r);
                	  Color color = new Color(rgb);
                	  int colorValue = (color.getRed() + color.getGreen() + color.getBlue());
                	  //if the color is white, then the point should be 0
                	  result[c][r]= colorValue>20 ? 0:1;
                  }
             }
             
        } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }
        
        return result;
  }
}

