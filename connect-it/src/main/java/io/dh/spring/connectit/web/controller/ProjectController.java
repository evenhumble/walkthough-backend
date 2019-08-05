package io.dh.spring.connectit.web.controller;


import io.dh.spring.connectit.common.DHValidators;
import io.dh.spring.connectit.common.entity.TreeEntityUtil;
import io.dh.spring.connectit.common.mvc.R;
import io.dh.spring.connectit.domain.Project;
import io.dh.spring.connectit.service.intf.ProjectService;
import io.dh.spring.connectit.web.converter.Converters;
import io.dh.spring.connectit.web.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
@RequestMapping(value = "/project",
        produces = APPLICATION_JSON_UTF8_VALUE)
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/project")
    @ResponseBody
    public ResponseEntity getProject() {

        List<Project> projects = projectService.getSubProjects(TreeEntityUtil.dummyRootEntity(Project.class));
        return ResponseEntity.ok().body(Converters.convert(projects, ProjectDTO.class));

    }

    @RequestMapping("/project/{projectId}")
    @ResponseBody
    public ResponseEntity getProjectById(@PathVariable(value = "projectId") Integer projectId) {

        Optional<Project> project = projectService.getProjectById(projectId);

        return project.<ResponseEntity>map(project1 -> ResponseEntity.ok(
                Converters.convert(project1, ProjectDTO.class)))
                .orElseGet(() -> ResponseEntity.ok(
                        R.error(1001, "Not Found")
                ));
    }


    @RequestMapping(value = "/project/{projectId}",
            produces = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteProject(@PathVariable(value = "projectId") Integer projectId) {

        projectService.deactiveProject(projectId);
        return ResponseEntity.ok(R.ok("update successful!"));
    }

    @RequestMapping(value = "/project/{projectId}",
            produces = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateProject(@PathVariable(value = "projectId") Integer projectId,
                                        @RequestBody ProjectDTO prjDto) {
        DHValidators.validateEntity(prjDto);
        DHValidators.validator().verifyNotNull(projectId);
        prjDto.setId(projectId);
        Project project = Converters.convert(prjDto, Project.class);
        projectService.createProject(project);
        return null;
    }

    @RequestMapping(value = "/project",
            produces = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity newProject(@RequestBody ProjectDTO dto) {
        DHValidators.validateEntity(dto);
        Project project = Converters.convert(dto, Project.class);
        Project savedProject = projectService.createProject(project);
        dto.setId(savedProject.getId());
        return ResponseEntity.ok(
                R.ok("success").put("data", dto)
        );
    }
}