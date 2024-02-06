package com.web.framework.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.model.FeatureModel;
import com.web.framework.model.UserModel;

@Mapper(componentModel = "spring")
public abstract class ComponentMapper {
 
    @Mapping(target = "authorities", expression = "java(mapAuthorities(jsonObject))")
	@Mapping(target = "id", expression = "java(jsonObject.get(\"id\").getAsString())")
    @Mapping(target = "username", expression = "java(jsonObject.get(\"username\").getAsString())")
    @Mapping(target = "status", expression = "java(jsonObject.get(\"status\").getAsString())")
    @Mapping(target = "email", expression = "java(jsonObject.get(\"email\").getAsString())")
    public abstract UserModel toUserModel(JsonObject jsonObject);

   
    // Custom mapping for 'authorities'
    protected Collection<? extends GrantedAuthority> mapAuthorities(JsonObject jsonObject) {
        if (jsonObject.has("role")) {
        	String role=jsonObject.get("role").getAsString();
        	 List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        	 grantedAuthorities.add(new SimpleGrantedAuthority(role));
              return grantedAuthorities;
        }
        return null; // or throw an exception if authorities are mandatory
    }
    
    
    @Mapping(target = "enricher", expression = "java(convertToArray(jsonObject.get(\"enricher\")))")
    @Mapping(target = "enrichers", expression = "java(convertToArray(jsonObject.get(\"enrichers\")))")
    @Mapping(target = "postinvoker", expression = "java(convertToArray(jsonObject.get(\"postinvoker\")))")
    @Mapping(target = "preinvoker", expression = "java(convertToArray(jsonObject.get(\"preinvoker\")))")
    @Mapping(target = "validatorsIds", expression = "java(convertToArray(jsonObject.get(\"validatorsIds\")))")
    public abstract FeatureVo toFeatureModel(JsonObject jsonObject);
    
    
	public List<String> convertToArray(JsonElement jsonElement) {
		List<String> datas = null;
		if (Objects.nonNull(jsonElement)) {
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			datas = new ArrayList<>();
			for (JsonElement element : jsonArray) {
				datas.add(element.getAsString());
			}
		}
		return datas;
	}
}
 
