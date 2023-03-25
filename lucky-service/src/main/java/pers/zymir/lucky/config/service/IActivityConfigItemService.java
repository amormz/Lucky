package pers.zymir.lucky.config.service;

import java.util.Set;

public interface IActivityConfigItemService {

  Set<Long> queryEmptyStockAwardIds(Long activityId);
}
