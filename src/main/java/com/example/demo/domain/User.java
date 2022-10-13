package com.example.demo.domain;

import com.example.demo.UserParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User {
  @Id
  private String userId;
  private String userName;
  private Integer age;

  public User(UserParam param) {
    this.userId = param.getUserId();
    this.userName = param.getUserName();
    this.age = param.getAge();
  }
}
