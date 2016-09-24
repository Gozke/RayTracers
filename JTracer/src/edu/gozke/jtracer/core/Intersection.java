package edu.gozke.jtracer.core;


public class Intersection {
	/**
	 * The coordinates of the point of intersection in the base of the world coordinate system.
	 */
	public final Vector intersectionPoint;
	
	/**
	 * The unit length surface normal of at the intersectionPoint in the base of the world coordinate system.
	 */
	public final Vector normalVector;
	
	public final float tParameter;
	/**
	 * The value representing a non existing intersection.
	 */
	public static final Intersection NO_INTERSECION = new Intersection(null, null, 0); 
	
	/**
	 * Creates an intersectionPoint with the given parameters. The given surface normal is normalized.
	 * 
	 * @param intersectionPoint the point of intersection in the base of the world coordinate system.
	 * @param normalVector surface normal of at the intersectionPoint in the base of the world coordinate system.
	 * @param the t parameter you have to plug into the equation of the ray's line to get this intersection point
	 */
	public Intersection(Vector intersectionPoint, Vector normalVector, float tParameter) {
		this.intersectionPoint = intersectionPoint;
		this.normalVector = normalVector.normalize();
		this.tParameter = tParameter;
	}
	
}
