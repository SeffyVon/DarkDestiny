package com.darkdensity.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;


/*
 * **************************************************************************************************************
 * Class: ControlManager implements KeyListener, MouseListener,MouseMotionListener, MouseWheelListener
 * **************************************************************************************************************
 * Attributes:
 *  private boolean dragging = false;
 *	private int mouseX;
 *	private int mouseY;
 *	private int sx, sy, ex, ey;
 *	private final int MOUSE_LEFT_BUTTON = 1;
 *	private final int MOUSE_RIGHT_BUTTON = 3;
 *	private boolean isLeftButtonPressed = false;
 *	private boolean isRightButtonPressed = false;
 *	private GamePanel panel;
 *	private LeftButtonListener leftButtonListener;
 *	private RightButtonListener rightButtonListener;
 *	private RightDraggedListener rightDragListener;
 *	private LeftDragListener leftDragListener;
 *	private MoveListener moveListener;
 *	private LeftDraggingListener leftDraggingListener;
 ***************************************************************************************************************
 * Methods:
 * 	public void dragActionDrawer(Graphics g) {}
 *  public void mouseWheelMoved(MouseWheelEvent arg0) {}
 *  public void mouseMoved(MouseEvent e) {}
 *	public void mouseClicked(MouseEvent arg0) {}
 *	public void mouseEntered(MouseEvent arg0) {}
 *	public void mouseExited(MouseEvent arg0) {}
 *	public void mousePressed(MouseEvent e) {}
 *	public void mouseDragged(MouseEvent e) {}
 *	public void mouseReleased(MouseEvent e) {}
 *	public void keyPressed(KeyEvent e) {}
 *	public void keyReleased(KeyEvent e) {}
 *	public void keyTyped(KeyEvent e) {}
 *	
 *	public static interface LeftButtonListener {}
 * 	public ControlManager addLeftButtonListener(LeftButtonListener callBack) {}
 *	
 *	public static interface RightButtonListener {}	
 *	public ControlManager addRightButtonListener(RightButtonListener callBack) {}
 *	
 *	public ControlManager addRightDraggedListener(RightDraggedListener callBack) {}
 *	public static interface RightDraggedListener {}
 *	
 *	public ControlManager addLeftDraggedListener(LeftDragListener callBack) {}
 *	public static interface LeftDraggedListener {}
 *	
 *	public ControlManager addLeftDraggingListener(LeftDraggingListener callBack) {}
 *	public static interface LeftDraggingListener {}
 *	
 *	public ControlManager addMoveListener(MoveListener callBack) {}
 *	public static interface MoveListener {}
 * 
 */

public class ControlManager implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	private boolean dragging = false;
	// where the mouse is now
	private int mouseX;
	private int mouseY;

	// start position and end position for dragging
	private int sx, sy, ex, ey;
	private final int MOUSE_LEFT_BUTTON = 1;
	private final int MOUSE_RIGHT_BUTTON = 3;

	private boolean isLeftButtonPressed = false;
	private boolean isRightButtonPressed = false;

	// private static final int NUM_KEY_CODES = 600;

	private GameWorld panel;

	// callback
	private LeftButtonListener leftButtonListener;
	private RightButtonListener rightButtonListener;
	private RightDraggedListener rightDragListener;
	private LeftDraggedListener leftDraggedListener;
	private MoveListener moveListener;
	private LeftDraggingListener leftDraggingListener;

	/**
	 * Add listener to the component
	 */
	public ControlManager(GameWorld component, JFrame frame) {
		this.panel = component;
		frame.addKeyListener(this);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.addMouseWheelListener(this);
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void dragActionDrawer(Graphics g) {
		if (dragging) {
			g.setColor(Color.GREEN);
			int width = Math.abs(sx - ex);
			int height = Math.abs(sy - ey);
			g.drawRect(Math.min(sx, ex), Math.min(sy, ey), width, height);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (moveListener != null) {
			moveListener.move(mouseX, mouseY);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		sx = e.getX();
		sy = e.getY();

		// start for dragging
		if (e.getButton() == MOUSE_LEFT_BUTTON) {
			isLeftButtonPressed = true;
			if (leftButtonListener != null) {
				leftButtonListener.press(sx, sy);
			}
		}

		if (e.getButton() == MOUSE_RIGHT_BUTTON) {
			isRightButtonPressed = true;
			dragging = false;
			if (rightButtonListener != null) {
				rightButtonListener.press(sx, sy);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		ex = e.getX();
		ey = e.getY();

		if (isRightButtonPressed) {

		}

		if (isLeftButtonPressed) {
			//Only when the drag distance is bigger than 30 pixel
			//dragging begin
//			if (Math.abs(ex - sx) > 30 && Math.abs(ey - sy) > 30) {
				dragging = true;
//			}
			//if is dragging
			if (dragging) {
				if (leftDraggingListener != null) {
					leftDraggingListener.dragging(sx, sy, ex, ex);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// start for dragging
		if (e.getButton() == MOUSE_RIGHT_BUTTON) {
			isRightButtonPressed = false;
		}

		if (e.getButton() == MOUSE_LEFT_BUTTON) {
			isLeftButtonPressed = false;
			
			//end for dragging
			if (dragging) {
				dragging = false;
				//drag action complete, call the drag method
				if (leftDraggedListener != null) {
					leftDraggedListener.dragged(sx, sy, ex, ey);
				}
			}
		}
	}

	/**
	 * set corresponding key to pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.panel.exitGameWorld();
		}
		e.consume();
	}

	/**
	 * set corresponding key to released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	// left button listener
	public ControlManager addLeftButtonListener(LeftButtonListener callBack) {
		this.leftButtonListener = callBack;
		return this;
	}

	public static interface LeftButtonListener {
		public void press(int x, int y);
	}

	// right button listener
	public ControlManager addRightButtonListener(RightButtonListener callBack) {
		this.rightButtonListener = callBack;
		return this;
	}

	public static interface RightButtonListener {

		public void press(int x, int y);
	}

	// drag listener
	public ControlManager addRightDraggedListener(RightDraggedListener callBack) {
		this.rightDragListener = callBack;
		return this;
	}

	public static interface RightDraggedListener {
		public void drag(int sx, int sy, int ex, int ey);
	}

	// drag listener
	public ControlManager addLeftDraggedListener(LeftDraggedListener callBack) {
		this.leftDraggedListener = callBack;
		return this;
	}

	public static interface LeftDraggedListener {
		public void dragged(int sx, int sy, int ex, int ey);
	}

	// drag listener
	public ControlManager addLeftDraggingListener(LeftDraggingListener callBack) {
		this.leftDraggingListener = callBack;
		return this;
	}

	public static interface LeftDraggingListener {

		public void dragging(int sx, int sy, int ex, int ey);
	}

	// move listener
	public ControlManager addMoveListener(MoveListener callBack) {
		this.moveListener = callBack;
		return this;
	}

	public static interface MoveListener {
		public void move(int x, int y);
	}

}
