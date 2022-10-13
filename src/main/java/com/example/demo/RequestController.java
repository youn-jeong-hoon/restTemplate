package com.example.demo;

import com.example.demo.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/request")
@RestController
@Slf4j
public class RequestController {
  private static final String USER_URL = "http://localhost:8080/user";

  @RequestMapping("/user/add/entity")
  public CommonResponse addUserEntity() {
    RestTemplate restTemplate = new RestTemplate();

    // 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    // MultiValueMap를 사용할 경우 default값이 APPLICATION_FORM_URLENCODED
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // 파라미터 세팅
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("userId", "lim");
    map.add("userName", "임꺽정");
    map.add("age", "21");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

    ResponseEntity<CommonResponse> response = restTemplate.postForEntity(USER_URL + "/form", request, CommonResponse.class);

    return response.getBody();
  }

  @RequestMapping("/user/add")
  public CommonResponse addUserObject() {
    RestTemplate restTemplate = new RestTemplate();

    // 헤더 설정
    HttpHeaders headers = new HttpHeaders();

    // 파라미터 세팅
    UserDto user = new UserDto();
    user.setUserId("hong");
    user.setUserName("홍길동");
    user.setAge(26);

    HttpEntity<UserDto> request = new HttpEntity<>(user, headers);

    return restTemplate.postForObject(USER_URL, request, CommonResponse.class);
  }

  @RequestMapping("/user/get")
  public String get() throws JsonProcessingException {
    RestTemplate restTemplate = new RestTemplate();
    // 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<UserDto> dto = new HttpEntity<>(headers);
    log.info(dto.toString());

    return dto.toString();
  }

  @RequestMapping("/user/list")
  public List<User> list () {
    RestTemplate restTemplate = new RestTemplate();
    User[] users = restTemplate.getForObject(USER_URL, User[].class);
    if(users == null) {
      return new ArrayList<>();
    }

    return Arrays.asList(users);
  }

  @RequestMapping("/user/info")
  public UserDto info () {
    RestTemplate restTemplate = new RestTemplate();
    UserDto user = restTemplate.getForObject(USER_URL + "/{id}", UserDto.class, "hong");
    if(user == null) {
      return new UserDto();
    }

    return user;
  }

  @RequestMapping("/user/modify")
  public boolean modify() {
    RestTemplate restTemplate = new RestTemplate();

    // 헤더 설정
    HttpHeaders headers = new HttpHeaders();

    // 수정할 정보를 setting
    UserDto user = new UserDto();
    user.setUserName("홍길동2");
    user.setAge(30);

    HttpEntity<UserDto> request = new HttpEntity<>(user, headers);

    restTemplate.put(USER_URL + "/{id}", request, "hong");

    return true;
  }

  @RequestMapping("/user/del")
  public boolean del() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(USER_URL + "/{id}", "hong");
    return true;
  }
}
