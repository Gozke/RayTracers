package edu.gozke.jtracer.unit;

import org.junit.Test;

import edu.gozke.jtracer.core.QuadMatrix;
import edu.gozke.jtracer.core.Vector;
import junit.framework.TestCase;

public class VectorTest extends TestCase {

	@Test
	public void testMultiplyByMatrix() {
		Vector testVector = new Vector(3,0,10);
		Vector expectedResult= new Vector(3,0,10);
		assertEquals(expectedResult, testVector.multiplyByMatrix(QuadMatrix.UNIT_MATRIX));
		
		QuadMatrix otherMatrix = new QuadMatrix(new float[][]{
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10,11,12},
			{13,14,15,16}
		});
		testVector = new Vector(1, 1, 0);
		expectedResult= new Vector(19,22,25); //w = 28, but should be ignored
		
		assertEquals(expectedResult, testVector.multiplyByMatrix(otherMatrix));
	}

}
