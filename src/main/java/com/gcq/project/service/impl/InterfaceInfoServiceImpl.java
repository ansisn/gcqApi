package com.gcq.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcq.gcqclientsdk.client.GcqClient;
import com.gcq.gcqclientsdk.model.UserLogin;
import com.gcq.gcqcommon.common.ErrorCode;
import com.gcq.gcqcommon.model.entity.InterfaceInfo;

import com.gcq.gcqcommon.model.entity.User;
import com.gcq.gcqcommon.model.enums.InterfaceStatusEnum;
import com.gcq.project.exception.BusinessException;

import com.gcq.project.service.InterfaceInfoService;
import com.gcq.project.mapper.InterfaceInfoMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Resource
    private UserServiceImpl userService;

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

    @Override
    public Object invokeInterfaceInfo(Long id, String requestParams, HttpServletRequest request) {
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口不存在");
        }
        if (InterfaceStatusEnum.OFFLINE.getValue() == (interfaceInfo.getStatus())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已下线");
        }

        User loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        GcqClient gcqClient = new GcqClient(accessKey,secretKey);
        Gson gson = new Gson();
        UserLogin userLogin = gson.fromJson(requestParams, UserLogin.class);
        String s = gcqClient.postByUserName(userLogin);
        return s;
    }


}




