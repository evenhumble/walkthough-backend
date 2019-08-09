package io.qkits.fileservice.config;

import lombok.Data;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.IOException;

@Component
@ConfigurationProperties(prefix="localfilestore")
@Data
public class LocalFileConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(LocalFileConfig.class);
    @Value("{}")
    private String basePath;
    private String uploadPath;

    @PostConstruct
    public void setupWorkingDirectory() throws IOException {
            LOGGER.info("start to create local directories");
            FileUtils.forceMkdir(new File(basePath));
            FileUtils.forceMkdir(new File(uploadPath));

    }
}
