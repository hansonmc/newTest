package com.jlc.api.dto;

import java.io.IOException;
import java.io.Serializable;

/**
 * 奖池
 * @author xjt
 *
 */
public class CJPond implements Serializable{
	
    private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "奖池表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SERIAL_NUMBER = "序列号";
	public static final String ALIAS_PRIZE_ID = "对应奖品id  T_cj_prize.id（必须加密）";
	public static final String ALIAS_CREATE_DATE = "生成时间";
	public static final String ALIAS_REGULATION_ID = "规则id，T_cj_regulation.id";

	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Long id;
    /**
     * 序列号       db_column: SERIAL_NUMBER 
     */ 	
	private java.lang.String serialNumber;
    /**
     * 对应奖品id  T_cj_prize.id（必须加密）       db_column: PRIZE_ID 
     */ 	
	private java.lang.String prizeId;
    /**
     * 生成时间       db_column: CREATE_DATE 
     */ 	
	
	private java.util.Date createDate;
    /**
     * 规则id，T_cj_regulation.id       db_column: REGULATION_ID 
     */ 	
	
	private java.lang.Long regulationId;
	//columns END

	
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialNumber(java.lang.String value) {
		this.serialNumber = value;
	}
	
	public java.lang.String getPrizeId() throws IOException {
		return this.prizeId;
	}
	
	public void setPrizeId(java.lang.String value) {
		this.prizeId = value;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	public java.lang.Long getRegulationId() {
		return this.regulationId;
	}
	
	public void setRegulationId(java.lang.Long value) {
		this.regulationId = value;
	}
}
