package edu.gozke.jtracer.objects;

import edu.gozke.jtracer.core.Ray;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.Vector;
import edu.gozke.jtracer.materials.RoughSurface;

public class Sphere extends RenderableObject{
	protected float radius;
	
	/**
	 * @param radius
	 */
	public Sphere(float radius, RoughSurface material) {
		this.radius = radius;
		this.material = material;
	}
	
	public Sphere(float radius, TransformationMatrix transformations, RoughSurface material) {
		super(transformations, material);
		this.radius = radius;
	}

	@Override
	protected Float findIntersectionTParameter(Ray ray) {
		float Px = ray.origin.x;
		float Py = ray.origin.y;
		float Pz = ray.origin.z;
		float Vx = ray.direction.x;
		float Vy = ray.direction.y;
		float Vz = ray.direction.z;
		
		float A = Vx*Vx + Vy*Vy + Vz*Vz; // |V|^2
		float B = 2*ray.origin.dotProduct(ray.direction); // P dot V
		float C = Px*Px + Py*Py + Pz*Pz - radius*radius;  // |P|^2 - R^2
		
		Float tParameter = smallestNonNegative(solveQuadratic(A, B, C));
		return tParameter;
	}

	private Float smallestNonNegative(Float[] arr){
		if(arr == null ){
			return null;
		}
		if(arr.length == 1){
			return arr[0] >= Vector.EPSILON ? arr[0] : null;
		}
		if(arr[0] > 0){
			return arr[0];
		}
		
		return arr[1] >= Vector.EPSILON ? arr[1] : null;
	}
	
	protected Float[] solveQuadratic(float A, float B, float C){
		double discriminant = B*B - 4*A*C;
		Float solutions[] = null;
		float sol1;
		float sol2;
		
		if(discriminant >= 0){
			sol1 = (float)  (-1*B+Math.sqrt(discriminant)) / (2*A);
			if(discriminant > 0){
				solutions = new Float[2];
				sol2 = (float) (-1*B-Math.sqrt(discriminant)) / (2*A);
				solutions[0] = Float.min(sol1, sol2);
				solutions[1] = Float.max(sol1, sol2);
			} else {
				solutions = new Float[1];
				solutions[0] = sol1;
			}
		}
		
		return solutions;
	}
	@Override
	public Vector calculateSurfaceNormalAt(Vector point) {
		return point;
	}

}
