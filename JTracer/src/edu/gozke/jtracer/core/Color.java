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
	
	public Color(int red, int green, int blue, boolean sh){
		this.red = red/255f;
		this.green = green/255f;
		this.blue = blue/255f;
	}
	
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
	
	
	public Color plus(Color otherColor){
		return new Color(Math.min(1, this.red + otherColor.red), Math.min(1, this.green + otherColor.green), Math.min(1, this.blue + otherColor.blue));
	}
	
	public Color multiply(Color otherColor){
		return new Color(red*otherColor.red, green*otherColor.green, blue*otherColor.blue);
	}
	
	public Color multiply(float s){
		return new Color(s*red, s*green, s*blue);
	}
	
	public Color normalise(){
		float max = Math.max(red, Math.max(green, blue));
		float scalingFactor = 1f/max;
		if(max > 1){
			return new Color(red*scalingFactor, green*scalingFactor, blue*scalingFactor);
		}
		return this;
	}
}
