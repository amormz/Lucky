package pers.zymir.lucky.draw.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AwardRateDTO {

  /**
   * 奖品id
   */
  private Long awardId;

  /**
   * 中奖概率
   */
  private BigDecimal winRate;
}
