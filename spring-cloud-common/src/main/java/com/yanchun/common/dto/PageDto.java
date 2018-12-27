package com.yanchun.common.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageDto<T> implements Serializable {

    private int pn;// 页码
    private int ps;// 每页显示记录数
    private int total;// 总共的记录数
    private List<T> list;// 每页显示的数据

    public PageDto() {
    }

    public PageDto(int pn, int ps, int total, List<T> list) {
        this.pn = pn;
        this.ps = ps;
        this.total = total;
        this.list = list;
    }
}
