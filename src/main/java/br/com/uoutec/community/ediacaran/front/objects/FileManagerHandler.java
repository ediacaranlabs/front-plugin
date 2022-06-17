package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.IOException;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;

public interface FileManagerHandler {

	String toFilePath(String value);
	
	FileMetadata toMetadata(File base, File file);

	FileMetadata toMetadata(File base, String path);
	
	File toFile(File base, FileMetadata omd);

	Object read(File file, FileMetadata metadata) throws IOException ;
	
	void write(File file, FileMetadata metadata, Object value) throws IOException ;
	
}
