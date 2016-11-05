package edu.gozke.jtracer.core;

public class TransformationMatrix {
	private QuadMatrix tMatrix;
	
	public enum TransformationTarget{
		/**
		 * Vt = v * [M]
		 * 
		 */
		POINT_FROM_OBJECT_TO_WORLD_BASE,
		
		/**
		 * Vt = v * [M transpose]
		 */
		VECTOR_FROM_OBJECT_TO_WORLD_BASE,
		
		/**
		 * Vt = v * [M inverse]
		 */
		POINT_FROM_WORLD_TO_OBJECT_BASE,
		
		/**
		 * Vt = v * [M inverse transpose]
		 */
		VECTOR_FROM_WORLD_TO_OBJECT_BASE,
	}
	
	/**
	 * Creates the identity transformation matrix.
	 * 
	 */
	public TransformationMatrix(){
		tMatrix = QuadMatrix.UNIT_MATRIX.clone();
	}

	/**
	 * This function performs the requested transformation of the given vector or point between world and object space.
	 * 
	 * @param vector vector/point to transform
	 * @param target specifies the direction of the transformation.
	 * @param treatVectorAsPoint if {@code false} the input is treated as a vector (transposed matrix is used)
	 * @return
	 */
	public Vector transform(Vector vector, TransformationTarget target){
		Vector transformed = null;
		switch (target) {
		case POINT_FROM_OBJECT_TO_WORLD_BASE:
			transformed = tMatrix.multiplyVectorFromRight(vector);
			break;
		case POINT_FROM_WORLD_TO_OBJECT_BASE:
			transformed = tMatrix.getInverseClone().multiplyVectorFromRight(vector);
			break;
		case VECTOR_FROM_OBJECT_TO_WORLD_BASE:
			transformed = tMatrix.getInverseClone().transpose().multiplyVectorFromRight(vector);
			break;
		case VECTOR_FROM_WORLD_TO_OBJECT_BASE:
			transformed = tMatrix.getTransposedClone().multiplyVectorFromRight(vector);
			break;
		}
		return transformed;
	}
	
	/**
	 * Appends the transformation of counter-clockwise rotation around the given axis by the given angle [rad].
	 * 
	 * @param axis
	 * @param angleInRad
	 * @return
	 */
	public TransformationMatrix applyRotation(Axis axis, float angleInRad){
		QuadMatrix rotationMatrix = null;
		switch (axis) {
		case X:
			rotationMatrix = rotationAroundX(angleInRad);
			break;
		case Y:
			rotationMatrix = rotationAroundY(angleInRad);
		case Z:
			rotationMatrix = rotationAroundZ(angleInRad);
		default:
			break;
		}
		
		tMatrix.multiplyByLeftMatrix(rotationMatrix);
		return this;
	}
	
	public TransformationMatrix applyTranslation(float newX, float newY, float newZ){
		QuadMatrix translationMatrix = new QuadMatrix(new float[][]{
			{1, 0, 0, 0},
			{0, 1, 0, 0},
			{0,	0, 1, 0},
			{newX, newY, newZ, 1}
		});
		tMatrix.multiplyByLeftMatrix(translationMatrix);
		return this;
	}
	
	public TransformationMatrix applyScaling(float xScalingFactor, float yScalingFactor, float zScalingFactor){
		QuadMatrix scalingMatrix = new QuadMatrix(new float[][]{
			{xScalingFactor, 0, 0, 0},
			{0, yScalingFactor, 0, 0},
			{0,	0, zScalingFactor, 0},
			{0, 0, 0, 1}
		});
		tMatrix.multiplyByLeftMatrix(scalingMatrix);
		return this;
	}
	
	
	/**
	 * Returns the transformation matrix. Only for JUnit!
	 * 
	 * @return Transformation matrix. DO NOT USE IT for anything other then JUNIT tests!
	 */
	public QuadMatrix getTransformationMatrix(){
		return tMatrix;
	}
	
	private QuadMatrix rotationAroundX(float tetha){
		float cosTetha = (float) Math.cos(tetha);
		float sinTetha = (float) Math.sin(tetha);
		QuadMatrix rotationMatrix = new QuadMatrix(new float[][]{
			{1,   0,        0,       0},
			{0, cosTetha, sinTetha,  0},
			{0, -sinTetha, cosTetha, 1},
			{0,   0,		0,		 1}
		});
		return rotationMatrix;
	}
	
	private QuadMatrix rotationAroundY(float tetha){
		float cosTetha = (float) Math.cos(tetha);
		float sinTetha = (float) Math.sin(tetha);
		QuadMatrix rotationMatrix = new QuadMatrix(new float[][]{
			{cosTetha, 0, -sinTetha, 0},
			{0, 1, 0, 0},
			{sinTetha, 0, cosTetha, 0},
			{0, 0, 0, 1}
		});
		return rotationMatrix;
	}
	
	private QuadMatrix rotationAroundZ(float tetha){
		float cosTetha = (float) Math.cos(tetha);
		float sinTetha = (float) Math.sin(tetha);
		QuadMatrix rotationMatrix = new QuadMatrix(new float[][]{
			{cosTetha, sinTetha, 0, 0},
			{-sinTetha, cosTetha, 0, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1}
		});
		return rotationMatrix;
	}
}
