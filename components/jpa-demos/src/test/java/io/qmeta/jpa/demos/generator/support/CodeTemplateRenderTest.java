package io.qmeta.jpa.demos.generator.support;


import cn.hutool.core.lang.Dict;
import freemarker.template.TemplateException;
import io.qmeta.jpa.demos.generator.domain.CodeTemplateConfiguration;
import io.qmeta.jpa.demos.generator.domain.java.EntityObject;
import io.qmeta.jpa.demos.generator.domain.java.FieldObject;
import io.qmeta.jpa.demos.generator.domain.java.RepositoryObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CodeTemplateRenderTest {

    @org.junit.Test
    public void testRenderTemplate() throws IOException, TemplateException {
        CodeTemplateRender render = new CodeTemplateRender();
        render.initEngine();
        CodeTemplateConfiguration conf = new CodeTemplateConfiguration();
        conf.setAuthorName("patrick");
        conf.setBasePackage("com.qmeta.domain");
        conf.setCodeType("JPA");
        conf.setUiCodeType("ELEMENT");
        EntityObject entityObject = new EntityObject();
        entityObject.setTableName("table_name");
        entityObject.setClassName("BaseEntity");
        entityObject.setClassComment("Base Entity Comments");
        List<FieldObject> objects = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            objects.add(FieldObject.builder()
                    .fieldName("test" + i)
                    .fieldComment("Test comments")
                    .fieldType("String").build());
        }
        entityObject.setFields(objects);

        RepositoryObject repositoryObject = RepositoryObject.builder()
                .className("tableName").
                        classComment("table_name_repository").build();

        Map<String, Object> data = Dict.create()
                .set("packageName",conf.getBasePackage())
                .set("classInfo", entityObject)
                .set("authorName", conf.getAuthorName());
        Map<String, Object> data1 = Dict.create()
                .set("packageName",conf.getBasePackage())
                .set("classInfo", repositoryObject)
                .set("authorName", conf.getAuthorName());
        String result = render.renderTemplate(data,conf.entityTemplateFile());
        String repoStr = render.renderTemplate(data1,conf.repositoryTemplateFile());
        System.out.println(result);
        System.out.println(repoStr);
    }
}