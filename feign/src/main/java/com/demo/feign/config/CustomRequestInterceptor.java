package com.demo.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.MediaType;

public class CustomRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(final RequestTemplate requestTemplate) {
    requestTemplate.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    requestTemplate.header("Authorization", getToken());
    requestTemplate.header("Accept", MediaType.APPLICATION_JSON_VALUE);
  }

  public String getToken() {
    return "some-security-token";
  }
}

