package com.demo.feign.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import java.lang.reflect.Type;
import org.springframework.data.domain.Pageable;

public class PageableQueryEncoder implements Encoder {

  private final Encoder delegate;

  PageableQueryEncoder(final Encoder delegate) {
    this.delegate = delegate;
  }

  @Override
  public void encode(final Object object, final Type bodyType, final RequestTemplate template)
      throws EncodeException {
    if (object instanceof Pageable pageable) {
      template.query("page", pageable.getPageNumber() + "");
      template.query("size", pageable.getPageSize() + "");
    } else {
      this.delegate.encode(object, bodyType, template);
    }
  }
}
