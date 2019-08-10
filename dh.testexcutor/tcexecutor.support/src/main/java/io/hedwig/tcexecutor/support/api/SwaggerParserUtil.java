package io.hedwig.tcexecutor.support.api;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public class SwaggerParserUtil {

    public static void main(String[] args) {
        Swagger swagger = new SwaggerParser().read("http://petstore.swagger.io/v2/swagger.json");

//        Swagger swagger = new SwaggerParser().read("./path/to/swagger.json");
//        Swagger swagger = new SwaggerCompatConverter().read("http://petstore.swagger.io/api/api-docs");
        System.out.println(swagger);
    }
}
