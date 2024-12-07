package com.gcq.project.service;

import com.gcq.project.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author guochuqu
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2024-11-15 21:13:21
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验接口信息
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    Boolean onlineInterfaceInfo(Long id);

    Boolean offlineInterfaceInfo(Long id);

    Object invokeInterfaceInfo(Long id, String requestParams, HttpServletRequest request);
}
