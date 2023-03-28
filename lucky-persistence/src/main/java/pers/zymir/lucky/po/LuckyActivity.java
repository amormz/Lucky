package pers.zymir.lucky.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LuckyActivity extends BaseEntity{
  private Long id;

  private Long configId;
}
