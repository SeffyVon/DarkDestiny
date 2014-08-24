package com.darkdensity.core;

import java.awt.Color;
import java.awt.DisplayMode;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.darkdensity.gui.MainMenuPanel;
import com.darkdensity.setting.Config;



/* ****************************************
 * Class: GameCore
 * ****************************************
 * Attributes:
 * private JPanel displayPanel;
 * ****************************************
 * Methods:
 * 	N/A
 * */

public class GameCore {

	private JPanel displayPanel;
	
	public GameCore() {
		//Switch to full screen 
		ScreenManager screen = new ScreenManager();
		DisplayMode displayMode = screen.getCurrentDisplayMode();
		screen.setFullScreen(displayMode);
		
		//Initialize frame
		JFrame frame = screen.getFullScreenWindow();
		frame.setBackground(Color.white);
		frame.setForeground(Color.white);
		frame.setTitle(Config.PANEL_TITLE);
		frame.setLayout(null);
		((JComponent) frame.getContentPane()).setOpaque(false);
		frame.setVisible(true);
		
		//Show main menu
		MainMenuPanel mp = new MainMenuPanel(frame);
		displayPanel = mp;
		frame.setContentPane(displayPanel);
		
		//Play background music
		MusicPlayer mu = new MusicPlayer(Config.BACKGROUND_MUSIC_PATH);
		mu.start();
	}


}
