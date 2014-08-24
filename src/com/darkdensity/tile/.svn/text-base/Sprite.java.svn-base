package com.darkdensity.tile;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/* ****************************************
 * Class: Sprite
 * ****************************************
 * Attributes:
 *  private Animation animation;
    private int x, y; // position
    private int vx, vy; //velocity
    private int dx, dy; // destination
    private int direction;
    private int category;
    private SpriteManager spriteManager;
    public static final int HUMAN1 = 1;
    public static final int ZOMBIE = 0;
    public static final int HUMAN2 = 2;
    public int spriteID;
    private boolean dead = false;
    private BufferedImage deadPicture;
    public int topX, topY;    //to see the scope of the people, for mouse selection and collision detection
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
    private int STEP_VELOCITY;
    public boolean beManipulated = false; // specific for select
 * ****************************************
 * Methods:
 * 	int getCategory(); //return the category of the sprite 
	void walkStep(int spriteID);// walk a step 
	void stop();
	void getAnimation();
	int getX();
	int getY();
	void setX(int);
	void setY(int);
	int getVx()
	int getVy()
	void setVx(int)
	void setVy(int)
	void setSelected()
	void resetSelected()
	void setHighLight()
	void resetHighLight()
	void getSize()
	void getDefence()
	void draw(Graphics2D, int, int)
	int getDestinationY()
	void setDestinationY(int)
	int getDestinationX()
	void setDestinationX(int)
	int getDirection()
	void setDirection(int)
	void update(); // update the status of the sprite
	void setTheSpriteDestination(int x, int y);//
	void die();
	boolean isTouched(int sx, int sy, int ex, int ey, int x, int y, int width, int length);
	isDead();
	getDeadBufferedImage();
	getType(); // Tile class unimplemented
 * */

public class Sprite extends AbstractTile {
    private Animation animation;
    private int x, y; // position
    private int vx, vy; //velocity
    private int dx, dy; // destination
    private int direction;
    private int category;
    private SpriteManager spriteManager;
    public static final int HUMAN1 = 1;
    public static final int ZOMBIE = 0;
    public static final int HUMAN2 = 2;
    public int spriteID;
    private boolean dead = false;
    private BufferedImage deadPicture;
    
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
    private int STEP_VELOCITY;
    
    public boolean beManipulated = false; // specific for select

    public Sprite(int x, int y, int category, SpriteManager spriteManager) throws IOException{
    	super(count);
    	this.spriteID = spriteID;
    	this.x = x;
    	this.y = y;
    	this.dx = x;
    	this.dy = y;
    	this.category = category;
    	this.spriteManager = spriteManager;
    	if(category == HUMAN1){
    		animation = new Animation();
    		animation.createFrames("res/images/sprite/human1/human1.png");
    		animation.setSpeed(100);
    		animation.play();
    		STEP_VELOCITY = 2;
    	}else if(category == HUMAN2){
    		animation = new Animation();
    		animation.createFrames("res/images/sprite/human2/human2.png");
    		animation.setSpeed(100);
    		animation.play();
    		STEP_VELOCITY = 2;
    	}else{ // zombie
    		animation = new Animation();
    		animation.createFrames("res/images/sprite/zombie1/zombie1.png");
    		animation.setSpeed(100);
    		animation.play();
    		STEP_VELOCITY = 1;
    	}
    	
    }
    
  //return the category of the sprite 
    public int getCategory(){
    	return category;
    }
    
    public void walkStep(int direction){
    	// walk a step according to the direction
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
    	this.setDirection(direction);
    	
    	// update the position
    	x += vx;
    	y += vy;
    	animation.setRunning(true);
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
		 System.out.println("set stand selected in sprite");
		if(!animation.isRunning()){
			animation.setStandHighLight();
			animation.update(System.currentTimeMillis());
		}else{
			animation.setHighLight();
		}
		
	}
	
	public void resetSelected(){
	
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
		
		if(!animation.isRunning()){
			animation.setStandHighLight();
			animation.update(System.currentTimeMillis());
		}else
			animation.setHighLight();
	}
	public void resetHighLight(){
		
		if(!animation.isRunning()){
			animation.resetStandHighLight();
			animation.update(System.currentTimeMillis());
		}else
			animation.resetHighLight();
	}

	@Override
	public Point getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getDefence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics2D g, int offsetX, int offsetY) {
		System.out.println("draw sprite:" + offsetX + offsetY);
	//	g.drawImage(animation.sprite, x, y, null);
		
	}

	public int getDestinationY() {
		return dy;
	}

	public void setDestinationY(int dy) {
		this.dy = dy;
	}

	public int getDestinationX() {
		return dx;
	}

	public void setDestinationX(int dx) {
		this.dx = dx;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void update() throws IOException {
		
		if(category == ZOMBIE){
			// if this is a zombie, to be modified to subclass
			Sprite nearestSprite = spriteManager.getNearestHuman(this);
			if(nearestSprite != null){
			setDestinationX(nearestSprite.getX());
			setDestinationY(nearestSprite.getY());
			}else{
				
			}
		}else{
			// if this is human
			if(dead)
				return;
			// it needs to detect the zombies position
			for (int i = 0; i<spriteManager.getZombiesNum();i++){
				//detect die
				Sprite zombie = spriteManager.getZombie(i);
				if(isTouched(zombie.x,zombie.y,zombie.x+zombie.width,zombie.y+zombie.length,x,y,width,length) && dead == false){
					
					System.out.println("die:zombie.x"+zombie.x+"zombie.y"+zombie.y+"x"+x+"y"+y);
					this.die();
				}
			}
		}
			
			int distanceX = getDestinationX() - getX();
			int distanceY = getDestinationY() - getY();
			
			int direction = 0;
			if (distanceX == 0 && distanceY == 0) {
				if(this.animation.isRunning())
					this.stop();
				return;
			}
			if(distanceX == 0 && distanceY>0){
				direction = FACE_SOUTH;
				return;
			}
			if(distanceX == 0 && distanceY<0){
				direction = FACE_NORTH;
				return;
			}
			double tanDest = distanceY/distanceX;
			//tangent value;
			//-22.5 ~ 22.5 -> -0.41 ~ 0.41 east
			//22.5 ~ 67.5 ->  0.41 ~ 2.41 south east
			//67.5 ~ 112.5 -> ~-2.41 && 2.41 ~ south
			//112.5 ~ 157.5 -> -2.41 ~ -0.41 south west 
			
			//157.5 ~ 202.5 -> -0.41 ~ 0.41 west
			//202.5 ~ 247.5 -> 0.41 ~ 2.41 north west
			//247.5 ~ 292.5 -> && ~-2.41 && 2.41 ~ north
			//292.5 ~ 337.5 -> -2.41 ~ -0.41 north east
			
			if(distanceX > 0){
				if(tanDest > -0.41 && tanDest <= 0.41)
					direction = FACE_EAST;
				else if(tanDest > 0.41 && tanDest <= 2.41)
					direction = FACE_SOUTH_EAST;
				else if(tanDest > 2.41)
					direction = FACE_SOUTH;
				else if(tanDest <-0.41 && tanDest >= -2.41)
					direction = FACE_NORTH_EAST;
				else 
					direction = FACE_NORTH;
				
			}else{
				if(tanDest > -0.41 && tanDest <= 0.41)
					direction = FACE_WEST;
				else if(tanDest > 0.41 && tanDest <= 2.41)
					direction = FACE_NORTH_WEST;
				else if(tanDest > 2.41)
					direction = FACE_NORTH;
				else if(tanDest <-0.41 && tanDest >= -2.41)
					direction = FACE_SOUTH_WEST;
				else 
					direction = FACE_SOUTH;
			}
			
		walkStep(direction);
		
	}

	public void setTheSpriteDestination(int dx, int dy) {
		setDestinationX(dx);
		setDestinationY(dy);
	}
	
	//if it is dead
	public void die() throws IOException{
		this.dead = true;
		Random generator = new Random();
		int line = generator.nextInt(4);
		if(category == HUMAN1){
			BufferedImage allDeadPicsBufferedImage = ImageIO.read(new File("res/images/sprite/human1/human1_dead.png"));
			int width = allDeadPicsBufferedImage.getWidth();
			int height = allDeadPicsBufferedImage.getHeight();
			deadPicture = allDeadPicsBufferedImage.getSubimage(width/3*1, height/4*line, width/3, height/4);
		}
		else if(category == HUMAN2){
			BufferedImage allDeadPicsBufferedImage = ImageIO.read(new File("res/images/sprite/human2/human2_dead.png"));
			int width = allDeadPicsBufferedImage.getWidth();
			int height = allDeadPicsBufferedImage.getHeight();
			deadPicture = allDeadPicsBufferedImage.getSubimage(width/3*1, height/4*line, width/3, height/4);
		}
	}
	
	
	boolean isTouched(int sx, int sy, int ex, int ey, int x, int y, int width, int length){
		// to see if the two sprites touch or not
		if(sx >= ex && sy > ey ){ // from bottom right to top left 
			Rectangle rect1 = new Rectangle(ex,ey,Math.abs(sx-ex),Math.abs(sy-ey));
			Rectangle rect2 = new Rectangle(x,y,width,length);
			return rect1.intersects(rect2) ;
		
		}
		if(sx <= ex && sy > ey  ){// from bottom left to top right 
			Rectangle rect1 = new Rectangle(sx,ey,Math.abs(sx-ex),Math.abs(sy-ey));
			Rectangle rect2 = new Rectangle(x,y,width,length);
			return rect1.intersects(rect2) ;
			}
		if(sx <= ex && sy < ey){// from top left to bottom right
			Rectangle rect1 = new Rectangle(sx,sy,Math.abs(ex-sx),Math.abs(ey-sy));
			Rectangle rect2 = new Rectangle(x,y,width,length);
			return rect1.intersects(rect2) ;
		}
		else { // from top right to bottom left
			Rectangle rect1 = new Rectangle(ex,sy,Math.abs(sx-ex),Math.abs(ey-sx));
			Rectangle rect2 = new Rectangle(x,y,width,length);
			return rect1.intersects(rect2) ;

		}
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public BufferedImage getDeadBufferedImage(){
		return deadPicture;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}
 }