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
public abstract class AbstractLotteryDrawingService extends LotteryDrawingSupport implements ILotteryDrawingService {

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
    Optional<IDrawAlgorithmStrategy> drawAlgorithm =
        super.decisionDrawAlgorithm(activityConfig.getDrawAlgorithm());
    if (!drawAlgorithm.isPresent()) {
      log.warn("抽奖失败，没有找到对应抽奖算法实现！算法类型:{}", activityConfig.getDrawAlgorithm());
      return drawingRes;
    }

    // 执行抽奖算法
    Set<Long> excludeAwardIds = queryExcludeAwardIds(req, activityConfig.getId());
    Optional<Long> awardId =
        drawAlgorithm.get().executeDrawAlgorithm(activityAggregation.getAwardRates(), excludeAwardIds);
    log.info("执行抽奖算法完成, 活动ID:{}, 算法类型:{}, 中奖奖品Id:{}",
        req.getActivityId(), activityConfig.getDrawAlgorithm(), awardId.orElse(null));

    // 抽奖结果后置处理
    Optional<Long> finallyAwardId = lotteryResPostProcess(req, awardId);

    // 构建中奖结果
    if (finallyAwardId.isPresent()) {
      return buildLotteryRes(req, finallyAwardId.get());
    }

    return drawingRes;
  }

  /**
   * 获取本次不参与抽奖的awardId
   * @param req 抽奖请求
   * @param activityConfigId 活动配置Id
   * @return 不参与本次抽奖的awardIds
   */
  protected abstract Set<Long> queryExcludeAwardIds(LotteryDrawingReq req, Long activityConfigId);

  /**
   * 构建中奖结果
   * @param req 抽奖请求
   * @param awardId 中奖奖品Id
   * @return 中奖结果
   */
  protected abstract LotteryDrawingRes buildLotteryRes(LotteryDrawingReq req, Long awardId);

  /**
   * 执行抽奖算法后置处理器：例 未中奖可发放保底奖品
   * @param req 抽奖请求
   * @param awardId 当前中奖奖品Id
   * @return 处理后的中奖奖品Id
   */
  protected abstract Optional<Long> lotteryResPostProcess(LotteryDrawingReq req, Optional<Long> awardId);
}
