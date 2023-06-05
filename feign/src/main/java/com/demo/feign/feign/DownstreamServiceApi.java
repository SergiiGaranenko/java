package com.demo.feign.feign;

import com.demo.feign.bff.model.DataRs;
import com.demo.feign.bff.model.FilterRq;
import com.demo.feign.bff.model.UpdateRq;
import com.demo.feign.bff.model.UpdateRs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public interface DownstreamServiceApi {

  @GetMapping("/downstream/data")
  DataRs getData(@SpringQueryMap FilterRq filter);

  @GetMapping("/downstream/pageable-data")
  DataRs getPaginatedData(@SpringQueryMap FilterRq filter, Pageable pageable);

  @PostMapping("/downstream/some-operation")
  UpdateRs process(UpdateRq updateRq);

  @GetMapping("/downstream/error")
  public void testErrorResponse();
}
