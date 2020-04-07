package com.demo.common;

import lombok.Data;

import java.util.List;

@Data
public class ResponsePageEntity {
    private int pageIndex = 1;
    private int pageSize = 10;
    private int totalCount;
    private List list;
    private String code;
    private String msg;

    public ResponsePageEntity() {
    }
}
