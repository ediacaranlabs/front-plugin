package br.com.uoutec.community.ediacaran.front;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import br.com.uoutec.application.se.ApplicationBootstrapProvider;
import br.com.uoutec.community.ediacaran.EdiacaranBootstrap;

@SuppressWarnings("serial")
public class ServerBootstrapTest {
	
	public static void main(String[] s) throws IOException {
		File cfg = new File("config/ediacaran-config.xml").getCanonicalFile();
		XMLDecoder xml = new XMLDecoder(new FileInputStream(cfg));
		EdiacaranBootstrap sb = (EdiacaranBootstrap) xml.readObject();
		xml.close();

		ApplicationBootstrapProvider.setBootstrap(sb);
		
		sb.applicationInitialized(new HashMap<String, Object>(){{
			put("default", "config/ediacaran-dev.properties");
			put("logger",  "config/log4j.configuration");
		}});
	}
}