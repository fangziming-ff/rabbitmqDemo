package com.fangming.rabbitmqdemo.excel;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.hutool.core.util.StrUtil;


/**
 * @author Ming
 * @date 2021/10/26 15:46
 */
public class CustomerExcelDataHandler extends ExcelDataHandlerDefaultImpl<Customer> {

    @Override
    public Object exportHandler(Customer obj, String name, Object value) {
        if("客户编号".equals(name)){
            String emptyValue = "游客";
            if(value==null){
                return super.exportHandler(obj,name,emptyValue);
            }
            if(value instanceof String&& StrUtil.isBlank((String) value)){
                return super.exportHandler(obj,name,emptyValue);
            }
        }
        return super.exportHandler(obj, name, value);
    }

    @Override
    public Object importHandler(Customer obj, String name, Object value) {
        return super.importHandler(obj, name, value);
    }
}

