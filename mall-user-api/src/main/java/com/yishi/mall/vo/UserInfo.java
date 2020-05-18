package com.yishi.mall.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class UserInfo implements Serializable {

  private static final long serialVersionUID = 7446076647867050376L;
  private Integer id;

  private String mobile;

  private String password;

  private String qq;

  private String email;

  private String photoUrl;

  private String nickName;

  private Integer state;

  private Date registerDate;

  private Date createTime;

  private LocalDateTime updateTime;
}
