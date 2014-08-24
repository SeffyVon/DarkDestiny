package com.darkdensity.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.setting.Config;
import com.darkdensity.setting.NLS;

/*
 * ****************************************
 * Class: MainMenuPanel
 * ****************************************
 * Attributes:
 * public class GamePanel extends JLayeredPane:
 * private final int gameMode;
 * private ControlManager controlManager;
 * private MapPanel mapPanel;
 * private MiniMapPanel miniMapPanel;
 * private SpriteManager spriteManager;
 * private SpritePanel spritePanel;
 * private boolean isRunning = true;
 * private Map map;
 * private JFrame frame;
 * private Sprite sprite;
 * *****************************************
 * Methods:
 * public GamePanel(JFrame frame, int gameMode) throws IOException;
 * public void init() throws IOException;
 * public void gameLoop();
 * public boolean gameLoopCore() ;
 * public void update(long elapsedTime) throws Throwable;
 * public void cameraScolling();
 * private void redrawScreen(Graphics2D g);
 * public void exitGameWorld();
 * public JFrame getFrame();
 * public SpriteManager getSpriteManager();
 * boolean isInDrag(int sx, int sy, int ex, int ey, int x, int y, int width, int length);
 */
public class MainMenuPanel extends AbstractPanel {
	private MenuItem item;

	public MainMenuPanel(JFrame parent) {
		super(parent);
		item = new MenuItem(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(super.background, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void update(long elapsedTime) {
		item.update(elapsedTime);
	}

	@Override
	public void reset() {
		// item.elastic.slide(0, 100);
	}

	private class MenuItem extends JPanel {

		NLS nls;
		JButton solo;
		JButton coop;
		JButton versus;
		JButton options;
		JButton exit;

		int x, y;

		// Elastic elastic = new Elastic(0, 100);

		public MenuItem(MainMenuPanel parent) {
			try {
				this.nls =  (NLS) Class.forName(Config.NLS_NAME).newInstance();
			} catch (Throwable e) {
				e.printStackTrace();
			//	this.nls = new NLS();
			}
			
			
			System.out.println("Config:" + Config.NLS_NAME);
			System.out.println(this.nls.soloBtn);
			solo = new JButton(nls.soloBtn);
			coop = new JButton(nls.coopBtn);
			versus = new JButton(nls.vsBtn);
			options = new JButton(nls.optionBtn);
			exit = new JButton(nls.exitBtn);
			
			this.setLayout(new GridLayout(5, 1));
			setSize(250, 400);
			x = parent.getWidth() - getWidth();
			y = 0;
			// y = (parent.getHeight() - getHeight()) / 2;
			setLocation(x, y);

			parent.add(this);
			this.initButton(solo);
			this.initButton(coop);
			this.initButton(versus);
			this.initButton(options);
			this.initButton(exit);
		}

		private void initButton(JButton button) {
			button.setBorder(null);
			button.setForeground(Color.WHITE);
			button.setIgnoreRepaint(true);
			button.setFocusable(false);
			button.setBorder(null);
			button.setContentAreaFilled(false);
			button.addActionListener(listenner);
			this.add(button);

		}

		public void paintComponent(Graphics g) {
			g.setColor(fillColor);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
			g.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 10, 10);
		}

		public void update(long elapsedTime) {
			setLocation(x, y);
		}

		ActionListener listenner = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton) e.getSource();
				JFrame jFrame = (JFrame) MainMenuPanel.this.getRootPane()
						.getParent();
				if (jb.equals(solo)) {
					jFrame.setContentPane(new SoloPanel(jFrame));
				} else if (jb.equals(coop)) {
					jFrame.setContentPane(new SoloPanel(jFrame));
				} else if (jb.equals(versus)) {
					jFrame.setContentPane(new VersusPanel(jFrame));
				}
				if (jb.equals(exit)) {
					System.exit(0);
				}
			}
		};
	}
}
