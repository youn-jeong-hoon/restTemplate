package com.example.demo;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private String userId;
  private String userName;
  private Integer age;

  public UserDto(User user) {
    this.userId = user.getUserId();
    this.userName = user.getUserName();
    this.age = user.getAge();
  }
}
