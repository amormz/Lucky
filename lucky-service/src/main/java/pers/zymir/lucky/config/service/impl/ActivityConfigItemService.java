package pers.zymir.lucky.config.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import pers.zymir.lucky.config.service.IActivityConfigItemService;
import pers.zymir.lucky.mapper.ActivityConfigItemMapper;

@Service
public class ActivityConfigItemService implements IActivityConfigItemService {

  @Autowired
  ActivityConfigItemMapper activityConfigItemMapper;

  @Override
  public Set<Long> queryEmptyStockAwardIds(Long activityId) {
    return activityConfigItemMapper.queryEmptyStockAwardIds(activityId);
  }
}
