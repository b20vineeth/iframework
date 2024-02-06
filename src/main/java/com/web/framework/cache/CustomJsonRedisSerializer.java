package com.web.framework.cache;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.lang.reflect.Field;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJsonRedisSerializer<T> extends GenericJackson2JsonRedisSerializer {

	private final ObjectMapper objectMapper;

    public CustomJsonRedisSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        try { 
        	 LinkedHashMap<String, Object> map =  (LinkedHashMap<String, Object>) objectMapper.readValue(bytes, Object.class);
        	 Object obj=instantiateObject(  map);
        	 return (T) obj;
        } catch (Exception e) {
            throw new SerializationException("Error deserializing JSON bytes", e);
        }
       
    }
    public static Object instantiateObject(Map<String, Object> map) throws Exception {
        Class<?> classz = null;
        Object object = null;

        for (String key : map.keySet()) {
            if (key.equals("@class")) {
                classz = Class.forName(map.get(key).toString());
                object = classz.getDeclaredConstructor().newInstance();
            } else {
                Object value = map.get(key);
                if (value instanceof Map) {
                    // Recursively instantiate nested objects
                    Object nestedObject = instantiateObject((Map<String, Object>) value);
                    // Set the nested object to the corresponding field
                    Field field = classz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(object, nestedObject);
				} else {
					try {
						Field field = classz.getDeclaredField(key);
						field.setAccessible(true);
						Class<?> fieldType = field.getType();

						if (fieldType.isEnum() && Objects.nonNull(value)) {
							// If the field is an enum, set it using Enum.valueOf() method
							Object enumValue = Enum.valueOf((Class<Enum>) fieldType, value.toString());
							field.set(object, enumValue);
						} else {
							// For non-enum fields, set the value directly
							field.set(object, value);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
            }
        }
        return object;
    }
  
}
