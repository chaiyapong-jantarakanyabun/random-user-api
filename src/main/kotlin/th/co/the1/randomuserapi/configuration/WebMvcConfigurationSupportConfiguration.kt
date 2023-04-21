package th.co.the1.randomuserapi.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport


@Configuration
class WebMvcConfigurationSupportConfiguration : WebMvcConfigurationSupport() {

    public override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/configuration/ui", "/swagger-resources/configuration/ui")
    }

    public override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
    
}