package com.uramens.config;

import com.uramens.user.presentation.interceptor.AuthenticationInterceptor;
import com.uramens.user.presentation.resolver.RequireLoginUserArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final AuthenticationInterceptor authenticationInterceptor;
  private final RequireLoginUserArgumentResolver requireLoginUserArgumentResolver;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authenticationInterceptor);
//        .addPathPatterns("/users/**", "/myReceipe/**", "/addRecipe/**")
//        .excludePathPatterns("/users/register", "/users/login");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(requireLoginUserArgumentResolver);
  }
}
