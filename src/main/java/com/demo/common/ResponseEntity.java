package com.demo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resultCode = "0";
    private String resultDesc = "";
    private Object data;


    public ResponseEntity() {
    }

    public ResponseEntity(String resultCode, String resultDesc, Object data) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.data = data;
    }

    public ResponseEntity(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public ResponseEntity(Object data) {
        this.data = data;
    }

}
