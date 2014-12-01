package com.darkdensity.core;

import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.setting.Config;
import com.darkdensity.setting.NLS;

public class GameCore extends JFrame implements ActionListener {
	/**
	 * -----Method----- public static void main(String[] args){ -Application
	 * entry point }
	 * 
	 * public GameCore(){ - Constructor for building main menu }
	 * 
	 * public void actionPerformed(ActionEvent e) { -Action listener for
	 * handling button's action }
	 * 
	 */
	JPanel jPanel; // main content panel
	private JButton singleBtn;
	private JButton multiplayerBtn;
	private JButton optionBtn;
	private JButton exitBtn;
	boolean notSelected = true;

	private GameManager gameWorld;

	public static void main(String[] args) {

		new GameCore();

	}

	public GameCore() {
		if (Config.IS_FULL_SCREEN) {
			setUndecorated(true);
			// Switch into full screen if the setting is true
			GraphicsEnvironment e = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			GraphicsDevice device = e.getDefaultScreenDevice();
			device.setFullScreenWindow(this);

		} else {
			setSize(Config.PANEL_WIDTH, Config.PANEL_HEIGHT);
			setUndecorated(Config.PANEL_IS_UNDECORATED);// set if show status
														// bar
		}

		this.gameWorld = null;
		this.setTitle(Config.PANEL_TITLE);
		this.setResizable(Config.PANEL_RESIZABLE);

		jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
		this.setContentPane(jPanel);

		singleBtn = new JButton(NLS.singleBtn);
		singleBtn.addActionListener(this);
		singleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(singleBtn);

		multiplayerBtn = new JButton(NLS.multiplayerBtn);
		multiplayerBtn.addActionListener(this);
		multiplayerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(multiplayerBtn);

		optionBtn = new JButton(NLS.optionBtn);
		optionBtn.addActionListener(this);
		optionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(optionBtn);

		exitBtn = new JButton(NLS.exitBtn);
		exitBtn.addActionListener(this);
		exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(exitBtn);
		this.setConfig();
		while (notSelected) {
		}
		// if right click
		// end
		// else
		// do nothing...
	}

	public void setConfig() {

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eo = e.getSource();
		if (eo.equals(this.singleBtn)) {
			// System.out.println("Single btn is clicked");
			try {
				this.gameWorld = new GameManager(Config.GAME_MODE_SINGLE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		} else if (eo.equals(this.multiplayerBtn)) {
			try {
				this.gameWorld = new GameManager(Config.GAME_MODE_MULTI);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
			// System.out.println("Multiplayer btn is clicked");
		} else if (eo.equals(this.optionBtn)) {
			// System.out.println("option btn is clicked");
		} else if (eo.equals(this.exitBtn)) {
			// System.out.println("exit btn is clicked");
			System.exit(0);
		} else {
			//System.out.println("Nothing happened");
		}
	}
	
	public void stop(){
		System.exit(0);
	}
}
