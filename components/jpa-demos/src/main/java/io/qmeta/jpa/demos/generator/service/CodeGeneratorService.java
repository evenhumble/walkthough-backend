package io.qmeta.jpa.demos.generator.service;

import io.qmeta.jpa.demos.generator.domain.CodeTemplateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MySQL first
 */
@Service
public class CodeGeneratorService {

    @Autowired
    CodeMetaService codeMetaService;

    public void generate(String tableName,CodeTemplateConfiguration configuration){

    }

}
