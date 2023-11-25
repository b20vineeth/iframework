package com.web.framework.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.Gson;
import com.web.framework.enricher.IEnricher;
import com.web.framework.exception.BussinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.invoker.IInvoker;
import com.web.framework.message.service.KafkaProducer;
import com.web.framework.validator.IValidator;
import com.web.framework.vo.AbstractVo;
import com.web.framework.vo.ErrorVo;
import com.web.framework.vo.EventVo;
import com.web.framework.vo.UserVo;
 
public abstract class Feature<T extends AbstractVo> implements IFeature {

	@SuppressWarnings("rawtypes")
	@Autowired
	Map<String, IValidator> validators;

	@Autowired
	Map<String, IInvoker> iInvoker;

	@Autowired
	Map<String, IEnricher> iEnricher;
	
	 @Autowired
	 KafkaProducer publisher;

	List<ErrorVo> errors;

	protected abstract <R extends Object> R perform(T featureVo) throws BussinessException;

	protected abstract FeatureVo getBussinessConfiguration() throws BussinessException;

	public <R extends Object> R execute(T featureVo) throws BussinessException {
		this.errors=null;
		FeatureVo featureConfig = getBussinessConfiguration();

		updateUserDetails(featureVo);
		preinvoker(featureVo, featureConfig);
		validator(featureVo, featureConfig);
		enricher(featureVo, featureConfig);
		
		if(Objects.isNull(featureConfig.getPostinvoker())) {
		featureConfig.setPostinvoker(new ArrayList<>());
		featureConfig.getPostinvoker().add("framework.removesessioninvoker");
		}
	
		R response = perform(featureVo);
		postinvoker(featureVo, featureConfig);
		 
		try {
			 Class<?> event = Class.forName(this.getClass().getName()); 
			 if(Objects.nonNull(event.getAnnotation(com.web.framework.util.Event.class))) {
				 String eventName= event.getAnnotation(com.web.framework.util.Event.class).name();
				 EventVo eventvo=new EventVo();
				 
				 eventvo.setMsg(new Gson().toJson(featureVo));
				 eventvo.setEventName(eventName);  
				 eventvo.setMsgClass(featureVo.getClassName());
				 publisher.send(new Gson().toJson(eventvo));
				 
			 } 	 
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		}
		
		return response;

	}

	private void enricher(T featureVo, FeatureVo featureConfig) {

		if (Objects.nonNull(featureConfig) && Objects.nonNull(featureConfig.getEnricher())) {

			featureConfig.getEnricher().stream().forEach(enricher -> {
				try {
					iEnricher.get(enricher).execute(featureVo);
				} catch (BussinessException e) {
					handleErrorMessage(e,"enricher");
				}
			});

		}

	}

	
	private void preinvoker(T featureVo, FeatureVo featureConfig) {

		if (Objects.nonNull(featureConfig) && Objects.nonNull(featureConfig.getPreinvoker())) {

			featureConfig.getPreinvoker().stream().forEach(invoker -> {
				try {
					iInvoker.get(invoker).execute(featureVo);
				} catch (BussinessException e) {

					handleErrorMessage(e,"invoker");
				}
			});

		}

	}
	private void postinvoker(T featureVo, FeatureVo featureConfig) {

		if (Objects.nonNull(featureConfig) && Objects.nonNull(featureConfig.getPreinvoker())) {

			featureConfig.getPostinvoker().stream().forEach(invoker -> {
				try {
					iInvoker.get(invoker).execute(featureVo);
				} catch (BussinessException e) {

					handleErrorMessage(e,"invoker");
				}
			});

		}

	}

	private void updateUserDetails(T featureVo) {
		UserVo uservo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (Objects.nonNull(authentication) && !"anonymousUser".equals(authentication.getPrincipal())) {

//			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//			if (Objects.nonNull(userDetails)) {
//				uservo = new UserVo();
//				Long id = userDetails.getId();
//				uservo.setId(id.intValue());
//				uservo.setUsername(userDetails.getUsername());
//				uservo.setEmail(userDetails.getEmail());
//			}
		}
		if (Objects.nonNull(featureVo) && Objects.nonNull(uservo)) {
			featureVo.setUserVo(uservo);
		}

	}

	@SuppressWarnings("unchecked")
	protected void validator(T featureVo, FeatureVo featureConfig) throws BussinessException {

		if (Objects.nonNull(featureConfig) && Objects.nonNull(featureConfig.getValidatorsIds())) {

			featureConfig.getValidatorsIds().stream().forEach(validator -> {
				try {
					validators.get(validator).execute(featureVo);
				} catch (BussinessException e) {

					handleErrorMessage(e,"Validation");
					
				}
			});

			if (Objects.nonNull(this.errors) && !this.errors.isEmpty()) {

				throw new BussinessException(this.errors);
			}

		}

	}

	private void handleErrorMessage(BussinessException e, String type) {
		if (Objects.isNull(this.errors) || !this.errors.isEmpty()) {
			this.errors = new ArrayList<>();
		}
		if(Objects.nonNull(e.getErrors())) {
			this.errors.addAll(e.getErrors());
		}
		else {
			ErrorVo error=new ErrorVo();
			error.setCode(type);
			error.setMessage(e.getMessage());
			this.errors.add(error);
		}
	}

}
