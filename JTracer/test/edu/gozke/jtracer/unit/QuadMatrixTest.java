package edu.gozke.jtracer.unit;

import org.junit.Test;

import edu.gozke.jtracer.core.QuadMatrix;
import edu.gozke.jtracer.core.Vector;
import junit.framework.TestCase;

public class QuadMatrixTest extends TestCase {
		@Test
		public void testMultiplyFromLeft(){
			QuadMatrix A = new QuadMatrix(new float[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15, 0}});
			QuadMatrix B = new QuadMatrix(new float[][] {{16,17,18,19},{20,21,22,23},{24,25,26,27},{28,29,30,31}});
			QuadMatrix expectedResult = new QuadMatrix(new float[][] {
				{240, 250, 260, 270},
				{592, 618, 644, 670},
				{944, 986, 1028, 1070},
				{848, 890, 932, 974}
			});
			
			/// B = A * B
			B.multiplyByLeftMatrix(A);
			assertEquals(expectedResult, B);
		}
		
		@Test
		public void testTranspose(){
			QuadMatrix A = new QuadMatrix(new float[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15, 16}});
			QuadMatrix expectedResult = new QuadMatrix(new float[][] {
				{1, 5,  9, 13},
				{2, 6, 10, 14},
				{3, 7, 11, 15},
				{4, 8, 12, 16}
			});
			
			// A = A'
			A.transpose();
			assertEquals(expectedResult, A);
		}
		
		@Test
		public void testMultiplyVectorFromRight(){
			Vector origin = new Vector(0,0,0);
			Vector testVector = new Vector(1,1,1);
			Vector testVectorMultiplied = new Vector(2,2,1);
			Vector originMultiplies = new Vector(1,1,0);
			QuadMatrix matrix = new QuadMatrix(new float[][]{
					{1, 0, 0, 0},
					{0, 1, 0, 0},
					{0, 0, 1, 0},
					{1, 1, 0, 1}
			});
			
			// v * [matrix]
			Vector res = matrix.multiplyVectorFromRight(testVector);
			assertEquals(testVectorMultiplied, res);

			res = matrix.multiplyVectorFromRight(origin);
			assertEquals(originMultiplies, res);
		}
		
		@Test
		public void testInverse(){
			QuadMatrix testMatrix = new QuadMatrix(new float[][]{
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{1, 1, 0, 1}
			});
			
			QuadMatrix expectedResult = new QuadMatrix(new float[][]{
				{1,  0, 0, 0},
				{0,  1, 0, 0},
				{0,  0, 1, 0},
				{-1,-1, 0, 1}
			});
			
			// testMatrix = testMatrix^(-1)
			testMatrix.inverse();
			assertEquals(expectedResult, testMatrix);
		}
}
