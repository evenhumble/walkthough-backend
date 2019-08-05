package io.hedwig.dh.fileservice.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public interface FileRWer {

    public String write(byte[] file, String filePath);

    public InputStream read(String filePath);
}
