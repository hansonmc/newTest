package com.jlc.api.dto;

/**
 * <p>
 * Title: 性别
 * </p>
 * <p>
 * Description: 性别说明枚举类
 * </p>
 * <p>
 * Company: jyd
 * </p>
 * 
 * @author zhangzhigang
 * @date Nov 5, 2013
 */
public enum Sex {

    MALE(1, "男"), FEMALE(2, "女"), SECRET(0, "保密");

    private int id;
    private String name;

    private Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getName(int id) {
        for (Sex s : Sex.values()) {
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
