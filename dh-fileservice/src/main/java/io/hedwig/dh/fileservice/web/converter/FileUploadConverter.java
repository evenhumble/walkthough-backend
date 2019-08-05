package io.hedwig.dh.fileservice.web.converter;

import io.hedwig.dh.fileservice.domain.FileUpload;
import io.hedwig.dh.fileservice.web.dto.FileUploadDTO;

public class FileUploadConverter {

    public static FileUploadDTO convert(FileUpload fu){
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setFilePath(fu.getFilePath());
        fileUploadDTO.setId(fu.getId());
        return fileUploadDTO;
    }
}
