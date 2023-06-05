package com.demo.feign.config;


import com.demo.feign.bff.model.ErrorRs;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

  private static final int BAD_REQUEST = 400;
  private static final int INTERNAL_SERVER_ERROR = 500;

  private final ErrorDecoder errorDecoder = new Default();

  @Override
  public Exception decode(final String methodKey, final Response response) {
    ErrorRs message = null;

    try (InputStream bodyIs = response.body().asInputStream()) {
      final ObjectMapper mapper = new ObjectMapper();
      message = mapper.readValue(bodyIs, ErrorRs.class);
    } catch (IOException e) {
      return new RuntimeException("UNHANDLED_DOWNSTREAM_SERVICE_ERROR");
    }

    return switch (response.status()) {
      case BAD_REQUEST -> new RuntimeException(message.getErrorCode());
      case INTERNAL_SERVER_ERROR -> new RuntimeException(message.getErrorCode());
      default -> errorDecoder.decode(methodKey, response);
    };
  }

}