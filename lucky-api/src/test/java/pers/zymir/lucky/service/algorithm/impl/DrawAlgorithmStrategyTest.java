package pers.zymir.lucky.service.algorithm.impl;

import org.assertj.core.util.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pers.zymir.lucky.draw.model.dto.AwardRateDTO;
import pers.zymir.lucky.draw.serivce.algorithm.IDrawAlgorithmStrategy;
import pers.zymir.lucky.draw.serivce.algorithm.impl.ReplenishDrawAlgorithmStrategy;

public class DrawAlgorithmStrategyTest {

  @Test
  public void replenishDrawAlgorithmTest() {
    IDrawAlgorithmStrategy drawAlgorithmStrategy = new ReplenishDrawAlgorithmStrategy();

    List<AwardRateDTO> awardRates = new ArrayList<>();

    awardRates.add(new AwardRateDTO(1L, new BigDecimal("0.05")));
    awardRates.add(new AwardRateDTO(2L, new BigDecimal("0.15")));
    awardRates.add(new AwardRateDTO(3L, new BigDecimal("0.20")));
    awardRates.add(new AwardRateDTO(4L, new BigDecimal("0.25")));
    // awardRates.add(new AwardRateDTO(5L, new BigDecimal("0.35")));

    Optional<Long> awardId = drawAlgorithmStrategy.executeDrawAlgorithm(awardRates, Sets.newHashSet());
    Assert.assertTrue(awardId != null && awardId.isPresent());

    System.out.println(awardId.get());
  }
}
