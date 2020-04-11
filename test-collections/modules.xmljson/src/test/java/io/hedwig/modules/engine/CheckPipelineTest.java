package io.hedwig.modules.engine;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CheckPipelineTest {

    @Test
    public void testPipeline() throws IOException {

        CheckPipeline pipeline = new CheckPipeline();
        pipeline.setupBaseLine();
        pipeline.setupTestSource();
        pipeline.startChecking();
        pipeline.output();
    }
}
