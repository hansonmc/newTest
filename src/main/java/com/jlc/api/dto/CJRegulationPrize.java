package com.jlc.api.dto;

import java.io.Serializable;

/**
 * 规则奖品关联
 * @author xjt
 *
 */
public class CJRegulationPrize implements Serializable{
	
    private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "规则奖品关联表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_REGULATION_ID = "T_cj_regulation.id";
	public static final String ALIAS_PRIZE_ID = "T_cj_prize.id";
	public static final String ALIAS_CAMPAIGN_ID = "红包，奖券，";
	public static final String ALIAS_CREATE_TIME = "创建时间 默认为sysdate";
	public static final String ALIAS_ACCOUNT = "数据";

	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Long id;
    /**
     * T_cj_regulation.id       db_column: REGULATION_ID 
     */ 	
	private java.lang.Long regulationId;
    /**
     * T_cj_prize.id       db_column: PRIZE_ID 
     */ 	
	private java.lang.Long prizeId;
    /**
     * 红包，奖券，       db_column: CAMPAIGN_ID 
     */ 	
	
	private java.lang.Long campaignId;
    /**
     * 创建时间 默认为sysdate       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
    /**
     * 数据       db_column: ACCOUNT 
     */ 	
	private java.lang.Long account;
	
	private String prizeLevel;//奖品的等级
	//columns END

	
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getRegulationId() {
		return this.regulationId;
	}
	
	public void setRegulationId(java.lang.Long value) {
		this.regulationId = value;
	}
	
	public java.lang.Long getPrizeId() {
		return this.prizeId;
	}
	
	public void setPrizeId(java.lang.Long value) {
		this.prizeId = value;
	}
	
	public java.lang.Long getCampaignId() {
		return this.campaignId;
	}
	
	public void setCampaignId(java.lang.Long value) {
		this.campaignId = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.lang.Long getAccount() {
		return this.account;
	}
	
	public void setAccount(java.lang.Long value) {
		this.account = value;
	}

	public String getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	
	
}
