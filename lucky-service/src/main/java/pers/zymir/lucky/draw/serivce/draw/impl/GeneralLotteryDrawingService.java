package pers.zymir.lucky.draw.serivce.draw.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import pers.zymir.lucky.award.model.vo.AwardVO;
import pers.zymir.lucky.award.service.IAwardService;
import pers.zymir.lucky.config.service.IActivityConfigItemService;
import pers.zymir.lucky.draw.model.req.LotteryDrawingReq;
import pers.zymir.lucky.draw.model.res.LotteryDrawingRes;
import pers.zymir.lucky.draw.serivce.draw.AbstractLotteryDrawingService;

@Service
public class GeneralLotteryDrawingService extends AbstractLotteryDrawingService {

  @Autowired
  private IActivityConfigItemService activityConfigItemService;

  @Autowired
  private IAwardService awardService;

  @Override
  protected Set<Long> queryExcludeAwardIds(LotteryDrawingReq req, Long activityConfigId) {
    return activityConfigItemService.queryEmptyStockAwardIds(activityConfigId);
  }

  @Override
  protected Optional<Long> lotteryResPostProcess(LotteryDrawingReq req, Optional<Long> awardId) {
    return awardId;
  }

  @Override
  protected LotteryDrawingRes buildLotteryRes(LotteryDrawingReq req, Long awardId) {
    LotteryDrawingRes drawingRes = new LotteryDrawingRes(awardId);
    drawingRes.setAwardInfo(AwardVO.apply(awardService.getByAwardId(awardId)));
    return drawingRes;
  }
}
