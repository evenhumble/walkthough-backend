package io.dh.spring.connectit.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    private Integer parentId;
    private Integer projectType;
    private String projectName;
    private Date createdTime = new Date();
    private Date updatedTime = new Date();
    private String createdBy;
    private String updatedBy;
    private String projectVersion;
    private int status;
}