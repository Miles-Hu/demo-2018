package com.miles.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;  
/**  
 * test-sc  
 * Created by Grady on 2017/4/18.  
 */  
@EnableSwagger2  
@SpringBootApplication  
//@ImportResource("classpath:/META-INF/spring/spring-dubbo-provider.xml") 
@EnableAutoConfiguration//启用自动配置  
@ComponentScan("com.miles")  
public class SwaggerApplication extends SpringBootServletInitializer {  
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(SwaggerApplication.class);  
    }  
  
    public static void main(String[] args) {  
        SpringApplication.run(SwaggerApplication.class);  
    }  
}  
