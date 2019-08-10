package io.hedwig.dh.sprintinternal.io;

import java.net.URL;

public class ResourceLoader {

    public Resource getResource(String location) throws Exception {
        URL url = this.getClass().getResource(location);
        return new UrlResource(url);
    }
}