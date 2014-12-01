package com.darkdensity.tile;

import java.awt.Graphics;
import java.io.IOException;

import com.darkdensity.core.TileManager;
import com.darkdensity.player.Resource;
import com.darkdensity.setting.Config;
import com.darkdensity.setting.Constant;

/** 
 * 
* @ClassName: SmallBarricade
* @Description: 
* @author Team A1 - Ting Yuen Lam
* @date Mar 28, 2014 11:05:08 AM
 */

public class SmallBarricade extends Barricade {
	public SmallBarricade() throws IOException{
		super();
		this.tileWidth = 60;
		this.tileHeight = 60;
		this.setSize(tileWidth, tileHeight);
		this.imageOffset =  2 * Constant.BARRICADE_MAX_LEVEL;
		this.bufferedImages = Constant.getFrames(Config.BARRICADE_PATH + "smallBarricade.png",  Config.BARRICADE_PATH + "smallBarricade" + highlight , 2, Constant.BARRICADE_MAX_LEVEL);
		this.blockingMap = Constant.SB_BLOCKINGAREA;
		this.maxHealth = Config.SB_MAX_HEALTH;
		this.consumeResource = Config.SB_RESOURCE;
	}
	
	public SmallBarricade(int x, int y, TileManager spriteManager)
			throws IOException {
		super(x, y, spriteManager);
		this.tileWidth = 60;
		this.tileHeight = 60;
		this.setSize(tileWidth, tileHeight);
		this.imageOffset =  2 * Constant.BARRICADE_MAX_LEVEL;
		this.bufferedImages = Constant.getFrames(Config.BARRICADE_PATH + "smallBarricade.png",  Config.BARRICADE_PATH + "smallBarricade" + highlight, 2, Constant.BARRICADE_MAX_LEVEL);
		this.consumeResource = Config.SB_RESOURCE;
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}
}
