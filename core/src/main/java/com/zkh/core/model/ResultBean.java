package com.zkh.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultBean<T> {
    public static int SUCCESS = 0;
    public static int FAIL = -1;
    public static int NO_LOGIN = -2;
    public static int NO_PERMISSION = -3;

    private String msg;
    private T data;
    private int code;

    public ResultBean(T data) {
        this.data = data;
        this.msg = "success";
        this.code = SUCCESS;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
