package vip.bigeye.order;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @Author wolf  VX:a815193474
 * @Date 2019-09-03 13:40
 */
public class Start extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //Application的类名
        return application.sources(OrderApplication.class);
    }
}
