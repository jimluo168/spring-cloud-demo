package com.spring.cloud.demo.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统用户.
 *
 * @author luojm
 * @date 2020/6/3 17:18
 */
@TableName("sys_user")
@Data
public class User {
  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;
  @TableField(value = "name" )
  private String name;
}
