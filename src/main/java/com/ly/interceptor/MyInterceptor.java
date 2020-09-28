package com.ly.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * @Description
 * @Created by Administrator
 * @Date 2020/9/23 17:37
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    private int openTime;
    private int closeTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (openTime <= hour && hour <=closeTime){
            return true;
        }
        response.sendRedirect("http://host.com/outsideOfficeHours.html");
        return false;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
}
