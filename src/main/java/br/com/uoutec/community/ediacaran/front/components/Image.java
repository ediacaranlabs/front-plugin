package br.com.uoutec.community.ediacaran.front.components;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.brandao.brutos.annotation.Basic;

import br.com.uoutec.application.io.Path;
import br.com.uoutec.application.io.Vfs;

public class Image {

	private static final int maxWidth = 2048; 
	
	private static final int maxHeight = 1024;
	
	private static final int maxPoints = maxWidth*maxHeight;
			
	private Double x1;
	
	private Double x2;
	
	private Double y1;
	
	private Double y2;
	
	private File tmpFile;

	public File getTmpFile() {
		return tmpFile;
	}

	@Basic(bean="file")
	public void setTmpFile(File tmpFile) {
		this.tmpFile = tmpFile;
	}

	public void setX1(Double x1) {
		this.x1 = x1;
	}

	public void setX2(Double x2) {
		this.x2 = x2;
	}

	public void setY1(Double y1) {
		this.y1 = y1;
	}

	public void setY2(Double y2) {
		this.y2 = y2;
	}

	public Double getX1() {
		return x1;
	}

	public Double getX2() {
		return x2;
	}

	public Double getY1() {
		return y1;
	}

	public Double getY2() {
		return y2;
	}

	public Path save(int width, int height) throws IOException {
		if(tmpFile != null) {
			Path f = Vfs.createTempFile("img-field", ".png");
			f.deleteOnExit();
			save(width, height, f);
			return f;
		}
		
		return null;
	}

	public void save(int width, int height, Path file) throws IOException {
		
		if(x1 == null || x2 == null || y1 == null || y2 == null) {
			throw new IllegalStateException("x1 | x2 | y1 | y2");
		}

		if(width < 10 || height < 10) {
			throw new IllegalStateException("width | height");
		}
		
		if(x2-x1 < 10 || y2-y1 < 10) {
			throw new IllegalStateException("x | y");
		}

		if((x2-x1)*(y2-y1) > maxPoints) {
			throw new IllegalStateException("maxPoints");
		}
		
		BufferedImage img = ImageIO.read(tmpFile);
		
		if(img == null) {
			throw new IllegalStateException("image");
		}

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		
		//java.awt.Image rescaled = img.getScaledInstance(width, height, java.awt.Image.SCALE_AREA_AVERAGING);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, width, height, x1.intValue(), y1.intValue(), x2.intValue(), y2.intValue(), null);
		
		try (OutputStream out = file.openOutputStream()){
			ImageIO.write(image, "png", out);
		}
		
	}
	
}
