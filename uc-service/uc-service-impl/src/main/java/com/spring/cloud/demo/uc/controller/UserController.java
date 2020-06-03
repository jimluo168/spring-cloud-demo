package com.spring.cloud.demo.uc.controller;

import com.spring.cloud.demo.common.model.Result;
import com.spring.cloud.demo.uc.entity.User;
import com.spring.cloud.demo.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.cloud.demo.common.model.Result.ok;

/**
 * TODO 简要说明
 *
 * @author luojm
 * @date 2020/6/3 17:25
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("")
  public Result<User> create(@RequestBody User user) {
    userService.insert(user);
    return ok(user);
  }
}
