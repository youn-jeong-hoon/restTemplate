package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepository;

  /**
   * ContentType에 APPLICATION_FORM_URLENCODED를 사용하지 않는 경우
   * RequestBody Annotation을 사용해야 함.
   * @param param
   * @return
   */
  @PostMapping
  public CommonResponse add(@Valid @RequestBody UserParam param) {
    User user = new User(param);
    userRepository.save(user);

    return new CommonResponse();
  }

  @PostMapping("/form")
  public CommonResponse addForm(@Valid UserParam param) {
    User user = new User(param);
    userRepository.save(user);

    return new CommonResponse();
  }

  @GetMapping("/{id}")
  public UserDto get(@PathVariable String id) throws Exception {
    User user = userRepository.findById(id).orElseThrow(() -> new Exception("사용자 정보가 없습니다."));

    return new UserDto(user);
  }

  @GetMapping
  public List<User> list() {
    return userRepository.findAll();
  }

  @PutMapping("/{id}")
  public void modify(@PathVariable String id, @RequestBody UserDto param) throws Exception {
    User user = userRepository.findById(id).orElseThrow(() -> new Exception("사용자 정보가 없습니다."));

    user.setUserName(param.getUserName());
    user.setAge(param.getAge());

    userRepository.save(user);
  }

  @DeleteMapping("/{id}")
  public void del(@PathVariable String id) {
    userRepository.deleteById(id);
  }
}