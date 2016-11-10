package com.jlc.api.util.noteplatform;

public class SmsChannel {

	private String code; // 编号
	private String name;// 名称
	private String status;// 状态 0 不可用 1 可用

	private int index;// 索引

	private Long time;// 时间

	public SmsChannel() {

	}

	public SmsChannel(String code, String name, String status) {
		this.code = code;
		this.name = name;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
