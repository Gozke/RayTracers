package edu.gozke.jtracer.core;

import edu.gozke.jtracer.objects.RenderableObject;

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
	 * The object with which the ray intersected with.
	 */
	public final RenderableObject intersectedObject;
	
	/**
	 * The value representing a non existing intersection.
	 */
	public static final Intersection NO_INTERSECTION = new Intersection(null, null, Float.POSITIVE_INFINITY, null); 
	
	/**
	 * Creates an intersectionPoint with the given parameters. The given surface normal is normalized.
	 * 
	 * @param intersectionPoint the point of intersection in the base of the world coordinate system.
	 * @param normalVector surface normal of at the intersectionPoint in the base of the world coordinate system.
	 * @param the t parameter you have to plug into the equation of the ray's line to get this intersection point
	 */
	public Intersection(Vector intersectionPoint, Vector normalVector, float tParameter, RenderableObject object) {
		this.intersectionPoint = intersectionPoint;
		this.normalVector = normalVector == null ? null : normalVector.normalize();
		this.tParameter = tParameter;
		this.intersectedObject = object;
	}
	
}
