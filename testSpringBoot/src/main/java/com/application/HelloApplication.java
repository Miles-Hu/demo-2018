package com.application;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@SpringBootApplication
@EnableScheduling
public class HelloApplication {
	public static int i=0;
	 private final static Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    @RequestMapping("hello")
    @ResponseBody
    public String hello(HttpServletRequest request){
    	System.out.println(request.getRemoteAddr());
        long beginTime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {  
            logger.trace("trace level");  
            logger.debug("debug level");  
            logger.info("info level");  
            logger.warn("warn level");  
            logger.error("error level");
        }
        logger.info("请求处理结束，耗时：{}毫秒", (System.currentTimeMillis() - beginTime));    //第一种用法
        logger.info("请求处理结束，耗时：" + (System.currentTimeMillis() - beginTime)  + "毫秒");    //第二种用法
        return "hello world！";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        test();
    }
    //@Scheduled(cron = "0/5 * * * * ?")
    public static void test() {
    	i+=5;
    	System.out.println(i);
    }

}