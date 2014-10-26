package com.xor.spring.model;

import javax.annotation.PostConstruct;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.xor.spring.service.CompensationService;
import com.xor.spring.util.SpringBeanFactory;

public class Employee {

	private int id;
	private String name;
	private String designation;
	private int yoe;
	private Compensation compensation;
	private final ExpressionParser parser;

	public Employee() {
		parser = new SpelExpressionParser();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getYoe() {
		return yoe;
	}

	public void setYoe(int yoe) {
		this.yoe = yoe;
	}

	public Compensation getCompensation() {
		return compensation;
	}

	public void setCompensation(Compensation compensation) {
		this.compensation = compensation;
	}

	@PostConstruct
	public void calculateCompensation() {
		if (this.compensation == null) {
			StandardEvaluationContext ctx = new StandardEvaluationContext();
			ctx.setVariable("sseComp1", ((CompensationService) SpringBeanFactory.getBean("compensationService")).get("sseComp1"));
			ctx.setVariable("sseComp2", ((CompensationService) SpringBeanFactory.getBean("compensationService")).get("sseComp2"));
			ctx.setVariable("tlComp1", ((CompensationService) SpringBeanFactory.getBean("compensationService")).get("tlComp1"));
			ctx.setVariable("tlComp2", ((CompensationService) SpringBeanFactory.getBean("compensationService")).get("tlComp2"));
			ctx.setVariable("seComp", ((CompensationService) SpringBeanFactory.getBean("compensationService")).get("seComp"));
			ctx.setVariable("yoe", this.getYoe());
			Expression sseExpression = parser.parseExpression("#yoe ge 3 and #yoe le 5 ? #sseComp1 : #sseComp2");
			Expression tlExpression = parser.parseExpression("#yoe ge 6 and #yoe le 8 ? #tlComp1 : #tlComp2");
			Expression seExpression = parser.parseExpression("#seComp");
			if (designation.equals("se")) {
				this.compensation = seExpression.getValue(ctx, Compensation.class);
			} else if (designation.equals("sse")) {
				this.compensation = sseExpression.getValue(ctx, Compensation.class);
			} else if (designation.equals("tl")) {
				this.compensation = tlExpression.getValue(ctx, Compensation.class);
			}
		}
	}

}
