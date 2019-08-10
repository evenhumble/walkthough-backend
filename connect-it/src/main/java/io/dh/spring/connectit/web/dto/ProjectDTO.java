package io.dh.spring.connectit.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProjectDTO {

    private Integer id;
    private Integer parentId=0;
    @NotNull(message = "project Type should be not null")
    private Integer projectType;
    @NotNull(message = "project name should be not null")
    private String projectName;

    private String projectVersion = "1.0.0";
}
