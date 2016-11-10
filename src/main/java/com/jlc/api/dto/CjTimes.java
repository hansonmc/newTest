package com.jlc.api.dto;

import java.io.Serializable;
import java.util.Date;


public class CjTimes implements Serializable{

    private static final long serialVersionUID = 1L;


    private Long id;// 会员ID
    private Integer zpTimes;//可用抽奖次数
    private Integer zpGetTimes;//投资赠送抽奖次数
    private Integer zpGetShareTimes;//分享赠送抽奖次数
    private Date zpDate;//更新日期
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getZpTimes() {
		return zpTimes;
	}
	public void setZpTimes(Integer zpTimes) {
		this.zpTimes = zpTimes;
	}
	public Integer getZpGetTimes() {
		return zpGetTimes;
	}
	public void setZpGetTimes(Integer zpGetTimes) {
		this.zpGetTimes = zpGetTimes;
	}
	public Integer getZpGetShareTimes() {
		return zpGetShareTimes;
	}
	public void setZpGetShareTimes(Integer zpGetShareTimes) {
		this.zpGetShareTimes = zpGetShareTimes;
	}
	public Date getZpDate() {
		return zpDate;
	}
	public void setZpDate(Date zpDate) {
		this.zpDate = zpDate;
	}
    
	
}