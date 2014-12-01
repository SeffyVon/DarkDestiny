package com.darkdensity.net.chat;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import java.util.*;

public class ChatManager {

	final int port = 5000;
	final String group = "225.4.5.6";
	MulticastSocket mcastSocket;
	int ttl;

	public ChatManager() {
		try {
			mcastSocket = new MulticastSocket(port);
			ttl = mcastSocket.getTimeToLive();
			mcastSocket.joinGroup(InetAddress.getByName(group));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String receiveMessage() throws Exception {
		byte buf[] = new byte[1024];
		DatagramPacket datapack = new DatagramPacket(buf, buf.length);
		mcastSocket.receive(datapack);
		String packStr = new String(datapack.getData(), 0, datapack.getLength());
		return packStr;
	}

	public void sendMessage(String message) throws Exception {
		byte[] buf = message.getBytes();
		DatagramPacket pack = new DatagramPacket(buf, buf.length,
				InetAddress.getByName(group), port);
		mcastSocket.send(pack);
	}

}
