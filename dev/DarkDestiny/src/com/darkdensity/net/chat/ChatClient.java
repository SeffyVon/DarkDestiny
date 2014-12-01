package com.darkdensity.net.chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.darkdensity.setting.Config;

public class ChatClient extends JFrame {
	// TODO: change the udp address, and other variable into config.java
	BufferedImage buffer = null;
	static ChatManager cm;
	static JTextField tf;
	static JTextArea tv;
	private int x = 0;
	private int y = 0;
	private final int width = 400;
	private final int height = 230;

	public ChatClient() {
		cm = new ChatManager();
		JPanel jPanel = new JPanel();
		this.setContentPane(jPanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tv = new JTextArea(10, 30);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		tv.setBorder(border);
		tv.setBackground(Color.WHITE);
		tv.setEditable(false);
		this.add(tv);
		JLabel jl = new JLabel("Chat:");
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
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		this.add(btnSend);
		this.setSize(this.width, this.height);

		Runnable r = new Runnable() {
			public void run() {
				receiveMessage();
			};
		};
		new Thread(r).start();

	}

	public static void receiveMessage() {
		while (true) {
			try {
				String s = cm.receiveMessage();
				tv.append(s + "\n"); // TODO: Change to dynamic user name after
										// implementing mult-player game
				tf.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void redraw() {
		this.getParent().add(this);
		this.setVisible(true);

	}
}
