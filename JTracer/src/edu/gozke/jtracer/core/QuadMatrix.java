package edu.gozke.jtracer.core;

import java.util.Arrays;

import org.hamcrest.core.IsInstanceOf;

import Jama.Matrix;

/**
 * This class represents an immutable quadratic matrix with some of the standard mathematical operations. 
 * Elements are stored as {@code float}.
 * 
 * 
 * @author Gozke
 *
 */
public class QuadMatrix {
	/**
	 * 2D array storing the values in the array[row][column] format.
	 */
	private float elements[][];
	
	/**
	 * A matrix with 1s in it's diagonal and 0s everywhere else.
	 */
	public static final QuadMatrix UNIT_MATRIX = new QuadMatrix(new float[][]{
			{1, 0, 0, 0},
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1}
	});

	/**
	 * Creates an all 0s matrix.
	 */
	public QuadMatrix(){
		elements = new float[4][4];
	}
	
	/**
	 * Creates a new matrix with the elements provided as parameter.
	 * <br> The parameter array should be index this way array[row][column]);
	 * 
	 * @param elements the array. Must be a 4x4 array. (Not checked!)
	 */
	public QuadMatrix(float[][] elements){
		this.elements = new float[4][4];
		for(int i = 0;i <4; i++){
			for(int j = 0; j<4; j++){
				this.elements[i][j] = elements[i][j];
			}
		}
	}

	/**
	 * This function multiples this matrix by the parameter from left and saves the result in this matrix. That is the result will be:
	 * <br> result = [parameter matrix] * [this].
	 * 
	 * @param leftMatrix
	 * @return this matrix after the multiplication. [parameter matrix] * [this]
	 */
	public QuadMatrix multiplyByLeftMatrix(QuadMatrix leftMatrix){
		float[][] A = leftMatrix.elements;
		float[][] B = this.elements;
		float[][] C = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                	C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
		elements = C;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<4;i++){
			for(int j = 0; j<4; j++){
				builder.append(elements[i][j]);
				builder.append(' ');				
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	/**
	 * This function transposes the matrix and returns it after that.
	 * 
	 * @return this matrix transposed
	 */
	public QuadMatrix transpose(){
	    swap(1, 0);
	    swap(2, 0);
	    swap(2, 1);
	    swap(3, 0);
	    swap(3, 1);
	    swap(3, 2);
		return this;
	}
	
	public QuadMatrix getTransposedClone(){
		return clone().transpose();
	}
	
	/**
	 * Inverts the this matrix. (Changes this matrix)
	 * 
	 * @return this matrix inverted
	 */
	public QuadMatrix inverse(){
		Matrix m = new Matrix(toDoubleArray(elements));
		elements = toFloatArray(m.inverse().getArray());
		return this;
	}
	
	public QuadMatrix getInverseClone(){
		return clone().inverse();
	}
	
	public QuadMatrix clone(){
		return new QuadMatrix(elements);
	}
	
	/**
	 * This function multiplies this matrix by a vector from the left.
	 * That is in case if vector {@code v} and this matrix: result = {@code v} * this.
	 * 
	 * @return parameter * [this matrix]
	 */
	public Vector multiplyVectorFromRight(Vector v){
		float[] coords = new float[4];
		for(int c = 0; c<4; c++){
			coords[c] += v.x * elements[0][c];
			coords[c] += v.y * elements[1][c];
			coords[c] += v.z * elements[2][c];
			coords[c] += v.w * elements[3][c];
		}
		coords[3] = 1; // Ignore the w value
		return new Vector(coords);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof QuadMatrix)){
			return false;
		}
		QuadMatrix other = (QuadMatrix)obj;
		return Arrays.deepEquals(elements, other.elements);
	}

	/**
	 * elements(r,c) <=> elements(c,r)
	 * 
	 * @param r
	 * @param c
	 */
	private void swap(int r, int c){
		float tmp = elements[r][c];
		elements[r][c] = elements[c][r];
		elements[c][r] = tmp;
	}
	
	private float[] toFloatArray(double[] f){
		float[] res = new float[f.length];
		for(int i = 0;i <f.length; i++){
			res[i] = (float) f[i];
		}
		return res;
	}
	
	private double[][] toDoubleArray(float[][] fArray){
		double[][] a = new double[4][4];
		for(int r = 0; r<4; r++){
			for(int c = 0; c<4; c++) a[r][c] = (double) fArray[r][c];
		}
		return a;
	}
	
	private float[][] toFloatArray(double[][] dArray){
		float[][] a = new float[4][4];
		for(int r = 0; r<4; r++){
			for(int c = 0; c<4; c++) a[r][c] = (float) dArray[r][c];
		}
		return a;
	}
	
	
}
