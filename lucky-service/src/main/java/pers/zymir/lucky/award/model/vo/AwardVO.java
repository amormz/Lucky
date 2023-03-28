package pers.zymir.lucky.award.model.vo;

import lombok.Data;
import pers.zymir.lucky.po.LuckyAward;

@Data
public class AwardVO {

  private Long id;

  private String awardName;

  public static AwardVO apply(LuckyAward award) {
    if (award == null) {
      return null;
    }

    AwardVO awardVO = new AwardVO();
    awardVO.setId(awardVO.getId());
    awardVO.setAwardName(award.getAwardName());
    return awardVO;
  }
}
