package io.hedwig.tcexecutor.support.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class AuditableEntity {

    private Date createdDate;
    private String createdBy = "perftester";
    private String updatedBy="perftester";
    private Date updatedDate = new Date();
}
