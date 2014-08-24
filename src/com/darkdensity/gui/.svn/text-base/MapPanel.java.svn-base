package com.darkdensity.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.maprender.Map;

/*
 * ****************************************
 * Class: MapPanel
 * ****************************************
 * Attributes:
 * private Map map;
 * private JFrame frame;
 * *****************************************
 * Methods:
 * public MapPanel(JFrame frame);
 * public Map getMap();
 * public void paintComponent(Graphics g);
 * public void update(long elapsedTime);
 * public void reset();
 */
public class MapPanel extends JPanel {
	private Map map;
	private JFrame frame;
	
	private int iRenderX=0;
	private int iRenderY=0;
	

	public MapPanel(JFrame frame) {
		this.frame = frame;
		setLayout(null);
		setSize(frame.getWidth(), frame.getHeight());
		map = new Map("BigMap");
	}

	public Map getMap() {
		return map;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// System.out.println("MapPanel: paintComponent()");
		// System.out.println("MapPanel.getWidth: " + getWidth() +
		// " getHeight: " + getHeight());
		Image i = map.redraw((Graphics2D) g, getWidth(), getHeight());
		g.drawImage(i, 0-iRenderX, 0-iRenderY, this);
	}

	public void update(long elapsedTime) {
	}

	public void reset() {
		// item.elastic.slide(0, 100);
	}
	
	public void setPainXY(int x, int y){
		iRenderX=x;
		iRenderY=y;
	}

}
