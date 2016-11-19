package edu.gozke.jtracer.materials;

import edu.gozke.jtracer.core.Color;
import edu.gozke.jtracer.core.Vector;

/**
 * This class represents a diffuse + specular surface. That is a matte, rough material with small, blurry shining points.
 * 
 * @author Gozke
 *
 */
public class RoughSurface {
	/**
	 * Diffuse color.
	 */
	private Color kd;
	
	/**
	 * Specular color.
	 */
	private Color ks;
	
	private float shininess;

	/**
	 * @param kd
	 * @param ks
	 * @param shininess
	 */
	public RoughSurface(Color kd, Color ks, float shininess) {
		this.kd = kd;
		this.ks = ks;
		this.shininess = shininess;
	}
	
	/**
	 * All coordinates are in world's base.
	 * 
	 * @param lightsDirection the vector pointing from the point of intersection to the light source
	 * @param camerasDirection the vector pointing from the point of intersection to the ray's origin
	 * @param surfaceNormal the surface normal of the object in the point of intersection
	 * @param incomingIllumination the color of the light that is shining at the object
	 * @return
	 */
	public Color calulcateReflectedRadiance(Vector lightsDirection, Vector camerasDirection, Vector surfaceNormal, Color incomingIllumination){
		float cosTetha = surfaceNormal.dotProduct(lightsDirection);
        if (cosTetha < 0) return new Color(0, 0, 0);
        Color lightReflected = incomingIllumination.multiply(kd).multiply(cosTetha);
        Vector H = lightsDirection.plus(camerasDirection).normalize();
        float cosDelta = surfaceNormal.dotProduct(H);
        if(cosDelta < 0){
        	return lightReflected;
        }
        
		return lightReflected.plus(lightReflected.multiply(ks).multiply((float)Math.pow(cosDelta, shininess)));
	}
}
