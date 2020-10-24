package br.com.uoutec.community.ediacaran.front.tags.front;

public class WrapperComponent extends AbstractComponent {

	public WrapperComponent() throws Throwable {
		super();
	}

	protected void loadConfiguration() {
		super.template = "/default_template/front/components/wrapper.tmp";
	}

}
