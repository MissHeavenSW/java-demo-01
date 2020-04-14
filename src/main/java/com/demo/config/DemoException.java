package com.demo.config;

import com.nhsoft.provider.common.AbstractException;

public class DemoException extends AbstractException {

    public DemoException(DemoError code) {
        super(code.getCode(), code.getDescribe());
    }
}
