package br.com.uoutec.community.ediacaran.front.objects;

public class ObjectHandlerImp implements ObjectHandler{

	private ObjectReader reader;
	
	private ObjectWriter writer;
	
	public ObjectHandlerImp() {
		this.reader = new ObjectReaderImp();
		this.writer = new ObjectWriterImp();
	}
	@Override
	public String getType() {
		return "json";
	}

	@Override
	public ObjectReader getReader() {
		return reader;
	}

	@Override
	public ObjectWriter getWriter() {
		return writer;
	}

	@Override
	public boolean accept(Object o) {
		return true;
	}

	@Override
	public boolean accept(String type) {
		return "json".equals(type);
	}

}
