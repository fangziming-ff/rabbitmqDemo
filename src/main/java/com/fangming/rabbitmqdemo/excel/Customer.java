package com.fangming.rabbitmqdemo.excel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

/**
 * t_customer
 * @author 
 */
@Data
public class Customer implements Serializable {
    /**
     * id自增主键
     */
    @Excel(name = "ID", width = 20, needMerge = true)
    private Integer id;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号", width = 20, needMerge = true)
    private String customerId;

    /**
     * 商店id
     */
    @Excel(name = "商店ID", width = 20, needMerge = true)
    private Integer shopId;

    /**
     * 名
     */
    @Excel(name = "名", width = 20, needMerge = true)
    private String firstName;

    /**
     * 姓
     */
    @Excel(name = "姓", width = 20, needMerge = true)
    private String lastName;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", width = 30, needMerge = true)
    private String email;

    /**
     * 所在国家
     */
    @Excel(name = "所在国家", width = 30, needMerge = true)
    private String country;

    /**
     * 手机国家区号
     */
    @Excel(name = "手机国家区号", width = 20, needMerge = true)
    private String countryCode;

    /**
     * 手机号
     */
    @Excel(name = "手机号", width = 20, needMerge = true)
    private String mobile;

    /**
     * 注册时间
     */
    @Excel(name = "注册时间", width = 20, format = "yyyy-MM-dd hh:mm:ss",needMerge = true)
    private Date registryTime;

    /**
     * 是否订阅
     */
    @Excel(name = "是否订阅", width = 20, needMerge = true)
    private Boolean subscribed;

    /**
     * 用户头像
     */
    @Excel(name = "用户头像", width = 20, needMerge = true)
    private String customerLogo;

    /**
     * 标签
     */
    @Excel(name = "标签", width = 20, needMerge = true)
    private String tag;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd hh:mm:ss",needMerge = true)
    private Date createAt;

    /**
     * 创建人
     */
    @Excel(name = "创建人", width = 20, needMerge = true)
    private String createBy;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", width = 20, format = "yyyy-MM-dd hh:mm:ss",needMerge = true)
    private Date updateAt;

    /**
     * 更新人
     */
    @Excel(name = "更新人", width = 20, needMerge = true)
    private String updateBy;

    @ExcelCollection(name = "客户姓名")
    private List<CustomerName> customerNames;


}