package edu.gozke.jtracer.objects;

import edu.gozke.jtracer.core.Axis;
import edu.gozke.jtracer.core.Intersection;
import edu.gozke.jtracer.core.Ray;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.TransformationMatrix.TransformationTarget;
import edu.gozke.jtracer.core.Vector;
import edu.gozke.jtracer.materials.RoughSurface;

public abstract class RenderableObject {
	TransformationMatrix transformationManager;
	RoughSurface material;
	
	public RenderableObject() {
		transformationManager = new TransformationMatrix();
	}

	public RenderableObject(TransformationMatrix transformations, RoughSurface material) {
		transformationManager = transformations;
		this.material = material;
	}

	public RoughSurface getMaterial(){
		return material;
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
		Vector surfaceNormalInObjectBase = calculateSurfaceNormalAt(hitPoint);
		Vector normalInWorldBase = transformationManager.transform(surfaceNormalInObjectBase, TransformationTarget.VECTOR_FROM_OBJECT_TO_WORLD_BASE).normalize();
		Vector hitPointInWorldBase = transformationManager.transform(hitPoint, TransformationTarget.POINT_FROM_OBJECT_TO_WORLD_BASE);
		
		float tInWorldSpace = hitPointInWorldBase.minus(rayInWorldBase.origin).lenght() / rayInWorldBase.direction.lenght();
		
		return new Intersection(hitPointInWorldBase, normalInWorldBase, tInWorldSpace, this);
	}

	/**
	 * Translates the object along the x/y/z axis by the specified amount.
	 * <br>Remember, the translation happens in the object's own base.
	 * This means that you have to take into account the previous rotation(s) / scaling(s) applied to 
	 * the object's base.
	 * 
	 * @param x the number of unit the object should be translated along the x axis
	 * @param y the number of unit the object should be translated along the y axis
	 * @param z the number of unit the object should be translated along the z axis
	 * 
	 * @return this object after the translation
	 * 
	 */
	public RenderableObject translate(float x, float y, float z){
		transformationManager.applyTranslation(x, y, z);
		return this;
	}
	
	/**
	 * Rotates the object around the given axis by the specified angle counter-clockwise.
	 * 
	 * @param axis the axis to rotate around
	 * @param degrees the angle in degrees. A positive angle means counter-clockwise rotation.
	 * @return this object after the tranformation
	 */
	public RenderableObject rotate(Axis axis, float degrees){
		transformationManager.applyRotation(axis, (float)Math.toRadians(degrees));
		return this;
	}
	
	/**
	 * Scales the object by the given scaling factors.
	 * 
	 * @param x scale object by this much along x axis
	 * @param y scale object by this much along y axis
	 * @param z scale object by this much along z axis
	 * @return
	 */
	public RenderableObject scale(float x, float y, float z){
		transformationManager.applyScaling(x, y, z);
		return this;
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
