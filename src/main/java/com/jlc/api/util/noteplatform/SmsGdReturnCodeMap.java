package com.jlc.api.util.noteplatform;


public enum SmsGdReturnCodeMap {
	BATCHSUCCESS("批量短信提交成功", "00"), 
	SPECIALSUCCESS("个性化短信提交成功", "01"), 
    IPLIMIT("IP限制", "02"), 
    SINGLESUCCESS("单条短信提交成功", "03"), 
    NAMEERROT("用户名错误", "04"), 
    PWDERROT("密码错误", "05"), 
    SPECIALFORMATERROT("个性化短信格式错误", "06"),
    SENTTIMEERROT("发送时间有误", "07"),
    MSGNULL("内容为空", "08"),
    MOBILENULL("手机号码为空", "09"),
    EXTFORMATERROT("扩展号格式错误", "10"),
    NOTENOUGHMONEY("余额不足", "11"),
    SERVICEERROT("服务器内部错误", "-1");
    
    private String code;  
    private String name;
    
    private SmsGdReturnCodeMap(String name,String code){
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {  
        for (SmsGdReturnCodeMap c : SmsGdReturnCodeMap.values()) {  
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
		System.out.println(SmsGdReturnCodeMap.getName("03"));
	}
}
