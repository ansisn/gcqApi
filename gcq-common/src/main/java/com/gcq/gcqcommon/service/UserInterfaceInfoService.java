package com.gcq.gcqcommon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gcq.gcqcommon.model.entity.UserInterfaceInfo;


/* @author guochuqu
* @description 针对表【user_interface_info(接口信息)】的数据库操作Service
* @createDate 2024-12-07 12:03:05
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {


    boolean invokeCount(long interfaceInfoId, long userId);


}
