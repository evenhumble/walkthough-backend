package io.hedwig.dh.fileservice.service;

import io.hedwig.dh.fileservice.BaseSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class LocalFileRWerTest extends BaseSpringTest {

    @Autowired
    FileRWer localFileRWer;

    @Test
    public void write() throws IOException, URISyntaxException {
        URI filePath = LocalFileRWerTest.class.getClassLoader().getResource("testfile.txt").toURI();

        String resultPath = localFileRWer.write(Files.readAllBytes(Paths.get(filePath)),
                "test_result.txt");
        File file = new File(resultPath);
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void read() {
    }
}