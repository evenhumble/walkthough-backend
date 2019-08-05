package io.hedwig.modules.jodd.json;

import lombok.Data;

public class ChildEntity {

    private String name;
    private String details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ChildEntity{" +
            "name='" + name + '\'' +
            ", details='" + details + '\'' +
            '}';
    }
}
