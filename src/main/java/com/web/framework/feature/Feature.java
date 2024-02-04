package com.web.framework.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.web.framework.enricher.IEnricher;
import com.web.framework.exception.BusinessException;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.invoker.IInvoker;
import com.web.framework.message.service.KafkaProducer;
import com.web.framework.model.EErrorType;
import com.web.framework.util.ICommonUtl;
import com.web.framework.validator.IValidator;
import com.web.framework.vo.AbstractVo;
import com.web.framework.vo.ErrorVo;
import com.web.framework.vo.EventVo;
import com.web.framework.vo.PrivilegeFilterVo;
import com.web.framework.vo.UserVo;

import jakarta.servlet.http.HttpSession;
 
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
	 
	 @Autowired
	 ICommonUtl utl;

	List<ErrorVo> errors;
	 
	
	@Autowired
	private HttpSession httpSession;

	protected abstract <R extends Object> R perform(T featureVo) throws BusinessException;

	protected abstract FeatureVo getBussinessConfiguration() throws BusinessException;

	public <R extends Object> R execute(T featureVo) throws BusinessException {
		this.errors=null;
		FeatureVo featureConfig = getBussinessConfiguration();
		if(Objects.nonNull(featureConfig.getFeatureName())){
			
			try {
				 Class<?> component = Class.forName(this.getClass().getName()); 
				 if(Objects.nonNull(component.getAnnotation(Component.class))) {
					 String componentName= component.getAnnotation(Component.class).value();
					 PrivilegeFilterVo filterVo=new PrivilegeFilterVo();
					 filterVo.setPrivilegeCode(componentName);
//					 PrivilegeVo privilegeVo= privilegeComponent.fetchPrivilege(filterVo);
//					 httpSession.setAttribute(PrivilegeVo.PRIVILEGE_DETAILS, privilegeVo.getPrivilegeCodes());
//					 
				 } 	 
			} catch (ClassNotFoundException e) {
				 
				e.printStackTrace();
			}
			
		}

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
				} catch (BusinessException e) {
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
				} catch (BusinessException e) {

					handleErrorMessage(e,"invoker");
				}
			});

		}

	}
	private void postinvoker(T featureVo, FeatureVo featureConfig) {

		if (Objects.nonNull(featureConfig) && Objects.nonNull(featureConfig.getPostinvoker())) {

			featureConfig.getPostinvoker().stream().forEach(invoker -> {
				try {
					iInvoker.get(invoker).execute(featureVo);
				} catch (BusinessException e) {

					handleErrorMessage(e,"invoker");
				}
			});

		}

	}

	private void updateUserDetails(T featureVo) {
		UserVo uservo = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (Objects.nonNull(authentication) && !"anonymousUser".equals(authentication.getPrincipal())) {

			UserVo userDetails = (UserVo) authentication.getPrincipal();
			if (Objects.nonNull(userDetails)) {
				uservo = new UserVo();
				Integer id = userDetails.getId();
				uservo.setId(id.intValue());
				uservo.setUname(userDetails.getUname());
				uservo.setEmail(userDetails.getEmail());
			}
		}
		if (Objects.nonNull(featureVo) && Objects.nonNull(uservo)) {
			featureVo.setUserVo(uservo);
		}

	}

	@SuppressWarnings("unchecked")
	protected void validator(T featureVo, FeatureVo featureConfig) throws BusinessException {

		if (Objects.nonNull(featureConfig) && Objects.nonNull(featureConfig.getValidatorsIds())) {

			featureConfig.getValidatorsIds().stream().forEach(validator -> {
				try {
					validators.get(validator).execute(featureVo);
				} catch (BusinessException e) {

					handleErrorMessage(e,"Validation");
					
				}
			});

			if (Objects.nonNull(this.errors) && !this.errors.isEmpty()) {

				throw new BusinessException(this.errors);
			}

		}

	}

	private void handleErrorMessage(BusinessException e, String type) {
		if (Objects.isNull(this.errors) || this.errors.isEmpty()) {
			this.errors = new ArrayList<>();
		}
		if(Objects.nonNull(e.getErrors())) {
			this.errors.addAll(e.getErrors());
		}
		else {
			ErrorVo error=utl.generateErrorVo(e.getMessage(), EErrorType.E);
			this.errors.add(error);
		}
	}

}
