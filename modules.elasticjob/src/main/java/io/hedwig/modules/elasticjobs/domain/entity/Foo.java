package io.hedwig.modules.elasticjobs.domain.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Foo implements Serializable {

    private long id;
    private String location;
    private JobStatus status;
}
