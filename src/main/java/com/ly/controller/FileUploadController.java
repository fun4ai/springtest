package com.ly.controller;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Created by Administrator
 * @Date 2020/9/24 0:56
 */
@Controller
public class FileUploadController {

    @RequestMapping(path = "/form", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            //store the bytes somewhere
            return "redirect:uploadSuccess";
        }
        return "redirect:uploadFailure";
    }

    //
/** POST /someUrl
    Content-Type: multipart/mixed
    --edt7Tfrdusa7r3lNQc79vXuhIIMlatb7PQg7Vp
    Content-Disposition: form-data; name="meta-data"
    Content-Type: application/json; charset=UTF-8
    Content-Transfer-Encoding: 8bit
    {
        "name": "value"
    }
    --edt7Tfrdusa7r3lNQc79vXuhIIMlatb7PQg7Vp
    Content-Disposition: form-data; name="file-data"; filename="file.properties"
    Content-Type: text/xml
    Content-Transfer-Encoding: 8bit
    ... File Data ...   */
//  请注意 MultipartFile 方法参数是如何能够在 @RequestParam 或 @RequestPart 注解下互用的，两种方法都能拿到数据。
//  但，这里的方法参数 @RequestPart("meta-data") MetaData 则会因为请求中的内容类型请求头 'Content-Type' 被读入成为JSON数据，
//  然后再通过 MappingJackson2HttpMessageConverter 被转换成特定的对象。
    @RequestMapping(path = "/someUrl", method = RequestMethod.POST)
    public String onSubmit(@RequestPart("meta-data") WSEndpointReference.Metadata metadata, @RequestPart("file-data") MultipartFile file) {



        return null;
    }


}
