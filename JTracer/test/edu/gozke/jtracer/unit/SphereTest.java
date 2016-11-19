package edu.gozke.jtracer.unit;

import edu.gozke.jtracer.core.Intersection;
import edu.gozke.jtracer.core.Ray;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.Vector;
import edu.gozke.jtracer.objects.Sphere;
import junit.framework.TestCase;

public class SphereTest extends TestCase {
	public class ExposerClass extends Sphere{

		public ExposerClass(float radius) {
			super(radius, null);
		}
		
		@Override
		public Float[] solveQuadratic(float A, float B, float C) {
			return super.solveQuadratic(A, B, C);
		}
	}
	
	public void testSolveQuadratic(){
		ExposerClass testSphere = new ExposerClass(1);
		
		Float sol[] = testSphere.solveQuadratic(2, 5, 2);
		assertNotNull(sol);
		assertEquals(2, sol.length);
		assertEquals(-2f, sol[0], 0.0001);
		assertEquals(-0.5f, sol[1], 0.0001);
		
		sol = testSphere.solveQuadratic(2, 4, 2);
		assertNotNull(sol);
		assertEquals(1, sol.length);
		assertEquals(-1f, sol[0], 0.0001);
		
		assertNull(testSphere.solveQuadratic(5, 1, 2));
	}
	
	public void testIntersect(){
		Ray r = new Ray(new Vector(5,2,0), new Vector(-1,0,0));
		TransformationMatrix trafo = new TransformationMatrix().applyScaling(2, 2, 2);
		Sphere testSphere = new Sphere(1, trafo, null);
		Intersection hit = testSphere.intersectWith(r);
		assertEquals(5, hit.tParameter, 0.0001);
		assertEquals(new Vector(0,1,0), hit.normalVector);
		System.out.println(hit.intersectionPoint);
	
	}
}
