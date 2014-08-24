package com.darkdensity.net.chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.darkdensity.core.GameWorld;
import com.darkdensity.setting.Config;
import com.darkdensity.setting.NLS;
import com.darkdensity.setting.NLS_zh_HK;

public class ChatPanel extends JPanel implements KeyListener {
	// TODO: change the udp address, and other variable into config.java
	private NLS nls;
	private final Color fillColor = new Color(0, 0, 0, 70);
	BufferedImage buffer = null;
	private ChatManager cm;
	private JTextField tf;
	private JTextArea tv;
	private int x = 0;
	private int y = 0;
	private final int width = 400;
	private final int height = 230;
	private GameWorld gamePanel;
	private Runnable receiveMessageRunnable;
	private Thread receiveMessageThread;
	private boolean isReceiveMessage;

	public ChatPanel(GameWorld gamePanel) {
		this.cm = new ChatManager();
		this.gamePanel = gamePanel;
		this.setBackground(fillColor);
		try {
			this.nls = (NLS) Class.forName(Config.NLS_NAME).newInstance();
		} catch (Throwable e) {
			//this.nls = new NLS();
		}
		tv = new JTextArea(10, 30);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		tv.setBorder(border);
		tv.setBackground(Color.WHITE);
		tv.setEditable(false);
		tv.setFocusable(false);
		this.add(tv);
		JLabel jl = new JLabel(nls.chatlbl);
		this.add(jl);
		tf = new JTextField(20);
		this.add(tf);
		JButton btnSend = new JButton("Send");

		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = tf.getText();
					if (!message.isEmpty()) {
						cm.sendMessage(Config.PLAYER_NAME + ": " + message);
						tf.setText("");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		this.add(btnSend);
		this.setSize(this.width, this.height);
		btnSend.addKeyListener(this);
		this.addKeyListener(this);

	}

	public void receiveMessage() {
		while (this.isReceiveMessage) {
			System.out.println("TRUE");
			try {
				String s = cm.receiveMessage();
				tv.append(s + "\n"); // TODO: Change to dynamic user name after
										// implementing mult-player game
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

	public void startReceiveMessage() {

		this.isReceiveMessage = true;
		this.receiveMessageRunnable = new Runnable() {
			public void run() {
				receiveMessage();
			};
		};
		this.receiveMessageThread = new Thread(this.receiveMessageRunnable);

		this.receiveMessageThread.start();
	}

	public void stopReceiveMessage() {
		try {
			this.isReceiveMessage = false;
			this.cm.closeSocket();
			this.receiveMessageThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KeyPressed");
		if (e.getKeyCode() == KeyEvent.VK_3) {
			// do something
			e.consume();
		} else {
			this.gamePanel.getFrame().dispatchEvent(e);
		}
		// TODO Auto-generated method stub

		System.out.println("KeyFinishPressed");

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Release");
		this.gamePanel.getFrame().dispatchEvent(e);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("typed");
		this.gamePanel.getFrame().dispatchEvent(e);
	}

}
