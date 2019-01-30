package com.life.game.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 使用Swagger展现Api文档
 * 在createRestApi设置Api中方法所在的包
 * 在apiInfo设置Api文档基本信息
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${server.context-path}")
    private String contextPath;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathProvider(pathProvider())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.newland.bomc"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BOMC RESTful APIs")
                .description("BOMC 微服务API文档")
                .contact("BOMC")
                .version("1.0-SNAPSHOT")
                .build();
    }

    private PathProvider pathProvider() {
        return new PathProvider() {


            /*根路径：默认就是contextPath，但是在网关因为ui服务的页面上的连接问题而在网关加入了serviceIdFilter后，需要加入../来往上追溯一层*/
            public String getApplicationBasePath() {
                return "/.." + contextPath;
            }

            /*具体服务的地址:controller里@RequestMapping对应的地址*/
            public String getOperationPath(String operationPath) {
                return operationPath;
            }

            /*  */
            public String getResourceListingPath(String s, String controllerName) {
                return null;
            }
        };
    }
}


