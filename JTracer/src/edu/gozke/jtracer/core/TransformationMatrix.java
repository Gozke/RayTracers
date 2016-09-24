package edu.gozke.jtracer.core;

public class TransformationMatrix {
	private QuadMatrix tMatrix;
	
	/**
	 * Creates the identity transformation matrix.
	 * 
	 */
	public TransformationMatrix(){
		tMatrix = QuadMatrix.UNIT_MATRIX;
	}

	public TransformationMatrix applyRotationAroundX(float angleInRad){
		QuadMatrix rotationMatrix = new QuadMatrix(new float[][]{
			
		});
		
		tMatrix.multiplyByLeftMatrix(rotationMatrix);
		return this;
	}
	
	public TransformationMatrix applyTransition(float newX, float newY, float newZ){
		return this;
	}
	
	public TransformationMatrix applyScaling(float xScalingFactor, float yScalingFactor, float zScalingFactor){
		return this;
	}
	

	public Vector transformVector(Vector v){
		return tMatrix.multiplyVectorFromRight(v);
	}
	
}
