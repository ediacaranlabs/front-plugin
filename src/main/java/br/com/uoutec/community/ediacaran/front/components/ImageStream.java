package br.com.uoutec.community.ediacaran.front.components;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import br.com.uoutec.application.security.ContextSystemSecurityCheck;

public class ImageStream {

	public static final IMask CIRCLE_MASK_RENDER = (d, q)->{
		
		BufferedImage mask = new BufferedImage(
				(int)d.getWidth(), 
				(int)d.getHeight(), 
				BufferedImage.TYPE_INT_ARGB
		);
		
	    Graphics2D g2d = ContextSystemSecurityCheck.doPrivileged(()->mask.createGraphics());
		try {
			q.quality(g2d);
			g2d.fillOval(0, 0, (int)d.getWidth() - 1,  (int)d.getHeight() - 1);
		}
		finally {
			g2d.dispose();
		}
		
		return mask;
	};
	
	private Rectangle imageBounds;
	
	private Dimension dimension;
	
	private IRender render = (g, i, d, ib)->{
		g.drawImage(
				i, 
				0, 0, (int)d.getWidth(), (int)d.getHeight(), 
				(int)ib.getX(), (int)ib.getY(), (int)ib.getMaxX(), (int)ib.getMaxY(), 
				null
		);
	};
	
	private IMask mask;
	
	private IQuality quality = (g)->{
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	};
	
	private ILoadImage loadImage;
	
	public ImageStream() {
	}
	
	public ImageStream dimension(Dimension value) {
		
		if(value.getWidth() < 10 || value.getHeight() < 10) {
			throw new IllegalStateException("width | height");
		}
		
		this.dimension = value;
		
		return this;
	}

	public ImageStream imageBounds(Rectangle value) {
		
		if(value.getWidth() < 10 || value.getHeight() < 10) {
			throw new IllegalStateException("x | y");
		}
		
		this.imageBounds = value;
		return this;
	}
	
	public ImageStream render(IRender value) {
		this.render = value;
		return this;
	}

	public ImageStream mask(IMask value) {
		this.mask = value;
		return this;
	}

	public ImageStream quality(IQuality value) {
		this.quality = value;
		return this;
	}

	public ImageStream image(ILoadImage value) {
		this.loadImage = value;
		return this;
	}

	public void save(ISaveImage value) throws IOException {
		BufferedImage image = toImage();
		value.save(image);
	}
	
	public BufferedImage toImage(){

		BufferedImage originalImage = loadImage.load();
		
		BufferedImage image = new BufferedImage(
				(int)dimension.getWidth(), 
				(int)dimension.getHeight(), 
				BufferedImage.TYPE_INT_ARGB
		);
		
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		try {
			quality.quality(g2d);
			
			render.render(g2d, originalImage, dimension, imageBounds);
			
			if(mask != null) {
				java.awt.Image maskIMG = mask.createMask(dimension, quality);
			    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
			    g2d.drawImage(maskIMG, 0, 0, null);
			}
			
		}
		finally {
			g2d.dispose();
		}
		
		return image;
	}

	public interface IMask {
		java.awt.Image createMask(Dimension dimension, IQuality quality);
	}
	
	public interface IRender {
		void render(Graphics2D value, java.awt.Image image, Dimension dimension, Rectangle imageBounds);
	}
	
	public interface IQuality {
		void quality(Graphics2D value);
	}

	public interface ILoadImage {
		BufferedImage load();
	}

	public interface ISaveImage {
		void save(BufferedImage value) throws IOException;
	}
	
}
