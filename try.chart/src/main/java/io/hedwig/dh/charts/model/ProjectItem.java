package io.hedwig.dh.charts.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project_items")
public class ProjectItem {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private int active;
    private Date createdTime = new Date();
    private Date updatedTime = new Date();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
