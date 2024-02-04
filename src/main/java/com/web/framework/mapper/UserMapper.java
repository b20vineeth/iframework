package com.web.framework.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.web.framework.model.UserModel;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
 
    @Mapping(target = "authorities", expression = "java(mapAuthorities(jsonObject))")
	@Mapping(target = "id", expression = "java(jsonObject.get(\"id\").getAsString())")
    @Mapping(target = "username", expression = "java(jsonObject.get(\"username\").getAsString())")
    @Mapping(target = "status", expression = "java(jsonObject.get(\"status\").getAsString())")
    @Mapping(target = "email", expression = "java(jsonObject.get(\"email\").getAsString())")
    public abstract UserModel toUserModel(JsonObject jsonObject);

    // Custom method to convert JsonArray to Collection<GrantedAuthority>
    protected Collection<? extends GrantedAuthority> mapAuthorities(JsonArray authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (JsonElement element : authorities) {
            if (element.isJsonObject()) {
                JsonObject authorityObject = element.getAsJsonObject();
                if (authorityObject.has("authority")) {
                    String authority = authorityObject.get("authority").getAsString();
                    grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                }
            }
        }
        return grantedAuthorities;
    }

    // Custom mapping for 'authorities'
    protected Collection<? extends GrantedAuthority> mapAuthorities(JsonObject jsonObject) {
        if (jsonObject.has("authorities")) {
            JsonArray authoritiesArray = jsonObject.getAsJsonArray("authorities");
            return mapAuthorities(authoritiesArray);
        }
        return null; // or throw an exception if authorities are mandatory
    }
}
 
