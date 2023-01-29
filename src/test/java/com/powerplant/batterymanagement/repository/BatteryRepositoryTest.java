package com.powerplant.batterymanagement.repository;

import com.powerplant.batterymanagement.model.Battery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BatteryRepositoryTest
{
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private BatteryRepository batteryRepository;

  @Test
  public void findBatteriesBetweenPostCodesTest() {
    Battery battery = new Battery(2599, "Panasonic", 74000);
    entityManager.persist(battery);
    entityManager.flush();

    List<Battery> batteryList = (List<Battery>)batteryRepository.findAll();

    assertThat(batteryList.size()).isEqualTo(1);
    assertThat(batteryList.get(0)).isEqualTo(battery);
  }
}
