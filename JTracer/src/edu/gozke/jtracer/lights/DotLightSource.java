package edu.gozke.jtracer.lights;

import javax.naming.OperationNotSupportedException;

import edu.gozke.jtracer.core.Color;
import edu.gozke.jtracer.core.Vector;

/**
 * This class represents a multi-directional dot-like light source.
 * 
 * @author Gozke
 *
 */
public class DotLightSource implements LightSource {
	Color ownColor;
	Vector position;
		
	/**
	 * @param ownColor
	 * @param position
	 */
	public DotLightSource(Color ownColor, Vector position) {
		this.ownColor = ownColor;
		this.position = position;
	}

	@Override
	public Color getColor() {
		return ownColor;
	}

	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public Vector getDirection() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

}
