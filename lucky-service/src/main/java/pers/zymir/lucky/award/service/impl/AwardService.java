package pers.zymir.lucky.award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.zymir.lucky.award.service.IAwardService;
import pers.zymir.lucky.mapper.AwardMapper;
import pers.zymir.lucky.po.LuckyAward;

@Service
public class AwardService implements IAwardService {

  @Autowired
  private AwardMapper awardMapper;

  @Override
  public LuckyAward getById(Long id) {
    return awardMapper.selectById(id);
  }
}
