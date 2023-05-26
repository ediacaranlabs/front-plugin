<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<c:set var="obj" value="${param1_}"/>
<c:set scope="request" var="countMenuID" value="${empty countMenuID? 1 : countMenuID + 1}"/>
<c:set var="menuID" value="${countMenuID}"/>

<div id="MenuID_${menuID}" menuid="${obj.id}" draggable="true" class="menu-item">
<ec:accordion>
	<ec:accordion-item id="menu_item_${menuID}" title="${obj.name}">
	<ed:row>
		<ed:col size="3" classStyle="form-group has-feedback">
			<input type="hidden" value="${obj.id}" name="id">
			<ec:textfield label="Icon" value="${obj.icon}"/>
		</ed:col>
		<ed:col size="9" classStyle="form-group has-feedback">
			<ec:textfield label="Nome" name="name" value="${obj.name}">
				<ec:event type="keyup">

					var $accordionItem = $.AppContext.utils.getById("menu_item_${menuID}");
					
					$accordionItem.setValue('title', $event.source.getProperty('value'));
					
				</ec:event>
			</ec:textfield>
		</ed:col>
	</ed:row>
	<ed:row>
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:textfield label="Resource" name="rawResource" value="${obj.rawResource}"/>
		</ed:col>
	</ed:row>
	</ec:accordion-item>
</ec:accordion>
<c:forEach var="item" items="${obj.itens}">
	<c:set scope="request" var="param1_" value="${item}"/>
	<c:set scope="request" var="d_eep" value="${empty d_eep? 1 : d_eep + 1}"/>
	<c:if test="${d_eep < 3}">
		<c:import url="/admin/menubar/menu.jsp"/>
	</c:if>
	<c:set scope="request" var="d_eep" value="${d_eep - 1}"/>
</c:forEach>
</div>
<script type="text/javascript">
<!--

$.AppContext.onload(function(){

	var localContext = {};
	
	localContext.dragging = false;
	localContext.currentDragging = null;
	localContext.drafts = [];
	
	localContext.enableDraft = function ($target){
		
		$target.each(function (e){
			if(e.containsClass('card')){
				e.addClass('card-header-temp');
				return false;
			}
		});
		
	};

	localContext.disableDraft = function ($target){
		
		if($target == null){
			return;
		}
		
		$target.each(function (e){
			if(e.containsClass('card')){
				e.removeClass('card-header-temp');
				return false;
			}
		});
		
	};
	
	localContext.getRoot = function ($target){

		if($target.getProperty('draggable')){
			return $target;
		}
		
		var i = 0;

		while($target !== 'undefined' && i < 1000 ){
			
			if($target.getProperty('draggable')){
				return $target;
			}
			
			$target = $target.getParent();
			i = i + 1;
		}
		
		return null;
	};
	
	var $accordionItem = $.AppContext.utils.getById("MenuID_${menuID}");

	$accordionItem.registerEvent("dragstart", function ($event){
		$event.handler.dataTransfer.setData("data", $event.handler.target.id);
		localContext.dragging = true;
	});
	
	$accordionItem.registerEvent("dragover", function ($event){
		$event.handler.preventDefault();


		$target = $.AppContext.utils.getById($event.sourceID);
		localContext.enableDraft($target);

		
	});

	$accordionItem.registerEvent("dragleave", function ($event){
		$event.handler.preventDefault();

		$target = $.AppContext.utils.getById($event.sourceID);
		localContext.disableDraft($target);

	});
	
	$accordionItem.registerEvent("drop", function ($event){
		$event.handler.preventDefault();
		
		var $data   = $event.handler.dataTransfer.getData("data");
		var $obj    = $.AppContext.utils.getById($data);
		var $target = $.AppContext.utils.getByAdvise($event.handler.target);

		$target = localContext.getRoot($target);
		localContext.disableDraft($target);
		
		setTimeout(function(){
			
			$x = $.AppContext.utils.mouse.position.x;
			$left = $target.getLeft();
			
			if($x - $left > 120){
				$obj.insertAfter($target.getFirstChild());
			}
			else{
				$obj.insertAfter($target);
			}

			var $parent = $target.getParent();
			
			while($parent != null){
				
				if($parent.getProperty('draggable')){
					localContext.disableDraft($parent);
				}

				$parent = $parent.getParent();
			}
			
		}, 100);
		
		localContext.dragging = false;
		
	});

	
});
//-->
</script>

<%--	
	<ed:row>
		<ed:col size="9">
		</ed:col>
		<ed:col size="3">
			<ec:button align="right" label="Delete" classStyle="last-item">
				<ec:event type="click">
				
					var $obj = $.AppContext.utils.getById("MenuID_${menuID}");
					
					$obj.setProperty("removed", true);
					$obj.setVisible(false);
					
				</ec:event>
			</ec:button>
			<span></span>
			<ec:button label="Add Menu" align="right">
				<ec:event type="click">
				
					$.AppContext.utils.appendContentByID(
						'${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new',
						'list_menus_${menuID}'
					);
					
				</ec:event>
			</ec:button>
		</ed:col>
	</ed:row>
 --%>	
	