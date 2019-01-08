package com.zkh.core.exception;

import com.zkh.core.model.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultBean<?> handleMethodArgumentNotValidException(Exception e) {

        ResultBean<Map<String,String>> result = new ResultBean<>();
        result.setCode(ResultBean.FAIL);

        if(e instanceof BindException) {
            List<FieldError> errors = ((BindException)e).getBindingResult().getFieldErrors();
            Map<String,String> msgs = new HashMap<>();
            for(FieldError error : errors) {
                msgs.put(error.getField(),error.getDefaultMessage());
            }

            result.setData(msgs);
            result.setMsg("Invalid parameter");

            log.error(msgs.toString());

        } else {
            result.setMsg(e.getMessage());
            log.error(e.getMessage());
        }

        return result;
    }
}
