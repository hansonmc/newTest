package com.jlc.api.type;
/**
 * 短信来源类型 
 */
public enum SmsFromType {

	APPMOBILEREG("注册", "01000"),
    APPFASTREG("快速注册", "01001"),
    APPCASHAPP("提现申请", "01002"),
    APPUNLOGIN("登录前短信", "01003"),
    APPLOGIN("登录后短信", "01004"),
	APPSWEEPSTAKE("抽奖短信", "01005"),
	APPDISTRIBUTE("发奖短信", "01006");
	
	private String name;
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private SmsFromType(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
}
