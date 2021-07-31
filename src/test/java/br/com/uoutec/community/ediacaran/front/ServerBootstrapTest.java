package br.com.uoutec.community.ediacaran.front;

import java.io.IOException;
import java.net.URISyntaxException;

import br.com.uoutec.application.se.ApplicationBoot;
import br.com.uoutec.application.se.ApplicationBootstrap;
import br.com.uoutec.application.se.ApplicationBootstrapProxy;

public class ServerBootstrapTest {
	
	public static void main(String[] s) throws IOException, URISyntaxException {
		
		ApplicationBootstrap applicationBootstrap  = 
				new ApplicationBootstrapProxy("app");

		ApplicationBoot.run(applicationBootstrap, new String[] {
				"--app=config/ediacaran-config.xml",
				"--default=config/ediacaran-dev.properties",
				"--logger=config/log4j.configuration"
		});
		
	}

	
}
