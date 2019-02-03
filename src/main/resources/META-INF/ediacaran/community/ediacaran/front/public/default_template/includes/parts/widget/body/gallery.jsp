<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="entity" value="${requestScope.entity}"/>
        <div class="row">
          <div class="span12">
            <h4 class="heading">${entity['title']}</h4>
            <div class="row">
              <section id="projects">
                <ul id="thumbs" class="portfolio">
                  <!-- Item Project and Filter Name -->
                  <c:forEach items="${entity['itens']}" var="i" varStatus="count">
                  <li class="item-thumbs span3 design" data-id="id-${count.index}" data-type="web">
                    <!-- Fancybox - Gallery Enabled - Title - Full Image -->
                    <a class="hover-wrap fancybox" data-fancybox-group="${i['group']}" title="${i['title']}" href="${i['image']}">
						<span class="overlay-img"></span>
						<span class="overlay-img-thumb font-icon-plus"></span>
						</a>
                    <!-- Thumb Image and Description -->
                    <img src="${i['icon']}" alt="${i['content']}">
                  </li>
                  </c:forEach>
                  <!-- End Item Project -->
                </ul>
              </section>
            </div>
          </div>
        </div>
