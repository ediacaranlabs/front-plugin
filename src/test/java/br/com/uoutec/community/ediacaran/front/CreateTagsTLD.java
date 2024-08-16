package br.com.uoutec.community.ediacaran.front;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.uoutec.application.scanner.DefaultScanner;
import br.com.uoutec.community.ediacaran.front.tags.AccordionItemTagComponent;
import br.com.uoutec.community.ediacaran.front.util.CreateTLDFile;

public class CreateTagsTLD {

	public static void main(String[] a) throws InstantiationException, IllegalAccessException, FileNotFoundException, IOException {
		CreateTLDFile createFile = new CreateTLDFile();
		
		System.setProperty("system.security.print",	"false");
		System.setProperty("system.security","false");
		
		DefaultScanner s = new DefaultScanner();
		s.setBasePackage(new String[] {AccordionItemTagComponent.class.getPackage().getName()});
		
		createFile.create(s);
	}
	
}
