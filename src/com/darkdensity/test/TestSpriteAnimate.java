package com.darkdensity.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import com.darkdensity.tile.Animation;


public class TestSpriteAnimate extends JFrame{
	
	 public static void main(String[] args) throws IOException {
		 TestSpriteAnimate testSpriteAnimate = new TestSpriteAnimate();
	 }
	 
	Animation anim;
	BufferedImage sprite;
	
	 public TestSpriteAnimate() throws IOException {
		
		setSize(800, 600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
   }
	 
	 private void init() throws IOException{
		 anim = new Animation();
		 anim.createFrames("res/images/sprite/human1.png");
		 anim.setSpeed(100);
		 anim.play();
		 anim.setDirection(anim.FACE_NORTH);
	 }

	 Image dbImage;
	 Graphics dbg;
	 
	 @Override
     public void paint(Graphics g){
		 dbImage = createImage(getWidth(), getHeight());
	 	 dbg = dbImage.getGraphics();
	 	 paintComponent(dbg);
	 	 g.drawImage(dbImage, 0, 0, null);
	 }
	 
	 public void paintComponent(Graphics g){
		 if(anim != null){
			 anim.update(System.currentTimeMillis());
			 g.drawImage(anim.sprite, 100, 100, 50, 50, null);
		 }
		 	System.out.println("~~");
		 	repaint();
	 }
	 
	
	 
	
}
