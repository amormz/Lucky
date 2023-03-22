package pers.zymir.lucky.draw.serivce.algorithm;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import pers.zymir.lucky.draw.model.dto.AwardRateDTO;
import zymir.base.infrastructure.identity.Identifiable;

public interface IDrawAlgorithmStrategy extends Identifiable {

  /**
   * 执行抽奖算法
   * 抽奖算法只关心在【多个奖品之间抽奖】的算法，并不关心其他业务逻辑，如奖品库存为空、风控策略...
   * 所以只提供参数 excludeAwardIds 表示用来排除不参与本次抽奖的奖品id 由具体算法自行实现
   *
   * @param awardRates      参与抽奖奖品概率信息
   * @param excludeAwardIds 不参与本次抽奖的奖品id
   * @return 中奖返回奖品id，未中奖返回 {@link Optional#empty()}
   */
  Optional<Long> executeDrawAlgorithm(List<AwardRateDTO> awardRates,
                                      @NotNull Set<Long> excludeAwardIds);
}
