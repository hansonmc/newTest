package com.jlc.api.dto;


public class User implements java.io.Serializable{


	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -7687634624982838967L;

	/**
	 * 
	 */

	private Integer id;
	
	private String name;
	
	private Integer version;
	
	public static final String OBJECT_KEY = "USER";  

	public User() {  
    }  
  
    public User(Integer id) {  
    }  
  
    public User(Integer id, String name) {  
        this.id = id;  
        this.name = name;  
    }  
    
    public User(Integer id, String name,Integer version) {  
        this.id = id;  
        this.name = name;  
        this.version = version;  
    } 
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String toString() {  
        return "User [id=" + id + ", name=" + name + "]";  
    }  
  
    public Integer getKey() {  
        return getId();  
    }  
  
    public String getObjectKey() {  
        return OBJECT_KEY;  
    }  
	
}
