package io.hedwig.tcexecutor.support.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Entity
@MappedSuperclass
@Data
public class BaseAuditableEntity {

    private String createdBy;
    private Date createdTime;
    private Date updatedTime;
    private String updatedBy;
}
