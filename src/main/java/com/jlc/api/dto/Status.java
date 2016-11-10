package com.jlc.api.dto;

/**
 * <p>
 * Title: 是否生效
 * </p>
 * <p>
 * Description: 是否生效枚举类
 * </p>
 * <p>
 * Company: jyd
 * </p>
 * 
 * @author zhangzhigang
 * @date Nov 5, 2013
 */
public enum Status {
    IS_EXIST(1, "生效"), IS_NOT_EXIST(0, "失效");

    private int id;
    private String name;

    private Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getName(int id) {
        for (Status s : Status.values()) {
            if (s.getId() == id) {
                return s.name;
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
