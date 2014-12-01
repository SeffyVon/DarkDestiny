package com.darkdensity.units;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Animation {
	private BufferedImage[] frames; //store all the frames (128 in total)
	public BufferedImage sprite; //current frame
	
	public boolean highlighted = false;
	private volatile boolean running = false;
	private long previousTime, speed;
	private int frameAtPause, currentFrame;
	private int direction;
	private int startFrame, endFrame;
	
	public static final int FACE_NORTH = 1;
	public static final int FACE_WEST = 2;
	public static final int FACE_SOUTH = 3;
	public static final int FACE_EAST = 4;
	public static final int FACE_NORTH_WEST = 5;
	public static final int FACE_SOUTH_WEST = 6;
	public static final int FACE_SOUTH_EAST = 7;
	public static final int FACE_NORTH_EAST = 8;
	
	public int getCurrentFrame(){
		return currentFrame;
	}
	
	
	public void createFrames(String filename) throws IOException{
		
		//south, west, east, north
		BufferedImage bigImg = ImageIO.read(new File(filename));
		
		//south-west, north-west, south-east, north-east
		BufferedImage bigImg_ISO = ImageIO.read(new File( filename.substring(0, filename.length()-4) + "_ISO.png" ));
		
		// (south, west, east, north) high light frames
		BufferedImage bigImgHl = ImageIO.read(new File( filename.substring(0, filename.length()-4) + "_highlight.png" ));
		
		//(south-west, north-west, south-east, north-east) high light frames
		BufferedImage bigImgHl_ISO = ImageIO.read(new File( filename.substring(0, filename.length()-4) + "_highlight_ISO.png" ));
		final int rows = 4;
		final int cols = 8;
		final int width = bigImg.getWidth()/cols;
		final int height = bigImg.getHeight()/rows;

		frames = new BufferedImage[4 * rows * cols];

		for (int i = 0; i < rows; i++)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        frames[(i * cols) + j] = bigImg.getSubimage(
		            j * width,
		            i * height,
		            width,
		            height
		        );
		    }
		}
		for (int i = 0; i < rows; i++)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        frames[ rows*cols + (i * cols) + j] = bigImg_ISO.getSubimage(
		            j * width,
		            i * height,
		            width,
		            height
		        );
		    }
		}
		
		
		// this is for highlight
		for (int i = 0; i < rows; i++)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        frames[2*rows*cols + (i * cols) + j] = bigImgHl.getSubimage(
		            j * width,
		            i * height,
		            width,
		            height
		        );
		    }
		}
		for (int i = 0; i < rows; i++)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        frames[ 3*rows*cols + (i * cols) + j] = bigImgHl_ISO.getSubimage(
		            j * width,
		            i * height,
		            width,
		            height
		        );
		    }
		}
	}
		
		public void setSpeed(long speed){
			this.speed = speed;
		}
		
		public void update(long time){
			
	        if(isRunning()){
	            if(time - previousTime >= speed){
	            	currentFrame = (currentFrame+1)%8 + startFrame;
	                previousTime = time;
	            }
	            if(highlighted){
	            	if(startFrame < 64)
	            		startFrame += 64;
	            	if(endFrame < 64)
	            		endFrame += 64;
	            	if(currentFrame < 64)
	            		currentFrame += 64;
	            }
	            else{
	            	startFrame %= 64;
	            	endFrame %= 64;
	            	currentFrame %= 64;
	            }
	        }else{// standing
	        	 if(highlighted){
		            	if(startFrame < 64)
		            		startFrame += 64;
		            	if(endFrame < 64)
		            		endFrame += 64;
		            	if(currentFrame < 64)
		            		currentFrame += 64;
		            	
		            }
		            else{
		            	startFrame %= 64;
		            	endFrame %= 64;
		            	currentFrame %= 64;
		            }
	        }
	        sprite = frames[currentFrame];
	       
	    }
	    
	    public void play(){
	        setRunning(true);
	        previousTime = 0;
	        frameAtPause = 0;
	        currentFrame = 0;
	    }
	    
	    public void stop(){

	    	switch (direction) {
			case FACE_SOUTH  :
				currentFrame = 0;
				break;
			case FACE_WEST  :
				currentFrame = 8;
				break;
			case FACE_EAST  :
				currentFrame = 16;
				break;
			case FACE_NORTH :
				currentFrame = 24;
				break;
			case FACE_SOUTH_EAST  :
				currentFrame = 48;
				break;
			case FACE_NORTH_WEST  :
				currentFrame = 40;
				break;
			case FACE_SOUTH_WEST  :
				currentFrame = 32;
				break;
			case FACE_NORTH_EAST :
				currentFrame = 56;
				break;
			default:
				break;
			}
	        setRunning(false);
	        previousTime = 0;
	        frameAtPause = 0;
	        sprite = frames[currentFrame];
	    }
	    
	    public void pause(){
	        frameAtPause = currentFrame;
	        setRunning(false);
	    }
	    
	    public void resume(){
	        currentFrame = frameAtPause;
	        setRunning(true);
	    }
	   
	    
	    public void setDirection(int direction){
	    	this.direction = direction;
	    	switch (direction) {
			case FACE_SOUTH  :
				startFrame = 0;
				endFrame = 7;
				break;
			case FACE_WEST  :
				startFrame = 8;
				endFrame = 15;
				break;
			case FACE_EAST  :
				startFrame = 16;
				endFrame = 23;
				break;
			case FACE_NORTH :
				startFrame = 24;
				endFrame = 31;
				break;
			case FACE_SOUTH_EAST  :
				startFrame = 48;
				endFrame = 55;
				break;
			case FACE_NORTH_WEST  :
				startFrame = 40;
				endFrame = 47;
				break;
			case FACE_SOUTH_WEST  :
				startFrame = 32;
				endFrame = 39;
				break;
			case FACE_NORTH_EAST :
				startFrame = 56;
				endFrame = 63;
				break;
			default:
				break;
			}

	    	
	    }

		void setHighLight(){
			highlighted = true;
		}
		
		void resetHighLight(){
			highlighted = false;
		}

		public boolean isRunning() {
			return running;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}
	
		public void setStandHighLight(){
			highlighted = true;
			
		}
	
		public void resetStandHighLight(){
			highlighted = false;
			
		}
	
 
}
