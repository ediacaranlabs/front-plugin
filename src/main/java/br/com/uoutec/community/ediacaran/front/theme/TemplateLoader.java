package br.com.uoutec.community.ediacaran.front.theme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.uoutec.application.io.Path;

public class TemplateLoader {

	public ComponentTemplate load(Path resource, String charsetName) throws IOException {
		
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader( resource.openInputStream(), Charset.forName(charsetName)))) {	
				while ((line = br.readLine()) != null) {
					if(stringBuilder.length() != 0) {
						stringBuilder.append("\n");
					}
					stringBuilder.append(line);
				}
		}
		
		String str = stringBuilder.toString();
		
		Pattern p = Pattern.compile(Pattern.quote("\\u") + "(\\d{4,4})");
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(m.find()) {
			String hex = m.group(1);
			int charValue = Integer.parseInt(hex, 16);
			String charSTR = String.valueOf( ((char)charValue) );
			m.appendReplacement(sb, charSTR);
		}
		
		m.appendTail(sb);
		str =  sb.toString();		
		
		return new ComponentTemplate(str);
	}
	
}
