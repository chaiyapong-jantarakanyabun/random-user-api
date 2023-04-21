package th.co.the1.randomuserapi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@Configuration
class SwaggerConfiguration {


    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("th.co.the1.randomuserapi"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun getApiInfo(): ApiInfo {
        val contact = Contact("Chaiyapong Jantarakanyabun", "http://localhost", "chaiyapong.jantarakanyabun@gmail.com")
        return ApiInfoBuilder()
            .title("Random User API")
            .description("Simple project using SpringBoot to get random user from Random User API.")
            .version("1.0.0")
            .contact(contact)
            .build()
    }

}