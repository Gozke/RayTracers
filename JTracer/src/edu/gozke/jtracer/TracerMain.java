package edu.gozke.jtracer;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JWindow;

import edu.gozke.jtracer.core.Scene;

public class TracerMain extends JWindow{
	Scene sceneToRender;
	
	/**
	 * Sets up the window. Creates controls and other stuff.
	 */
	public void initWindow() {
		JPanel contentPane = new JPanel();
		
		
	}
	
	/**
	 * Creates and initializes the scene and it' related objects. Eg Lights, Camera, Renderable objects.
	 */
	public void initScene(){
		
	}
	
	/**
	 * Renders the scene in the given resolution. While rendering a progressbar is displayed.
	 * 
	 * @param resolution the resolution of the rendered image.
	 */
	public void renderAndShowScene(Dimension resolution){
		
	}
	
	
	public static void main(String[] args)
	{
		TracerMain tracerWindow = new TracerMain();
		tracerWindow.initScene();
		tracerWindow.initWindow();
	}
}
