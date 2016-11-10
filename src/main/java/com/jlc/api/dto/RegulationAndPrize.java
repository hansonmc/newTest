package com.jlc.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 规则关联和奖品
 * @author xjt
 *
 */
public class RegulationAndPrize implements Serializable{
	
    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * id       db_column: ID 
     */ 	
	private Long RegulationPrizeId;
    /**
     * T_cj_prize.id       db_column: PRIZE_ID 
     */ 	
	private Long prizeId;
    /**
     * 红包，奖券，       db_column: CAMPAIGN_ID 
     */ 
	private Long campaignId;
    /**
     * 奖品的名称       db_column: NAME 
     */ 	
	private String prizeName;
    /**
     * T_dic.code 奖品类型       db_column: TYPE_CODE 
     */ 	
	private String typeCode;
    /**
     * 奖品的面值       db_column: AMOUNT 
     */ 	
	private BigDecimal amount;
	
	private String mConText;//短信内容
	
	private String zConText;//站内信内容
	
	private String prizeLevel;//奖品的等级

	public Long getRegulationPrizeId() {
		return RegulationPrizeId;
	}

	public void setRegulationPrizeId(Long regulationPrizeId) {
		RegulationPrizeId = regulationPrizeId;
	}

	public Long getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getmConText() {
		return mConText;
	}

	public void setmConText(String mConText) {
		this.mConText = mConText;
	}

	public String getzConText() {
		return zConText;
	}

	public void setzConText(String zConText) {
		this.zConText = zConText;
	}
	
	
}
