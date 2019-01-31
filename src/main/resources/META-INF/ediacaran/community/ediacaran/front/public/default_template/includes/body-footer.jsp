<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <div class="container">
        <div class="row">
	    <c:forEach items="${vars['footer']['itens']}" var="item" varStatus="_count">
          <div class="span3">
            <div class="widget">
    		<c:set var="item" value="${item}" scope="request"/>
			<jsp:include page="/plugins/community/ediacaran/front/${plugins.front.template}/includes/index/widget/footer/${item['template']}.jsp"/>
            </div>
          </div>
	    </c:forEach>
        </div>
      </div>
      
      <div id="sub-footer">
        <div class="container">
          <div class="row">
            <div class="span6">
              <div class="copyright">
                <p>
                  <span>${vars['copyright']}</span>
                </p>
                <div class="credits">
                  <!--
                    All the links in the footer should remain intact.
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Flattern
                  -->
                  Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                </div>
              </div>
            </div>
            <div class="span6">
              <ul class="social-network">
			    <c:forEach items="${vars['socialNetwork']}" var="item">
			    <li><a href="${item['link']}" data-placement="bottom" title="${item['title']}"><i class="${item['icon']} icon-square"></i></a></li>
              	</c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>