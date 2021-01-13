<!-- jQuery -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="/plugins/ediacaran/front/default_template/admin/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/moment/moment.min.js"></script>
<script src="/plugins/ediacaran/front/default_template/admin/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="/plugins/ediacaran/front/default_template/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="/plugins/ediacaran/front/default_template/admin/js/adminlte.js"></script>
<script type="text/javascript">
	
	messages.setDefaultLanguage('pt-BR');
	messages.addSupportedLanguages('pt-BR');
	
	$.AppContext.vars.contextPath = "";
	$.AppContext.vars.painel      = "#content-body";
	$.AppContext.vars.dialog      = "#defaultDialog";
	$.AppContext.vars.language    = messages.getLanguage();
	
	$.AppContext.loadContentOnPanel("/plugins/ediacaran/front${plugins.ediacaran.front.admin_context}/dashboard");
</script>