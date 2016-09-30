package edu.gozke.jtracer;

public interface RenderableScene {
	/**
	 * Passes the provided options to the ray tracing engine and renders the
	 * scene at the requested resolution.
	 * 
	 * @param width width of the output image
	 * @param height height of the output image
	 * @param options options passed to the ray tracer engine
	 * 
	 * @return the color rendered image in a flat BGR byte array. Size is width*height*3.
	 */
	public byte[] renderedScene(RenderOptions options);
}
