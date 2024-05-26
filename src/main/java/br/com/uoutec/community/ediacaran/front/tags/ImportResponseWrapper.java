package br.com.uoutec.community.ediacaran.front.tags;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ImportResponseWrapper extends HttpServletResponseWrapper { 
	 
	 private ServletOutputStream sos; 
	 
	 private int status = 200; 
	 
	 private Writer writter;
	 
	 public ImportResponseWrapper(HttpServletResponse response, Writer writter) { 
		 super(response); 
	     this.writter = writter;
	     this.sos = new ServletOutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				writter.write(b);
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {
				
			}
			
	     }; 		     
	 } 
	  
	 public PrintWriter getWriter() throws IOException { 
	     return new PrintWriter(writter); 
	 } 
	  
	 public ServletOutputStream getOutputStream() { 
	     return sos; 
	 } 
	 
	 public void setContentType(String x) { 
	 } 
	 
	 public void setLocale(Locale x) { 
	 } 
	 
	 public void setStatus(int status) { 
	     this.status = status; 
	 } 
	 
	 public int getStatus() { 
	     return status; 
	 } 
	 
}