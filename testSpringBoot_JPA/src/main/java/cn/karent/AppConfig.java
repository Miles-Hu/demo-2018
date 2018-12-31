package cn.karent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by wan on 2017/1/17.
 */
@EnableSwagger2 
@SpringBootApplication
@EnableJpaRepositories(basePackages = "cn.karent.repository")
@EntityScan(basePackages = "cn.karent")
public class AppConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}