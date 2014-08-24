package com.darkdensity.tile;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.gui.SpritePanel;


/* ****************************************
 * Class: SpriteManager
 * ****************************************
 * Attributes: 	
	ArrayList<Sprite> zombies;
	ArrayList<Sprite> humans;
	SpritePanel spritePanel; // to manage the spritePanel
 * ****************************************
 * Methods:
 * public SpriteManager(JFrame frame) throws IOException;
 * public Sprite getNearestHuman(Sprite zombie); // get nearest human, if there is no, return null
 * public int getHumansNum();
 * public int getZombiesNum();
 * public Sprite getHuman(int spriteID);
 * public Sprite getZombie(int spriteID);
 * public ArrayList<Sprite> getAllTheSprites();
 * public SpritePanel getSpritePanel();
 * */
public class SpriteManager {
	
	ArrayList<Sprite> zombies;
	ArrayList<Sprite> humans;
	SpritePanel spritePanel;
	
	public SpriteManager(JFrame frame) throws IOException{
		humans = new ArrayList<Sprite>();
		zombies = new ArrayList<Sprite>();
		
		Sprite sprite = new Sprite( 2432 + 100, 1920 + 300, Sprite.HUMAN1, this);
		Sprite sprite2 = new Sprite( 2432 + 100, 1920 +500, Sprite.HUMAN2,this);
		humans.add(sprite);
		humans.add(sprite2);
		
		Sprite sprite3 = new Sprite(2432+800, 1920 + 300, Sprite.ZOMBIE,this);
		Sprite sprite4 = new Sprite(2432+ 800, 1920 + 400, Sprite.ZOMBIE,this);
		
		zombies.add(sprite3);
		zombies.add(sprite4);
		
		spritePanel = new SpritePanel(this,frame);
	}
	
	public Sprite getNearestHuman(Sprite zombie){
		
		int nearestDistanceSqrt = 100000;
		int nearestHumanID = 100000;
		for(int i =0 ;i<humans.size(); i++){
			if(humans.get(i).isDead())
				continue;
			int distanceX = humans.get(i).getX() - zombie.getX();
			int distanceY = humans.get(i).getY() - zombie.getY();
			int distanceSqrt = (int)(Math.sqrt(distanceX)+ Math.sqrt(distanceX));
			if(distanceSqrt < nearestDistanceSqrt ){
				nearestDistanceSqrt = distanceSqrt;
				nearestHumanID = i;
			}
		}
		if(nearestHumanID == 100000)
			return null;
		return humans.get(nearestHumanID);
	}
	
	public int getHumansNum() {
		return humans.size();
	}
	
	public int getZombiesNum() {
		return zombies.size();
	}

	public Sprite getHuman(int spriteID) {
		return humans.get(spriteID);
	}
	
	public Sprite getZombie(int spriteID) {
		return zombies.get(spriteID);
	}
	
	public ArrayList<Sprite> getAllTheSprites(){
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(humans);
		sprites.addAll(zombies);
		return sprites;
	}
	
	public SpritePanel getSpritePanel(){
		return spritePanel;
	}
	
}
