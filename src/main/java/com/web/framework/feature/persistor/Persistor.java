package com.web.framework.feature.persistor;

import java.math.BigInteger;
import java.util.Objects;
  

import com.web.framework.entity.BaseEntity;
import com.web.framework.exception.BusinessException;
import com.web.framework.vo.AbstractVo;
import com.web.framework.vo.Page;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
 
public abstract class Persistor<T extends AbstractVo> implements IPersistor{
	
	@PersistenceContext
	public EntityManager em;
	
	 public abstract <R extends Object> R perform(T featureVo) throws BusinessException;
	 
	 
	 public void updatePage(Query q, Page page) {
		 
		 if(Objects.isNull(page)) {
			 page=new Page();
		 }
		 int currentPage=page.getCurrentPage()-1;
		 int maxResult=page.getMaxResult()==0?10:page.getMaxResult();
		 q.setFirstResult(currentPage*maxResult);
		 q.setMaxResults(maxResult);
	}
	 
	 public Page populatePageCount(AbstractVo detailVo, StringBuilder query) {
		 
		 	Page page=detailVo.getPage();
		 	if(Objects.isNull(page)) {
		 		page=new Page();
		 	}
		 	 Query q1 = em.createNativeQuery("Select count(*) from ("+query.toString()+") c where 1=1");
			setParameter(detailVo, q1);
			int count= ((BigInteger) q1.getSingleResult()).intValue();
			page.setMaxResult(count);
			int totalPage=count/page.getPerPage();
			int reminder=count%page.getPerPage();
			if(reminder>0) {
				totalPage+=1;
			}
			page.setTotalPage(totalPage);
			return page;
		}

	public AbstractVo populate(AbstractVo abstractVo, BaseEntity baseEntity) {

		abstractVo.setCreatedDate(baseEntity.getCreatedDate());
		abstractVo.setValidFrom(baseEntity.getFromDate());
		abstractVo.setValidTo(baseEntity.getToDate());
		abstractVo.setStatus(baseEntity.getStatus());
		abstractVo.setLastupdate(baseEntity.getLastupdate());
		return abstractVo;
	}
	protected abstract void setParameter(AbstractVo detailVo, Query q);

}
