package com.darkdensity.setting;

import java.awt.DisplayMode;

public class Config {
	//Game Setting
	public static int GAME_WINNING_TIME = 60;
	
	
	// General setting
	public static int PANEL_WIDTH = 1024;
	public static int PANEL_HEIGHT = 768;
	public static int SENSITIVE_AREA = 20;
	public static int SCREEN_MOVE_SPEED = 30;
	public static final boolean IS_FULL_SCREEN = true;
	//public static final String NLS_NAME = "com.darkdensity.setting.NLS_zh_HK";
	public static final String NLS_NAME = "com.darkdensity.setting.NLS";
	
	// 768 is too large for my laptop
	public static final String PANEL_TITLE = "Dark Destiny";
	public static final boolean PANEL_IS_UNDECORATED = true;
	public static final boolean PANEL_RESIZABLE = false;
	public static final int PANEL_STARTUP_POSITION_X = 0;
	public static final int PANEL_STARTUP_POSITION_Y = 0;
	
	// Resource path
	public static final String MAP_PATH = "res/images/map/classic/";
	public static final String MINI_MAP_PATH = "res/images/map/minimap/";
	public static final String BACKGROUND_MUSIC_PATH ="res/music/bg.wav";
	public static final String MUSIC_WINNING_PATH ="res/music/winning.wav";
	public static final String IMG_WINNING_PATH = "res/images/youwin.png";
	public static final String IMG_LOSING_PATH = "res/images/youdied.png";
	public static final String MUSIC_LOSING_PATH = "res/music/losing.wav";
	public static final String LOOBY_BACKGROUND = "res/images/lobby/gameLobby.png";
	
	
	// Constant
	public static final String PLAYER_NAME = "Bowie"; // Since multiplayer is
														// not implement yet,
														// this's for demo only
	
	public static final int CHAT_PORT = 5000;
	public static final String CHAT_GROUP = "225.4.5.6";
	
	public static final int GAME_MODE_SOLO = 1;
	public static final int GAME_MODE_COOP = 2;
	public static final int GAME_MODE_VS = 3;

	public static final int FRAMES_PER_SECOND = 60;
    public static final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
    
	public static final int GAME_LOOP_TIMER_DELAY = 10;
}