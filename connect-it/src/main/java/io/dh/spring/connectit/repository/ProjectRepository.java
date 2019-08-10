package io.dh.spring.connectit.repository;

import io.dh.spring.connectit.domain.Project;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Query(nativeQuery = true,
           value = "select * from project where parent_id = :parentId and status=1")
    List<Project> findProjectsByParentId(@Param(value = "parentId") Integer parentId);


    @Query(nativeQuery = true,value ="update project set status = :status and id=:projectId")
    @Modifying
    @Transactional
    int updateProjectStatus(@Param(value = "status") Integer status,
                            @Param(value="projectId") Integer projectId);

    @Query(nativeQuery = true,value = "select * from project where status=1 and project_name=:projectName")
    Project findProjectByProjectName(@Param(value = "projectName") String projectName);


    @Query(nativeQuery = true,value = "select * from project where status=1 and project_name=:projectName" +
            " and id!= :projectId")
    Project findProjectByProjectNameWithExclusiveId(@Param(value = "projectName") String projectName,
                                                    @Param(value = "projectId") Integer projectId);
}