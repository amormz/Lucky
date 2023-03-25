package pers.zymir.lucky.draw.model.res;

import lombok.Data;

@Data
public class LotteryDrawingRes {
  private Long awardId;

  // TODO 抽奖结果填充奖品信息等

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
