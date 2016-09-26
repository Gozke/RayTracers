package edu.gozke.jtracer;

import edu.gozke.jtracer.core.Color;
import edu.gozke.jtracer.core.Scene;
import edu.gozke.jtracer.swing.TracerFrame;

public class TracerMain{
	public Scene sceneToRender;

	
	/**
	 * Creates and initializes the scene and it' related objects. Eg Lights, Camera, Renderable objects.
	 */
	public void initScene(){
		sceneToRender = new Scene(800, 600);
	}

	
	public static void main(String[] args)
	{
		TracerMain tracerCoorinator = new TracerMain();
		tracerCoorinator.initScene();
		TracerFrame mainWindow = new TracerFrame(tracerCoorinator.sceneToRender);
		mainWindow.setVisible(true);
	}
	
}
