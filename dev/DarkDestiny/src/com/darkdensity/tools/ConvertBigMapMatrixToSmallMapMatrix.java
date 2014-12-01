package com.darkdensity.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConvertBigMapMatrixToSmallMapMatrix {
	
	private static int[][] bigMapMatrix;
	private static int[][] smallMapMatrix;
	
	//Element size to project to one point
	private static final int ELEMENT_SIZE = 16;
	//For shifting, it is faster doing like this
	private static final int ELEMENT_SIZE_BITS = 4;
	
	//path to read and save
	private static final String BIG_MATRIX_PATH="minimaps\\maps.txt";   
	private static final String SMALL_MATRIX_PATH="minimaps\\map_matrix.txt";   
	
	//the rows and columns number after projected
	private static int SMALL_MATRIX_ROWS;
	private static int SMALL_MATRIX_COLUMNS;
	
	/**
	 * Set the CONSTANT and set the width and height of the bigMapMatrix
	 */
	public static void main(String[] args) throws Exception {   

		ConvertBigMapMatrixToSmallMapMatrix t = new ConvertBigMapMatrixToSmallMapMatrix(4864,3840);
		System.out.println("finished!");
		
		/*Test for the readMapMatrix()
		bigMapMatrix=new int[4864][3840];
		readMapMatrix();
		System.out.print(bigMapMatrix[0][0]);
		System.out.print(bigMapMatrix[2000][2000]);
		System.out.print(bigMapMatrix[2100][2300]);
		*/
	}  
	
	public ConvertBigMapMatrixToSmallMapMatrix(int mapWidthPx,int mapHeightPx) {
		
		// >> divide
		SMALL_MATRIX_COLUMNS = mapWidthPx>>ELEMENT_SIZE_BITS;
		SMALL_MATRIX_ROWS = mapHeightPx>>ELEMENT_SIZE_BITS;
		System.out.println(SMALL_MATRIX_COLUMNS);
		System.out.println(SMALL_MATRIX_ROWS);
		
		File dir = new File(BIG_MATRIX_PATH);
		bigMapMatrix = new int[mapWidthPx][mapHeightPx];
		smallMapMatrix =new int[SMALL_MATRIX_COLUMNS][SMALL_MATRIX_ROWS];
		if(dir.exists()){
			readMapMatrix();
			System.out.println("finished! readMapMatrix()");
		}
		//convert the big map matrix into small map matrix
		convert2SmallMatrix();
		System.out.println("finished! convert2SmallMatrix()");
		
		//save the small map matrix as file
		saveMapMatrix();
		System.out.print("finished! saveMapMatrix()");
	}
	
	/**
	 * read the data from the file into big map matrix
	 */
	public static void readMapMatrix() {
		
		try {
			FileReader reader = new FileReader(BIG_MATRIX_PATH);
			BufferedReader br = new BufferedReader(reader);
			String s1 = null;
			int r = 0;
			while ((s1 = br.readLine()) != null) {
				for (int c = 0; c < s1.length(); c++) {
					bigMapMatrix[c][r] = s1.charAt(c);
				}
				r++;
			}
			br.close();
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * save small map matrix into file
	 */
	public void saveMapMatrix(){	  
         try {   
              FileWriter fw=new FileWriter(SMALL_MATRIX_PATH,true);   
              BufferedWriter bw=new BufferedWriter(fw);   
          	for (int r = 0; r < SMALL_MATRIX_ROWS; r++) {
				for (int c = 0; c < SMALL_MATRIX_COLUMNS; c++) {
					//save as ASCII 0 and 1
					bw.write(smallMapMatrix[c][r]+48); 
				}
				bw.newLine();   
          	}   
			    bw.close();  
			    fw.close();   
       } catch (IOException e) {   
            // TODO Auto-generated catch block   
           e.printStackTrace();   
       }   
	}
	
	/**
	 * convert the big map matrix into small map matrix
	 */
	public static void  convert2SmallMatrix() {
		//
		for (int r = 0; r < SMALL_MATRIX_ROWS; r++) {
			for (int c = 0; c < SMALL_MATRIX_COLUMNS; c++) {
				 getSmallFromBig(c,r);
			}
		}
	}
	
	/**
	 * Project one ELEMENT of big map matrix 
	 * corresponding to (x,y) in small map matrix
	 * into 1 or 0
	 */
	public static void  getSmallFromBig(int sc,int sr) {
		int w=0;
		//<< multiply
		int r=sr<<ELEMENT_SIZE_BITS;
		int c=sc<<ELEMENT_SIZE_BITS;
		System.out.println("r is"+r);
		System.out.println("c is"+c);
		
		//by seeing the four vertex of the ELEMENT and the center point
		//if three of them are 1, then the projected
		//point should be one 
		if(bigMapMatrix[c][r]>48){
			w++;
		}
		if(bigMapMatrix[c][r+ELEMENT_SIZE-1]>48){
			w++;
		}
		if(bigMapMatrix[c+ELEMENT_SIZE-1][r]>48){
			w++;
		}
		if(bigMapMatrix[c+ELEMENT_SIZE-1][r+ELEMENT_SIZE-1]>48){
			w++;
		}
		if(bigMapMatrix[c+ELEMENT_SIZE>>2][r+ELEMENT_SIZE>>2]>48){
			w++;
		}
		
		/* bad choice, slow and inefficient to 
		 * check every points of the ELEMENT
		for (; r < r+ELEMENT_SIZE; r++) {
			for (; c < ELEMENT_SIZE; c++) {
				w += (bigMapMatrix[c][r]);
			}
		}
		*/
		
		//if three of the point is 1 than the result should be 1
		smallMapMatrix[sc][sr]=w>3?1:0;
		System.out.println("smallMapMatrix[sc][sr] is"+smallMapMatrix[sc][sr]);
	}
			
}

