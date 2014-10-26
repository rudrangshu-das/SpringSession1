package com.xor.spring.service;

import com.xor.spring.model.Compensation;
import com.xor.spring.model.SECompensation;
import com.xor.spring.model.SSEBand1Compensation;
import com.xor.spring.model.SSEBand2Compensation;
import com.xor.spring.model.TLBand1Compensation;
import com.xor.spring.model.TLBand2Compensation;
import com.xor.spring.util.SpringBeanFactory;

public class CompensationFactory {
	
	public static Compensation getInstance(String type){

		if(type.equals("seComp")){
			return (SECompensation)SpringBeanFactory.getBean("seComp");
		} else if(type.equals("sseComp1")){
			return (SSEBand1Compensation)SpringBeanFactory.getBean("sseComp1");
		} else if(type.equals("sseComp2")){
			return (SSEBand2Compensation)SpringBeanFactory.getBean("sseComp2");
		} else if(type.equals("tlComp1")){
			return (TLBand1Compensation)SpringBeanFactory.getBean("tlComp1");
		} else if(type.equals("tlComp2")){
			return (TLBand2Compensation)SpringBeanFactory.getBean("tlComp2");
		}
		return null;
	
	}

}
