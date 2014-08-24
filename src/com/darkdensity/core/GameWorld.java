package com.darkdensity.core;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import com.darkdensity.core.ControlManager.LeftButtonListener;
import com.darkdensity.core.ControlManager.LeftDraggedListener;
import com.darkdensity.core.ControlManager.LeftDraggingListener;
import com.darkdensity.core.ControlManager.RightButtonListener;
import com.darkdensity.gui.MapPanel;
import com.darkdensity.gui.MiniMapPanel;
import com.darkdensity.gui.SpritePanel;
import com.darkdensity.maprender.Map;
import com.darkdensity.net.chat.ChatPanel;
import com.darkdensity.setting.Config;
import com.darkdensity.tile.Sprite;
import com.darkdensity.tile.SpriteManager;
import com.darkdensity.util.ImageLoader;


/*
 * 
 * @author heiyinwong
 * ****************************************
 * Class: GameWorld extends JLayeredPane:
 * ****************************************
 * Attributes:
 * private final int gameMode;
 * 	- A int variable to define which game mode should be initialized
 * private ControlManager controlManager;
 *  - A class that implements Keyboard & Mouse listenser for handling user's input
 * private MapPanel mapPanel;
 *  - A controller class that handling operation on map 
 * private MiniMapPanel miniMapPanel;
 *  - A controller class that handling operation mini-map
 * private SpriteManager spriteManager;
 *  - A controller class that handling operation on sprite (unit)
 * private SpritePanel spritePanel;
 *  - A view class that actually show unit
 * private boolean isRunning = true;
 *  - A flag that define if the gameloop should run 
 * private Map map;
 *  - A model class of Map 
 * private JFrame frame;
 * - The root frame of our game 
 * private Sprite sprite;
 * - The unit's model class
 * *****************************************
 * Methods:
 * public GameWorld(JFrame frame, int gameMode) throws IOException{
 * 	- Constructor that initialize GameWorld, it accept a JFrame and gameMode.
 *  - Based on user options to initialize game world on JFrame 
 * }
 * public void init() throws IOException{
 * 	- Method that actually build game world
 * }
 * public void gameLoop(){
 * 	- A method that handling game logic
 *  - To keep check if user is lose
 * }
 * public boolean gameLoopCore() {
 *  - A method that implement game loop and call appropriate function to finish work
 * }
 * public void update(long elapsedTime) throws Throwable{
 * 	- A method that call spriteManager to update sprite location
 * }
 * public void cameraScolling(){
 *  - A method that calculate where the player is viewing nmow 
 * }
 * private void redrawScreen(Graphics2D g){
 *  - A method that triggers repaint of each layer
 * }
 * public void exitGameWorld(){
 *  - A method that used to quit game
 * }
 * public JFrame getFrame(){
 *  - A method that used by its child to get frame back (instead of calling somethign.getRootPane().getParent());
 * }
 * public SpriteManager getSpriteManager(){
 *  - A method that used to get sprite manager
 * }
 * boolean isInDrag(int sx, int sy, int ex, int ey, int x, int y, int width, int length){
 * 
 * }
 */
public class GameWorld extends JLayeredPane {
	private final int gameMode;
	private ControlManager controlManager;
	
	//parent frame
	private JFrame frame;
	
	//game render beginning point
	private int iRenderX=0;
	private int iRenderY=0;
		
	//add panels
	private MapPanel mapPanel;
	private MiniMapPanel miniMapPanel;
	private SpriteManager spriteManager;
	private SpritePanel spritePanel;

	private boolean isRunning = true;
	
	
	private Sprite sprite;
	private BufferStrategy strategy;
	private ChatPanel chatPanel;
	private java.util.Timer timer;
	private Map map;

	//time counter for the game
	public long currentTime;
	public long elapsedTime;

	Thread gameWorldThread;
	

	public GameWorld(JFrame frame, int gameMode) throws IOException {
		this.frame = frame;
		setLayout(null);
		setSize(frame.getWidth(), frame.getHeight());
		this.gameMode = gameMode;
		init();

		Runnable gameWorldRunnable = new Runnable() {
			@Override
			public void run() {
				while (GameWorld.this.frame.getBufferStrategy() == null) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				strategy = GameWorld.this.frame.getBufferStrategy();
				GameWorld.this.gameLoop();

			}
		};

		gameWorldThread = new Thread(gameWorldRunnable);
		gameWorldThread.start();
	}

	public void init() throws IOException {
		// ResourceManager.load();
		this.controlManager = new ControlManager(this, frame);
		
		this.frame.addKeyListener(this.controlManager);

		
		mapPanel = new MapPanel(frame);
		map = mapPanel.getMap();
		iRenderX= map.getMapWidthPx()/2;
		iRenderY= map.getMapHeightPx()/2;
		
		miniMapPanel = new MiniMapPanel(frame);
		// gridMapPanel = new GridMapPanel(frame);

		spriteManager = new SpriteManager(frame);
		spritePanel = spriteManager.getSpritePanel();
		spritePanel.setVisible(true);

		if (this.gameMode == Config.GAME_MODE_VS
				|| this.gameMode == Config.GAME_MODE_COOP) {
			chatPanel = new ChatPanel(this);
			chatPanel.setLocation(0, getHeight() - chatPanel.getHeight());
			chatPanel.setVisible(true);
			chatPanel.startReceiveMessage();
			add(chatPanel, JLayeredPane.POPUP_LAYER);
		}

		add(spriteManager.getSpritePanel(), JLayeredPane.MODAL_LAYER);
		add(miniMapPanel, JLayeredPane.POPUP_LAYER);

		

		controlManager.addLeftDraggingListener(new LeftDraggingListener() {

			@Override
			public void dragging(int sx, int sy, int ex, int ey) {
				sx += iRenderX;
				ex += iRenderX;
				sy += iRenderY;
				ey += iRenderY;
				for (int i = 0; i < spriteManager.getHumansNum(); i++) {
					Sprite sprite = spriteManager.getHuman(i);
					if (isInDrag(sx, sy, ex, ey, sprite.getX(), sprite.getY(),
							sprite.width, sprite.length))
						sprite.setHighLight();
					else
						sprite.resetHighLight();
				}

			}
		});

		controlManager.addLeftDraggedListener(new LeftDraggedListener() {

			@Override
			public void dragged(int sx, int sy, int ex, int ey) {
				sx += iRenderX;
				ex += iRenderX;
				sy += iRenderY;
				ey += iRenderY;
				for (int i = 0; i < spriteManager.getHumansNum(); i++) {
					Sprite sprite = spriteManager.getHuman(i);
					if (isInDrag(sx, sy, ex, ey, sprite.getX(), sprite.getY(),
							sprite.width, sprite.length))
						sprite.setSelected();
					continue;

				}
			}
		});

		controlManager.addLeftButtonListener(new LeftButtonListener() {
			public void press(int x, int y) {
	
				x += iRenderX;
				y += iRenderY;
				for (int i = 0; i < spriteManager.getHumansNum(); i++) {
					Sprite sprite = spriteManager.getHuman(i);
					if (x >= sprite.getX() && x <= sprite.getX() + sprite.width
							&& y >= sprite.getY()
							&& y <= sprite.getY() + sprite.length) {

						sprite.setSelected();
						return;
					} else {
						if (sprite.beManipulated) {
							sprite.resetSelected();
						}
					}
				}

			}
		});

		controlManager.addRightButtonListener(new RightButtonListener() {
			public void press(int x, int y) {
				

				
				x += iRenderX;
				y += iRenderY;

				for (int i = 0; i < spriteManager.getHumansNum(); i++) {
					Sprite sprite = spriteManager.getHuman(i);

					if (x >= sprite.getX() && x <= sprite.getX() + sprite.width
							&& y >= sprite.getY()
							&& y <= sprite.getY() + sprite.length) {

					} else {
						if (sprite.beManipulated) {
							sprite.setDestinationX(x - sprite.width);
							sprite.setDestinationY(y - sprite.length);

						}
					}
				}
			}
		});

		timer = new java.util.Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Image bimage = ImageLoader.loadImage(Config.IMG_WINNING_PATH);
				JLabel jLabel = new JLabel(new ImageIcon(bimage));
				jLabel.setLocation(
						GameWorld.this.getWidth() / 2 - bimage.getWidth(null)
								/ 2,
						GameWorld.this.getHeight() / 2 - bimage.getHeight(null)
								/ 2);
				jLabel.setSize(bimage.getWidth(null), bimage.getHeight(null));

				GameWorld.this.add(jLabel);

				MusicPlayer mu = new MusicPlayer(Config.MUSIC_WINNING_PATH);
				mu.start();
				GameWorld.this.isRunning = false;

			}
		}, Config.GAME_WINNING_TIME * 1000);

	}

	public void gameLoop() {

		currentTime = System.currentTimeMillis();
		elapsedTime = 0;

		Timer sTimer = new Timer(Config.GAME_LOOP_TIMER_DELAY,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (spritePanel.isLose()) {

							Image bimage = ImageLoader
									.loadImage(Config.IMG_LOSING_PATH);
							JLabel jLabel = new JLabel(new ImageIcon(bimage));
							jLabel.setLocation(
									GameWorld.this.getWidth() / 2
											- bimage.getWidth(null) / 2,
									GameWorld.this.getHeight() / 2
											- bimage.getHeight(null) / 2);
							jLabel.setSize(bimage.getWidth(null),
									bimage.getHeight(null));
							GameWorld.this.add(jLabel);
							MusicPlayer mu = new MusicPlayer(
									Config.MUSIC_LOSING_PATH);
							mu.start();
							GameWorld.this.timer.cancel();
							GameWorld.this.isRunning = false;
						}

						if (!gameLoopCore()) {
							((Timer) e.getSource()).stop();

							if (GameWorld.this.gameMode == Config.GAME_MODE_VS
									|| GameWorld.this.gameMode == Config.GAME_MODE_COOP) {
								chatPanel.stopReceiveMessage();
							}

							try {
								gameWorldThread.join();
							} catch (InterruptedException e1) {

								e1.printStackTrace();
							}
						}

					}
				});
		sTimer.start();

	}

	public boolean gameLoopCore() {

		elapsedTime = System.currentTimeMillis() - currentTime;
		currentTime = System.currentTimeMillis();

		// update
		try {
			update(elapsedTime);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cameraScolling();

		// get the graphic and redraw it
		
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		
		redrawScreen(g);
		g.dispose();// must dispose and then show

		if (!strategy.contentsLost()) {
			strategy.show();
		}

		return isRunning;

	}

	public void update(long elapsedTime) throws Throwable {
		for (int i = 0; i < spriteManager.getHumansNum(); i++)
			spriteManager.getHuman(i).update();
		for (int i = 0; i < spriteManager.getZombiesNum(); i++)
			spriteManager.getZombie(i).update();

	}

	public void cameraScolling() {

		int SCREEN_MOVE_SPEED = Config.SCREEN_MOVE_SPEED;

		// getMouseX() mouse event get x is a ,
		// relative value to the JFrame
		
		int actualX = controlManager.getMouseX() + getX();
		int actualY = controlManager.getMouseY() + getY();

		// so the actualX will be the

		int frameRight = getX() + getWidth() - Config.SENSITIVE_AREA;

		int frameDown = getY() + getHeight() - Config.SENSITIVE_AREA;

		if (actualX == 0 && iRenderX > 0) {
			iRenderX = iRenderX - SCREEN_MOVE_SPEED;
		}
		if (actualX > frameRight
				&& iRenderX < (map.getMapWidthPx() - Config.PANEL_WIDTH - Config.SENSITIVE_AREA)) {
			iRenderX = iRenderX + SCREEN_MOVE_SPEED;
		}
		if (actualY == 0 && iRenderY > 0) {
			iRenderY = iRenderY - SCREEN_MOVE_SPEED;
		}
		if (actualY > frameDown
				&& iRenderY < (map.getMapHeightPx() - Config.PANEL_HEIGHT - Config.SENSITIVE_AREA)) {
			iRenderY = iRenderY + SCREEN_MOVE_SPEED;
		}

		miniMapPanel.setRectangleXY(iRenderX, iRenderY);
		mapPanel.setPainXY(iRenderX, iRenderY);
		spritePanel.setiRenderX(iRenderX);
		spritePanel.setiRenderY(iRenderY);

	}

	private void redrawScreen(Graphics2D g) {
		mapPanel.paintComponent(g);
		miniMapPanel.paintComponent(g);
		spritePanel.paintComponents(g);
		controlManager.dragActionDrawer(g);
		this.paint(g);
	}

	public void exitGameWorld() {
		System.out.println("EXIT GAME WORLD");
		this.isRunning = false;
		frame.dispose();
		new GameCore();

	}

	public JFrame getFrame() {
		return frame;
	}

	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

	boolean isInDrag(int sx, int sy, int ex, int ey, int x, int y, int width,
			int length) {
		if (sx >= ex && sy > ey) { // from bottom right to top left
			Rectangle rect1 = new Rectangle(ex, ey, Math.abs(sx - ex),
					Math.abs(sy - ey));
			Rectangle rect2 = new Rectangle(x, y, width, length);
			return rect1.intersects(rect2);

		}
		if (sx <= ex && sy > ey) {// from bottom left to top right
			Rectangle rect1 = new Rectangle(sx, ey, Math.abs(sx - ex),
					Math.abs(sy - ey));
			Rectangle rect2 = new Rectangle(x, y, width, length);
			return rect1.intersects(rect2);
		}
		if (sx <= ex && sy < ey) {// from top left to bottom right
			Rectangle rect1 = new Rectangle(sx, sy, Math.abs(ex - sx),
					Math.abs(ey - sy));
			Rectangle rect2 = new Rectangle(x, y, width, length);
			return rect1.intersects(rect2);
		} else { // from top right to bottom left
			Rectangle rect1 = new Rectangle(ex, sy, Math.abs(sx - ex),
					Math.abs(ey - sx));
			Rectangle rect2 = new Rectangle(x, y, width, length);
			return rect1.intersects(rect2);

		}
	}

}