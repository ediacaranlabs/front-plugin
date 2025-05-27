package br.com.uoutec.community.ediacaran.front.page;

import java.util.Locale;
import java.util.Objects;

public class PageId {

	private final String path;
	
	private final String id;
	
	private final Locale locale;

	public PageId(String path, String id, Locale locale) {
		this.path = path;
		this.id = id;
		this.locale = locale;
	}

	public String getPath() {
		return path;
	}

	public String getId() {
		return id;
	}

	public Locale getLocale() {
		return locale;
	}

	public int toInt() {
		return this.hashCode();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, locale, path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageId other = (PageId) obj;
		return Objects.equals(id, other.id) && Objects.equals(locale, other.locale) && Objects.equals(path, other.path);
	}
	
}
