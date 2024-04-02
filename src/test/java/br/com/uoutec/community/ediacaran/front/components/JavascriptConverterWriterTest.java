package br.com.uoutec.community.ediacaran.front.components;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

public class JavascriptConverterWriterTest {

	@Test
	public void testSimpleCode() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		PrintWriter pr = new PrintWriter(bo);
		JavascriptConverterWriter w = new JavascriptConverterWriter(pr);
		
		String str = 
		"<ed:row>" +
		"	<ed:col size=\"2\">" +
		"   \u0000if(test == null){\u0001" +
		"		#{item.id}" +
		"   \u0000}\u0001" +
		"	</ed:col>" +
		"	<ed:col size=\"6\">" +
		"		#{item.name}" +
		"	</ed:col>" +
		"	<ed:col size=\"2\">" +
		"		#{item.gender}" +
		"	</ed:col>" +
		"	<ed:col size=\"2\">" +
		"		<a href=\"${pageContext.request.contextPath}/edit/#{item.id}/\">Edit</a> |" +
		"		<a href=\"${pageContext.request.contextPath}/delete/#{item.id}/\">Delete</a>" +
		"	</ed:col>" +
		"</ed:row>";
		
		w.write(str);
		w.flush();
		w.close();
		
		String code = new String(bo.toByteArray());
		System.out.println(code);
	}
	
}
