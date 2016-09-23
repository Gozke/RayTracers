package edu.gozke.jtracer.core;

public class TransformationMatrix {
	private Matrix tMatrix;
	
	/**
	 * Creates the identity transformation matrix.
	 * 
	 */
	public TransformationMatrix(){
		tMatrix = Matrix.UNIT_MATRIX;
	}

	public TransformationMatrix applyRotationAroundX(float angleInRad){
		Matrix rotationMatrix = new Matrix(new float[]{
			
		});
		
		tMatrix.multiplyFromRigh(rotationMatrix);
		return this;
	}
	
	public TransformationMatrix applyTransition(float newX, float newY, float newZ){
		return this;
	}
	
	public TransformationMatrix applyScaling(float xScalingFactor, float yScalingFactor, float zScalingFactor){
		return this;
	}
	

	public Vector transformVector(Vector v){
		return tMatrix.multiplyWithVector(v);
	}
	
}
