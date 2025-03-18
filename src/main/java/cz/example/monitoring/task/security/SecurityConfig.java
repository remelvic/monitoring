package cz.example.monitoring.task.security;

import cz.example.monitoring.task.filter.AuthenticationFilter;
import cz.example.monitoring.task.repository.UserRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> loggingFilter(UserRepository userRepository) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(userRepository));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
