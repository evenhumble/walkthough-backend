package io.hedwig.modules.engine;

import io.hedwig.modules.engine.result.OverallResult;

public interface Checker {

    OverallResult check(String baseLine,
                        String testLine,
                        CheckConfiguration checkConfig);
}
