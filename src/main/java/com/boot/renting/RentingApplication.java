package com.boot.renting;

import com.didispace.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableSwagger2Doc
@SpringBootApplication(scanBasePackages = {"com.boot.renting"})
@MapperScan("com.boot.renting.mapper")
public class RentingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentingApplication.class, args);
    }

}
