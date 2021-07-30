package br.com.uoutec.community.ediacaran.front;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import br.com.uoutec.application.se.ApplicationBoot;
import br.com.uoutec.application.se.ApplicationBootstrap;

public class ServerBootstrapTest {
	
	public static void main(String[] s) throws IOException, URISyntaxException {
		
		File cfg = new File("config/ediacaran-config.xml").getCanonicalFile();
		XMLDecoder xml = new XMLDecoder(new FileInputStream(cfg));
		ApplicationBootstrap applicationBootstrap  = (ApplicationBootstrap) xml.readObject();
		xml.close();

		ApplicationBoot.run(applicationBootstrap, new String[] {
				"--default=config/ediacaran-dev.properties",
				"--logger=config/log4j.configuration"
		});
		
	}

	
}
