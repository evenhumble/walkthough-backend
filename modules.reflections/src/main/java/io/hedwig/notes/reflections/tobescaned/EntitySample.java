package io.hedwig.notes.reflections.tobescaned;

@AnnotationSample(value = "table")
public class EntitySample {

    @AnnotationSample(value = "key")
    private String entityName;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
