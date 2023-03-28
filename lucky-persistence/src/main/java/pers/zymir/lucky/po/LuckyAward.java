package pers.zymir.lucky.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LuckyAward extends BaseEntity{
  private Long awardId;

  private String awardName;

  private Integer awardType;
}
