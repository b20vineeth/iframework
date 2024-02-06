package com.web.util.bev.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.web.framework.exception.BusinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.httpcontroller.HttpActionMethod;
import com.web.framework.httpcontroller.IHttpPost;
import com.web.framework.mapper.ComponentMapper;
import com.web.framework.model.FeatureModel;
import com.web.framework.model.HttpRequestModel;
import com.web.framework.vo.FeatureFilterVo;

@Component
public class BevComponent implements IBevComponent {

	@Autowired
	IHttpPost httpPost;
	@Autowired
	ComponentMapper mapper;
	
	
	@Override
	public FeatureVo fetchBevRules(FeatureFilterVo featureFilterVo) throws BusinessException {
		FeatureModel model=new FeatureModel();
		HttpRequestModel requestmodel=new HttpRequestModel();
		model.setFeatureName(featureFilterVo.getFeatureName());
		requestmodel.setData(model);
		requestmodel.setRequestType(HttpMethod.POST);
		requestmodel.setService(HttpActionMethod.ADMIN_FETCHBEV);
		JsonObject details =httpPost.getDetails(requestmodel);
		return mapper.toFeatureModel(details.getAsJsonObject("data"));
		 
	}

}
