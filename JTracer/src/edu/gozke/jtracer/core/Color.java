package edu.gozke.jtracer.core;

/**
 * Standard class representing a RGB color
 * @author Gozke
 *
 */
public class Color {
	/**
	 * r[0] = green, blue, red
	 */
	public final float red;
	public final float green;
	public final float blue;
	
	
	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public Color(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public Color(short red, short green, short blue){
		this.red = red/255f;
		this.green = green/255f;
		this.blue = blue/255f;
	}
	
}
