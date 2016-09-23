package edu.gozke.jtracer.unit;

import edu.gozke.jtracer.core.Matrix;
import edu.gozke.jtracer.core.Vector;
import junit.framework.TestCase;

public class MatrixTest extends TestCase{
	public void testMultiplyFromLeft(){
		Matrix A = new Matrix(new float[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15, 0});
		Matrix B = new Matrix(new float[] {16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
		
		Matrix res = A.multiplyFromRigh(B);
		System.out.println(res);
		//assertEquals(new Matrix(new float[] {}), actual);
	}
	
	public void testTranspose(){
		Matrix A = new Matrix(new float[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15, 16});
		A.transpose();
		System.out.println(A);
	}
	
	public void testMultiplyWithVector(){
		Vector origin = new Vector(0,0,0);
		Vector unit = new Vector(1,1,1);
		Matrix M = new Matrix(new float[]{
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				1, 1, 0, 1
		});
		
		Vector res = M.multiplyWithVector(origin);
		System.out.println(res);
		
		res = M.multiplyWithVector(unit);
		System.out.println(res);
	}
}
