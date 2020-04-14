package io.qmeta.jpa.demos.generator.domain;

import lombok.Data;

@Data
public class CodeTemplateConfiguration {
    private String authorName;
    private String basePackage;
    private String entityTemplateFile;
    private String repositoryTemplateFile;
    private String serviceTemplateFile;
    private String controllerTemplateFile;
    private String uiTemplateFile;
    private String codeType;
    private String uiCodeType;

    public String entityTemplateFile() {
        return String.format("%s/entity.ftl", codeType.toLowerCase());
    }

    public String repositoryTemplateFile() {
        return String.format("%s/repository.ftl", codeType.toLowerCase());
    }

    public String sviceTemplateFile() {
        return String.format("%s/service.ftl", codeType.toLowerCase());
    }

    public String controllerTemplateFile() {
        return String.format("%s/controller.ftl", codeType.toLowerCase());
    }

    public String uiTemplateFile() {
        return String.format("ui/%s.ftl", uiCodeType.toLowerCase());
    }

    public static enum BackEndCodeGeneratorType {
        JPA, MYBATIS_PLUS
    }

    public static enum FrontEndCodeGeneratorType {
        ELEMENT, ANTD;
    }
}
