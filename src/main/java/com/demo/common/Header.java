package com.demo.common;

import lombok.Data;

@Data
public class Header {
    private static final long serialVersionUID = 1L;
    public String version;
    public String token;
    public String isSecurity;
    public String security;
    public String businessID;
    public String isSync;
    public String syncSignal;
    public String expendTime;
    public String resultCode;
    public String resultDesc;
    public Header() {
    }

}
