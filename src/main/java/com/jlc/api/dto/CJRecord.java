package com.jlc.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖记录
 * @author xjt
 *
 */
public class CJRecord implements Serializable{
    private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "抽奖记录表";
	public static final String ALIAS_ID = "活动";
	public static final String ALIAS_CJ_AID = "活动id，t_cj_activite.id";
	public static final String ALIAS_USER_ID = "用户id";
	public static final String ALIAS_PRIZE_ID = "奖品id ，T_cj_prize.id （必须加密）";
	public static final String ALIAS_CJ_DATE = "抽奖时间";
	public static final String ALIAS_DETAIL = "奖品明细";
	public static final String ALIAS_SERIAL_NUMBER = "序列号";
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 活动       db_column: ID 
     */ 	
	private Long id;
    /**
     * 活动id，t_cj_activite.id       db_column: CJ_AID 
     */ 	
	
	private Long cjAid;
    /**
     * 用户id       db_column: USER_ID 
     */ 	
	
	private Long userId;
    /**
     * 奖品id ，T_cj_prize.id （必须加密）       db_column: PRIZE_ID 
     */ 	
	private String prizeId;
    /**
     * 抽奖时间       db_column: CJ_DATE 
     */ 	
	
	private Date cjDate;
    /**
     * 奖品明细       db_column: DETAIL 
     */ 	
	private String detail;
    /**
     * 序列号       db_column: SERIAL_NUMBER 
     */ 	
	private String serialNumber;
	
	private String boundPhone;
	
    private String phone;
	
	private Long regulationId;//规则id
	
	private String status;//红包领取状态
	
	private String randomMd5;//时间戳+随机数MD5，用于手机号未注册抽奖，发奖用
	
	

	//columns END
	
     public String getBoundPhone() {
		return boundPhone;
	}

	public void setBoundPhone(String boundPhone) {
		this.boundPhone = boundPhone;
	}


	public String getRandomMd5() {
		return randomMd5;
	}

	public void setRandomMd5(String randomMd5) {
		this.randomMd5 = randomMd5;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRegulationId() {
		return regulationId;
	}

	public void setRegulationId(Long regulationId) {
		this.regulationId = regulationId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getCjAid() {
		return this.cjAid;
	}
	
	public void setCjAid(Long value) {
		this.cjAid = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public String getPrizeId() {
		return this.prizeId;
	}
	
	public void setPrizeId(String value) {
		this.prizeId = value;
	}
	
	public Date getCjDate() {
		return this.cjDate;
	}
	
	public void setCjDate(Date value) {
		this.cjDate = value;
	}
	
	public String getDetail() {
		return this.detail;
	}
	
	public void setDetail(String value) {
		this.detail = value;
	}
	
	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialNumber(String value) {
		this.serialNumber = value;
	}
	
	
}
