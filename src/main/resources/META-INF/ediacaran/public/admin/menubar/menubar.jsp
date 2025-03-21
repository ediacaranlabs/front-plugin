<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<style>
<!--
.menu-item > .menu-item > div {
    margin-left: 2em;
}

.card-header-temp{
	border-style: dashed;
	border-color: black;
}

-->
</style>

<script type="text/javascript">
<!--

$.AppContext.onload(function(){
	
	pageContext = {};
	
	/* vars */

	pageContext.dragging = false;
	
	pageContext.currentDragging = null;
	
	pageContext.drafts = [];
	
	/* functions */
	
	pageContext.init = function(){
		//var $allMenus = $.AppContext.utils.getById("menus");
		//pageContext.init0($allMenus);
		
		pageContext.updateFieldIndex();
	};
	
	pageContext.init0 = function($obj){
		
		var $o = $obj.getFirstChild()
		
		while($o != null){
			
			if($o.containsClass('menu-item')){
				pageContext.install($o)
			}
			
			pageContext.init0($o);
			
			$o = $o.getNext();
		}
		
	};
	
	pageContext.install = function ($o){
		
		$o.registerEvent("dragstart", function ($event){
			pageContext.dragstart($event);
		});
		
		$o.registerEvent("dragover", function ($event){
			$event.handler.preventDefault();
			pageContext.dragover($event);
		});

		$o.registerEvent("dragleave", function ($event){
			$event.handler.preventDefault();
			pageContext.dragleave($event);
		});
		
		$o.registerEvent("drop", function ($event){
			$event.handler.preventDefault();
			pageContext.drop($event);
		});
		
	};

	pageContext.dragstart = function($event){
		$event.handler.dataTransfer.setData("data", $event.handler.target.id);
		pageContext.dragging = true;
	};
	
	pageContext.dragover = function($event){
		$target = $.AppContext.utils.getById($event.sourceID);
		pageContext.enableDraft($target);
	};

	pageContext.dragleave = function($event){
		$target = $.AppContext.utils.getById($event.sourceID);
		pageContext.disableDraft($target);
	};

	pageContext.drop = function($event){
		var $data   = $event.handler.dataTransfer.getData("data");
		var $obj    = $.AppContext.utils.getById($data);
		var $target = $.AppContext.utils.getByAdvise($event.handler.target);

		pageContext.dragging = false;
		
		$target = pageContext.getRoot($target);
		
		setTimeout(function(){
			pageContext.dropObject($obj, $target);
		}, 100);
		
	};

	pageContext.dropObject = function($obj, $target){
		
		var $x    = $.AppContext.utils.mouse.position.x;
		var $left = $target.getLeft();
		
		if($x - $left > 120){
			$obj.insertAfter($target.getFirstChild());
		}
		else{
			$obj.insertAfter($target);
		}

		pageContext.disableDraftAll($target);
		pageContext.updateFieldIndex();		
	};

	pageContext.updateFieldIndex = function(){
		var $form = $.AppContext.utils.getById("menubarForm");
		$form.updateFieldIndex();
		$form.updateFieldNames();
	};
	
	/* utils  */
	
	pageContext.enableDraft = function ($target){
		
		$target.each(function (e){
			if(e.containsClass('card-header')){
				e.addClass('card-header-temp');
				return false;
			}
		});
		
	};

	pageContext.disableDraft = function ($target){
		
		if($target == null){
			return;
		}
		
		$target.each(function (e){
			if(e.containsClass('card-header')){
				e.removeClass('card-header-temp');
				return false;
			}
		});
		
	};

	pageContext.disableDraftAll = function ($target){
		
		pageContext.disableDraft($target);
		
		var $parent = $target.getParent();
		
		while($parent != null){
			
			if($parent.getProperty('draggable')){
				pageContext.disableDraft($parent);
			}

			$parent = $parent.getParent();
		}
		
	};
	
	pageContext.getRoot = function ($target){

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
	
	
	pageContext.init();
	
});

//-->
</script>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Menu</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Editar">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
				<ec:breadcrumb-path text="Menu" lnk="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ec:box>
	<ec:box-body>
		<ec:form id="menubarForm" extAttrs="formgrouptype=\"index\"" update="result_page_edit_form">
			<input type="hidden" value="${empty metadata? '' : metadata.hashCode()}" name="gid">
			<ed:row>
				<ed:col size="12" id="result_page_edit_form">
				</ed:col>
			</ed:row>
		<ed:row>
			<ed:col size="7" classStyle="form-group has-feedback">
				<ec:textfield name="path" label="Caminho" value="${metadata.path}" readonly="${!empty metadata}">
					<ec:field-validator>
						<ec:field-validator-rule name="notEmpty" message="Please inform the Path!"/>
						<ec:field-validator-rule name="regexp" message="Invalid path!">
							<ec:field-validator-param name="regexp" raw="true">/^(\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*$/</ec:field-validator-param>
						</ec:field-validator-rule>
					</ec:field-validator>
				</ec:textfield>
			</ed:col>
			<ed:col size="5" classStyle="form-group has-feedback">
				<ec:textfield label="Identificação" name="id" value="${menubar.id}" readonly="${!empty metadata}">
					<ec:field-validator>
						<ec:field-validator-rule name="notEmpty" message="Please inform the ID!"/>
						<ec:field-validator-rule name="regexp" message="Invalid ID!">
							<ec:field-validator-param name="regexp" raw="true">/^[a-z0-9][a-z0-9]+([_-][a-z0-9]+)*$/</ec:field-validator-param>
						</ec:field-validator-rule>
					</ec:field-validator>
				</ec:textfield>
			</ed:col>
		</ed:row>
		<ed:row>
			<ed:col size="9" classStyle="form-group has-feedback">
				<ec:textfield label="Nome" name="name" value="${menubar.name}">
					<ec:field-validator>
						<ec:field-validator-rule name="notEmpty" message="Please inform the Name!"/>
						<ec:field-validator-rule name="regexp" message="Invalid name!">
							<ec:field-validator-param name="regexp" raw="true">/^.{5,60}$/</ec:field-validator-param>
						</ec:field-validator-rule>
					</ec:field-validator>
				</ec:textfield>
			</ed:col>
			<ed:col size="3" classStyle="form-group has-feedback">
				<ec:select label="Idioma" name="locale" readonly="${!empty metadata}">
					<ec:option value=""></ec:option>
					<c:forEach items="${locales}" var="loc">
					<ec:option value="${loc.key}" selected="${metadata.locale == loc.key}">${loc.value}</ec:option>
					</c:forEach>
				</ec:select>
			</ed:col>
		</ed:row>
		<ed:row>
			<ed:col size="12" id="menus" classStyle="list-menus">
				<h5>Menus</h5>
				<ec:separator/>
				<c:forEach varStatus="menubarItemStatus" var="menubarItem" items="${menubar.allItens}">
				<c:if test="${menubarItem.persistent}">
					<c:set scope="request" var="param1_" value="${menubarItem}"/>
					<c:set scope="request" var="d_eep" value="0"/>
					<c:import url="/admin/menubar/menu.jsp"/>
				</c:if>
				</c:forEach>
			</ed:col>
		</ed:row>
		<ed:row>
			<ed:col size="12">
				<ec:button actionType="button" label="Novo" align="right" classStyle="last-item">
					<ec:event type="click">
					
						$.AppContext.utils.appendContentByID(
							'${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new-menu',
							'menus'
						);
						
					</ec:event>
				</ec:button>
				<ec:button 
					actionType="submit"
					method="POST"
					action="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/save" 
					label="Salvar" 
					classStyle="last-item"
					align="right"/>
				
			</ed:col>
		</ed:row>
		</ec:form>
	</ec:box-body>
</ec:box>