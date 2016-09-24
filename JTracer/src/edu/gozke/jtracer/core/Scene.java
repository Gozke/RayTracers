package edu.gozke.jtracer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import edu.gozke.jtracer.objects.RenderableObject;

public class Scene {
	private List<RenderableObject> objectsInScene;
	private List<LightSource> lightsInScene;
	private Camera camera;
	
	private int width;
	private int height;
	private Byte[] rasterBuffer; 
			
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
		rasterBuffer = new Byte[width*height];
	}
	
	public Byte[] render(){
		Color[][] buffer = new Color[height][width];
		for(int r = 0; r<height; r++){
			for(int c = 0; c<width; c++){
				buffer[r][c] = trace(camera.getRay(r, c));
			}
		}
		return rasterBuffer;
	}
	
	public Color trace(Ray ray){
		return null;
	}
	
}
