package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;
import br.com.uoutec.community.ediacaran.front.objects.ObjectsManagerImp.ObjectFileMetadataManager;

public class ObjectsFileManagerHandler extends AbstractFileManagerHandler{

	@Override
	public Object read(File file, FileMetadata metadata) throws IOException {

		ObjectFileMetadataManager omd = (ObjectFileMetadataManager)metadata;
		Object object;
		
		try(InputStream stream = new FileInputStream(file)){
			object = omd.getHandler().getReader().read(stream);
		}
		
		return object;
		
	}

	@Override
	public void write(File file, FileMetadata metadata, Object value) throws FileNotFoundException, IOException {
		
		ObjectFileMetadataManager omd = (ObjectFileMetadataManager)metadata;
		
		ObjectHandler handler = omd.getHandler();
		try(OutputStream stream = new FileOutputStream(file)){
			handler.getWriter().write(value, stream);
			stream.flush();
		}
		
	}

}
