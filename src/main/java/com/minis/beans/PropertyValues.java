package com.minis.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public class PropertyValues {
  private final List<PropertyValue> propertyValueList;


  public PropertyValues() {
    propertyValueList = new ArrayList<>();
  }

  public List<PropertyValue> getPropertyValueList() {
    return this.propertyValueList;
  }

  public int size() {
    return this.propertyValueList.size();
  }

  public void addPropertyValue(PropertyValue pv) {
    this.propertyValueList.add(pv);
  }

  public void addPropertyValue(String propertyType, String propertyName, Object propertyValue) {
    addPropertyValue(new PropertyValue(propertyType, propertyName, propertyValue));
  }

  public void removePropertyValue(PropertyValue pv) {
    this.propertyValueList.remove(pv);
  }




}
