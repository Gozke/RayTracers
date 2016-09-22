package edu.gozke.Jtracer;

import java.util.ArrayList;
import java.util.List;

public class Scene {
	private List<RenderableObject> objectsInScene;
	private List<LightSource> lightsInScene;
	private Camera camera;
	
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
		return rasterBuffer;
	}
	
	private Color trace(){
		return null;
	}
}
