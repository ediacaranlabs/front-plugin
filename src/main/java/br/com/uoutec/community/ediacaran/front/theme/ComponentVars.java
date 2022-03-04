package br.com.uoutec.community.ediacaran.front.theme;

import java.util.Map;
import java.util.Set;

public interface ComponentVars {

	Map<String, Object> prepareVars(Map<String, PropertyParser> propertyParsers, Set<String> defaultProperties,
			Map<String, PropertyParser> attributeParsers, Set<String> emptyAttributes, Set<String> defaultAttributes);
}
