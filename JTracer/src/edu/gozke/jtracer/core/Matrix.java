package edu.gozke.jtracer.core;

import java.util.Arrays;

/**
 * This class represents an immutable quadratic matrix with some of the standard mathematical operations. 
 * Elements are stored as {@code float}.
 * 
 * 
 * @author Gozke
 *
 */
public class Matrix {
	private float elements[];
	
	/**
	 * A matrix with 1s in it's diagonal and 0s everywhere else.
	 */
	public static final Matrix UNIT_MATRIX = new Matrix(new float[]{
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
	});

	/**
	 * Creates an all 0s matrix.
	 */
	public Matrix(){
		elements = new float[16];
	}
	
	/**
	 * Creates a new matrix with the elements provided as parameter. Only uses the first 16.
	 * If the parameter has less then 16 elements, the rest of the matrix is filled with 0s.
	 * <br> The parameter array should contain matrix elements this way A=[e1, e2, e3, ... eN);
	 * 
	 * @param elements the array containing elements the flattened matrix
	 */
	public Matrix(float[] elements){
		this.elements = Arrays.copyOf(elements, 16);
	}

	/**
	 * This function multiples this matrix by the parameter from left and saves the result in this matrix. That is the result will be:
	 * <br> result = this * rightMatrix.
	 * 
	 * @param rightMatrix
	 * @return this matrix after the multiplication
	 */
	public Matrix multiplyFromRigh(Matrix rightMatrix){
		float[] arr = new float[16];
		for(int i = 0;i <4; i++){
			for(int j = 0; j<4; j++){
				float sum = 0;
				for(int k = 0; k<4; k++){
					sum+=elements[i*4 + k] * rightMatrix.elements[k*4 + j];
				}
				arr[i*4+j] = sum;
			}
		}
		elements = arr;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<16;i++){
			if(i % 4 == 0) builder.append("\n");
			builder.append(elements[i]);
			builder.append(' ');
		}
		return builder.toString();
	}
	
	/**
	 * This function transposes the matrix and returns it after that.
	 * 
	 * @return this matrix transposed
	 */
	public Matrix transpose(){
	    swap(4, 1);
	    swap(8, 2);
	    swap(9, 6);
	    swap(12, 3);
	    swap(13, 7);
	    swap(14, 11);
		return this;
	}
	
	public Matrix getTransposedClone(){
		return clone().transpose();
	}
	
	/**
	 * Inverts the this matrix.
	 * 
	 * @return this matrix inverted
	 */
	public Matrix inverse(){
		
		return this;
	}
	
	public Matrix getInverseClone(){
		return clone().inverse();
	}
	
	public Matrix clone(){
		return new Matrix(elements);
	}
	
	/**
	 * This function multiplies this matrix by a vector from the left.
	 * That is in case if vector {@code v} and this matrix result = {@code v} X this.
	 * 
	 * @return v X this
	 */
	public Vector multiplyWithVector(Vector v){
		float[] coords = new float[4];
		for(int i = 0;i<4;i++){
			coords[i] = v.x * elements[i];
			coords[i] += v.y * elements[4+i];
			coords[i] += v.z * elements[8+i];
			coords[i] += v.w * elements[12+i];	
		}
		return new Vector(coords);
	}
	
	private void swap(int i, int j){
		float tmp = elements[i];
		elements[i] = elements[j];
		elements[j] = tmp;
	}
	
}
