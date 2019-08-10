package io.qkits.fileservice.service;


import io.qkits.fileservice.config.LocalFileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;

//@Service(value = "localFileRW")
//@Service
@Component
public class LocalFileRWer implements FileRWer {
    @Autowired
    LocalFileConfig fileConfig;

    public String write(byte[] content, String fileName) {

        String filePath = fileConfig.getUploadPath()+"/"+fileName;
        try {
            FileCopyUtils.copy(content,new File(filePath));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public InputStream read(String filePath) {
        File file = new File(filePath);
        if(file.exists()){
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("file is not existing");
    }
}
