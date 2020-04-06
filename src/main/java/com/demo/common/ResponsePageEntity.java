package com.demo.common;

import lombok.Data;

@Data
public class ResponsePageEntity extends ResponseEntity {
    protected int pageIndex = 1;
    protected int pageSize = 10;
    protected int totalCount;
    protected int totalPage;

    public ResponsePageEntity() {
    }
}
