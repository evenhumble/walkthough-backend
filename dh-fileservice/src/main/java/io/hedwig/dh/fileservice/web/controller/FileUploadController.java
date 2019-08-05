package io.hedwig.dh.fileservice.web.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import io.hedwig.dh.fileservice.domain.FileUpload;
import io.hedwig.dh.fileservice.service.LocalFileService;
import io.hedwig.dh.fileservice.web.converter.FileUploadConverter;
import io.hedwig.dh.fileservice.web.dto.DHResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping(value = "/files",
    produces = APPLICATION_JSON_UTF8_VALUE)
public class FileUploadController {
  private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
  @Autowired
  LocalFileService localFileService;

  @RequestMapping(value = "/{fileId}}",
        produces = APPLICATION_JSON_UTF8_VALUE,
        method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getFilePath(@PathVariable Integer fileId){
    FileUpload fileUpload = localFileService.getById(fileId);

    return  ResponseEntity.ok(DHResponse.SUCCESS_RESP(
            FileUploadConverter.convert(fileUpload)));
  }

  @RequestMapping(value = "/download/{fileId}}",
          method = RequestMethod.GET)
  public void downloadFile(@PathVariable Integer fileId,
                                     HttpServletResponse response){
    response.setContentType("application/force-download");
    localFileService.getFileByFileId(fileId);
  }



  @RequestMapping(value = "/upload",
      produces = APPLICATION_JSON_UTF8_VALUE,
      method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity saveFile(@RequestParam("file")MultipartFile file){
        String name = file.getOriginalFilename();
    try {
      FileUpload fileUpload = localFileService.save(name, file.getBytes());
      return ResponseEntity.ok(DHResponse.SUCCESS_RESP(FileUploadConverter.convert(fileUpload)));
    } catch (IOException e) {
      LOGGER.error("save file failed, error={}",e.getCause());
      return ResponseEntity.ok(
              DHResponse.FAIL()
      );
    }
  }
}