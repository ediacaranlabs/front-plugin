package br.com.uoutec.community.ediacaran.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.brandao.brutos.io.Resource;
import org.brandao.brutos.io.ResourceLoader;

import br.com.uoutec.community.ediacaran.plugins.PublicBean;

public class TemplateLoader implements PublicBean{

	public StringPattern load(String resource, ResourceLoader loader, String charsetName) throws IOException {
		Resource r = loader.getResource(resource);
		return r == null? null : load(r, charsetName);
	}
	
	public StringPattern load(Resource resource, String charsetName) throws IOException {
		
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), Charset.forName(charsetName)))) {	
				while ((line = br.readLine()) != null) {
					stringBuilder.append(line);
				}
		}
		
		return new StringPattern(stringBuilder.toString());
	}
	
}
