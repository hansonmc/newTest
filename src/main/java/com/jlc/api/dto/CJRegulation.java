package com.jlc.api.dto;

import java.io.Serializable;

/**
 * 规则
 * @author xjt
 *
 */
public class CJRegulation implements Serializable{
    private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "规则";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "规则名称";
	public static final String ALIAS_STATUS = "0未进行，1进行中,2待生成奖池";
	public static final String ALIAS_CREATE_TIME = "创建时间，默认为sysdate";
	public static final String ALIAS_EACH_ACCOUNT = "每人中奖上限";
	public static final String ALIAS_TOTAL_ACCOUNT = "抽奖总次数";
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */ 	
	private java.lang.Long id;
    /**
     * 规则名称       db_column: NAME 
     */ 	
	private java.lang.String name;
    /**
     * 0未进行，1进行中       db_column: STATUS 
     */ 	
	private java.lang.Byte status;
    /**
     * 创建时间，默认为sysdate       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
    /**
     * 每人中奖上限       db_column: EACH_ACCOUNT 
     */ 	
	private java.lang.Long eachAccount;
    /**
     * 抽奖总次数       db_column: TOTAL_ACCOUNT 
     */ 	
	private java.lang.Long totalAccount;
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
	
	public java.lang.Byte getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Byte value) {
		this.status = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.lang.Long getEachAccount() {
		return this.eachAccount;
	}
	
	public void setEachAccount(java.lang.Long value) {
		this.eachAccount = value;
	}
	
	public java.lang.Long getTotalAccount() {
		return this.totalAccount;
	}
	
	public void setTotalAccount(java.lang.Long value) {
		this.totalAccount = value;
	}
}
