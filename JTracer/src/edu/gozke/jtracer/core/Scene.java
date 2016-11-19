package edu.gozke.jtracer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import edu.gozke.jtracer.RenderOptions;
import edu.gozke.jtracer.RenderableScene;
import edu.gozke.jtracer.lights.LightSource;
import edu.gozke.jtracer.materials.RoughSurface;
import edu.gozke.jtracer.objects.RenderableObject;

public class Scene implements RenderableScene{
	private List<RenderableObject> objectsInScene;
	private List<LightSource> lightsInScene;
	private Camera camera;
	private Color Lambient = new Color(10,10,10, true); // gray-ish
	private Color backgroundColor;
	
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
	 * Sets the color and intensity of the ambient light. 
	 * @param illColor new ambient light color/intensity
	 */
	public void setAmbientIllumination(Color illColor){
		Lambient = illColor;
	}
	
	/**
	 * Set the background color. This color is used when no objects intersected with the ray.
	 * Set this to {@code null} to disable this feature. (If disabled, the ambient color will be used in this case)
	 * 
	 * @param bgroundColor the color to use when no object intersects with the ray. Set to {@code null}
	 * to use ambient color in this case.
	 */
	public void setBackgroundColor(Color bgroundColor){
		backgroundColor = bgroundColor;
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
	
	protected Color trace(Ray ray){
		Intersection hit = findNearestInteresction(ray);
		Color color = Lambient;
		if(hit != Intersection.NO_INTERSECTION){
			for(LightSource light: lightsInScene){
				Ray shadowRay = new Ray(hit.intersectionPoint, light.getPosition().minus(hit.intersectionPoint));
				if(findNearestInteresction(shadowRay) == Intersection.NO_INTERSECTION){
					RoughSurface mat = hit.intersectedObject.getMaterial();
					Vector toLight = light.getPosition().minus(hit.intersectionPoint).normalize();
					Vector toCamera = ray.direction.scaleBy(-1);
					float lightIntensityFactor = (float) (3f/light.getPosition().minus(hit.intersectionPoint).lenght());
					Color newC = mat.calulcateReflectedRadiance(toLight, toCamera, hit.normalVector, light.getColor().multiply(lightIntensityFactor));
					color = color.plus(newC);
				}
			}
		} else {
			return backgroundColor != null ? backgroundColor : color; 
		}
		
		return color;
	}

	@Override
	public byte[] renderedScene(RenderOptions options) {
		int width = camera.getRenderingHeight();
		int height = camera.getRenderingWidth();
		byte[] buffer = new byte[width*height*3];
		Stream<Ray> rayStream = options.getParallelRendedingEnabled() ? camera.getAllRays().parallelStream() : camera.getAllRays().stream();
		rayStream.forEach(ray -> {
			Color pixelColor = trace(ray);
			buffer[ray.pixelId*3] = (byte) (pixelColor.red * 255);
			buffer[ray.pixelId*3+1] = (byte) (pixelColor.green * 255);
			buffer[ray.pixelId*3+2] = (byte) (pixelColor.blue * 255);
		});
		
		return buffer;
	}

	public void setRenderingResolution(int width, int height){
		camera.setRenderingWidth(width);
		camera.setRenderingHeight(height);
	}
	
	/**
	 * Checks every object find the nearest intersection with the ray.
	 * 
	 * @return Intersection details in world's base.
	 *  {@link Intersection#NO_INTERSECTION} if no intersecting object was found.
	 */
	private Intersection findNearestInteresction(Ray ray){
		Intersection nearestIntersection = Intersection.NO_INTERSECTION;
		for(RenderableObject obj : objectsInScene){
			Intersection hit = obj.intersectWith(ray);
			if(hit != Intersection.NO_INTERSECTION && hit.tParameter < nearestIntersection.tParameter){
				nearestIntersection = hit;
			}
		}
		
		return nearestIntersection;		
	}

}
