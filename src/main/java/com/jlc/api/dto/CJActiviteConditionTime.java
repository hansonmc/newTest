package com.jlc.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class CJActiviteConditionTime implements Serializable{
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * 最小投资额，默认为0
     */ 	
	
	private BigDecimal minAmount;
    /**
     * 分享次数    
     */ 	
	private Integer shareTime;
    /**
     * 投资次数
     */ 	
	private Integer investTime;
    /**
     * 赠送次数 
     */ 	
	private Integer freeTime;
	
	public BigDecimal getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	public Integer getShareTime() {
		return shareTime;
	}
	public void setShareTime(Integer shareTime) {
		this.shareTime = shareTime;
	}
	public Integer getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Integer investTime) {
		this.investTime = investTime;
	}
	public Integer getFreeTime() {
		return freeTime;
	}
	public void setFreeTime(Integer freeTime) {
		this.freeTime = freeTime;
	}
	

}
