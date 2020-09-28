package com.ly.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @Description
 * @Created by Administrator
 * @Date 2020/9/23 16:27
 */
@ControllerAdvice
public class JsonAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonAdvice() {
        super("callback");
    }
}
