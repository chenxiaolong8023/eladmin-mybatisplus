package me.zhengjie.swagger2;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * api页面 /swagger-ui.html
 * 如controller在不同的包中，@ComponentScan(basePackages = {"me.aurora.app.rest","..."})
 * @author jie
 * @date 2018-11-23
 */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
        "me.zhengjie.rest",
        "me.zhengjie.modules.system.rest",
        "me.zhengjie.modules.monitor.rest",
        "me.zhengjie.modules.monitor.rest",
        "me.zhengjie.modules.quartz.rest"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("Bearer ")
                .required(true)
                .build();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("禾苗REST接口文档")
            .description("swagger-bootstrap-ui")
            .contact(new Contact("张俊辉","","galaxist@vip.qq.com"))
            .version("1.5")
            .build();
    }

}
