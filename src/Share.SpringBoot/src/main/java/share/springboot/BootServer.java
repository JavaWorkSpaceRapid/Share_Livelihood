package share.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import share.base.SystemHelper;
import share.base.extensions.StringExtension;
import share.base.token.TokenContext;
import share.logging.log4j.Log4j;

/**
 * Created by SunDawei on 2019/6/19.
 * 后期做拓展封装
 */
@ComponentScan({"livelihood", "lycan"})
@Configuration
@SpringBootApplication
public class BootServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootServer.class);
    private static String service_port;

    @Value("${server.port}")
    public void setService_port(String service_port) {
        this.service_port = service_port;
    }

    public static void start(Class clazz, String[] args) throws InterruptedException {
        String servicePort = SystemHelper.getProperty("server.port");
        if (!StringExtension.isNullOrWhiteSpace(servicePort)) {
            service_port = servicePort;
        }
        start(clazz, args, service_port);
    }

    public static void start(Class clazz, String[] args, String servicePort) {
        service_port = SystemHelper.getProperty("server.port");
        Log4j.setConsoleLog();
        Log4j.setFileLog();
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<System Init Begin>>>>>>>>>>>>>>>>>>>>>>");
        SpringApplication.run(clazz, args);
        LOGGER.info(String.format("http://" + IpAddress.getIpAddress() + ":" + servicePort + "/swagger-ui.html#/"));
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<System Init End>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Bean
    public TokenContext tokenContext() {
        return new TokenContext();
    }
}
