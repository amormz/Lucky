package pers.zymir.lucky.award.service;

import pers.zymir.lucky.po.LuckyAward;

public interface IAwardService {

  LuckyAward getByAwardId(Long awardId);

}
