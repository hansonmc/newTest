package com.jlc.api.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	/**
	 * 
	 */
	protected Logger log = Logger.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;

	protected Integer pageNo;
	
	protected Integer pageSize;
	
	protected String pageInfo;

	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
