<%@taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
<div class="box aligncenter">
  <div class="aligncenter icon">
    <i class="${entity['icon']} icon-circled icon-64 active"></i>
  </div>
  <div class="text">
    <h6>${entity['title']}</h6>
    <p>
      ${entity['content']}
    </p>
    <a href="${entity['link']}">Learn more</a>
  </div>
</div>
