package edu.gozke.jtracer;

import edu.gozke.jtracer.core.Color;

public interface SceneRenderer {
	/**
	 * Passes the provided options to the ray tracing engine and renders the
	 * scene at the requested resolution. A Color array is returned with the
	 * size of width*height.
	 * 
	 * @param width width of the output image
	 * @param height height of the output image
	 * @param options options passed to the ray tracer engine
	 * 
	 * @return color array representing the image
	 */
	public Color[] renderedScene(int width, int height, RenderOptions options);
}
