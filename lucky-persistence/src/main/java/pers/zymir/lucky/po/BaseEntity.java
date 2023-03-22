package pers.zymir.lucky.po;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseEntity {
  protected boolean deleted;

  protected LocalDateTime createTime;

  protected LocalDateTime updateTime;
}
