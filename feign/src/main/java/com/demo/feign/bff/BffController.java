package com.demo.feign.bff;

import com.demo.feign.bff.model.DataRs;
import com.demo.feign.bff.model.FilterRq;
import com.demo.feign.bff.model.UpdateRq;
import com.demo.feign.bff.model.UpdateRs;
import com.demo.feign.feign.DownstreamServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BffController {

  @Autowired
  private DownstreamServiceApi downstreamServiceApi;

  @GetMapping("/getData")
  public ResponseEntity<DataRs> getData(final FilterRq filterRq) {
    final DataRs data = downstreamServiceApi.getData(filterRq);
    return ResponseEntity.ok(data);
  }

  @GetMapping("/getPaginatedData")
  public ResponseEntity<DataRs> getPaginatedData(
      final FilterRq filterRq,
      final Pageable pageable) {
    final DataRs paginatedData = downstreamServiceApi.getPaginatedData(filterRq, pageable);
    return ResponseEntity.ok(paginatedData);
  }

  @PostMapping("/some-operation")
  public ResponseEntity<UpdateRs> process(@RequestBody final UpdateRq updateRq) {
    final UpdateRs response = downstreamServiceApi.process(updateRq);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/error-case")
  public void testErrorResponse() {
    downstreamServiceApi.testErrorResponse();
  }

}
