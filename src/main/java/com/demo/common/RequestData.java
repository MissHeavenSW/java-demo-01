package com.demo.common;

import lombok.Data;

import javax.validation.Valid;

@Data
public class RequestData<T> {
    private Header header;
    @Valid
    private T body;

    public RequestData() {
    }
}
