package com.fangming.rabbitmqdemo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author Ming
 * @date 2021/10/26 15:24
 */
@Data
public class CustomerName {

    @Excel(name = "名称ID",width = 20,needMerge = true)
    private Integer id;


    @Excel(name = "名称",width = 20,needMerge = true)
    private String name;
}
