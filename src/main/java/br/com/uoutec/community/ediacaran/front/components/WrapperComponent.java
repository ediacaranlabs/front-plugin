package br.com.uoutec.community.ediacaran.front.components;

public class WrapperComponent extends AbstractTemplateComponent {

	public WrapperComponent() throws Throwable {
		super();
	}

	public void loadConfiguration() {
		super.template = "/default_template/front/components/wrapper.tmp";
	}

}
