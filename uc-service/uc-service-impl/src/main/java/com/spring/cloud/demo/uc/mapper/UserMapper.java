package com.spring.cloud.demo.uc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.cloud.demo.uc.entity.User;
import org.springframework.stereotype.Repository;

/**
 * User.
 *
 * @author luojm
 * @date 2020/6/3 17:24
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
