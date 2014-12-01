package com.darkdensity.tile;

import java.io.IOException;

import com.darkdensity.setting.Config;
import com.darkdensity.setting.Constant.PlayerRole;
import com.darkdensity.sound.Sound;
/**
 * 
* @ClassName: Survivor
* @Description: Survivor set up class
* @author Team A1 - Ting Yuen Lam
* @date Mar 28, 2014 11:04:25 AM
 */
public abstract class Survivor extends Sprite {
	public Survivor() throws IOException {
		super();
		this.role = PlayerRole.SURVIVOR;
		this.reveal = 800;
		this.sound = new Sound("res/music/yes sir.wav");
		this.sound_die_path = "res/music/man-dying.wav";
	}
}
