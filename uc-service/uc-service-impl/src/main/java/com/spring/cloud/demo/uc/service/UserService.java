package com.spring.cloud.demo.uc.service;

import com.spring.cloud.demo.uc.entity.User;
import com.spring.cloud.demo.uc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User.
 *
 * @author luojm
 * @date 2020/6/3 17:27
 */
@Service
public class UserService {
  @Autowired
  private UserMapper userMapper;

  @Transactional(rollbackFor = Exception.class)
  public void insert(User user) {
    userMapper.insert(user);
    throw new RuntimeException("rollback");
  }
}
