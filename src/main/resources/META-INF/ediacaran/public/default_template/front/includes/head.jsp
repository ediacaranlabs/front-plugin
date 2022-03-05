<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<ec:resources type="css"/>
<ec:resources type="js"/>
<script type="text/javascript">
	messages.setDefaultLanguage('pt-BR');
	messages.addSupportedLanguages('pt-BR');

	$.AppContext.vars.contextPath = "";
	$.AppContext.vars.painel      = "#content-body";
	$.AppContext.vars.dialog      = "#defaultDialog";
	$.AppContext.vars.language    = messages.getLanguage();
	
	$.AppContext.onload(function(){			
		$.AppContext.utils.loadResourceContent($.AppContext.vars.painel, '/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/dashboard')
	});
	
</script>
<%--
<link href="https://fonts.googleapis.com/css?family=Noto+Serif:400,400italic,700|Open+Sans:300,400,600,700" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet" >
<link href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
<link href="/plugins/ediacaran/front/default_template/front/css/bootstrap.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/bootstrapValidator.min.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/jquery.jcarousel.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/style.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/prettify.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/nivo.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/cta.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/price-box.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/box.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/gallery.css" rel="stylesheet" />
<link href="/plugins/ediacaran/front/default_template/front/css/lightgallery.css" rel="stylesheet" />
  
<script src="/plugins/ediacaran/front/default_template/front/js/jquery.min.js"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/jquery.nivo.slider.js"></script>	
<script src="/plugins/ediacaran/front/default_template/front/js/jquery.jcarousel.min.js"></script>	
<script src="/plugins/ediacaran/front/default_template/front/js/bootstrap.js"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/bootstrapValidator/bootstrapValidator.min.js"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/lightgallery.js"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/sidebar.js"></script>	
<script src="/plugins/ediacaran/front/default_template/front/js/prettify.js"></script>	
<script src="/plugins/ediacaran/front/default_template/front/js/messages.js" charset="utf-8"></script>
<script	src="/plugins/ediacaran/front/default_template/front/js/regex-patterns.js" charset="utf-8"></script>
<script	src="/plugins/ediacaran/front/default_template/front/js/application.js" charset="utf-8"></script>
<script	src="/plugins/ediacaran/front/default_template/front/js/application.validator.js" charset="utf-8"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/nivo.js"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/gallery.js"></script>
<script src="/plugins/ediacaran/front/default_template/front/js/carousel.js"></script>
<script	src="/plugins/ediacaran/front/default_template/front/js/application.event.js" charset="utf-8"></script>
<script	src="/plugins/ediacaran/front/default_template/front/js/application.page.js" charset="utf-8"></script>
<script type="text/javascript">
	messages.setDefaultLanguage('pt-BR');
	messages.addSupportedLanguages('pt-BR');

	$.AppContext.vars.contextPath = "";
	$.AppContext.vars.painel      = "#content-body";
	$.AppContext.vars.dialog      = "#defaultDialog";
	$.AppContext.vars.language    = messages.getLanguage();
</script>
--%>