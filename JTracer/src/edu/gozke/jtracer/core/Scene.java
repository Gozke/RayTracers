package edu.gozke.jtracer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import edu.gozke.jtracer.RenderOptions;
import edu.gozke.jtracer.RenderableScene;
import edu.gozke.jtracer.objects.RenderableObject;

public class Scene implements Function<Ray, Color>, RenderableScene{
	private List<RenderableObject> objectsInScene;
	private List<LightSource> lightsInScene;
	private Camera camera;
	
	private int width;
	private int height;
	
	private Color Lambient = new Color(152,244,250);
	
	/**
	 * This constructor will construct a scene with the given resolution. (800x600 could be a safe start)
	 * 
	 * @param width Width in pixels. 
	 * @param height Height in pixels.
	 */
	public Scene(int width, int height){
		objectsInScene = new ArrayList<>();
		lightsInScene = new ArrayList<>();
		camera = null;
	}
	
	
	public Color trace(Ray ray){
		Intersection hit = findNearestInteresction(ray);
		if(hit != Intersection.NO_INTERSECTION){
			// do stuff with intersection point..
		}
		
		// return ambient color..
		return Lambient;
	}
	
	
	/**
	 * Checks every object find the nearest intersection with the ray.
	 * 
	 * @return Intersection details in world's base.
	 *  {@link Intersection#NO_INTERSECTION} if no intersecting object was found.
	 */
	private Intersection findNearestInteresction(Ray ray){
		RenderableObject nearestObject = null;
		Intersection nearestIntersection = Intersection.NO_INTERSECTION;
		for(RenderableObject obj : objectsInScene){
			Intersection hit = obj.intersectWith(ray);
			if(hit != Intersection.NO_INTERSECTION && hit.tParameter < nearestIntersection.tParameter){
				nearestIntersection = hit;
				nearestObject = obj;
			}
		}
		
		return Intersection.NO_INTERSECTION;		
	}

	@Override
	public Color apply(Ray t) {
		return trace(t);
	}

	@Override
	public byte[] renderedScene(int width, int height, RenderOptions options) {
		byte[] buffer = new byte[width*height*3];
		for(int r = 0; r<height; r++){
			for(int c = 0; c<width; c++){
				int pixelBaseIndex = r*width + c;
				Color color = trace(camera.getRay(r, c));
				buffer[pixelBaseIndex] = (byte) (color.blue*255);
				buffer[pixelBaseIndex+1] = (byte) (color.green*255);
				buffer[pixelBaseIndex+2] = (byte) (color.red*255);
			}
		}
		return buffer;
	}
	
}
