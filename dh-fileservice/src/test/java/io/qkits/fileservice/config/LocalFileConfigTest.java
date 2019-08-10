package io.qkits.fileservice.config;

import io.qkits.fileservice.BaseSpringTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class LocalFileConfigTest extends BaseSpringTest {

    @Autowired
    LocalFileConfig localFileConfig ;

    @Before
    public void setUpConfig(){
        this.localFileConfig.setBasePath("/tmp/backup_files/");
        this.localFileConfig.setUploadPath("/tmp/uploaded_files/");
    }
    @Test
    public void setupWorkingDirectory() throws IOException {
        this.localFileConfig.setupWorkingDirectory();
    }
}