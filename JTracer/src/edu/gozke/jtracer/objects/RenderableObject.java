package edu.gozke.jtracer.objects;

import edu.gozke.jtracer.core.Intersection;
import edu.gozke.jtracer.core.Ray;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.TransformationMatrix.TransformationTarget;
import edu.gozke.jtracer.core.Vector;

public abstract class RenderableObject {
	TransformationMatrix transformationManager;
	

	public RenderableObject() {
		transformationManager = new TransformationMatrix();
	}

	public RenderableObject(TransformationMatrix transformations) {
		transformationManager = transformations;
	}

	/**
	 * Transforms the ray into object's space and determines if the given ray intersects
	 * with this object and returns intersection point, and surface normal in that point.
	 * 
	 * @param rayInWorldBase
	 *            the ray to intersect object with
	 * @return Never null. The details of the intersection. Or {@link Intersection#NO_INTERSECTION} if no intersection was found.)
	 */
	public Intersection intersectWith(Ray rayInWorldBase) {
		Ray rayInObjectBase = new Ray(
				transformationManager.transform(rayInWorldBase.origin, TransformationTarget.POINT_FROM_WORLD_TO_OBJECT_BASE),
				transformationManager.transform(rayInWorldBase.direction, TransformationTarget.VECTOR_FROM_WORLD_TO_OBJECT_BASE));
		
		Float t = findIntersectionTParameter(rayInObjectBase);
		if(t == null){
			return Intersection.NO_INTERSECTION;
		}
		
		Vector hitPoint = rayInObjectBase.getPointOfRay(t);
		//Vector surfaceNormalInObjectBase = calculateSurfaceNormalAt(hitPoint);
		//Vector normalInWorldBase = transformationManager.transform(surfaceNormalInObjectBase, TransformationTarget.VECTOR_FROM_OBJECT_TO_WORLD_BASE).normalize();
		Vector normalInWorldBase = null;
		Vector hitPointInWorldBase = transformationManager.transform(hitPoint, TransformationTarget.POINT_FROM_OBJECT_TO_WORLD_BASE);
		
		float tInWorldSpace = hitPointInWorldBase.minus(rayInWorldBase.origin).lenght() / rayInWorldBase.direction.lenght();
		
		return new Intersection(hitPointInWorldBase, normalInWorldBase, tInWorldSpace, this);
	}

	/**
	 * Performs the intersection check in the object's base. The ray provided as
	 * parameter is already transformed to object's base. The results must be in
	 * the object's base as well. <br>
	 * The problem this function must solve: <br>
	 * Let the object be positioned (centered) at the origin. What is the value
	 * of the ray's t parameter using which the ray and the object intersect?
	 * (If there is no intersection {@code null} is returned.
	 * 
	 * @param ray
	 *            the ray in object's base to check intersection with
	 * @return the value of t parameter in case of intersection. In the ray does not intersect with the object
	 * {@link Float#POSITIVE_INFINITY} is returned.
	 */
	protected abstract Float findIntersectionTParameter(Ray ray);

	/**
	 * Transforms the parameter vector from world's base to object's base and
	 * computes the surface normal of the object in the given point and returns the normal in world's base.
	 * 
	 * @param point the point at which the surface normal is to be calculated (in world's base)
	 * @return the normal the surface normal in world's base.
	 */
	public abstract Vector calculateSurfaceNormalAt(Vector point);
}
