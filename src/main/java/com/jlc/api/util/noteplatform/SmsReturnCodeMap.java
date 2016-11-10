package com.jlc.api.util.noteplatform;

public enum  SmsReturnCodeMap {
	    SUCCESS("提交成功", "0"), 
	    USERNOTEXIST("无此用户", "101"), 
	    PWDERROR("密码错", "102"), 
	    MANYTOSUBMIT("提交过快", "103"), 
	    BUSING("系统忙", "104"), 
	    CHECKNOPASS("敏感短信", "105"), 
	    CONTENTLENGTHERROR("消息长度错", "106"),
	    MOBILEERROR("包含错误的手机号码", "107"),
	    MOBILECOUNTERROR("手机号码个数错", "108"),
	    SMSISOVER("无发送额度", "109"),
	    NOTINSNETTIME("不在发送时间内", "110"),
	    USERMONTHSMSCOUNTISOVER("超出该账户当月发送额度限制", "111"),
	    NOTBUYPRODUCT("无此产品，用户没有订购该产品", "112"),
	    EXTNOERROR("extno格式错", "113"),
	    AUTOCHECKERROR("自动审核驳回", "115"),
	    SIGNERROR("签名不合法，未带签名", "116"),
	    SERVERIPERROR("IP地址认证错,请求调用的IP地址不是系统登记的IP地址", "117"),
	    USERNOSENTFUN("用户没有相应的发送权限", "118"),
	    USEREXPIRED("用户已过期", "119");
	    
	    private String code;  
	    private String name;
	    
	    private SmsReturnCodeMap(String name,String code){
	        this.code = code;
	        this.name = name;
	    }

	    public static String getName(String code) {  
	        for (SmsReturnCodeMap c : SmsReturnCodeMap.values()) {  
	            if (c.getCode().equals(code)) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
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
	    
		public static void main(String[] args){
			System.out.println(SmsReturnCodeMap.getName("118"));
		}
	    
}
