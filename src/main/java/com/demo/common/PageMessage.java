package com.demo.common;

import lombok.Data;

@Data
public class PageMessage extends Message{
    public int pageIndex = 1;
    public int pageSize = 10;
    public int totalCount = 0;
    public int totalPage = 0;

}
