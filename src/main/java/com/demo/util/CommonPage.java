package com.demo.util;

import lombok.Data;

import java.util.List;

@Data
public class CommonPage<T> {
    private Integer begin;
    private Integer end;
    private Integer totalPage;
    private Integer total;
    private List<T> list;

    public static <T> CommonPage<T> restPage(List<T> list, int begin, int end, int total){
        CommonPage<T> result = new CommonPage<T>();
        result.setBegin(begin);
        result.setEnd(end);
        result.setTotal(total);
        result.setTotalPage((total + end - 1)/end);
        result.setList(list);
        return result;
    }
}
