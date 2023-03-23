package pers.zymir.lucky.draw.model.dto;

import java.util.List;

import lombok.Data;
import pers.zymir.lucky.config.model.dto.ActivityConfigDTO;

@Data
public class ActivityAggregation {
  private ActivityConfigDTO activityConfig;

  private List<AwardRateDTO> awardRates;
}
