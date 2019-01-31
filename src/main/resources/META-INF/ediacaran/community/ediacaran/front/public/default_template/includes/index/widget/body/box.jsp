<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <div class="row">
          <div class="span12">
            <div class="row">
            
            <c:forEach items="${item['itens']}" var="box" varStatus="count">
              <div class="span3">
                <div class="box aligncenter">
                  <div class="aligncenter icon">
                    <i class="${box['icon']} icon-circled icon-64 active"></i>
                  </div>
                  <div class="text">
                    <h6>${box['title']}</h6>
                    <p>
                      ${box['content']}
                    </p>
                    <a href="${box['link']}">Learn more</a>
                  </div>
                </div>
              </div>
            </c:forEach>
            
            </div>
          </div>
        </div>
