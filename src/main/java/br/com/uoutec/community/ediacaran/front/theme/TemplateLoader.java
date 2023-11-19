package br.com.uoutec.community.ediacaran.front.theme;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import br.com.uoutec.community.ediacaran.io.FileSystem;

public class TemplateLoader {

	public ComponentTemplate load(File resource, String charsetName) throws IOException {
		
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader( FileSystem.getInputStream(resource), Charset.forName(charsetName)))) {	
				while ((line = br.readLine()) != null) {
					if(stringBuilder.length() != 0) {
						stringBuilder.append("\n");
					}
					stringBuilder.append(line);
				}
		}
		
		return new ComponentTemplate(stringBuilder.toString());
	}
	
}
