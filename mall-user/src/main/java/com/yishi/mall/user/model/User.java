package com.yishi.mall.user.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class User {
  private Integer id;

  private String mobile;

  private String password;

  private String qq;

  private String email;

  private String photoUrl;

  private String nickName;

  private Date registerDate;

  private Date createTime;

  private LocalDateTime updateTime;
}
