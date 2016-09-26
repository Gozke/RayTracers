package edu.gozke.jtracer.objects;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;

public class RGBImageReader extends ImageReader {

	protected RGBImageReader(ImageReaderSpi originatingProvider) {
		super(originatingProvider);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getHeight(int arg0) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IIOMetadata getImageMetadata(int arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<ImageTypeSpecifier> getImageTypes(int arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumImages(boolean arg0) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IIOMetadata getStreamMetadata() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(int arg0) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedImage read(int arg0, ImageReadParam arg1) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
		Raster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, 800, 600, 1, null);
		;
		return null;
	}

}
