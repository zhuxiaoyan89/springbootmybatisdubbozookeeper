package com.zxy.common.JDK8Test;

public enum Seasons {
    SPRING("春天", "1"), SUMMER("夏天", "2"), AUTUMN("秋天", "3"), WINTER("冬天", "4");

    private String msg;
    private String index;

    Seasons(String msg, String index) {
        this.msg = msg;
        this.index = index;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
