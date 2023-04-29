package br.com.uoutec.community.ediacaran.front.pub;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.brandao.brutos.annotation.AcceptRequestType;
import org.brandao.brutos.annotation.Action;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.ResponseType;
import org.brandao.brutos.annotation.web.MediaTypes;
import org.brandao.brutos.annotation.web.RequestMethod;

@Singleton
@Controller(value="/autocomplete")
@AcceptRequestType(MediaTypes.APPLICATION_JSON)
@ResponseType(MediaTypes.APPLICATION_JSON)
public class AutocompletePubResource {

	private List<String> list;
	
	public AutocompletePubResource() {
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
	
	@Action(value="/search")
	@RequestMethod("POST")
	public synchronized Serializable loadData(String value){
		return (Serializable)list.stream()
			.filter(e->value != null && e.toLowerCase().contains(value.toLowerCase()))
			.limit(10)
			.collect(Collectors.toList());
	}

}
