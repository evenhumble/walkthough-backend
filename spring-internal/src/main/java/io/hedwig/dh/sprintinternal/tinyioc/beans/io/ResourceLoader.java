package io.hedwig.dh.sprintinternal.tinyioc.beans.io;

import java.net.URL;

/**
 * Load Configuration
 */
public class ResourceLoader {

  public Resource getResource(String location) {
    URL resource = this.getClass().getClassLoader().getResource(location);
    return new UrlResource(resource);
  }
}
