package com.gcq.gcqclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.gcq.gcqclientsdk.model.UserLogin;
import com.gcq.gcqclientsdk.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

public class GcqClient {

    private  String accessKey;

    private String secretKey;


    public GcqClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }




    public String getName(){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "柳欢");
        String result3= HttpUtil.get("http://localhost:8123/api/name/", paramMap);
        return result3;
    }
    public String postName(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result3= HttpUtil.post("http://localhost:8123/api/name/", paramMap);
        return result3;
    }

    public String postByUserName(UserLogin userLogin){
        userLogin.setUsername(userLogin.getUsername());
        String json = JSONUtil.toJsonStr(userLogin);
        String result2 = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeader(userLogin.getUsername()))
                .body(json)
                .execute().body();
        return result2;
    }

    private Map<String, String> getHeader(String body){
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("body", body);
        paramMap.put("accessKey", accessKey);
        paramMap.put("sign", SignUtil.genSign(body,secretKey));
        return paramMap;
    }


}
