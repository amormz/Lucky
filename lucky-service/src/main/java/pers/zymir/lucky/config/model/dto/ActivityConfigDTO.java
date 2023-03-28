package pers.zymir.lucky.config.model.dto;

import lombok.Data;
import pers.zymir.lucky.constants.DrawAlgorithmEnum;

@Data
public class ActivityConfigDTO {
  private Long activityConfigId;

  private DrawAlgorithmEnum drawAlgorithm;
}
