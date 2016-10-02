package edu.gozke.jtracer.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is the camera class which is responsible for generating the trays that will be traced.
 * The concept is that the camera is sitting in the point referred to as the 'eye' and it is looking at the virtual
 * world through a 2x2 rectangle called the 'viewing rectangle'. The field of view is determined by 
 * how close this rectangle is to the eye. See the constructor parameters to more information and ASCI art below.
 * <br> When the rays are generated the viewing rectangle is traversed and a ray origination from the 'eye' and going
 * though a point on viewing rectangle is created.
 * 
 * @author Gozke
 *
 */
public class Camera {
	/**
	 * This point represents the location of the eye in the world coordinate system.
	 */
	private Vector eye;
	
	/**
	 * This vector denotes the direction in which the camera is looking.
	 */
	private Vector lookingDirection; 
	
	/**
	 * This is the center of the rectangle through which we are looking at the virtual world.
	 */
	private Vector viewingPlaneCenter;
	
	/**
	 * This is a helping vector that denotes the direction 'right' when traversing the viewing rectangle.
	 */
	private Vector right;
	
	/**
	 * This is a helping vector that denotes the direction 'up' when traversing the viewing rectangle.
	 */
	private Vector up;
	
	private int xMax;
	private int yMax;
	
	 
	/**
	 * This constructor creates a camera that is located in 'eyePoint' is looking in the direction 'lookingDirectionVector' and as a 
	 * field of view 'fieldOfView' and has a resultion of 'xResolution' * 'yResolution' pixels.
	 * 
	 * @param eyePoint the location of the camera.
	 * @param lookingDirectionVector The direction towards which the camera is looking
	 * @param upVector This vector denotes where the direction that is considered 'Up' 
	 * @param xResolution 
	 * @param yResolution
	 * @param fieldOfView FoV in degrees
	 */
	public Camera(Vector eyePoint, Vector lookingDirectionVector, Vector upVector, int xResolution, int yResolution, double fieldOfView) {
		super();
		this.eye = eyePoint;
		this.xMax = xResolution;
		this.yMax = yResolution;
		this.up = upVector.normalize();
		this.lookingDirection = lookingDirectionVector.normalize();
				
		float fovInRad = (float) Math.toRadians(fieldOfView);
		// The nominator is 1 because we have a 2x2 rectangle
		float distanceOfViewingPlaneAndEye = (float) (1/Math.tan(fovInRad/2));
		this.viewingPlaneCenter = eyePoint.plus( lookingDirection.scaleBy(distanceOfViewingPlaneAndEye) );
		this.right = viewingPlaneCenter.minus(eyePoint).normalize().crossProductWith(up);
	}
	
	/**
	 * This constructor creates a camera that is located in 'eyePoint' is looking in the direction 'lookingDirectionVector' and has a 
	 * field of view 60 deg and a resultion of 'xResolution' * 'yResolution' pixels.
	 * 
	 * @param eyePoint the location of the camera.
	 * @param lookingDirectionVector The direction towards which the camera is looking
	 * @param upVector This vector denotes where the direction that is considered 'Up'
	 * @param xResolution
	 * @param yResolution
	 */
	public Camera(Vector eyePoint, Vector lookingDirectionVector, Vector upVector, int xResolution, int yResolution){
		this(eyePoint, lookingDirectionVector, upVector, xResolution, yResolution, 60);
	}

	/**
	 * Returns a {@link Ray} that originates from a point on the view rectangle that corresponds to the x,y coordinates and
	 * has it direction set to eye -> point on view rectangle.
	 * 
	 * @param x Should not be greater than x resolution
	 * @param y Should not be greater than y resolution
	 * @return
	 */ 
	public Ray getRay(int x, int y) {
		Vector pointOnViewRect = viewingPlaneCenter.plus(right.scaleBy( 2*((float)x/(xMax)-0.5f) ).plus(up.scaleBy( 2*(0.5f-(float)y/(yMax) ))));
		return new Ray(
				pointOnViewRect,
				pointOnViewRect.minus(eye).normalize(),
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
	
	public int getRenderingWidth(){
		return xMax;
	}
	
	public int getRenderingHeight(){
		return yMax;
	}
	
	public void setRenderingWidth(int xMax) {
		this.xMax = xMax;
	}

	public void setRenderingHeight(int yMax) {
		this.yMax = yMax;
	}

	public Vector getPosition() {
		return eye;
	}

	public void setPosition(Vector eye) {
		this.eye = eye;
	}

	public Vector getLookingDirection() {
		return lookingDirection;
	}

	public void setLookingDirection(Vector lookingDirection) {
		this.lookingDirection = lookingDirection;
	}

	
	protected Vector getViewingPlaneCenter() {
		return viewingPlaneCenter;
	}

	protected Vector getRight() {
		return right;
	}

	protected Vector getUp() {
		return up;
	}
	
	
}
