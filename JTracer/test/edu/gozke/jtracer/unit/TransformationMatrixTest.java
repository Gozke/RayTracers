package edu.gozke.jtracer.unit;

import edu.gozke.jtracer.core.Axis;
import edu.gozke.jtracer.core.QuadMatrix;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.TransformationMatrix.TransformationTarget;
import edu.gozke.jtracer.core.Vector;
import junit.framework.TestCase;

public class TransformationMatrixTest extends TestCase {
	
	public void testConstructor(){
		TransformationMatrix identityTransformation = new TransformationMatrix();
		assertEquals(QuadMatrix.UNIT_MATRIX, identityTransformation.getTransformationMatrix());
	}
	
	public void testApplyTranslation(){
		TransformationMatrix testTMatrix = new TransformationMatrix();
		QuadMatrix expectedTMatrix = new QuadMatrix(new float[][]{
			{1, 0, 0, 0},
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{3, 4, 2, 1}
		});
		
		testTMatrix.applyTranslation(3, 4, 2);
		assertEquals(expectedTMatrix, testTMatrix.getTransformationMatrix());
	}
	
	public void testApplyScaling(){
		TransformationMatrix testTMatrix = new TransformationMatrix();
		QuadMatrix expectedTMatrix = new QuadMatrix(new float[][]{
			{1, 0, 0, 0},
			{0, 3, 0, 0},
			{0, 0, 2, 0},
			{0, 0, 0, 1}
		});
		
		testTMatrix.applyScaling(1, 3, 2);
		assertEquals(expectedTMatrix, testTMatrix.getTransformationMatrix());
	}
	
	public void testTransformationApplyOrder(){
		TransformationMatrix testTransf = new TransformationMatrix();
		testTransf.applyTranslation(1, 1, 1).applyScaling(2, 3, 2);
		
		QuadMatrix expectedResult = new QuadMatrix(new float[][]{
			{2, 0, 0, 0},
			{0, 3, 0, 0},
			{0, 0, 2, 0},
			{1, 1, 1, 1}
		});
		
		assertEquals(expectedResult, testTransf.getTransformationMatrix());
	}
	
	public void testZRotation(){
		TransformationMatrix testTransf = new TransformationMatrix();
		testTransf.applyRotation(Axis.Z, (float)Math.PI/2);
		
		QuadMatrix expectedResult = new QuadMatrix(new float[][]{
			{0,  1, 0, 0},
			{-1, 0, 0, 0},
			{0,  0, 1, 0},
			{0,  0, 0, 1}
		});
		
		//assertEquals(expectedResult, testTransf.getTransformationMatrix());
		System.out.println(testTransf.getTransformationMatrix());
		fail("Not implemented!");
	}
	
	public void testPerformTransformation(){
		TransformationMatrix tranlation = new TransformationMatrix()
				.applyTranslation(3, 2, 0)
				.applyRotation(Axis.Z, (float)Math.PI)
				.applyScaling(2, 2, 1);
				
		Vector origin = new Vector(0, 0, 0);
		Vector testPoint = new Vector(4, 2, 0);
		Vector up = new Vector(0,1,0);
		Vector right = new Vector(1,0,0);
		
		Vector worldOriginInObjectBase = tranlation.transform(origin, TransformationTarget.POINT_FROM_WORLD_TO_OBJECT_BASE);
		assertEquals(new Vector(1.5f,1f,0), worldOriginInObjectBase);
		
		Vector objectOriginInWorldBase = tranlation.transform(origin, TransformationTarget.POINT_FROM_OBJECT_TO_WORLD_BASE);
		assertEquals(new Vector(3, 2, 0), objectOriginInWorldBase);
		
		Vector objectTestPointInWorldBase = tranlation.transform(testPoint, TransformationTarget.POINT_FROM_OBJECT_TO_WORLD_BASE);
		assertEquals(new Vector(-5,-2, 0), objectTestPointInWorldBase);
		
		Vector worldVectorRightInObjectBase = tranlation.transform(right, TransformationTarget.VECTOR_FROM_OBJECT_TO_WORLD_BASE);
		assertEquals(new Vector(-0.5f, 0, 0), worldVectorRightInObjectBase);
		
		Vector worldVectorUpInObjectBase = tranlation.transform(up, TransformationTarget.VECTOR_FROM_OBJECT_TO_WORLD_BASE);
		assertEquals(new Vector(0, -0.5f, 0), worldVectorUpInObjectBase);
		
	}
	
	public void testScaling(){
		//TransformationMatrix trafo = new TransformationMatrix().applyScaling(2, 2, 2);
		TransformationMatrix trafo = new TransformationMatrix().applyTranslation(1, 1, 1);
		//TransformationMatrix trafo = new TransformationMatrix().applyRotation(Axis.Z, (float)Math.PI/2);
		Vector up = new Vector(0, 1, 0);
		System.out.println(up + "=> ");
		
		QuadMatrix transM = trafo.getTransformationMatrix();
		System.out.println(transM.multiplyVectorFromRight(up));
		System.out.println(transM.getInverseClone().multiplyVectorFromRight(up));
		System.out.println(transM.getTransposedClone().multiplyVectorFromRight(up));
		System.out.println(transM.getInverseClone().transpose().multiplyVectorFromRight(up));
	}
}
