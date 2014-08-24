package com.darkdensity.net.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import java.util.*;

import com.darkdensity.setting.Config;

public class ChatManager {

	MulticastSocket mcastSocket;
	int ttl;

	public ChatManager() {
		try {
			mcastSocket = new MulticastSocket(Config.CHAT_PORT);
			ttl = mcastSocket.getTimeToLive();
			mcastSocket.joinGroup(InetAddress.getByName(Config.CHAT_GROUP));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String receiveMessage() throws IOException {
		byte buf[] = new byte[1024];
		DatagramPacket datapack = new DatagramPacket(buf, buf.length);
		mcastSocket.receive(datapack);
		String packStr = new String(datapack.getData(), 0, datapack.getLength());
		return packStr;
	}

	public void sendMessage(String message) throws Exception {
		byte[] buf = message.getBytes();
		DatagramPacket pack = new DatagramPacket(buf, buf.length,
				InetAddress.getByName(Config.CHAT_GROUP), Config.CHAT_PORT);
		mcastSocket.send(pack);
	}

	public void closeSocket() {
		this.mcastSocket.close();
	}
}
