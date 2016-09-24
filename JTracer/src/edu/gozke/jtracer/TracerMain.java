package edu.gozke.jtracer;

import edu.gozke.jtracer.core.Color;
import edu.gozke.jtracer.core.Scene;

public class TracerMain implements SceneRenderer{
	Scene sceneToRender;

	
	/**
	 * Creates and initializes the scene and it' related objects. Eg Lights, Camera, Renderable objects.
	 */
	public void initScene(){
		
	}

	@Override
	public Color[] renderedScene(int width, int height, RenderOptions options) {
		try {
			System.out.println("sleeping for 5 secs");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		TracerMain tracerCoorinator = new TracerMain();
		tracerCoorinator.initScene();
		TracerFrame mainWindow = new TracerFrame(tracerCoorinator);
		mainWindow.setVisible(true);
	}
	
}
