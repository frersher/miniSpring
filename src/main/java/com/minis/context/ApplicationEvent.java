package com.minis.context;

import java.util.EventObject;

/**
 * @author: chenb
 * @date: 2023/07/11
 **/
public class ApplicationEvent extends EventObject {

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public ApplicationEvent(Object source) {
    super(source);
  }
}
