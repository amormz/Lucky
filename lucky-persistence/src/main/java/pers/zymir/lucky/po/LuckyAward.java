package pers.zymir.lucky.po;

import lombok.Data;

@Data
public class LuckyAward extends BaseEntity{
  private Long awardId;

  private String awardName;

  private Integer awardType;
}
