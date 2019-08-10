package io.hedwig.tcexecutor.support.env.entity;

import io.hedwig.tcexecutor.support.base.AuditableEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "perf_machine")
public class PerfMachine extends AuditableEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private String port;
    private String desc;
    private String user;
    private String pwd;
    private String name;


}
