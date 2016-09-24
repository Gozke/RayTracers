package edu.gozke.jtracer.objects;

import edu.gozke.jtracer.core.Intersection;
import edu.gozke.jtracer.core.Ray;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.Vector;

public abstract class RenderableObject {
	TransformationMatrix appliedTransformations;

	public RenderableObject() {
		appliedTransformations = new TransformationMatrix();
	}

	public RenderableObject(TransformationMatrix transformations){
		appliedTransformations = transformations;
	}
	
	/**
	 * Determines if the given ray intersects with this object and returns the
	 * computed intersection point and surface normal (if any).
	 * 
	 * @param ray
	 *            the ray to intersect object with
	 * @return the intersection details. If no intersection was found
	 *         {@link Intersection#NO_INTERSECION} is returned. (Never null).
	 */
	public Intersection intersectWith(Ray ray){
		return null;
	}


	/**
	 * Performs the intersection check in the object's base. The ray provided as
	 * parameter is transformed to object's base. The results must be in the
	 * object's base as well. <br>
	 * The problem this function must solve: <br>
	 * Let the object be positioned (centered) at the origin. Does the ray intersect with
	 * the object. If so what are the coordinates of the intersection point and
	 * what is the surface normal of the object in that particular point.
	 * 
	 * @param ray
	 *            the ray in object's base to check intersection with
	 * @return the intersection details in the object's base. If no intersection
	 *         was found {@link Intersection#NO_INTERSECION} is returned. (Never
	 *         null).
	 */
	protected abstract Vector[] findIntersection(Ray ray);

}
