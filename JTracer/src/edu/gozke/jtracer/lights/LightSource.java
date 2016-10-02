package edu.gozke.jtracer.lights;

import javax.naming.OperationNotSupportedException;

import edu.gozke.jtracer.core.Color;
import edu.gozke.jtracer.core.Vector;

public interface LightSource {
	/**
	 * Can be used to get the color of this light source.
	 * 
	 * @return color of the light source.
	 */
	public Color getColor();
	
	/**
	 * Returns the position of the light source in world's base.
	 * 
	 * @return Position of the light source in world's base.
	 */
	public Vector getPosition();
	
	/**
	 * Can be used to return the direction in which the light source emits light. Might not be applicable
	 * to all light sources. If the given light source does not support directions {@link OperationNotSupportedException}
	 * is returned.
	 * 
	 * @return the direction in which the light source emits light
	 * @throws OperationNotSupportedException if the given light source is not directional.
	 */
	public Vector getDirection() throws OperationNotSupportedException;
}
