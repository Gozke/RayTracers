package edu.gozke.jtracer;

import edu.gozke.jtracer.core.Camera;
import edu.gozke.jtracer.core.Scene;
import edu.gozke.jtracer.core.TransformationMatrix;
import edu.gozke.jtracer.objects.Sphere;
import edu.gozke.jtracer.swing.TracerFrame;

public class TracerMain{
	public Scene sceneToRender;

	
	/**
	 * Creates and initializes the scene and it' related objects. Eg Lights, Camera, Renderable objects.
	 */
	public void initScene(){
		sceneToRender = new Scene(new Camera(800, 600));
		Sphere testSphere = new Sphere(1, new TransformationMatrix().applyTranslation(3, 0, 0));
		sceneToRender.addObject(testSphere);
	}

	
	public static void main(String[] args)
	{
		TracerMain tracerCoorinator = new TracerMain();
		tracerCoorinator.initScene();
		TracerFrame mainWindow = new TracerFrame(tracerCoorinator.sceneToRender);
		mainWindow.setVisible(true);
	}
	
}
