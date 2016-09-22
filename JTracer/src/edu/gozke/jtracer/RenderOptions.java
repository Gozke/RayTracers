package edu.gozke.jtracer;

public class RenderOptions {
	private Boolean parallelRendedingEnabled;
	
	/**
	 * Sets the parallel rendering option's value.  
	 * 
	 * @param enabled {@code True} or {@code False}. {@code Null} means undetermined.
	 * @return this render object after setting the flag
	 */
	public RenderOptions setIsParalellRenderingEnabled(Boolean enabled){
		parallelRendedingEnabled = enabled;
		return this;
	}
}
