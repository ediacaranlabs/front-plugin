<%@taglib uri="http://java.sun.com/jsp/jstl/core"                   prefix="c"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/components" prefix="ec"%>
<%@taglib uri="https://www.uoutec.com.br/ediacaran/tags/designer"   prefix="ed"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"                    prefix="fmt"%> 
<ec:setTemplatePackage name="admin"/>

<style>
<!--
/*.menu-item div:not(:first-of-type, .card) {*/
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
	
	localContext = {};
	
	/* vars */

	localContext.dragging = false;
	
	localContext.currentDragging = null;
	
	localContext.drafts = [];
	
	/* functions */
	
	localContext.init = function(){
		//var $allMenus = $.AppContext.utils.getById("menus");
		//localContext.init0($allMenus);
		
		localContext.updateFieldIndex();
	};
	
	localContext.init0 = function($obj){
		
		var $o = $obj.getFirstChild()
		
		while($o != null){
			
			if($o.containsClass('menu-item')){
				localContext.install($o)
			}
			
			localContext.init0($o);
			
			$o = $o.getNext();
		}
		
	};
	
	localContext.install = function ($o){
		
		$o.registerEvent("dragstart", function ($event){
			localContext.dragstart($event);
		});
		
		$o.registerEvent("dragover", function ($event){
			$event.handler.preventDefault();
			localContext.dragover($event);
		});

		$o.registerEvent("dragleave", function ($event){
			$event.handler.preventDefault();
			localContext.dragleave($event);
		});
		
		$o.registerEvent("drop", function ($event){
			$event.handler.preventDefault();
			localContext.drop($event);
		});
		
	};

	localContext.dragstart = function($event){
		$event.handler.dataTransfer.setData("data", $event.handler.target.id);
		localContext.dragging = true;
	};
	
	localContext.dragover = function($event){
		$target = $.AppContext.utils.getById($event.sourceID);
		localContext.enableDraft($target);
	};

	localContext.dragleave = function($event){
		$target = $.AppContext.utils.getById($event.sourceID);
		localContext.disableDraft($target);
	};

	localContext.drop = function($event){
		var $data   = $event.handler.dataTransfer.getData("data");
		var $obj    = $.AppContext.utils.getById($data);
		var $target = $.AppContext.utils.getByAdvise($event.handler.target);

		localContext.dragging = false;
		
		$target = localContext.getRoot($target);
		
		setTimeout(function(){
			localContext.dropObject($obj, $target);
		}, 100);
		
	};

	localContext.dropObject = function($obj, $target){
		
		var $x    = $.AppContext.utils.mouse.position.x;
		var $left = $target.getLeft();
		
		if($x - $left > 120){
			$obj.insertAfter($target.getFirstChild());
		}
		else{
			$obj.insertAfter($target);
		}

		localContext.disableDraftAll($target);
		localContext.updateFieldIndex();		
	};

	localContext.updateFieldIndex = function(){
		var $form = $.AppContext.utils.getById("menubarForm");
		$form.updateFieldIndex();
		$form.updateFieldNames();
	};
	
	/* utils  */
	
	localContext.enableDraft = function ($target){
		
		$target.each(function (e){
			if(e.containsClass('card-header')){
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
			if(e.containsClass('card-header')){
				e.removeClass('card-header-temp');
				return false;
			}
		});
		
	};

	localContext.disableDraftAll = function ($target){
		
		localContext.disableDraft($target);
		
		var $parent = $target.getParent();
		
		while($parent != null){
			
			if($parent.getProperty('draggable')){
				localContext.disableDraft($parent);
			}

			$parent = $parent.getParent();
		}
		
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
	
	
	localContext.init();
	
});

//-->
</script>

<section class="inner-headline">
	<ed:row>
		<ed:col size="4">
			<div class="inner-heading">
				<h2>Editar Menubar</h2>
			</div>
		</ed:col>
		<ed:col size="8">
			<ec:breadcrumb title="Editar">
				<ec:breadcrumb-path icon="home" text="" lnk="#" />
				<ec:breadcrumb-path text="menubar" lnk="#!${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar" />
			</ec:breadcrumb>
		</ed:col>
	</ed:row>
</section>

<ec:box>
	<ec:box-body>
		<ec:form id="menubarForm" extAttrs="formgrouptype=\"index\"" update="result_page_edit_form">	
		<c:if test="${!empty metadata}">
			<input type="hidden" value="${metadata.hashCode()}" name="gid">
			<input type="hidden" value="${metadata.path}" name="path">
			<input type="hidden" value="${metadata.id}" name="id">
			<input type="hidden" value="${metadata.locale}" name="locale">
		</c:if>
			<ed:row>
				<ed:col size="12" id="result_page_edit_form">
				</ed:col>
			</ed:row>
		<ed:row>
			<ed:col size="7" classStyle="form-group has-feedback">
				<ec:textfield name="path" label="Path" value="${metadata.path}" enabled="${empty metadata}">
					<ec:field-validator>
						<ec:field-validator-rule name="notEmpty" message="Please inform the Path!"/>
						<ec:field-validator-rule name="regexp" message="Invalid path!">
							<ec:field-validator-param name="regexp" raw="true">/^(\/[a-z][a-z0-9]+(_[a-z0-9]+)*)*$/</ec:field-validator-param>
						</ec:field-validator-rule>
					</ec:field-validator>
				</ec:textfield>
			</ed:col>
			<ed:col size="5" classStyle="form-group has-feedback">
				<ec:textfield label="ID" name="id" value="${menubar.id}" enabled="${empty metadata}">
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
				<ec:textfield label="Name" name="name" value="${menubar.name}">
					<%--
					<ec:field-validator>
						<ec:field-validator-rule name="notEmpty" message="Please inform the Name!"/>
					</ec:field-validator>
					--%>
				</ec:textfield>
			</ed:col>
			<ed:col size="3" classStyle="form-group has-feedback">
				<ec:select label="Language" name="locale" enabled="${empty metadata}">
					<ec:option value=""></ec:option>
					<c:forEach items="${locales}" var="loc">
					<ec:option value="${loc.key}" selected="${metadata.locale == loc.key}">${loc.value}</ec:option>
					</c:forEach>
				</ec:select>
			</ed:col>
		</ed:row>
		<ed:row>
			<ed:col size="12" id="menus" classStyle="list-menus">
				<c:forEach varStatus="menubarItemStatus" var="menubarItem" items="${menubar.itens}">
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
				<ec:button actionType="button" label="Add Menu" align="right" classStyle="last-item">
					<ec:event type="click">
					
						$.AppContext.utils.appendContentByID(
							'${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new',
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
<%--		
	<ed:row>
		<ed:col size="12">
			<ec:button label="Add Menu" align="right" classStyle="last-item">
				<ec:event type="click">
				
					$.AppContext.utils.appendContentByID(
						'${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/new',
						'list_menus'
					);
					
				</ec:event>
			</ec:button>
			<ec:button align="right" label="Delete" classStyle="last-item">
				<ec:event type="click">
				</ec:event>
			</ec:button>
			<span></span>
			<ec:button label="Save" align="right" classStyle="last-item">
				<ec:event type="click">
				</ec:event>
			</ec:button>
		</ed:col>
	</ed:row>
 --%>
	</ec:box-body>
</ec:box>

<%--	
	<ed:row>
		<ed:col size="12" classStyle="form-group has-feedback">
			<ec:textfield name="resource">
				<ec:autocomplete resource="${plugins.ediacaran.front.web_path}${plugins.ediacaran.front.admin_context}/menubar/search-resource" />
			</ec:textfield>
		</ed:col>
	</ed:row>
 --%>