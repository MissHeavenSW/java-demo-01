package com.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public BaseController() {
    }

    public Message convert(Header header, ResponseEntity responseEntity) {
        Message message = new Message();
        header.setResultCode(responseEntity.getResultCode());
        header.setResultDesc(responseEntity.getResultDesc());
        message.setHeader(header);
        message.setBody(responseEntity.getData());
        return message;
    }

    public PageMessage convert(Header header, ResponsePageEntity responsePageEntity) {
        PageMessage message = new PageMessage();
        header.setResultCode(responsePageEntity.getResultCode());
        header.setResultDesc(responsePageEntity.getResultDesc());
        message.setHeader(header);
        message.setBody(responsePageEntity.getData());
        message.setPageIndex(responsePageEntity.getPageIndex());
        message.setPageSize(responsePageEntity.getPageSize());
        message.setTotalCount(responsePageEntity.getTotalCount());
        message.setTotalPage(responsePageEntity.getTotalPage());
        return message;
    }

    protected boolean isReturnSuccess(ResponseEntity responseEntity) {
        return null != responseEntity && "0".equals(responseEntity.getResultCode());
    }

    protected boolean isReturnSuccessAndDataNotNull(ResponseEntity responseEntity) {
        return this.isReturnSuccess(responseEntity) && null != responseEntity.getData();
    }

    protected boolean isReturnSuccess(ResponsePageEntity responsePageEntity) {
        return null != responsePageEntity && "0".equals(responsePageEntity.getResultCode());
    }

    protected boolean isReturnSuccessAndDataNotNull(ResponsePageEntity responsePageEntity) {
        return this.isReturnSuccess(responsePageEntity) && null != responsePageEntity.getData();
    }

}
