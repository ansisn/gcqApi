package com.gcq.gcqclient;

import com.gcq.gcqclientsdk.client.GcqClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class GcqClientApplicationTest {
    @Resource
    private GcqClient gcqClient;

    @Test
    public void contextLoads() {

    }
}
