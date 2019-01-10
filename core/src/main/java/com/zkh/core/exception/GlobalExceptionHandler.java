package com.zkh.core.exception;

import com.zkh.core.model.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ExceptionHandler(Exception.class)
    public ResultBean<?> handleMethodArgumentNotValidException(Exception e) {

        ResultBean<Map<String,String>> result = new ResultBean<>();
        result.setCode(ResultBean.FAIL);

        if(e instanceof BindException) {
            //请求参数校验异常
            List<FieldError> errors = ((BindException)e).getBindingResult().getFieldErrors();
            Map<String,String> msgs = new HashMap<>();
            for(FieldError error : errors) {
                msgs.put(error.getField(),error.getDefaultMessage());
            }

            result.setData(msgs);
            result.setMsg("Invalid parameter");

            log.error(msgs.toString());

        } else {
            //其它异常
            result.setMsg(e.getMessage());
            log.error(e.getMessage());
        }

        return result;
    }
}
