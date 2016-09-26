package edu.gozke.jtracer.core;

/**
 * This class represents the ray that should be traced.
 * 
 * @author Gozke
 *
 */
public class Ray {
	public final Vector origin;
	public final Vector direction;
	
	/**
	 * Creates a new ray with the specified origin and direction. The direction is normalized.
	 * Both parameters are cloned!
	 * 
	 * @param origin origin of the ray
	 * @param direction direction vector of the ray
	 */
	public Ray(Vector origin, Vector direction) {
		super();
		this.origin = origin.clone();
		this.direction = direction.clone();
	}
	 
	/**
	 * The ray represents a 3d line. This method returns a specific point on that line by substituting the 
	 * given t parameter to the equation of the line.
	 * 
	 * @param t parameter of the line
	 * @return the given point on the ray
	 */
	public Vector getPointOfRay(float t){
		return origin.plus(direction.scaleBy(t));
	}
}
