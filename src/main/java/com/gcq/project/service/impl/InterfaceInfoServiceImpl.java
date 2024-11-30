package com.gcq.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcq.project.common.ErrorCode;
import com.gcq.project.exception.BusinessException;
import com.gcq.project.model.entity.InterfaceInfo;

import com.gcq.project.model.enums.InterfaceStatusEnum;
import com.gcq.project.service.InterfaceInfoService;
import com.gcq.project.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author guochuqu
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2024-11-15 21:13:21
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{


    @Resource
     private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String url = interfaceInfo.getUrl();
        String requestParams = interfaceInfo.getRequestParams();
        Integer status = interfaceInfo.getStatus();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, url, requestParams, method) || ObjectUtils.anyNull(status, userId)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }

        }
    }

    @Override
    public Boolean onlineInterfaceInfo(Long id) {
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
        }
        interfaceInfo.setStatus(InterfaceStatusEnum.ONLINE.getValue());
        interfaceInfoMapper.update(interfaceInfo, new QueryWrapper<InterfaceInfo>().eq("id", interfaceInfo.getId()));
        return true;
    }

    @Override
    public Boolean offlineInterfaceInfo(Long id) {
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
        }
        interfaceInfo.setStatus(InterfaceStatusEnum.OFFLINE.getValue());
        interfaceInfoMapper.update(interfaceInfo, new QueryWrapper<InterfaceInfo>().eq("id", interfaceInfo.getId()));
        return true;
    }


}




