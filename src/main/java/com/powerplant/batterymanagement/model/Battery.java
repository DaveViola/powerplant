package com.powerplant.batterymanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Battery
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="battery_id", nullable = false, unique = true)
  private Long id;

  @Column(name="post_code")
  int postCode;

  @Column(name="battery_name")
  String name;

  @Column(name="wattage")
  double wattage;

  public Battery(int postCode, String name, double wattage) {
    this.postCode = postCode;
    this.name = name;
    this.wattage = wattage;
  }
}
