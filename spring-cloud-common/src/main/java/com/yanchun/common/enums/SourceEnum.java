package com.yanchun.common.enums;
/**
 * 终端来源类型
 * @author  quyanchun
 * @date    2018/10/29
 */
public enum SourceEnum {
    WEB(0, "web端"),
    ANDROID(1, "安卓端"),
    IOS(2, "ios端"),
    SMALL_PROGRAM(3, "小程序端"),
    PUBLIC_NUMBER(4, "公众号端"),
    ;
    private Integer type;//来源
    private String value;//对应的值

    private SourceEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
    
    
    /**
	 * 根据标号获取描述
	 * @param index
	 * @return
	 */
	public static String getDesc(int index) {
		SourceEnum[] items = SourceEnum.values();
		for(SourceEnum item : items) {
			if(item.getType() == index) {
				return item.getValue();
			}
		}
		return null;
	}
	//检测type参数是否存在
	public static boolean checkType(Integer type) {
		for (SourceEnum ue : SourceEnum.values()) {
			if (ue.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
	//通过type获取SourceEnum
	public static SourceEnum getSourceEnum(Integer type) {
		for (SourceEnum ue : SourceEnum.values()) {
			if (ue.getType().equals(type)) {
				return ue;
			}
		}
		return null;
	}
	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		type = type;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
