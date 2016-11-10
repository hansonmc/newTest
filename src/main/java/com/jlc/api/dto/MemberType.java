package com.jlc.api.dto;

/**
 * <p>
 * Title: 会员类型
 * </p>
 * <p>
 * Description: 会员类型枚举类
 * </p>
 * <p>
 * Company: jyd
 * </p>
 * 
 * @author zhangzhigang
 * @date Nov 5, 2013
 */
public enum MemberType {
    TOUZI(1, "投资方"), JIEKUAN(2, "借款方");

    private int id;
    private String name;

    private MemberType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(int id) {
        for (MemberType t : MemberType.values()) {
            if (t.getId() == id) {
                return t.name;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
