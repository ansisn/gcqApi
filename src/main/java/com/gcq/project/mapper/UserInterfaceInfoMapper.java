package com.gcq.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gcq.gcqcommon.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Update;

/**
* @author guochuqu
* @description 针对表【user_interface_info(接口信息)】的数据库操作Mapper
* @createDate 2024-12-07 12:03:05
* @Entity com.gcq.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    @Update("update user_interface_info set totalNum += 1  and leftNum -= 1 where userId = #{userId}, interfaceId = #{interfaceId}")
    boolean updateInvokeCount(long userId, long interfaceId);
}




