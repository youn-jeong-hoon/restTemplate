package com.example.demo;

public enum ResultCode {
  SUCCESS("0000", "success"),
  BIND_EXCEPTION("0400", "bind exception"),
  SYSTEM_ERROR("9999", "system error");

  String code;
  String message;

  ResultCode(final String code, final String message) {
    this.code = code;
    this.message = message;
  }
}
