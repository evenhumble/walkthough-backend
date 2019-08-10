package io.hedwig.modules.esper.client;

import lombok.Data;

/**
 * 1. author: patrick
 * todo: use jcommand for configuration
 */
@Data
public class EsperClientConfig {

    private String host;
    private int port;
    private int rate;
}
