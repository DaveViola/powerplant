package com.powerplant.batterymanagement.repository;

import com.powerplant.batterymanagement.model.Battery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatteryRepository extends CrudRepository<Battery, Integer> {

  @Query("select b from Battery b where b.postCode between :startPostCode and :endPostCode order by b.name asc")
  List<Battery> findBatteriesBetweenPostCodes(
      @Param("startPostCode") int startPostCode, @Param("endPostCode") int endPostCode);

}
