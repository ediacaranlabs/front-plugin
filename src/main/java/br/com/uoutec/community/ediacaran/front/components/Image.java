package br.com.uoutec.community.ediacaran.front.components;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.brandao.brutos.annotation.Basic;

import br.com.uoutec.application.imageio.ImageIO;
import br.com.uoutec.application.io.Path;
import br.com.uoutec.application.io.Vfs;

public class Image {

	//private static final int maxWidth = 2048; 
	
	//private static final int maxHeight = 2048;
	
	//private static final int maxPoints = maxWidth*maxHeight;
			
	private Double x1;
	
	private Double x2;
	
	private Double y1;
	
	private Double y2;
	
	private Path tmpFile;

	private String border;
	
	public Path getTmpFile() {
		return tmpFile;
	}

	@Basic(bean="file")
	public void setTmpFile(Path tmpFile) {
		this.tmpFile = tmpFile;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
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

	public ImageStream stream() {
		
		if(x1 == null || x2 == null || y1 == null || y2 == null) {
			throw new IllegalStateException("x1 | x2 | y1 | y2");
		}

		return  new ImageStream()
		.dimension(new Dimension(
				x2.intValue()-x1.intValue(), 
				y2.intValue()-y1.intValue()
		))
		.imageBounds(new Rectangle(
				x1.intValue(), 
				y1.intValue(), 
				x2.intValue()-x1.intValue(), 
				y2.intValue()-y1.intValue()
		))
		.image(()->{
			
			BufferedImage img = null;
			
			try(InputStream in = tmpFile.openInputStream()){
				img = ImageIO.read(in);	
			}
			catch(Throwable ex) {
				throw new RuntimeException(ex);
			}
			
			return img;
		})
		.mask(
			"circle".equals(border)? 
				ImageStream.CIRCLE_MASK_RENDER : 
				null 
		);
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

		/*
		stream()
			.dimension(new Dimension(
					width, 
					height
			))
			.imageBounds(new Rectangle(
					x1.intValue(), 
					y1.intValue(), 
					x2.intValue()-x1.intValue(), 
					y2.intValue()-y1.intValue()
			))
			.image(()->{
				
				BufferedImage img = null;
				
				try(InputStream in = tmpFile.openInputStream()){
					img = ImageIO.read(in);	
				}
				catch(Throwable ex) {
					throw new RuntimeException(ex);
				}
				
				return img;
			})
			.save((img)->{
				try (OutputStream out = file.openOutputStream()){
					ImageIO.write(img, "png", out);
					out.flush();
				}
			});
		*/
		
		BufferedImage img = null;
		
		try(InputStream in = tmpFile.openInputStream()){
			img = ImageIO.read(in);	
		}
		
		if(img == null) {
			throw new IllegalStateException("image");
		}

		BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	    
	    g2d.fillOval(0, 0, width -1, height - 1);
	    g2d.dispose();
	    
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	    
		g2.drawImage(img, 0, 0, width, height, x1.intValue(), y1.intValue(), x2.intValue(), y2.intValue(), null);
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2.drawImage(mask, 0, 0, null);
		g2.dispose();
		
		try (OutputStream out = file.openOutputStream()){
			ImageIO.write(image, "png", out);
			out.flush();
		}
		
		
	}
	
}
