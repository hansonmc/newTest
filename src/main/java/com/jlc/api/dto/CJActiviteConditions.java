package com.jlc.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动抽奖条件关联
 * @author xjt
 *
 */
public class CJActiviteConditions implements Serializable{
    private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "活动抽奖条件关联表";
	public static final String ALIAS_ACTIVITE_ID = "t_cj_activite.id";
	public static final String ALIAS_CONDITIONS_ID = "T_cj_conditions.id";
	public static final String ALIAS_MIN_AMOUNT = "最小投资额，默认为0";
	public static final String ALIAS_TIME = "次数";
	public static final String ALIAS_UNIT = "单位：H小时，D天，M月，Y年";
	public static final String ALIAS_CREATE_TIME = "默认为sysdate";
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * t_cj_activite.id       db_column: ACTIVITE_ID 
     */ 	
	private java.lang.Long activiteId;
    /**
     * T_cj_conditions.id       db_column: CONDITIONS_ID 
     */ 	
	private java.lang.Long conditionsId;
    /**
     * 最小投资额，默认为0       db_column: MIN_AMOUNT 
     */ 	
	
	private BigDecimal minAmount;
    /**
     * 次数       db_column: TIME 
     */ 	
	private Integer time;
    /**
     * 单位：H小时，D天，M月，Y年       db_column: UNIT 
     */ 	
	private String unit;
    /**
     * 默认为sysdate       db_column: CREATE_TIME 
     */ 	
	private Date createTime;
	
	/**
     * 赠送次数       db_column: TIME 
     */ 	
	private Integer mfTime;
	//columns END

	
	public Integer getMfTime() {
		return mfTime;
	}

	public void setMfTime(Integer mfTime) {
		this.mfTime = mfTime;
	}

	public Long getActiviteId() {
		return this.activiteId;
	}
	
	public void setActiviteId(Long value) {
		this.activiteId = value;
	}
	
	public Long getConditionsId() {
		return this.conditionsId;
	}
	
	public void setConditionsId(Long value) {
		this.conditionsId = value;
	}
	
	public BigDecimal getMinAmount() {
		return this.minAmount;
	}
	
	public void setMinAmount(BigDecimal value) {
		this.minAmount = value;
	}
	
	public Integer getTime() {
		return this.time;
	}
	
	public void setTime(Integer value) {
		this.time = value;
	}
	
	public java.lang.String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String value) {
		this.unit = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	
}
