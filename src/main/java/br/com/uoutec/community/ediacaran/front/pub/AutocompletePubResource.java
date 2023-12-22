package br.com.uoutec.community.ediacaran.front.pub;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.brandao.brutos.annotation.AcceptRequestType;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.web.MediaTypes;
import org.brandao.brutos.annotation.web.RequestMethod;

import br.com.uoutec.community.ediacaran.front.theme.TemplateVarParser;
import br.com.uoutec.community.ediacaran.front.theme.Theme;
import br.com.uoutec.community.ediacaran.front.theme.ThemeRegistry;
import br.com.uoutec.ediacaran.core.plugins.EntityContextPlugin;

@Singleton
@Controller(value="/autocomplete")
@AcceptRequestType(MediaTypes.APPLICATION_JSON)
@ResponseType(MediaTypes.APPLICATION_JSON)
public class AutocompletePubResource {

	private List<String> list;
	
	private List<String> icons;
	
	public AutocompletePubResource() {
		this.icons = Arrays.asList(
				"500px",
				"amazon",
				"balance-scale",
				"battery-0",
				"battery-1",
				"battery-2",
				"battery-3",
				"battery-4",
				"battery-empty",
				"battery-full",
				"battery-half",
				"battery-quarter",
				"battery-three-quarters",
				"black-tie",
				"calendar-check-o",
				"calendar-minus-o",
				"calendar-plus-o",
				"calendar-times-o",
				"cc-diners-club",
				"cc-jcb",
				"chrome",
				"clone",
				"commenting",
				"commenting-o",
				"contao",
				"creative-commons",
				"expeditedssl",
				"firefox",
				"fonticons",
				"genderless",
				"get-pocket",
				"gg",
				"gg-circle",
				"hand-grab-o",
				"hand-lizard-o",
				"hand-paper-o",
				"hand-peace-o",
				"hand-pointer-o",
				"hand-rock-o",
				"hand-scissors-o",
				"hand-spock-o",
				"hand-stop-o",
				"hourglass",
				"hourglass-1",
				"hourglass-2",
				"hourglass-3",
				"hourglass-end",
				"hourglass-half",
				"hourglass-o",
				"hourglass-start",
				"houzz",
				"i-cursor",
				"industry",
				"internet-explorer",
				"map",
				"map-o",
				"map-pin",
				"map-signs",
				"mouse-pointer",
				"object-group",
				"object-ungroup",
				"odnoklassniki",
				"odnoklas"				
		);

		 
		this.list = Arrays.asList(
					"United States", 
					"Canada", 
					"Afghanistan", 
					"Albania", 
					"Algeria", 
					"American Samoa", 
					"Andorra",
					"Czech Republic", 
					"Denmark", 
					"Djibouti", 
					"Dominica", 
					"Dominican Republic", 
					"East Timor", 
					"Ecudaor", 
					"Egypt",
					"South Sudan", 
					"Spain", 
					"Sri Lanka", 
					"St. Helena", 
					"St. Pierre and Miquelon", 
					"Sudan", 
					"Suriname", 
					"Svalbarn and Jan Mayen Islands", 
					"Swaziland", 
					"Sweden", 
					"Switzerland", 
					"Syrian Arab Republic", 
					"Taiwan", 
					"Tajikistan", 
					"Tanzania, United Republic of", 
					"Thailand", 
					"Togo", 
					"Tokelau", 
					"Tonga", 
					"Trinidad and Tobago", 
					"Ukraine", 
					"United Arab Emirates", 
					"Vanuatu"
				);
	}

	@SuppressWarnings("serial")
	@Action(value="/search-icons")
	@RequestMethod("POST")
	public synchronized Serializable searchIcons(@Basic(bean="value") String value){
		
		ThemeRegistry tr = EntityContextPlugin.getEntity(ThemeRegistry.class);
		Theme theme = tr.getCurrentTheme();
		
		return (Serializable)icons.stream()
			.filter(e->value != null && e.toLowerCase().contains(value.toLowerCase()))
			.map(e-> new HashMap<String,String>(){{
				put("label", " <h1>" + getIcon(e, theme) + " " + e + "</h1>");
				put("value", e);
			}})
			.limit(100)
			.collect(Collectors.toList());
	}
	
	private String getIcon(String name, Theme theme) {
		try {
			return new TemplateVarParser("/components/icon", theme).put("icon", name).put("size", 2).parse();
		}
		catch (IOException e) {
			return "error";
		}
	}
	
	@SuppressWarnings("serial")
	@Action(value="/search")
	@RequestMethod("POST")
	public synchronized Serializable loadData(@Basic(bean="value") String value){
		return (Serializable)list.stream()
			.filter(e->value != null && e.toLowerCase().contains(value.toLowerCase()))
			.map(e-> new HashMap<String,String>(){{
				put("label", e);
				put("value", e);
			}})
			.limit(10)
			.collect(Collectors.toList());
	}

}
