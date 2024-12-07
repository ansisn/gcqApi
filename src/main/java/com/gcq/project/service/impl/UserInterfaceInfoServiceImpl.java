package com.gcq.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcq.project.model.entity.InterfaceInfo;
import com.gcq.project.model.entity.UserInterfaceInfo;
import com.gcq.project.service.InterfaceInfoService;
import com.gcq.project.service.UserInterfaceInfoService;
import com.gcq.project.mapper.UserInterfaceInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author guochuqu
* @description 针对表【user_interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-12-07 12:03:05
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private  UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        //查询接口，判断该接口是否存在
        InterfaceInfo interfaceInfoServiceById = interfaceInfoService.getById(interfaceInfoId);
        if (interfaceInfoServiceById == null) {
            throw new RuntimeException("接口不存在");
        }
        //查询接口调用表，是否已经存在，不存在则创建
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne
                (new QueryWrapper<UserInterfaceInfo>().eq("interfaceInfoId", interfaceInfoId).eq("userId", userId));
        if (userInterfaceInfo == null) {
            userInterfaceInfo = new UserInterfaceInfo();
            BeanUtils.copyProperties(interfaceInfoServiceById, userInterfaceInfo);
            userInterfaceInfo.setTotalNum(1);
            userInterfaceInfo.setLeftNum(10);
            boolean save = save(userInterfaceInfo);
            if (!save) {
                throw new RuntimeException("接口调用记录保存失败");
            }
            return save;
        }
        //查看调用次数是否超过限制
        if (userInterfaceInfo.getLeftNum() <= 0) {
              throw new RuntimeException("接口调用次数已用完");
        }
        //将接口调用次数+1
        boolean result = userInterfaceInfoMapper.updateInvokeCount(userId, interfaceInfoId);

        return result;
    }


}




