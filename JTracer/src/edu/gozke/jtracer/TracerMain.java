package edu.gozke.jtracer;

import edu.gozke.jtracer.core.Camera;
import edu.gozke.jtracer.core.Color;
import edu.gozke.jtracer.core.Scene;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.core.Vector;
import edu.gozke.jtracer.lights.DotLightSource;
import edu.gozke.jtracer.materials.RoughSurface;
import edu.gozke.jtracer.objects.Sphere;
import edu.gozke.jtracer.swing.TracerFrame;

public class TracerMain{
	public Scene sceneToRender;

	
	/**
	 * Creates and initializes the scene and it' related objects. Eg Lights, Camera, Renderable objects.
	 */
	public void initScene(){
		Camera camera = new Camera(new Vector(0,0,3f), new Vector(0,0,-1), new Vector(0,1,0), 600, 600);
		sceneToRender = new Scene(camera);
		RoughSurface mat = new RoughSurface(new Color(255,255,255,true), new Color(254,254,254,true), 5);
		Sphere testSphere = new Sphere(1, new TransformationMatrix().applyTranslation(-1, 0, -2), mat);
		sceneToRender.addObject(testSphere);
		
		RoughSurface mat2 = new RoughSurface(new Color(200,10,240,true), new Color(254,254,254,true), 5);
		Sphere testSphere2 = new Sphere(1f, new TransformationMatrix().applyTranslation(1.5f, 0, -2), mat2);
		sceneToRender.addObject(testSphere2);
		
		DotLightSource lamp1 = new DotLightSource(new Color(0,255,0, true), new Vector(2,2,3));
		sceneToRender.addLight(lamp1);
		
		DotLightSource lamp2 = new DotLightSource(new Color(255,0,0, true), new Vector(-2,0,3));
		sceneToRender.addLight(lamp2);
	}

	
	public static void main(String[] args)
	{
		TracerMain tracerCoorinator = new TracerMain();
		tracerCoorinator.initScene();
		TracerFrame mainWindow = new TracerFrame(tracerCoorinator.sceneToRender);
		mainWindow.setVisible(true);
	}
	
}
