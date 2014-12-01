package com.darkdensity.core;
/**
 * 
* @ClassName: KeyAction
* @Description: For keyboard listener
* @author Han  Jaitch@163.com
* @date 2014/2/7 15:34:03
*
 */
public class KeyAction {
	
	//state constant
	private static final int STATE_RELEASED = 0;
    private static final int STATE_PRESSED = 1;

    private String name;
    // state flag
    private int state;
    
    /**
     * New KeyAction 
     */
    public KeyAction(String name) {
    	this.name = name;
    }
    /**
     * Get the name of the key action
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the key to pressed
     */
    public synchronized void press() {
    	this.state = STATE_PRESSED;
    }
    
    public synchronized void release() {
        this.state = STATE_RELEASED;
    }

    public boolean isPressed(){
    	return state == STATE_PRESSED;
	}

}
