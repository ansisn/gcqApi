package com.gcq.gcqcommon.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口信息
 * @TableName user_interface_info
 */
@TableName(value ="user_interface_info")
@Data
public class UserInterfaceInfo implements Serializable {
    /**
     * primary key
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 调用用户id
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * id
     */
    @TableField(value = "interfaceInfoId")
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    @TableField(value = "totalNum")
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    @TableField(value = "leftNum")
    private Integer leftNum;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除(0-未删除，1-已删)
     */
    @TableField(value = "isDelete")
    private Integer isDelete;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 接口地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 请求参数
     */
    @TableField(value = "requestParams")
    private String requestParams;

    /**
     * 请求头
     */
    @TableField(value = "requestHeader")
    private String requestHeader;

    /**
     * 响应头
     */
    @TableField(value = "responseHeader")
    private String responseHeader;

    /**
     * 请求类型
     */
    @TableField(value = "method")
    private String method;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}