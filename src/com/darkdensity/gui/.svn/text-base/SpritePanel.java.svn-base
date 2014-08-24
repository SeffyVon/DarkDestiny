package com.darkdensity.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.darkdensity.tile.Sprite;
import com.darkdensity.tile.SpriteManager;

/*
 * ****************************************
 * Class: SpritePanel
 * ****************************************
 * Attributes:
 private Image dbImage;
 private Graphics dbGraphics;
 private int iRenderX;
 private int iRenderY;

 private SpriteManager spriteManager;

 BufferedImage bf;
 JLabel label;
 * *****************************************
 * Methods:
 * public SpritePanel(SpriteManager spriteManager,JFrame frame);
 * public void paintComponent(Graphics g);
 * public int getiRenderX();
 * public void setiRenderX(int iRenderX) ;
 * public int getiRenderY();
 * public void setiRenderY(int iRenderY) ;
 * public void reset();
 */
public class SpritePanel extends JPanel {

	// Double buffering
	private Image dbImage;
	private Graphics dbGraphics;
	private int iRenderX;
	private int iRenderY;

	private SpriteManager spriteManager;

	BufferedImage bf;
	JLabel label;

	public SpritePanel(SpriteManager spriteManager, JFrame frame) {
		// setSize(Config.PANEL_WIDTH,Config.PANEL_HEIGHT);
		this.setLayout(null);
		setSize(frame.getWidth(), frame.getHeight());
		setFocusable(true);
		setVisible(true);
		setOpaque(false);
		this.spriteManager = spriteManager;
	}

	// @Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		ArrayList<Sprite> sprites = spriteManager.getAllTheSprites();
		for (int i = 0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (!sprite.isDead())
				this.getGraphics().drawImage(sprite.getAnimation().sprite,
						sprite.getX() - iRenderX, sprite.getY() - iRenderY,
						this);
			else {
				this.getGraphics().drawImage(sprite.getDeadBufferedImage(),
						sprite.getX() - iRenderX, sprite.getY() - iRenderY,
						this);
			}

		}
	}

	public int getiRenderX() {
		return iRenderX;
	}

	public void setiRenderX(int iRenderX) {
		this.iRenderX = iRenderX;
	}

	public int getiRenderY() {
		return iRenderY;
	}

	public void setiRenderY(int iRenderY) {
		this.iRenderY = iRenderY;
	}

	public boolean isLose() {
		boolean isLose = true;
		for (Sprite s : spriteManager.getAllTheSprites()) {
			if (s.getCategory() == Sprite.ZOMBIE) {
				continue;
			}
			if (!s.isDead()) {
				isLose = false;
			}
		}
		return isLose;
	}
}
