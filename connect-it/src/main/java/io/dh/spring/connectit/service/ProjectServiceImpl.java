package io.dh.spring.connectit.service;

import io.dh.spring.connectit.common.DHValidators;
import io.dh.spring.connectit.domain.Project;
import io.dh.spring.connectit.repository.ProjectRepository;
import io.dh.spring.connectit.service.intf.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project createProject(Project project) {
        DHValidators.validator().verifyNotNull(
                project, "project should be not null"
        ).verifyNotNull(project.getProjectName(),
                "project name should be not null")
                .verifyNotNull(project.getProjectType(),
                        "project type should be not null");
        Project duplicatedProjectName = projectRepository.findProjectByProjectName(project.getProjectName());
        if (duplicatedProjectName != null) {
            throw new DHServiceException("project name is already existed");
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        DHValidators.validator().verifyNotNull(
                project, "project should be not null"
        ).verifyNotNull(project.getProjectName(),
                "project name should be not null")
                .verifyNotNull(project.getProjectType(),
                        "project type should be not null")
                .verifyNotNull(project.getId(), "project id is should be not null");

        Project duplicatedProject = projectRepository.findProjectByProjectNameWithExclusiveId(project.getProjectName(), project.getId());
        if (duplicatedProject != null) throw new DHServiceException("project name is already existed");
        return projectRepository.save(project);
    }

    public Page<Project> listProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Optional<Project> getProjectById(Integer id) {
        Objects.requireNonNull(id, "project Id should be not empty");
        return projectRepository.findById(id);
    }

    public List<Project> getSubProjects(Project parentProject) {

        DHValidators.validator().verifyNotNull(
                parentProject, "project should be not null"
        ).verifyNotNull(parentProject.getId(), "project id should be not null");

        return projectRepository.findProjectsByParentId(parentProject.getId());
    }

    public int deactiveProject(Integer projectId) {
        return projectRepository.updateProjectStatus(0, projectId);
    }

    @Override
    public int activeProject(Integer projectId) {

        return projectRepository.updateProjectStatus(1, projectId);
    }


}