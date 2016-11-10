package com.jlc.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: 消息实体类
 *
 */
public class UserMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;// 用户消息ID
    private String title;// 标题
    private String content;// 内容
    private String type; // 消息类型(1:系统消息,2:公共消息,3:其他消息,默认1)
    private Integer readStatus;// 阅读状态(1:已阅,0:未阅,默认0)
    private Date readTime;// 阅读时间
    private Integer delStatus;// 删除状态(1:已删除,0:未删除,默认0)
    private Date delTime;// 删除时间
    private Date optTime;// 发送时间
    private String operator;// 发送人
    private Date startTime;// 开始时间
    private Date endTime;// 结束时间
    private String useriId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

	public void setUseriId(String useriId) {
		this.useriId = useriId;
	}

	public String getUseriId() {
		return useriId;
	}
}