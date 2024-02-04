package com.web.framework.httpcontroller;

import com.google.gson.JsonObject;
import com.web.framework.model.HttpRequestModel;

public interface IHttpPost {

	JsonObject getDetails(HttpRequestModel model);

	 
}
