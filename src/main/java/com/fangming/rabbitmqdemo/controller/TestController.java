package com.fangming.rabbitmqdemo.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.fangming.rabbitmqdemo.async.AsyncTask;
import com.fangming.rabbitmqdemo.excel.Customer;
import com.fangming.rabbitmqdemo.excel.CustomerExcelDataHandler;
import com.fangming.rabbitmqdemo.excel.LocalJsonUtil;
import com.fangming.rabbitmqdemo.excel.Member;
import com.fangming.rabbitmqdemo.rabbitmq.DelaySender;
import com.fangming.rabbitmqdemo.aop.GuavaRateLimiterUtils;
import com.fangming.rabbitmqdemo.aop.RateAllow;
import com.fangming.rabbitmqdemo.rabbitmq.Result;
import com.fangming.rabbitmqdemo.pojo.Order;
import com.fangming.rabbitmqdemo.repository.CustomerMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * @author DELL
 */
@RestController
@Api(value = "测试")
@Slf4j
public class TestController {
    @Autowired
    private DelaySender delaySender;
    private final CustomerMapper customerMapper;

    public TestController(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @GetMapping("/sendDelay/{orderId}/{orderName}/{orderStatus}")
    public Object sendDelay(@PathVariable("orderId") String orderId, @PathVariable("orderName") String orderName, @PathVariable("orderStatus") Integer orderStatus) {
        Order order1 = new Order();
        order1.setOrderStatus(orderStatus);
        order1.setOrderId(orderId);
        order1.setOrderName(orderName);
        delaySender.sendDelay(order1);
        return "test--ok";
    }

    @Autowired
    private GuavaRateLimiterUtils rateLimiterService;

    @ResponseBody
    @RateAllow
    @PostMapping("/ratelimiter")
    @ApiOperation(value = "测试限流aop")
    public Result testRateLimiter() {
/*        if(rateLimiterService.tryAcquire()){
            System.out.println("成功");
            return new Result(true);
        }
        System.out.println("失败");
        return new Result(false);
    }*/
        return new Result(true);
    }


    @ApiOperation(value = "导出会员列表Excel")
    @RequestMapping(value = "/exportCustomerList", method = RequestMethod.GET)
    public void exportMemberList(ModelMap map,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        List<Customer> customerList = customerMapper.selectByPrimaryKey(333);
        CustomerExcelDataHandler handler = new CustomerExcelDataHandler();
        handler.setNeedHandlerFields(new String[]{"客户编号"});
        ExportParams params = new ExportParams("客户列表", "客户列表", ExcelType.XSSF);
        //自定义过滤
        params.setDataHandler(handler);
        //去掉不需要的字段
        params.setExclusions(new String[]{"商店ID", "头像", "手机国家区号"});
        map.put(NormalExcelConstants.DATA_LIST, customerList);
        map.put(NormalExcelConstants.CLASS, Customer.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "memberList");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @ApiOperation("从Excel导入会员列表")
    @RequestMapping(value = "/importCustomerList", method = RequestMethod.POST)
    @ResponseBody
    public Result importMemberList(@RequestPart("file") MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        try {
            List<Customer> list = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    Customer.class, params);
            log.info("list:{}",list);
            return new Result(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false);
        }
    }


    @Autowired
    private AsyncTask asyncTasks;

    @RequestMapping(value = "/async", method = RequestMethod.POST)
    @ResponseBody
    @RateAllow
    public void asyncTest() throws Exception {

        long start = System.currentTimeMillis();

        CompletableFuture<String> task1 = asyncTasks.doTaskOne();
        CompletableFuture<String> task2 = asyncTasks.doTaskTwo();
        CompletableFuture<String> task3 = asyncTasks.doTaskThree();

        CompletableFuture.allOf(task1, task2, task3).join();

        long end = System.currentTimeMillis();

        log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }


}

