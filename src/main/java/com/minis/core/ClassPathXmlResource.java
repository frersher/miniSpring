package com.minis.core;

import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author: chenb
 * @date: 2023/07/10
 **/
public class ClassPathXmlResource implements Resource {
  Document document;
  Element rootElement;
  Iterator elementIterator;

   public ClassPathXmlResource(String path) {
     SAXReader saxReader = new SAXReader();
      try {
        document = saxReader.read(this.getClass().getClassLoader().getResource(path));
        rootElement = document.getRootElement();
        elementIterator = rootElement.elementIterator();
      } catch (Exception e) {
        e.printStackTrace();
      }

   }


  @Override
  public boolean hasNext() {
    return this.elementIterator.hasNext();
  }

  @Override
  public Object next() {
    return this.elementIterator.next();
  }
}
