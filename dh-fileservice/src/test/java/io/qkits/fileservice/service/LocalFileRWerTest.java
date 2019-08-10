package io.qkits.fileservice.service;

import io.qkits.fileservice.BaseSpringTest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


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