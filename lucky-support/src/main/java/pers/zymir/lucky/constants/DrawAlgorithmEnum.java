package pers.zymir.lucky.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zymir.base.infrastructure.identity.Identifiable;

@AllArgsConstructor
public enum DrawAlgorithmEnum implements Identifiable {

  COMMON("普通算法", 0),

  REPLENISH("补充算法，剩余可抽奖奖品总概率补充至百分百", 1);

  private final String description;

  @Getter
  private final int identityCode;
}
