package com.gcq.gcqclientsdk.utils;


import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {

    public  static  String genSign(String body,String secretKey){
        Digester digester = new Digester(DigestAlgorithm.SHA384);
        String content = body + secretKey  + ".";
        return digester.digestHex(content);
    }

}