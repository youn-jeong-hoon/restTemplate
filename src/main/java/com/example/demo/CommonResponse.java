package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
  private String code;

  public CommonResponse() {
    this.code = ResultCode.SUCCESS.code;
  }

  public CommonResponse(ResultCode resultCode) {
    this.code = resultCode.code;
  }
}
