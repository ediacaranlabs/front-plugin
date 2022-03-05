package br.com.uoutec.community.ediacaran.front.components;

public class WrapperTemplateComponent extends AbstractTemplateComponent {

	public WrapperTemplateComponent() throws Throwable {
		super();
	}

	public void loadConfiguration() {
		super.template = "/default_template/front/components/wrapper.tmp";
	}

}
