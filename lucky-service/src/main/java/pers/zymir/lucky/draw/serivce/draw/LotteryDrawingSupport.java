package pers.zymir.lucky.draw.serivce.draw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import pers.zymir.lucky.constants.DrawAlgorithmEnum;
import pers.zymir.lucky.draw.model.dto.ActivityAggregation;
import pers.zymir.lucky.draw.serivce.algorithm.IDrawAlgorithmStrategy;
import zymir.base.infrastructure.identity.IdentityFactory;

@Component
public class LotteryDrawingSupport {

  @Autowired
  private List<IDrawAlgorithmStrategy> drawAlgorithmStrategies;

  protected Optional<IDrawAlgorithmStrategy> decisionDrawAlgorithm(DrawAlgorithmEnum drawAlgorithm) {
    if (drawAlgorithm == null) {
      return Optional.empty();
    }

    return IdentityFactory.decisionOne(drawAlgorithmStrategies, drawAlgorithm.getIdentityCode());
  }

  protected ActivityAggregation obtainActivityAggregation(Long activityId) {
    ActivityAggregation activityAggregation = new ActivityAggregation();
    // TODO 待实现
    return activityAggregation;
  }
}
