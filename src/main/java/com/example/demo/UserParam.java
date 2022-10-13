package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserParam {
  private String userId;

  @NotNull(message = "{test.notnull.message}")
  private String userName;

  @Min(0)
  @Max(100)
  private int age;
}
