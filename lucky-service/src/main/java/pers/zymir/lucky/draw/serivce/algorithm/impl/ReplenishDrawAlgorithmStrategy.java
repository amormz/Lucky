package pers.zymir.lucky.draw.serivce.algorithm.impl;

import com.sun.istack.internal.NotNull;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import pers.zymir.lucky.constants.DrawAlgorithmEnum;
import pers.zymir.lucky.draw.model.dto.AwardRateDTO;
import pers.zymir.lucky.draw.serivce.algorithm.IDrawAlgorithmStrategy;

@Component
public class ReplenishDrawAlgorithmStrategy implements IDrawAlgorithmStrategy {

  @Override
  public Optional<Long> executeDrawAlgorithm(List<AwardRateDTO> awardRates,
                                             @NotNull Set<Long> excludeAwardIds) {

    // 当前可参与抽奖奖品
    List<AwardRateDTO> participatoryAwardRates = awardRates.stream()
        .filter(rate -> !excludeAwardIds.contains(rate.getAwardId()))
        .collect(Collectors.toList());

    // 当前可中奖总概率
    BigDecimal totalRate = calculateTotalRate(participatoryAwardRates);

    // 抽奖种子
    int drawSeed = RandomUtil.getSecureRandom().nextInt(100) + 1;

    int point = 0;
    for (AwardRateDTO awardRate : participatoryAwardRates) {
      int rateVal = generateRateVal(awardRate, totalRate);
      System.out.println("当前概率值为：" + rateVal);
      if (drawSeed <= (point + rateVal)) {
        return Optional.of(awardRate.getAwardId());
      }

      point += rateVal;
    }

    return Optional.empty();
  }

  private int generateRateVal(AwardRateDTO awardRate, BigDecimal totalRate) {
    return awardRate.getWinRate().divide(totalRate, 2, RoundingMode.UP).multiply(new BigDecimal(100)).intValue();
  }

  private BigDecimal calculateTotalRate(List<AwardRateDTO> participatoryAwardRates) {
    if (CollUtil.isEmpty(participatoryAwardRates)) {
      return BigDecimal.ZERO;
    }

    return participatoryAwardRates.stream()
        .map(AwardRateDTO::getWinRate)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Override
  public int getIdentityCode() {
    return DrawAlgorithmEnum.REPLENISH.getIdentityCode();
  }
}
