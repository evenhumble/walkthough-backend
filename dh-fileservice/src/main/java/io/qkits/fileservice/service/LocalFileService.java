package io.qkits.fileservice.service;

import io.qkits.fileservice.domain.FileUpload;
import io.qkits.fileservice.repository.FileUploadRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Service
public class LocalFileService {
    @Autowired
//    @Qualifier(value = "localFileRW")
    FileRWer fileRWer;

    @Autowired
    FileUploadRepo fileUploadRepo;

    public FileUpload save(String fileName,byte[] content){
        String savedFileName = System.currentTimeMillis()+"_"+fileName;
        String savedPath = fileRWer.write(content,savedFileName);
        if(StringUtils.isEmpty(savedPath)){
            throw new RuntimeException("save File Failed!");
        }else{
            FileUpload fileUpload = new FileUpload();
            fileUpload.setFilePath(savedPath);
            fileUpload.setFileName(savedFileName);
            fileUpload.setOriginFileName(fileName);
            fileUploadRepo.save(fileUpload);
            return fileUpload;
        }
    }

    public List<FileUpload> list(){
        //todo using pagenation
        return fileUploadRepo.findAll();
    }

    public FileUpload getById(Integer id){
        Objects.requireNonNull(id);
        return fileUploadRepo.getOne(id);
    }

    public InputStream getFileStreamById(Integer id){
        FileUpload fileUpload = getById(id);
        return fileRWer.read(fileUpload.getFilePath());
    }
    public FileUpload update(FileUpload t){
        throw new RuntimeException("Not Implemented YET!!!");
    }

}