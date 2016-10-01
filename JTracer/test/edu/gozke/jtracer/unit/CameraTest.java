package edu.gozke.jtracer.unit;

import edu.gozke.jtracer.core.Camera;
import edu.gozke.jtracer.core.Ray;
import edu.gozke.jtracer.core.Vector;
import junit.framework.TestCase;

public class CameraTest extends TestCase {
	private class MyCamera extends Camera{

		public MyCamera(Vector eyePoint, Vector lookingDirectionVector, Vector upVector, int xResolution, int yResolution, double fieldOfView) {
			super(eyePoint, lookingDirectionVector, upVector, xResolution, yResolution, fieldOfView);
		}

		/**
		 * @param eyePoint
		 * @param lookingDirectionVector
		 * @param upVector
		 * @param xResolution
		 * @param yResolution
		 */
		public MyCamera(Vector eyePoint, Vector lookingDirectionVector, Vector upVector, int xResolution, int yResolution) {
			super(eyePoint, lookingDirectionVector, upVector, xResolution, yResolution);
		}

		@Override
		public Vector getViewingPlaneCenter() {
			return super.getViewingPlaneCenter();
		}

		@Override
		public Vector getRight() {
			return super.getRight();
		}

		@Override
		public Vector getUp() {
			return super.getUp();
		}

	}
	
	public void testConstructorWithStdValues(){
		Vector up = new Vector(0,5,0);
		Vector lookAt = new Vector(0,0,-3);
		Vector eye = new Vector(0,0,4);
		
		MyCamera cameraUnderTest = new MyCamera(eye, lookAt, up, 4, 4);
		
		assertEquals(new Vector(1,0,0), cameraUnderTest.getRight());
		assertEquals(new Vector(0,0,-1), cameraUnderTest.getLookingDirection());
		assertEquals(new Vector(0,1,0), cameraUnderTest.getUp());
	}
	
	public void testConstructorWithTiltCamera(){
		Vector up = new Vector(2,0,0);
		Vector lookAt = new Vector(0,0,-3);
		Vector eye = new Vector(0,0,4);
		
		MyCamera cameraUnderTest = new MyCamera(eye, lookAt, up, 4, 4);
		
		assertEquals(new Vector(0,-1,0), cameraUnderTest.getRight());
		assertEquals(new Vector(0,0,-1), cameraUnderTest.getLookingDirection());
		assertEquals(new Vector(1,0,0), cameraUnderTest.getUp());
	}
	
	public void testGetRayOriginStdCamera(){
		Vector up = new Vector(0,5,0);
		Vector lookAt = new Vector(0,0,-3);
		Vector eye = new Vector(0,0,4);
		
		MyCamera cameraUnderTest = new MyCamera(eye, lookAt, up, 4, 4);
		Ray upperLeft = cameraUnderTest.getRay(0, 0);
		assertEquals(new Vector(-1,1,2.26795f), upperLeft.origin);
		
		Ray lowerLeft = cameraUnderTest.getRay(0, 4);
		assertEquals(new Vector(-1,-1,2.26795f), lowerLeft.origin);
		
		Ray lowerRight = cameraUnderTest.getRay(4, 4);
		assertEquals(new Vector(1,-1,2.26795f), lowerRight.origin);
		
		Ray upperRight = cameraUnderTest.getRay(4, 0);
		assertEquals(new Vector(1,1,2.26795f), upperRight.origin);
	}
	
	public void testGetRayOriginTiltCamera(){
		Vector up = new Vector(2,0,0);
		Vector lookAt = new Vector(0,0,-3);
		Vector eye = new Vector(0,0,4);
		
		MyCamera cameraUnderTest = new MyCamera(eye, lookAt, up, 4, 4);
		Ray upperLeft = cameraUnderTest.getRay(0, 0);
		assertEquals(new Vector(1,1,2.26795f), upperLeft.origin);
		
		Ray lowerLeft = cameraUnderTest.getRay(0, 4);
		assertEquals(new Vector(-1,1,2.26795f), lowerLeft.origin);
		
		Ray lowerRight = cameraUnderTest.getRay(4, 4);
		assertEquals(new Vector(-1,-1,2.26795f), lowerRight.origin);
		
		Ray upperRight = cameraUnderTest.getRay(4, 0);
		assertEquals(new Vector(1,-1,2.26795f), upperRight.origin);
	}
}

