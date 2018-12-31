package com.miles.controller;

import io.swagger.annotations.ApiOperation;  
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.RestController;  
  
/**  
 * Created by dell16 on 2017/4/18.    
 */  
@RequestMapping("/orderNew")  
@RestController  
public class SwaggerController {  
  
    @ApiOperation(value = "get获取某条订单信息")  
    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)  
    public String getOrderByGet(@ApiParam(value = "订单编号",required = true) @RequestParam(value = "orderNo") String orderNo,  
                              @ApiParam(value = "当前页") @RequestParam(value = "pageNum",required = false) Integer pageNum,  
                              @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize",required = false) Integer pageSize,
                              HttpServletRequest request) {  
        if(null != orderNo && !"".equals(orderNo) && null != pageNum && null != pageSize) {
        	return "订单编号: "+orderNo+" 当前页: "+pageNum+" 每页显示数量"+pageSize;
        }else {
        	System.out.println(request.getParameter("pageNum"));
        	return "请补全参数";
        }
    }  
    @ApiOperation(value = "post获取某条订单信息")  
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST)  
    public String getOrderByPost(@ApiParam(value = "订单编号",required = true) @RequestParam(value = "orderNo") String orderNo,  
    		@ApiParam(value = "当前页") @RequestParam(value = "pageNum",required = false) Integer pageNum,  
    		@ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize",required = false) Integer pageSize,
    		HttpServletRequest request) {  
    	if(null != orderNo && !"".equals(orderNo) && null != pageNum && null != pageSize) {
    		return "订单编号: "+orderNo+" 当前页: "+pageNum+" 每页显示数量"+pageSize;
    	}else {
    		System.out.println(request.getParameter("pageNum"));
    		return "请补全参数";
    	}
    }  
}  