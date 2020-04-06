package com.demo.common;

public enum ResponseEnum {
    SUCCESS("0","成功"),
    FAIL("1","失败"),

    DIRECTOR_ID_IS_NOT_NULL("101","教导主任标识不能为空"),
    REQUEST_ID_IS_NOT_DIRECTOR("102","不是教导主任没有权限查看"),
    SYSTEM_ERROR("103","系统异常,请稍后再试"),
    TEA_ID_IS_NOT_NULL("104","教师标识不能为空"),
    REQUEST_ID_IS_NOT_TEA("105","不是教师没有权限查看"),
    REQUEST_ID_IS_NOT_STU("106","不是学生没有权限查看"),
    STU_ID_IS_NOT_NULL("107","学生标识不能为空"),
    ;


    private String code;

    private String message;

    ResponseEnum() {
    }

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
