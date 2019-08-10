package io.qkits.fileservice.web.converter;

import io.qkits.fileservice.domain.FileUpload;
import io.qkits.fileservice.web.dto.FileUploadDTO;

public class FileUploadConverter {

    public static FileUploadDTO convert(FileUpload fu){
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setFilePath(fu.getFilePath());
        fileUploadDTO.setId(fu.getId());
        return fileUploadDTO;
    }
}
