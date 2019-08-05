package io.hedwig.dh.fileservice.config;

import io.hedwig.dh.fileservice.BaseSpringTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;


public class LocalFileConfigTest extends BaseSpringTest {

//    LocalFileConfig localFileConfig = new LocalFileConfig();
    @Autowired
    LocalFileConfig localFileConfig ;

    @Before
    public void setUpConfig(){
        this.localFileConfig.setBasePath("/www/fileservice/backup_files/");
        this.localFileConfig.setUploadPath("/www/fileservice/uploaded_files/");
    }
    @Test
    public void setupWorkingDirectory() throws IOException {
        this.localFileConfig.setupWorkingDirectory();
    }
}