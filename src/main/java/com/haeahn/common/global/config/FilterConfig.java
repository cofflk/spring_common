package com.haeahn.common.global.config;

import com.haeahn.common.global.filter.AuthTokenFilter;
import com.haeahn.common.global.filter.CustomHeaderFilter;
import com.haeahn.common.global.filter.MdcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomHeaderFilter> customHeaderFilterRegistration(CustomHeaderFilter customHeaderFilter) {
        FilterRegistrationBean<CustomHeaderFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(customHeaderFilter);
        registration.addUrlPatterns("/*");
        registration.setName("customHeaderFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<MdcFilter> mdcFilterRegistration() {
        FilterRegistrationBean<MdcFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MdcFilter());
        registration.addUrlPatterns("/*");
        registration.setName("mdcFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 20); // CustomHeaderFilter 다음에 실행
        return registration;
    }

    @Bean
    public FilterRegistrationBean<AuthTokenFilter> authTokenFilterRegistration(AuthTokenFilter authTokenFilter) {
        FilterRegistrationBean<AuthTokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(authTokenFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authTokenFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 30); // MdcFilter 다음에 실행
        return registration;
    }

}