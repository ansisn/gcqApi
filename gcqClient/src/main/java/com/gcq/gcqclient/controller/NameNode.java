package com.gcq.gcqclient.controller;

import com.gcq.gcqclientsdk.model.UserLogin;
import com.gcq.gcqclientsdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class  NameNode {


    @PostMapping("/")
    public String NameByPost(@RequestParam String name, HttpServletRequest servletRequest){


        return "Post请求 = " + name;
    }

    @GetMapping("/")
    public String NameByGet( String name){
        return "Get请求 = " + name;
    }


    @PostMapping("/user")
    public String NameByPost(@RequestBody UserLogin userLogin, HttpServletRequest servletRequest){
        String body = servletRequest.getHeader("body");
        String sign = servletRequest.getHeader("sign");
        String accessKey = servletRequest.getHeader("accessKey");
        if (accessKey == null || !accessKey.equals("123")){
            //无效，不再处理业务信息，返回失败
            System.out.println("无效");
            return null;
        }

        //secretKey 从数据库拿出来
        String s = SignUtil.genSign(body, "123");
        //加几个判断，判断是否合法

        if(!s.equals(sign)){
            //无效，不再处理业务信息，返回失败
            System.out.println("无效");
            return null;
        }
        System.out.println("有效请求，继续处理...");
        return "xxx" + userLogin.getUsername();
    }



}
