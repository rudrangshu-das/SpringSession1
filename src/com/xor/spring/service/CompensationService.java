package com.xor.spring.service;

import com.xor.spring.model.Compensation;

public abstract class CompensationService {

	public Compensation get(String type) {
		if(type.equals("seComp")){
			return createSECompensation();
		} else if(type.equals("sseComp1")){
			return createSSEBand1Compensation();
		} else if(type.equals("sseComp2")){
			return createSSEBand2Compensation();
		} else if(type.equals("tlComp1")){
			return createTLBand1Compensation();
		} else if(type.equals("tlComp2")){
			return createTLBand2Compensation();
		}
		return null;
	}

	protected abstract Compensation createSECompensation();

	protected abstract Compensation createSSEBand1Compensation();

	protected abstract Compensation createSSEBand2Compensation();

	protected abstract Compensation createTLBand1Compensation();

	protected abstract Compensation createTLBand2Compensation();
}
