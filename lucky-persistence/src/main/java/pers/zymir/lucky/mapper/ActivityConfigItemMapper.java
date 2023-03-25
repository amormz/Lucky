package pers.zymir.lucky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface ActivityConfigItemMapper {

  String TABLE_NAME = "lucky_activity_config_itme";

  @Select(
      " SELECT award_id FROM " + TABLE_NAME +
          " WHERE activity_id = #{activityId} AND deleted = false "
  )
  Set<Long> queryEmptyStockAwardIds(@Param("activityId") Long activityId);
}
