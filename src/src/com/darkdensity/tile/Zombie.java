package com.darkdensity.tile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import sun.nio.cs.ext.TIS_620;

import com.darkdensity.core.GameWorld;
import com.darkdensity.core.GridMapManager;
import com.darkdensity.core.TileManager;
import com.darkdensity.setting.Config;
import com.darkdensity.setting.Constant.PlayerRole;
import com.darkdensity.sound.Sound;

/**
 * 
* @ClassName: Zombie
* @Description: Zombie set up class
* @author Team A1 - Ting Yuen Lam
* @date Mar 28, 2014 11:02:28 AM
 */

public abstract class Zombie extends Sprite {

	long currentTime = 0;
	long updateUnitTime = 1000;

	public Zombie() throws IOException {
		super();
		this.role = PlayerRole.ZOMBIE;
		this.reveal = 700;
		this.sound = new Sound("res/music/creepy-low-voice.wav");
		this.sound_die_path = "res/music/man-dying.wav";
	}

	@Override
	public void update(long elapsedTime) throws IOException {
		super.update(elapsedTime);

		currentTime += elapsedTime;
		if (currentTime > updateUnitTime) {
			currentTime -= updateUnitTime;
			if (!Config.IS_NETWORK_MODE) {
				for (Survivor survivor : tileManager.getSurivors()) {
					if (survivor.isDestroyed()) {
						continue;
					}
					setDestination(survivor.getX() + survivor.getTileWidth()
							/ 2, survivor.getY() + survivor.getTileHeight() / 2);
					setTargetTile(survivor);
					break;
				}
			} else {
				Sprite s = tileManager.getNearestHuman(this);
				if (s != null) {
					// System.out.println("X: "+ s.getX() + " Y: "+ s.getY());
					setDestination(s.getX() + s.getTileWidth() / 2, s.getY()
							+ s.getTileHeight() / 2);
					setTargetTile(s);
				}
			}
		}

		super.toNextPoint();
	}
}
