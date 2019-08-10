package io.dh.spring.connectit.service.intf;

import io.dh.spring.connectit.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public Project createProject(Project project);
    public Project updateProject(Project project);
    public Page<Project> listProjects(Pageable pageable); //todo: convert to a common interface
    public Optional<Project> getProjectById(Integer id);
    public List<Project> getSubProjects(Project parentProject);
    public int deactiveProject(Integer projectId);
    public int activeProject(Integer projectId);
}
