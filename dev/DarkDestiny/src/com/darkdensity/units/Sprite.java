package com.darkdensity.units;



public class Sprite {
    private Animation animation;
    private int x, y; // position
    private int vx, vy; //velocity
    private int direction;
    
    //to see the scope of the people, for mouse selection and collision detection
    public int topX, topY;
    public static final int width=40, length = 73;
    
    static int count = 0;//if only count == 2 then the player walk a step, this is to slow down the step velocity.
	
    public static final int FACE_NORTH = 1;
	public static final int FACE_WEST = 2;
	public static final int FACE_SOUTH = 3;
	public static final int FACE_EAST = 4;
	public static final int FACE_NORTH_WEST = 5;
	public static final int FACE_SOUTH_WEST = 6;
	public static final int FACE_SOUTH_EAST = 7;
	public static final int FACE_NORTH_EAST = 8;
    public static final int STEP_VELOCITY = 1;
    
    public boolean beManipulated = false; // specific for select
    
    public Sprite(Animation animation){
    	this.animation = animation;
    	
    }
    
    public void walkStep(int direction){
    	switch (direction) {
		case FACE_SOUTH  :
			vx = 0;
			vy = STEP_VELOCITY;
			break;
		case FACE_WEST  :
			vx = -STEP_VELOCITY;
			vy = 0;
			break;
		case FACE_EAST  :
			vx = STEP_VELOCITY;
			vy = 0;
			break;
		case FACE_NORTH :
			vx = 0;
			vy = -STEP_VELOCITY;
			break;
		case FACE_SOUTH_EAST  :
			vx = STEP_VELOCITY;
			vy = STEP_VELOCITY;
			break;
		case FACE_NORTH_WEST  :
			vx = -STEP_VELOCITY;
			vy = -STEP_VELOCITY;
			break;
		case FACE_SOUTH_WEST  :
			vx = -STEP_VELOCITY;
			vy = STEP_VELOCITY;
			break;
		case FACE_NORTH_EAST :
			vx = STEP_VELOCITY;
			vy = -STEP_VELOCITY;
			break;
		default:
			break;
    	}
    	animation.setDirection(direction);
    	this.direction = direction;
    	count = (count+1)%3;
    	if(count == 2){
    	x += vx;
    	y += vy;}
    	animation.update(System.currentTimeMillis());
    	topX = x + 15; // the sprite is too thin 
    	topY = y ;
    	
    }
	
    public void stop(){
    	animation.stop();
    	
    }
    
    public Animation getAnimation(){
    	return animation;
    }
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVx() {
		return vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	public void setSelected(){
		beManipulated = true;
	//	System.out.println("be selected");
		if(!animation.isRunning()){
			animation.setStandHighLight();
			animation.update(System.currentTimeMillis());
		}else{
			animation.setHighLight();
		}
		
	}
	
	public void resetSelected(){
	//	System.out.println("be unselected");
		beManipulated = false;
		if(!animation.isRunning()){
			animation.resetStandHighLight();
			animation.update(System.currentTimeMillis());
		}
		else{
			animation.resetHighLight();
		}
	}
	
	public void setHighLight(){
		//player select by clicking or dragging 
		animation.setHighLight();
		
		if(!animation.isRunning()){
			animation.setStandHighLight();
			animation.update(System.currentTimeMillis());
		}
	}
	public void resetHighLight(){
		animation.resetHighLight();
		if(!animation.isRunning()){
			animation.resetStandHighLight();
			animation.update(System.currentTimeMillis());
		}
	}
	
	
	
	
 }