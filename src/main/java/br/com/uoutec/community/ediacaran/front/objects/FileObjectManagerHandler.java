package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;

public class FileObjectManagerHandler extends AbstractFileManagerHandler{

	@Override
	public Object read(File file, FileMetadata metadata) throws IOException {
		return new FileObject(file, false);
	}

	@Override
	public void write(File file, FileMetadata metadata, Object value) throws FileNotFoundException, IOException {
		
		FileObject ig = (FileObject)value;
		
		if(!ig.isTransient()) {
			return;
		}
		
		InputStream in   = null;
		OutputStream out = null;
		
		try {
			in  = ig.getInputStream();
			out = new FileOutputStream(file);
			
			byte[] b = new byte[2048];
			int l = -1;
			
			while((l = in.read(b, 0, b.length)) > 0) {
				out.write(b, 0, l);
			}
			
		}
		finally {
			
			try {
				if(in != null) {
					in.close();
				}
			}
			catch(Throwable x) {
			}
			
			try {
				if(out != null) {
					out.close();
				}
			}
			catch(Throwable x) {
			}
			
		}
		
	}

	@Override
	public void delete(File file, FileMetadata metadata) throws IOException {
		file.delete();
	}
	
}
