package br.com.uoutec.community.ediacaran.front.page.pub;

import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.MappingTypes;
import org.brandao.brutos.annotation.ScopeType;

import br.com.uoutec.community.ediacaran.front.components.Image;
import br.com.uoutec.community.ediacaran.front.page.BreadcrumbPath;
import br.com.uoutec.community.ediacaran.front.page.EditPage;
import br.com.uoutec.community.ediacaran.front.page.Page;
import br.com.uoutec.community.ediacaran.front.page.PageFileManagerHandler;
import br.com.uoutec.i18n.ValidationException;
import br.com.uoutec.pub.entity.AbstractPubEntity;

public class PagePubEntity 
	extends AbstractPubEntity<Page>{

	private static final long serialVersionUID = -796010157890313726L;

	private Long gid;
	
	@NotNull
	@Size(max = 600)
	@Pattern(regexp="(\\/|" + PageFileManagerHandler.PATH_FORMAT + ")")
	private String path;
	
	@Size(max = 255)
	@Pattern(regexp=PageFileManagerHandler.ID_FORMAT)
	private String id;
	
	@Pattern(regexp=PageFileManagerHandler.LOCALE_FORMAT)
	private String locale;
	
	@NotNull
	@Size(max = 255)
	private String title;
	
	@Size(max = 2097152)
	private String content;
	
	private Map<String, String> header;
	
	private List<BreadcrumbPath> breadcrumb;
	
	private Image thumbnail;
	
	@Size(max = 255)
	private String titleThumbnail;

	@Size(max = 600)
	@Pattern(regexp="^([^\"\',]+)(,([^\"\',]+))*$")
	private String keywords;
	
	@Size(max = 255)
	private String shortDescription;
	
	@NotNull
	@Size(max = 120)
	private String template;

	@Basic(scope=ScopeType.IOC, mappingType=MappingTypes.VALUE)
	public EditPage editPage;
 
	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public String getPath() {
		return path;
	}

	public EditPage getEditPage() {
		return editPage;
	}

	public void setEditPage(EditPage editPage) {
		this.editPage = editPage;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public List<BreadcrumbPath> getBreadcrumb() {
		return breadcrumb;
	}

	public void setBreadcrumb(List<BreadcrumbPath> breadcrumb) {
		this.breadcrumb = breadcrumb;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitleThumbnail() {
		return titleThumbnail;
	}

	public void setTitleThumbnail(String titleThumbnail) {
		this.titleThumbnail = titleThumbnail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	protected boolean isEqualId(Page instance) throws Throwable {
		return false;
	}

	@Override
	protected boolean hasId(Page instance) throws Throwable {
		return gid != null;
	}

	@Override
	protected Page reloadEntity() throws Throwable {
		return editPage.getPageById(path, id, locale);
	}

	@Override
	protected void throwReloadEntityFail() throws Throwable {
		throw new ValidationException("page not found!");
	}

	@Override
	protected Page createNewInstance() throws Throwable {
		return new Page();
	}

	@Override
	protected void copyTo(Page o, boolean reload, boolean override, boolean validate) throws Throwable {
		
		o.setBreadcrumb(breadcrumb);
		o.setHeader(header);
		o.setKeywords(keywords);
		o.setThumbnailDescription(shortDescription);
		o.setThumbnailTitle(titleThumbnail);
		o.setThumbnail(thumbnail == null? null : thumbnail.save(560, 292));
		o.setTemplate(template);
		o.setTitle(title);
		o.setContent(content == null? null : new CharArrayReader(content.toCharArray()));
		
	}

	protected void validate(Class<?> ... groups) throws ValidationException{
		super.validate(groups);
		
		if(gid != null) {
			Map<String,Object> md = new HashMap<String,Object>();
			md.put("path", path);
			md.put("id", id);
			md.put("locale", locale);

			if(gid != md.hashCode()) {
				throw new ValidationException("invalid gid");
			}
			
		}
		if(!editPage.isValidTemplate(template)) {
			throw new ValidationException("invalid template");
		}
		
	}
	
}
