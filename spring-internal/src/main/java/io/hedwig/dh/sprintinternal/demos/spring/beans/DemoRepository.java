package io.hedwig.dh.sprintinternal.demos.spring.beans;

import org.springframework.stereotype.Repository;

/**
 * @author: patrick on 2019-08-26
 * @Description:
 */
@Repository
public class DemoRepository {

  public void update(){
    System.out.println("this is updating method in repository");
  }
}
