package edu.gozke.jtracer.core;

import java.util.ArrayList;
import java.util.Collection;

public class Camera {
	private Vector eye;
	private Vector lookAt;
	private Vector right;
	private Vector up;
	
	private int xMax;
	private int yMax;
	 
	public Camera(Vector eye, Vector lookAt, Vector up, int xResolution, int yResolution) {
		super();
		this.eye = eye;
		this.lookAt = lookAt;
		this.up = up.normalize();
		this.right = Vector.subtract(lookAt, eye).crossProductWith(up);
		this.xMax = xResolution;
		this.yMax = yResolution;
	}
	
	public Camera(int canvasWidth, int canvasHeight){
		this(new Vector(0,1,-2), new Vector(0, 1, -1), new Vector(0, 1, 0), canvasWidth, canvasHeight);
	}
	 
	/**
	 * Returns a {@link Ray} that is shot from the eye position of the camera through the camera plane at x,y.
	 * 
	 * @param x Should not be greater than x resolution
	 * @param y Should not be greater than y resolution
	 * @return
	 */ 
	public Ray getRay(int x, int y) {
		return new Ray(
				eye,
				lookAt.plus(right.scaleBy(2*(x + 0.5f) / xMax-1))
					  .plus(up.scaleBy(2*(y + 0.5f) / yMax-1)),
				y*xMax + x);
	} 
	
	public Collection<Ray> getAllRays()	{
		ArrayList<Ray> rays = new ArrayList<>(xMax * yMax);
		for(int x = 0; x<xMax; x++){
			for(int y = 0; y<yMax; y++){
				rays.add(getRay(x, y));
			}
		}
		return rays; 
	}
	
	public int getCanvasWidth(){
		return xMax;
	}
	
	public int getCanvasHeight(){
		return yMax;
	}
}
