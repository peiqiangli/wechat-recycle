package com.wechat.recycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableTransactionManagement开启事务管理
//@MapperScan("com.wechat.recycle.mapper")
//@EnableCaching开启缓存
@SpringBootApplication
public class RecycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecycleApplication.class, args);
    }

}

