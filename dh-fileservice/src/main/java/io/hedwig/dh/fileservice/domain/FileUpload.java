package io.hedwig.dh.fileservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file_upload")
@Data
public class FileUpload {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String originFileName;
    private String filePath;
    private String createdBy;
    private String updatedBy;
    private Date createdTime = new Date();
    private Date updatedTime = new Date();

}