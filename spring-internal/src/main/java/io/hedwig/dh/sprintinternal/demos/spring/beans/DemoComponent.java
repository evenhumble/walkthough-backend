package io.hedwig.dh.sprintinternal.demos.spring.beans;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author: patrick on 2019-08-26
 * @Description:
 */
@Component
public class DemoComponent {

  public void update() {
    System.out.println("this is updating method in component");
  }
}
