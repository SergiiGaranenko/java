package com.demo.feign.bff.model;

import lombok.Data;

@Data
public class DataRs {

  private String name;
  private String ids;

  private Integer page;
  private Integer size;

}
