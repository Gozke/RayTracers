package edu.gozke.jtracer;

public class RenderOptions {
	private Boolean parallelRendedingStatus;
	
	/**
	 * Sets the parallel rendering option's value.  
	 * 
	 * @param enabled {@code True} or {@code False}. {@code Null} means undetermined.
	 * @return this render object after setting the flag
	 */
	public RenderOptions setParalellRenderingEnabled(Boolean enabled){
		parallelRendedingStatus = enabled;
		return this;
	}

	public Boolean getParallelRendedingEnabled() {
		return parallelRendedingStatus;
	}
	
}
