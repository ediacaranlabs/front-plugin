package br.com.uoutec.community.ediacaran.front.objects;

import java.io.File;
import java.io.IOException;

import br.com.uoutec.community.ediacaran.front.objects.FileManager.FileMetadata;

public interface FileManagerHandler {

	default String toFilePath(String value) {
		try {
			value = value.replaceAll("/+", "\\\\");
			File f = new File(value);
			f.getCanonicalFile();
			return f.toString();
		}
		catch(IOException e) {
			return null;
		}
	}
	
	FileMetadata toMetadata(File base, File file);

	default FileMetadata toMetadata(File base, String path) {
		return toMetadata(base, new File(path));
	}
	
	File toFile(File base, FileMetadata omd);

	Object read(File file, FileMetadata metadata) throws IOException;
	
	void write(File file, FileMetadata metadata, Object value) throws IOException;
	
	void delete(File file, FileMetadata metadata) throws IOException;
	
}
