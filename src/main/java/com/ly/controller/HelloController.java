package com.ly.controller;

import com.ly.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Created by Administrator
 * @Date 2020/9/22 18:36
 */
@Controller
@RequestMapping("hello")
public class HelloController {

//  value:  指定请求的实际地址， 比如 /action/info之类。
//  method：  指定请求的method类型， GET、POST、PUT、DELETE等
//  consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
//  produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
//  params： 指定request中必须包含某些参数值是，才让该方法处理
//  headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求


    //    @RequestMapping(method = RequestMethod.GET)
//    @RequestMapping(path = "/{day}", method = RequestMethod.GET);
    @RequestMapping("/helloworld")
    public String HelloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "helloWorld";
    }

    @RequestMapping(path = "/{day}", method = RequestMethod.GET)
    public String hellya(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date day, Model model) {
        return "aa";
    }

    //"/springweb/spring-web-3.0.5.jar"
    @RequestMapping("/spring-web/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public void handle(@PathVariable String version, @PathVariable String extension) {
        // 代码部分省略...
    }


//在Request中 ContentType 告诉服务器当前发送的数据是什么格式; Accept 告诉服务器，客户端能认识哪些格式,最好返回这些格式中的其中一种
//consumes 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
//produces 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回


    @RequestMapping(path = "/pets", method = RequestMethod.POST, consumes = "application/json")
    public void addPet(@RequestBody Object pet, Model model) {
        // 方法实现省略
    }

    @RequestMapping(path = "/pets/{petId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object getPet(@PathVariable String petId, Model model) {
        // 方法实现省略
        return new Object();
    }


    //    填充一些公共需要的属性或数据 如下拉列表预设几种状态
//    在 @RequestMapping 方法之前被调用
    @ModelAttribute
    public Object addObject(@RequestParam String number) {
        return new Object();    //通过返回值的方式默认地将添加一个属性
    }

    @ModelAttribute
    public void populateModel(@RequestParam String number, Model model) {
        model.addAttribute(new Object());   //方法接收一个 Model 对象，然后可以向其中添加任意数量的属性
    }


    @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
    public String processSubmit(@Validated @ModelAttribute("pet") Object pet, BindingResult result) {
//        new PetValidator().validate(pet, result);
        if (result.hasErrors()) {
            return "petForm";
        }
        // ...
        return null;
    }

    //列出该类型希望存储到session或converstaion中的model attributes or types of model attributes，
    // 一般是用于在请求之间保存一些表单数据的bean。
//    @Controller
//    @RequestMapping("/editPet.do")
//    @SessionAttributes("pet")
//    public class EditPetForm {
//        // ...
//    }

    //请求中COOKIE JSESSIONID=415A4AC178C59DACE0B2C9CA727CDD84
    @RequestMapping("/displayHeaderInfo.do")
    public void displayHeaderInfo(@CookieValue("JSESSIONID") String cookie) {
        // ...
    }

    //获得请求头属性，如果使用在Map/HttpHeaders上将接受所有header属性
    //可自动转化逗号分隔字符串 如@RequestHeader("Accept")方法参数可String/String[]/List<String>
    @RequestMapping("/displayHeaderInfo.do")
    public void displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding,
                                  @RequestHeader("Keep-Alive") long keepAlive) {
        //...
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        simpleDateFormat.setLenient(false);
//        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }


    //重定向   带参需要对中文编码进行处理。

    @RequestMapping(path = "/files/{path}", method = RequestMethod.POST)
    public String upload(Model model) {
        // ...
        return "redirect:files/{path}";
    }

    //response.sendRedirect
    @RequestMapping(value="/testredirect",method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView testredirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index");
        return null;
    }
    //ViewResolver直接跳转
    @RequestMapping(value="/testredirect",method = { RequestMethod.POST, RequestMethod.GET })
    public  String testredirect1(HttpServletResponse response){
        return "redirect:/index";
    }
    @RequestMapping("/testredirect")
    public String testredirect2(Model model, RedirectAttributes attr) {
        //跳转地址带上test参数 如ttp:/index.action?test=51gjie
        attr.addAttribute("test", "51gjie");
        //跳转地址不带上u2参数，参数保存在session中，待重定向url获取该参数后从session中移除
        attr.addFlashAttribute("u2", "51gjie");
        return "redirect:/user/users";
    }
    //ModelAndView重定向
    @RequestMapping(value="/restredirect",method = { RequestMethod.POST, RequestMethod.GET })
    public  ModelAndView restredirect(String userName){
        ModelAndView  model = new ModelAndView("redirect:/main/index");
        return model;
    }
    @RequestMapping(value="/toredirect",method = { RequestMethod.POST, RequestMethod.GET })
    public  ModelAndView toredirect(String userName){
        ModelAndView  model = new ModelAndView("/main/index");
        //把userName参数带入到controller的RedirectAttributes
        //ModelAndView 内部使用了一个 ModelMap 类，它是 Map 的一个实现，会自动为添加进来的对象生成一个键名，遵循最小惊喜原则。
        // 如User->user User[]->userList ... 即Set/List->xxxList
        // model.addObject(user);
        model.addObject("userName", userName);
        return model;
    }



    //ContentNegotiatingViewResolver 内容协商解析器
//    private List<SampleContent> contenList = new ArrayList<SampleContent>();
    @RequestMapping(path = "/content", method = RequestMethod.GET)
    public ModelAndView getContent(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content");
//        mav.addObject("sampleContentList", contenList);
        return mav;
    }


    //会接收并处理由控制器（或其任何子类）中的 @RequestMapping 方法抛出的异常
    //方法声明的异常才会处理
    @ExceptionHandler({IOException.class,IllegalAccessException.class})
    public ResponseEntity<String> handleIOException(IOException e){

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        return responseEntity;
    }


    //缓存Cache

    // Cache for an hour - "Cache-Control: max-age=3600"
//    CacheControl ccCacheOneHour = CacheControl.maxAge(1, TimeUnit.HOURS);

    // Prevent caching - "Cache-Control: no-store" 禁止缓存
//    CacheControl ccNoStore = CacheControl.noStore();

    // Cache for ten days in public and private caches,
    // public caches should not transform the response
    // "Cache-Control: max-age=864000, public, no-transform"
//    CacheControl ccCustom = CacheControl.maxAge(10, TimeUnit.DAYS)
//            .noTransform().cachePublic();

    //在响应头中设置cache eTag信息   HTTP 304 Not Modified
    @RequestMapping("/user/{name}")
    public ResponseEntity<User> showUser(@PathVariable String name){
        User user = new User();
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .eTag("version")    //这里也能操作最后修改时间lastModified
                .body(user);
    }


















}
