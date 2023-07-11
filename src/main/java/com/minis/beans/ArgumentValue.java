package com.minis.beans;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public class ArgumentValue {

  private Object value;

  private String type;

  private String name;


  public ArgumentValue(String type,String name, Object value) {
    this.value = value;
    this.name = name;
    this.type = type;
  }


  public Object getValue() {
    return value;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }
}
