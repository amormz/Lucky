package pers.zymir.lucky.award.model.vo;

import lombok.Data;
import pers.zymir.lucky.po.LuckyAward;

@Data
public class AwardVO {

  private Long awardId;

  private String awardName;

  public static AwardVO apply(LuckyAward award) {
    if (award == null) {
      return null;
    }

    AwardVO awardVO = new AwardVO();
    awardVO.setAwardId(award.getAwardId());
    awardVO.setAwardName(award.getAwardName());
    return awardVO;
  }
}
