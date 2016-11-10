package com.jlc.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 奖品
 * @author xjt
 *
 */
public class CJPrize implements Serializable{
     private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TcjPrize";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "奖品的名称";
	public static final String ALIAS_TYPE_CODE = "T_dic.code 奖品类型";
	public static final String ALIAS_AMOUNT = "奖品的面值";
	public static final String ALIAS_CREATE_TIME = "创建时间，默认为sysdate";
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Long id;
    /**
     * 奖品的名称       db_column: NAME 
     */ 	
	private java.lang.String name;
    /**
     * T_dic.code 奖品类型       db_column: TYPE_CODE 
     */ 	
	private java.lang.String typeCode;
    /**
     * 奖品的面值       db_column: AMOUNT 
     */ 	
	
	private BigDecimal amount;
    /**
     * 创建时间，默认为sysdate       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
	
	private String mConText;//短信内容
	
	private String zConText;//站内信内容
	
	private String prizeLevel;//奖品的等级
	//columns END

	
	
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getTypeCode() {
		return this.typeCode;
	}
	
	public void setTypeCode(java.lang.String value) {
		this.typeCode = value;
	}
	
	public BigDecimal getAmount() {
		return this.amount;
	}
	
	public void setAmount(BigDecimal value) {
		this.amount = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
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

	public String getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	
	
	
}
