package pers.zymir.lucky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pers.zymir.lucky.po.LuckyAward;

@Mapper
public interface AwardMapper {

  String TABLE_NAME = "lucky_award";

  @Select(
      " SELECT * FROM " + TABLE_NAME +
          " WHERE award_id = #{awardId} "
  )
  LuckyAward selectByAwardId(@Param("awardId") Long awardId);
}
