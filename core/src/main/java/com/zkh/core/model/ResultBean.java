package com.zkh.core.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {
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

    public ResultBean(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
