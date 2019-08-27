package io.hedwig.dh.sprintinternal.demos.spring.beans;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: patrick on 2019-08-26
 * @Description:
 */
@Service
public class DemoService {

  public void update() {
    System.out.println("this is updating method in service component");
  }
}
