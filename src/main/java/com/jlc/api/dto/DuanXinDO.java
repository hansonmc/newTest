package com.jlc.api.dto;


/**
 * 
 * <pre>项目名称：ebankApp    
 * 类名称：DuanXinDO    
 * 类描述：    接收短信接口返回数据
 * 创建人：lilei
 * 创建时间：2015-12-11 下午4:17:32     
 * @version </pre>
 */
public class DuanXinDO{
	
	
	private Boolean result;//标志
	private String code;//编码
	private String name;//描述
	
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
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
    
    
}
