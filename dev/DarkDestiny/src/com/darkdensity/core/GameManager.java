package com.darkdensity.core;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.darkdensity.commond.Command;
import com.darkdensity.gui.MiniMapPanel;
import com.darkdensity.gui.TestSpritePanel;
import com.darkdensity.maprender.Map;
import com.darkdensity.net.chat.ChatClient;
import com.darkdensity.setting.Config;
import com.darkdensity.units.Animation;
import com.darkdensity.units.Sprite;


public class GameManager extends JFrame {
	
	private SoundManager soundManager;
	private ScreenManager screenManager;
	private GridManager gridManager;
	private ControlManager controlManager;

	// private map map = new map();
	private Map map = new Map("BigMap");
	private MiniMapPanel miniMapPanel;
	private ChatClient chatClientPanel;
	private TestSpritePanel testSpritePanel;
	private Player player; // solo mode
	private ArrayList<Command> gameCommandList;
	
	public static Sprite sprite;
	private int gameMode;
	private boolean isRunning;
	BufferStrategy strategy;
	int step;
	
	//keyboard listen
	private KeyAction exit;
	
	public GameManager(int gameMode) throws IOException{
		
		//Set the display mode
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

		this.gameMode = gameMode;
		setTitle(Config.PANEL_TITLE);// set the title
		setResizable(Config.PANEL_RESIZABLE);// forbid resize
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// add mini map
		miniMapPanel = new MiniMapPanel();
		miniMapPanel.setLocation(Config.PANEL_STARTUP_POSITION_X,
				Config.PANEL_STARTUP_POSITION_Y);
		add(miniMapPanel);
		
		
		this.chatClientPanel = new ChatClient();
		// TODO: TO fix full screen bug
		// this.chatClientPanel.setLocation(this.getWidth()-chatClientPanel.getWidth(),
		// this.getHeight() - chatClientPanel.getHeight());
		// add(chatClientPanel);
		// chatClientPanel.setVisible(true);

		setVisible(true);// show directly
			
		this.controlManager = new ControlManager(this);
		exit = new KeyAction("exit");
		controlManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
		
		
		createBufferStrategy(2);
		this.isRunning = true;
		this.monitorKeyboardInput();

		// The gameLoop should run in a separated thread, otherwise it will
		// block the main thread
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					gameLoop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start();

	}

	public void initSprite() throws IOException{
		Animation anim;
		anim = new Animation();
		anim.createFrames("res/images/sprite/human1.png");
		anim.setSpeed(100);
		anim.play();
		sprite = new Sprite(anim);
		step = 0;
	}
	

	
	public void gameLoop() throws InterruptedException {
		BufferStrategy strategy = getBufferStrategy();
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		long elapsedTime;
		while (isRunning) {
			elapsedTime = System.currentTimeMillis() - currTime;
			currTime += elapsedTime;

			// update
			update(elapsedTime);
			
			// get the graphic and redraw it
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			redrawScreen(g);
			g.dispose();// must dispose and then show
			
			
				
			if (!strategy.contentsLost()) {
				strategy.show();
			}
		}

	}

	public void update(long elapsedTime) {
		checkControlChange();
	
	}

	private void monitorKeyboardInput() {
		// to handle keyboard input
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER
						&& gameMode == Config.GAME_MODE_MULTI) {
					chatClientPanel.setVisible(true);
				} else if (key == KeyEvent.VK_ESCAPE) {
					int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure to exit game?");
					if (dialogresult == JOptionPane.YES_OPTION) {
						Runnable r = new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								GameCore gc = new GameCore();
								gc.setVisible(true);
								
							}
						};
						
						new Thread(r).start();
						GameManager.this.dispose();
					}
				}
			}
		});

	}

	public void checkControlChange() {
		
		if (exit.isPressed()) {
			System.exit(0);
        }
		

		int iRenderX = map.iRenderX;
		int iRenderY = map.iRenderY;
		int SCREEN_MOVE_SPEED = 8;

		// getMouseX() mouse event get x is a ,
		// relative value to the JFrame
		int actualX = controlManager.getMouseX() + getX();
		int actualY = controlManager.getMouseY() + getY();
		// so the actualX will be the

		int frameLeft = getX();
		int frameRight = getX() + getWidth() - 20;
		int frameUp = getY();
		int frameDown = getY() + getHeight() - 20;

		if (actualX == 0 && iRenderX > 0) {
			iRenderX = iRenderX - SCREEN_MOVE_SPEED;
		}
		if (actualX > frameRight
				&& iRenderX < (map.mapWidthPx - Config.PANEL_WIDTH)) {
			iRenderX = iRenderX + SCREEN_MOVE_SPEED;
		}
		if (actualY == 0 && iRenderY > 0) {
			iRenderY = iRenderY - SCREEN_MOVE_SPEED;
		}
		if (actualY > frameDown
				&& iRenderY < (map.mapHeightPx - Config.PANEL_HEIGHT)) {
			iRenderY = iRenderY + SCREEN_MOVE_SPEED;
		}

		map.iRenderX = iRenderX;
		map.iRenderY = iRenderY;
		miniMapPanel.setRectangleXY(iRenderX, iRenderY);

	}

	/**
	 * redraw the screen
	 */
	private void redrawScreen(Graphics2D g) {
		// draw the map first
		map.redraw(g, getWidth(), getHeight());
		// draw the dragging rectangle
		controlManager.dragActionDrawer(g);
		// draw the mini map
		miniMapPanel.redraw(g);
//		strategy = getBufferStrategy();
//		testSpritePanel.draw(strategy);
	}
}