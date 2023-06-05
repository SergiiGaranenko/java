package com.demo.feign.downstream.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class FilterRq {

  private String name;
  private List<String> dataIds = new ArrayList<>();

}
