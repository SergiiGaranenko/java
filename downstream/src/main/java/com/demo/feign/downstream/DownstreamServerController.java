package com.demo.feign.downstream;

import com.demo.feign.downstream.model.DataRs;
import com.demo.feign.downstream.model.FilterRq;
import com.demo.feign.downstream.model.UpdateRq;
import com.demo.feign.downstream.model.UpdateRs;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownstreamServerController {

  @GetMapping(value = "/downstream/data", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DataRs> getData(final FilterRq filterRq) {
    //produce response
    final DataRs dataRs = new DataRs();
    dataRs.setName(filterRq.getName() + " - processed by DOWNSTREAM SERVICE(GET request)");
    dataRs.setIds(filterRq.getDataIds().toString()
        + " - List of ids processed by DOWNSTREAM SERVICE(GET request)");

    return ResponseEntity.ok(dataRs);
  }

  @GetMapping(value = "/downstream/pageable-data", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DataRs> getPaginatedData(
      final FilterRq filterRq,
      final Pageable pageable) {
    //produce response
    final DataRs dataRs = new DataRs();
    dataRs.setName(filterRq.getName() + " - processed by DOWNSTREAM SERVICE(GET request)");
    dataRs.setIds(filterRq.getDataIds().toString()
        + " - List of ids processed by DOWNSTREAM SERVICE(GET request)");
    dataRs.setPage(pageable.getPageNumber());
    dataRs.setSize(pageable.getPageSize());

    return ResponseEntity.ok(dataRs);
  }

  @PostMapping(value = "/downstream/some-operation", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateRs> process(@RequestBody final UpdateRq updateRq) {
    //produce response
    UpdateRs rs = new UpdateRs();
    rs.setField1(updateRq.getField1() + " UPDATED");
    rs.setField2(updateRq.getField2() + " UPDATED");

    return ResponseEntity.ok(rs);
  }

  @GetMapping("/downstream/error")
  public void throwCustomError() {
    throw new RuntimeException("01.02.14");
  }

}
