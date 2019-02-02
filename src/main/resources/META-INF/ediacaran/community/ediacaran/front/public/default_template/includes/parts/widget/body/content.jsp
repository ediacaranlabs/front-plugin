<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${entity}"/>
        <div class="row">
          <div class="span12">
            <h4>${entity['title']}</h4>
            ${entity['content']}
          </div>
        </div>
