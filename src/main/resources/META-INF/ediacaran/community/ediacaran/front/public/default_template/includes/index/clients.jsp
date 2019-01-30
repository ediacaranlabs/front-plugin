<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <div class="row">
          <div class="span12">
            <h4>${item['title']}</h4>
            <ul id="mycarousel" class="jcarousel-skin-tango recent-jcarousel clients">
              <c:forEach items="${item['itens']}" var="i" varStatus="count">
              <li>
                <a href="${i['link']}">
					<img src="${i['logo']}" class="client-logo" alt="" />
					</a>
              </li>
              </c:forEach>            
            </ul>
          </div>
        </div>
