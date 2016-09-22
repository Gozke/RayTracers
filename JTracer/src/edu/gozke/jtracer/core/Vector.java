package edu.gozke.jtracer.core;

/**
 * This is a standard 3D vector class. This is an immutable object we're talking about here. 
 * 
 * @author Gozke
 *
 */
public class Vector {
	public final float x,y,z;

	public Vector(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the length of the vector.
	 * @return
	 */
	public float lenght(){
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Returns the normalized version of this vector. (new object is created)
	 * If the vector's length is 0 the vector is returned without modification.
	 * 
	 * @return returns the normalized vector. 
	 */
	public Vector normalize(){
		float len = lenght();
		if(len != 0){
			return new Vector(x / len, y / len, z / len);
		}
		return this;
	}
	
	/**
	 * Returns the angle between this vector and the parameter vector.
	 * @return
	 */
	public float dotProduct(Vector v){
		return x*v.x + y*v.y + z*v.z; 
	}
	
	/**
	 * Returns the cross product of this vector and the parameter vector.

	 * @param otherVector
	 * @return thisVector X otherVector
	 */
	public Vector crossProductWith(Vector otherVector){
		return new Vector(
				 y*otherVector.z - z*otherVector.y
				,z*otherVector.x - x*otherVector.z
				,x*otherVector.y - y*otherVector.x);
	}
	
	/**
	 * Returns the sum of the two vectors.
	 * 
	 * @param a 
	 * @param b
	 * @return a + b
	 */
	public static Vector add(Vector a, Vector b){
		return new Vector(
				 a.x+b.x
				,a.y+b.y
				,a.z+b.z
				);
	}
	
	/**
	 * This method adds the parameter vector to this vector.
	 * 
	 * @param otherVector
	 * @return this vector + other vector
	 */
	public Vector add(Vector otherVector){
		return add(this,otherVector);
	}
	
	/**
	 * Returns the a - b new vector.
	 * For performance reasons this does not delegate calls to {@link #add(Vector, Vector)}.
	 * 
	 * @param a vector to subtract from
	 * @param b vector to be subtracted
	 * @return a - b in a new vector
	 */
	public static Vector subtract(Vector a, Vector b){
		return new Vector(
				 a.x - b.x
				,a.y - b.y
				,a.z - b.z
				);
	}
	
	
	/**
	 * Returns a scaled version of this vector.
	 * 
	 * @param scaler the scaling factor
	 * @return the scaled vector
	 */
	public Vector scaleBy(float scaler){
		return new Vector(scaler*x, scaler*y, scaler*z);
	}

	/**
	 * 
	 * @return a clone of this vector
	 */
	public Vector clone(){
		return new Vector(x,y,z);
	}
}
