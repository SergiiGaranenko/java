package com.demo.feign.config;

import com.demo.feign.feign.DownstreamServiceApi;
import feign.Feign;
import feign.Logger.Level;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  private final int RETRY_INTERVAL = 2000;
  private final int MAX_ATTEMPTS = 3;

  public String baseUrl = "http://localhost:8088";
  @Autowired
  private ObjectFactory<HttpMessageConverters> messageConverters;

  @Bean
  public DownstreamServiceApi getFeignProviderClient() {
    return getClient(DownstreamServiceApi.class, baseUrl);
  }

  private <T> T getClient(final Class<T> clazz, final String baseUrl) {
    return Feign.builder()
        .logger(new Slf4jLogger(clazz))
        .logLevel(Level.FULL)
        .contract(new SpringMvcContract())
        .encoder(new PageableQueryEncoder(new SpringEncoder(this.messageConverters)))
        .decoder(new JacksonDecoder())
        .requestInterceptor(new CustomRequestInterceptor())
        .retryer(new Retryer.Default(RETRY_INTERVAL, TimeUnit.SECONDS.toMillis(RETRY_INTERVAL),
            MAX_ATTEMPTS))
        .target(clazz, baseUrl);
  }
}
