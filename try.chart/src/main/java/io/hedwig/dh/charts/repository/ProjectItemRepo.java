package io.hedwig.dh.charts.repository;

import io.hedwig.dh.charts.model.ProjectItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectItemRepo extends JpaRepository<ProjectItem,Long> {

}
