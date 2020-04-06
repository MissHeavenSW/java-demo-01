package com.demo.common;

import lombok.Data;

@Data
public class RequestPageEntity<T> extends RequestData<T> {
    protected int pageIndex = 1;
    protected int pageSize = 10;
    protected int totalCount;
    protected int totalPage;

    public RequestPageEntity() {
    }
}
