package com.web.util.bev.feature.persistor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.web.framework.exception.BusinessException;
import com.web.framework.feature.persistor.Persistor;
import com.web.framework.feature.vo.FeatureVo;
import com.web.framework.util.LocalDate;
import com.web.framework.util.QueryString;
import com.web.framework.vo.AbstractVo;
import com.web.framework.vo.FeatureFilterVo;

import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;

@Component
public class BevEvaluatorPersistor extends Persistor {
 
	 
	
	@Override
	public FeatureVo perform(AbstractVo featureVo) throws BusinessException {

		FeatureVo featureDetailsVo = null;
		FeatureFilterVo featureFilterVo = (FeatureFilterVo) featureVo;
		StringBuilder query = constructQuery(featureFilterVo);
 
		Query q = (Query) em.createNativeQuery(query.toString());
		setParameter(featureVo, q);
		List<Object[]> featureVos = q.getResultList();
		if (Objects.nonNull(featureVos)) {
			featureDetailsVo = new FeatureVo();
			List<String> validator = null;
			List<String> enricher = null;
			List<String> postinvoker = null;
			List<String> preinvoker = null;

			for (Object[] featureVo1 : featureVos) {

				if ("VALIDATOR".equals(featureVo1[1])) {

					if (Objects.isNull(validator)) {
						validator = new ArrayList<>();
					}
					validator.add((String) featureVo1[0]);

				} else if ("ENRICHER".equals(featureVo1[1])) {

					if (Objects.isNull(enricher)) {
						enricher = new ArrayList<>();
					}
					enricher.add((String) featureVo1[0]);

				} else if ("POSTINVOKER".equals(featureVo1[1])) {

					if (Objects.isNull(postinvoker)) {
						postinvoker = new ArrayList<>();
					}
					postinvoker.add((String) featureVo1[0]);

				} else if ("PREINVOKER".equals(featureVo1[1])) {

					if (Objects.isNull(preinvoker)) {
						preinvoker = new ArrayList<>();
					}
					preinvoker.add((String) featureVo1[0]);

				}

			}
			if (Objects.nonNull(validator)) {
				featureDetailsVo.setValidatorsIds(new ArrayList<>());
				featureDetailsVo.setValidatorsIds(validator);
			}
			if (Objects.nonNull(enricher)) {
				featureDetailsVo.setEnricher(new ArrayList<>());
				featureDetailsVo.setEnricher(enricher);
			}
			if (Objects.nonNull(postinvoker)) {
				featureDetailsVo.setPostinvoker(new ArrayList<>());
				featureDetailsVo.setPostinvoker(postinvoker);
			}
			if (Objects.nonNull(preinvoker)) {
				featureDetailsVo.setPreinvoker(new ArrayList<>());
				featureDetailsVo.setPreinvoker(preinvoker);
			}

		}

		return featureDetailsVo;
	}

	private StringBuilder constructQuery(FeatureFilterVo featureFilterVo) {

		StringBuilder query = new StringBuilder();
		query.append(QueryString.FIND_FEATURE_DETAILS);

		return query;
	}

	@Override
	protected void setParameter(AbstractVo featureVo, Query q) {
		 
		FeatureFilterVo featureFilterVo = (FeatureFilterVo) featureVo;
		q.setParameter("configcod", featureFilterVo.getFeatureName());
		q.setParameter("fromDate",new LocalDate().now());
		q.setParameter("toDate",new LocalDate().now());
	 
		
	}

}
