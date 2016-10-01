package edu.gozke.jtracer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.gozke.jtracer.RenderOptions;
import edu.gozke.jtracer.RenderableScene;
import edu.gozke.jtracer.objects.RenderableObject;

public class Scene implements RenderableScene{
	private List<RenderableObject> objectsInScene;
	private List<LightSource> lightsInScene;
	private Camera camera;
	private Color Lambient = new Color(0.596f,0.957f,0.95f);
	
	/**
	 * This constructor will construct a scene with the given resolution. (800x600 could be a safe start)
	 * 
	 * @param camera the camera to use for rendering
	 */
	public Scene(Camera camera){
		objectsInScene = new ArrayList<>();
		lightsInScene = new ArrayList<>();
		this.camera = camera;
	}
	
	/**
	 * Adds the given light source to the scene. The object is taken by reference so changes 
	 * made to this object will take effect immediately in the scene as well.
	 * 
	 * @param lightsource the light source to be added. If {@code null} nothing happens.
	 */
	public void addLight(LightSource lightsource){
		lightsInScene.add(lightsource);
	}
	
	public void addObject(RenderableObject obj){
		objectsInScene.add(obj);
	}
	
	/**
	 * Sets the new camera. This object is set by reference and not cloned. This way changes 
	 * made to this object will take effect immediately in the scene as well.
	 * <br> This can be useful if you want to move the camera around for example.
	 * 
	 * 
	 * @param c the new camera. May not be {@code null}!
	 */
	public void setCamera(Camera c){
		this.camera = c;
	}
	
	public Color trace(Ray ray){
		Intersection hit = findNearestInteresction(ray);
		if(hit != Intersection.NO_INTERSECTION){
			// do stuff with intersection point..
		}
		
		// return ambient color..
		return Lambient;
	}

	@Override
	public byte[] renderedScene(RenderOptions options) {
		int width = camera.getRenderingHeight();
		int height = camera.getRenderingWidth();
		byte[] buffer = new byte[width*height*3];
		Stream<Ray> rayStream = options.getParallelRendedingEnabled() ? camera.getAllRays().parallelStream() : camera.getAllRays().stream();
		rayStream.forEach(ray -> {
			Color pixelColor = trace(ray);
			buffer[ray.pixelId*3] = (byte) (pixelColor.blue * 255);
			buffer[ray.pixelId*3+1] = (byte) (pixelColor.green * 255);
			buffer[ray.pixelId*3+1] = (byte) (pixelColor.red * 255);
		});
		
		return buffer;
	}

	public void setRenderingResolution(int width, int height){
		camera.setRenderingHeight(width);
		camera.setRenderingHeight(height);
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

}
