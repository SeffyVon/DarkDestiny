package com.darkdensity.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.core.GameManager;
import com.darkdensity.setting.Config;
import com.darkdensity.units.Sprite;

public class TestSpritePanel extends JPanel  implements MouseListener,MouseMotionListener {

     //Double buffering
    private Image dbImage;
    private Graphics dbGraphics;
    
    public TestSpritePanel(JFrame frame) throws IOException{
    	setSize(Config.PANEL_WIDTH,Config.PANEL_HEIGHT);
    	setFocusable(true);
    	setVisible(true);
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	
    }
    
    public void draw(BufferStrategy bufferStrategy){
    	Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(GameManager.sprite.getAnimation().sprite, GameManager.sprite.getX(),GameManager.sprite.getY(), null);
		
		// the rectangle
		g.drawRect(GameManager.sprite.topX, GameManager.sprite.topY, GameManager.sprite.width, GameManager.sprite.length);
		
    	g.dispose();
    	bufferStrategy.show();
    }
    @Override
	public void mouseClicked(MouseEvent e) {
		
		Sprite sprite = GameManager.sprite;
		// inside the scope
		if( e.getX() >= sprite.getX() && e.getX() <= sprite.getX() + sprite.width 
		&& e.getY() >= sprite.getY() && e.getY() <= sprite.getY() + sprite.length ){
			//select the sprite
			GameManager.sprite.setSelected();
		}
		else{
			//do not select the sprite
			if(GameManager.sprite.beManipulated)
				GameManager.sprite.resetSelected();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Sprite sprite = GameManager.sprite;
		// inside the scope
		if( e.getX() >= sprite.getX() && e.getX() <= sprite.getX() + sprite.width 
		&& e.getY() >= sprite.getY() && e.getY() <= sprite.getY() + sprite.length ){
			//select the sprite
			GameManager.sprite.setSelected();
		}
		else{
			//do not select the sprite
			if(GameManager.sprite.beManipulated)
				GameManager.sprite.resetSelected();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		Sprite sprite = GameManager.sprite;
		// inside the scope
		if( e.getX() >= sprite.getX() && e.getX() <= sprite.getX() + sprite.width 
		&& e.getY() >= sprite.getY() && e.getY() <= sprite.getY() + sprite.length ){
			//highlight the sprite
			GameManager.sprite.setHighLight();
		}
		else if(sprite.beManipulated == false){
			//don't highlight it
			GameManager.sprite.resetHighLight();
			
		}
		
	}
   
}
