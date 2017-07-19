package cn.com.njit.wd.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangdi on 2017/3/17.
 *
 */

@SpringBootApplication
@ImportResource({"classpath:dubbo-provider.xml"})
public class DubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class);
        //阻塞启动线程
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
