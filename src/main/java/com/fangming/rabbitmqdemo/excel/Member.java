package com.fangming.rabbitmqdemo.excel;

/**
 * @author Ming
 * @date 2021/10/26 11:24
 */

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


import java.util.Date;

/**
 * 购物会员
 *
 * @author macro
 * @date 2021/10/12
 */
@Data
public class Member {
    @Excel(name = "ID", width = 10)
    private Long id;
    @Excel(name = "用户名", width = 20, needMerge = true)
    private String username;
    private String password;
    @Excel(name = "昵称", width = 20, needMerge = true)
    private String nickname;
    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    private Date birthday;
    @Excel(name = "手机号", width = 20, needMerge = true)
    private String phone;
    private String icon;
    @Excel(name = "性别", width = 10, replace = {"男_0", "女_1"})
    private Integer gender;
}

