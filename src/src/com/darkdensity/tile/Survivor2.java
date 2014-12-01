package com.darkdensity.tile;

import java.io.IOException;

import com.darkdensity.setting.Config;
import com.darkdensity.sound.Sound;

/**
 * 
* @ClassName: Survivor2
* @Description: Survivor2 set up class
* @author Team A1 - Ting Yuen Lam
* @date Mar 28, 2014 11:03:41 AM
 */

public class Survivor2 extends Survivor {

	public Survivor2() throws IOException {
		super();
		initAnimation(Config.SPRITE_PATH + "human2/",  Config.PLAYER_ROLE ==  role);
		this.speed = STEP_VELOCITY = Config.SURVIVOR2_SPEED;
		this.maxHealth = this.health = Config.SURVIVOR2_MAX_HEALTH;
		this.attack = Config.SURVIVOR2_ATTACK;
		this.defense = 40;
		this.reveal = Config.SURVIVOR2_REVEAL;
//		this.sound = new Sound("res/music/ready_to_go.wav");
	}
}
