package io.vilya.boreas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vilya.boreas.exception.JsonStringifyException;

/**
 * @author iamaprin
 * @time 2017年7月29日 上午10:44:23
 */
public abstract class JsonUtils {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	private JsonUtils() {}
	
	public static String stringify(Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new JsonStringifyException(e);
		}
	}
	
}
