package edu.gozke.jtracer.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ImgDisplayer extends JComponent {
	private BufferedImage imgToDisplay;
	
	/**
	 * @param imgToDisplay
	 */
	public ImgDisplayer(BufferedImage imgToDisplay) {
		this.imgToDisplay = imgToDisplay;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(imgToDisplay, 0, 0, null);
		
		super.paintComponent(g);
	}

	
}
