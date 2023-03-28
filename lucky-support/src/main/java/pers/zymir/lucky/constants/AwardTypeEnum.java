package pers.zymir.lucky.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zymir.base.infrastructure.identity.Identifiable;

@AllArgsConstructor
public enum AwardTypeEnum implements Identifiable {
  CDKEY(1, "兑换码"),
  ENTITY(2, "实物商品"),
  COUPON(3, "优惠券");

  @Getter
  private final int identityCode;

  private String description;
}
