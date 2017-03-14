package com.ghagos.wsviajersey.model;

import java.math.BigDecimal;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExpensiveProduct {
	private Map<String, BigDecimal> pMap;

	public ExpensiveProduct() {}
	
	public ExpensiveProduct(Map<String, BigDecimal> pMap) {
		this.pMap = pMap;
	}

	public Map<String, BigDecimal> getpMap() {
		return pMap;
	}

	public void setpMap(Map<String, BigDecimal> pMap) {
		this.pMap = pMap;
	}

}
