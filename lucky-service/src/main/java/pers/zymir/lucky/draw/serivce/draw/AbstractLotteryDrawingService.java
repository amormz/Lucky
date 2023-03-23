package pers.zymir.lucky.draw.serivce.draw;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import pers.zymir.lucky.config.model.dto.ActivityConfigDTO;
import pers.zymir.lucky.draw.model.dto.ActivityAggregation;
import pers.zymir.lucky.draw.model.req.LotteryDrawingReq;
import pers.zymir.lucky.draw.model.res.LotteryDrawingRes;
import pers.zymir.lucky.draw.serivce.algorithm.IDrawAlgorithmStrategy;

@Slf4j
@Service
public abstract class AbstractLotteryDrawingService extends LotteryDrawingSupport implements ILotteryDrawingService  {

  @Override
  public LotteryDrawingRes drawing(LotteryDrawingReq req) {
    LotteryDrawingRes drawingRes = new LotteryDrawingRes();

    ActivityAggregation activityAggregation = super.obtainActivityAggregation(req.getActivityId());
    if (activityAggregation == null) {
      log.warn("抽奖失败，活动不存在！活动ID:{}", req.getActivityId());
      return drawingRes;
    }

    ActivityConfigDTO activityConfig = activityAggregation.getActivityConfig();

    // 选择对应抽奖算法
    Optional<IDrawAlgorithmStrategy> drawAlgorithm = super.decisionDrawAlgorithm(activityConfig.getDrawAlgorithm());
    if (!drawAlgorithm.isPresent()) {
      log.warn("抽奖失败，没有找到对应抽奖算法实现！算法类型:{}", activityConfig.getDrawAlgorithm());
      return drawingRes;
    }

    // 执行抽奖算法
    Set<Long> excludeAwardIds = queryExcludeAwardIds(activityConfig.getId());
    Optional<Long> awardId = drawAlgorithm.get().executeDrawAlgorithm(activityAggregation.getAwardRates(), excludeAwardIds);
    
    // 抽奖结果后置处理
    Optional<Long> finallyAwardId = lotteryResPostProcess(req, awardId);

    // 构建中奖结果
    if (finallyAwardId.isPresent()) {
      return buildLotteryRes(req, finallyAwardId.get());
    }

    return drawingRes;
  }

  abstract Set<Long> queryExcludeAwardIds(Long activityConfigId);

  abstract LotteryDrawingRes buildLotteryRes(LotteryDrawingReq req, Long awardId);
  
  abstract Optional<Long> lotteryResPostProcess(LotteryDrawingReq req, Optional<Long> awardId);
}
