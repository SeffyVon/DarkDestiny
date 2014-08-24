package com.darkdensity.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.setting.Config;
import com.darkdensity.util.ImageLoader;
/*
 * ****************************************
 * Class: MiniMapPanel
 * ****************************************
 * Attributes:
	BufferedImage buffer = null;
	private int miniWidth = 192;
	private int miniHeight = 243;
	private int x = 0;
	private int y = 0;
 * *****************************************
 * Methods:
 * public MiniMapPanel(JFrame frame);
 * public void setRectangleXY(int x, int y) ;
 * public void redraw(Graphics g) ;
 * public void paintComponent(Graphics g);
 */
public class MiniMapPanel extends AbstractPanel {

	BufferedImage buffer = null;
	private int miniWidth = 192;
	private int miniHeight = 243;

	private int x = 0;
	private int y = 0;

	public MiniMapPanel(JFrame frame) {
		super(frame);
		miniWidth=192;
		miniHeight=243;
		setSize(miniWidth, miniHeight);// set the size
		setFocusable(false);
		setVisible(true);
		
		// buffer
		buffer = new BufferedImage(miniHeight, miniWidth,
				BufferedImage.TYPE_INT_ARGB);
	}

	public void setRectangleXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void redraw(Graphics g) {
		//System.out.println("MiniMapPanel: redraw()");
		Graphics2D g2 = (Graphics2D) buffer.getGraphics();
		// load the mini map image
		Image miniMap = ImageLoader.loadImage(Config.MINI_MAP_PATH
				+ "minimap.jpg");
		g2.drawImage(miniMap, 0, 0, null);
		g2.dispose();

		// draw the mini map on the screen
		g.drawImage(buffer, 0, 0, null);

		// draw the rectangle
		g.setColor(Color.red);
		g.drawRect(x / 20, y / 20, frame.getWidth()/20, frame.getHeight()/20);
	}
	
	public void paintComponent(Graphics g) {
		redraw(g);
	}

}
