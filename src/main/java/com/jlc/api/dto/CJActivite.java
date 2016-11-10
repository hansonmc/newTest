package com.jlc.api.dto;

import java.io.Serializable;



/**
 * 抽奖活动
 * 
 */
public class CJActivite implements Serializable{
private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "抽奖活动";
	public static final String ALIAS_ID = "活动";
	public static final String ALIAS_NAME = "活动名称";
	public static final String ALIAS_TYPE = "1：双十一，2：砸金蛋3:摇一摇4：刮刮乐";
	public static final String ALIAS_STARTTIME = "活动开始时间";
	public static final String ALIAS_ENDTIME = "活动的结束时间";
	public static final String ALIAS_STATUS = "状态：0未开始，1进行中，2已结束";
	public static final String ALIAS_CREATE_TIME = "创建时候，默认为sysdate";
	public static final String ALIAS_REGULATION1_ID = "已登陆用户抽奖规则T_cj_regulation.id  ";
	public static final String ALIAS_REGULATION2_ID = "未登陆用户抽奖规则 T_cj_regulation.id ";
	public static final String ALIAS_REGULATION3_ID = "未投资用户抽奖规则 T_cj_regulation.id ";
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 活动       db_column: ID 
     */ 	
	private java.lang.Long id;
    /**
     * 活动名称       db_column: NAME 
     */ 	
	private java.lang.String name;
    /**
     * 1：双十一，2：砸金蛋3:摇一摇4：刮刮乐       db_column: TYPE 
     */ 	
	private Integer type;
    /**
     * 活动开始时间       db_column: STARTTIME 
     */ 	
	private java.util.Date starttime;
    /**
     * 活动的结束时间       db_column: ENDTIME 
     */ 	
	private java.util.Date endtime;
    /**
     * 状态：0未开始，1进行中，2已结束       db_column: STATUS 
     */ 	
	private Integer status;
    /**
     * 创建时候，默认为sysdate       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
    /**
     * 已登陆用户抽奖规则T_cj_regulation.id         db_column: REGULATION1_ID 
     */ 	
	
	private java.lang.Long regulation1Id;
    /**
     * 未登陆用户抽奖规则 T_cj_regulation.id        db_column: REGULATION2_ID 
     */ 	
	
	private java.lang.Long regulation2Id;
    /**
     * 未投资用户抽奖规则 T_cj_regulation.id        db_column: REGULATION3_ID 
     */ 	
	
	private java.lang.Long regulation3Id;
	
	/**
	 * 是否已删除 1，为已删除
	 */
	private Integer isDel;
	
	/**
	 * 短信，站内信模版
	 */
	private String messageTemplate;
	
	/**
	 * 是否发短信，0不发，1发
	 */
	private Integer isM;
	
	/**
	 * 是否发站内信，0不发，1发
	 */
	private Integer isZ;
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
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer value) {
		this.type = value;
	}
	
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.lang.Long getRegulation1Id() {
		return this.regulation1Id;
	}
	
	public void setRegulation1Id(java.lang.Long value) {
		this.regulation1Id = value;
	}
	
	public java.lang.Long getRegulation2Id() {
		return this.regulation2Id;
	}
	
	public void setRegulation2Id(java.lang.Long value) {
		this.regulation2Id = value;
	}
	
	public java.lang.Long getRegulation3Id() {
		return this.regulation3Id;
	}
	
	public void setRegulation3Id(java.lang.Long value) {
		this.regulation3Id = value;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public Integer getIsM() {
		return isM;
	}

	public void setIsM(Integer isM) {
		this.isM = isM;
	}

	public Integer getIsZ() {
		return isZ;
	}

	public void setIsZ(Integer isZ) {
		this.isZ = isZ;
	}
	
    
}
