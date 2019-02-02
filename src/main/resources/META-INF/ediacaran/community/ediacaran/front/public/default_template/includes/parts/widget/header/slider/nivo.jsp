<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${entity}"/>
    <section id="featured">
      <!-- start slider -->
      <!-- Slider -->
      <div id="nivo-slider">
        <div class="nivo-slider">
       	<c:forEach items="${entity['itens']}" var="slide" varStatus="count">
          <img src="${slide['image']}" alt="${slide['alt']}" title="#caption-${count.index}" />
       	</c:forEach>
        </div>
        <div class="container">
          <div class="row">
            <div class="span12">
	       	<c:forEach items="${entity['itens']}" var="i" varStatus="count">
              <div class="nivo-caption" id="caption-${count.index}">
                <div>
                  <h2><strong>${i['title']}</strong></h2>
                  <p>
                    ${i['content']}
                  </p>
                  <a href="${i['link']}" class="btn btn-theme">Read more</a>
                </div>
              </div>
	       	</c:forEach>
            </div>
          </div>
        </div>
      </div>
      <!-- end slider -->
    </section>