package com.darkdensity.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


/**
 * 
 * @ClassName: ControlManager
 * @Description: Control Manager, listen to all keyboard and mouse action
 * @author Han Jaitch@163.com
 * @date 2014/01/31 14:01:58
 * 
 */
public class ControlManager implements KeyListener, MouseListener,
MouseMotionListener, MouseWheelListener{
	
	
	//see whether is dragging
	public static boolean dragging = false;

    //where the mouse is now
    private int mouseX;
    private int mouseY;
	
	//start position and end position for dragging
	public static int sx,sy,ex,ey;
	public static final int MOUSE_LEFT_BUTTON= 1;
	public static final int MOUSE_RIGHT_BUTTON= 3;
	
	private static final int NUM_KEY_CODES = 600;
	
	private KeyAction[] keyActions = new KeyAction[NUM_KEY_CODES];
	
	/**
	 * Add listener to the component
	 */
	public ControlManager(Component component) {
		component.addKeyListener(this);
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
		component.addMouseWheelListener(this);
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	/**
	* @Description: draw rectangle when the user dragging the mouse
	 */
	public void dragActionDrawer(Graphics g){
		if(dragging){
			g.setColor(Color.GREEN);
			int width = Math.abs(sx - ex);
			int height = Math.abs(sy - ey);
			g.drawRect(Math.min(sx, ex), Math.min(sy, ey), width, height);
		}
		
	}

	
	public void mapToKey(KeyAction keyAction, int keyCode) {
		keyActions[keyCode] = keyAction;
	}
	
	private KeyAction getKeyAction(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode < keyActions.length) {
			return keyActions[keyCode];
		} else {
			return null;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX= e.getX();
		mouseY= e.getY();		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		//start for dragging
		if(e.getButton()== MOUSE_LEFT_BUTTON){
			sx = e.getX();
			sy = e.getY();
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		ex = e.getX();
		ey = e.getY();
		dragging = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//end for dragging
		dragging = false;
	}


	/**
	 * set corresponding key to pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {		
		KeyAction keyAction = getKeyAction(e);
		if (keyAction != null) {
			keyAction.press();
		}
		// make sure the key will not do anything else
		e.consume();
	}

	/**
	 * set corresponding key to released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		KeyAction keyAction = getKeyAction(e);
		if (keyAction != null) {
			keyAction.release();
		}
		// make sure the key will not do anything else
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		// make sure the key will not do anything else
		e.consume();
	}
	
	


}
