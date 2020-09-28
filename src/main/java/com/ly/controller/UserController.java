package com.ly.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ly.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;

/**
 * @Description
 * @Created by Administrator
 * @Date 2020/9/23 15:49
 */
@RestController
public class UserController {
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @JsonView(User.WithoutPasswordView.class)
    public User getUser() {
        return new User("fun", "aaa111");
    }

    @RequestMapping(path = "/user1", method = RequestMethod.GET)
    public String getUser(Model model) {
        model.addAttribute("user", new User("abc", "12134"));
        model.addAttribute(JsonView.class.getName(), User.WithoutPasswordView.class);
        return "userView";
    }


    //---异步请求的处理开始---
    @RequestMapping(method = RequestMethod.GET)
    public Callable<String> processUpload(final MultipartFile file) {

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "someView";
            }
        };

    }

    @RequestMapping("/quotes")
    @ResponseBody
    public DeferredResult<String> quotes() {
        DeferredResult<String> deferredResult = new DeferredResult<String>();
        // Save the deferredResult some where
        return deferredResult;
    }
    //In some other thread...
//    deferredResult.setResult(data);
    //---异步请求的处理结束---


    //发送多个对象
    @RequestMapping("/events")
    public ResponseBodyEmitter handle() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        //save the emitter somewhere..
        return emitter;
    }
    // In some other thread
    // emitter.send("Hello once");
    // and again later on
    // emitter.send("Hello again");
    // and done at some point
    // emitter.complete();

    @RequestMapping("/download")
    public StreamingResponseBody handle1(){
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                //write...
            }
        };
    }

}
