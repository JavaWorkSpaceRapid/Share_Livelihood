package share.livelihood.boot;

import lycan.springboot.BootServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import share.mybatis.config.MyBatisConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Lycanthrope_Cheng
 * @Date 2019/4/23 10:16
 */
@ComponentScan(basePackages = {
        "share"
})
@SpringBootApplication
@EnableSwagger2
@Configuration
@MapperScan(basePackages = {"livelihood.*.infrastructure.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
@AutoConfigureAfter(MyBatisConfig.class)
public class LivelihoodBoot
{
    public static void main(String[] args) throws Exception
    {
        BootServer.start(LivelihoodBoot.class, args);
    }
}
