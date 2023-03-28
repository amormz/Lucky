package pers.zymir.lucky.draw.model.res;

import lombok.Data;
import pers.zymir.lucky.award.model.vo.AwardVO;

@Data
public class LotteryDrawingRes {
  private Long awardId;

  private AwardVO awardInfo;

  /**
   * 是否中奖
   * @return 是否中奖
   */
  public boolean isLottery() {
    return awardId != null && awardId > 0;
  }

  public LotteryDrawingRes() {

  }

  public LotteryDrawingRes(Long awardId) {
    this.awardId = awardId;
  }
}
